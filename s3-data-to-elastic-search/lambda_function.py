# SJSU CS 218 SPRING 2023 TEAM4
import boto3
import json
import gzip
import urllib
import logging
from requests_aws4auth import AWS4Auth
import requests
from io import BytesIO

config = {}
config['awsRegion'] = "us-west-1"
config['service'] = "es"

logger = logging.getLogger()
logger.setLevel(logging.INFO)


def insert_to_es(es_Url, awsauth, es_dict):
    try:
        headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
        resp = requests.post(es_Url, auth=awsauth,
                             headers=headers, json=es_dict)
        if resp.status_code == 201:
            logger.info('INFO: Successfully inserted data into ES')
        else:
            logger.error('FAILURE: Failed to enter data into ES')
        logger.info(resp.json())

    except Exception as e:
        logger.error('ERROR: {0}'.format(str(e)))
        logger.error('ERROR: Unable to index line:"{0}"'.format(
            str(es_dict['content'])))
        print(e)


def lambda_handler(event, context):
    s3 = boto3.client('s3')
    credentials = boto3.Session().get_credentials()
    awsauth = AWS4Auth(credentials.access_key,
                       credentials.secret_key,
                       config['awsRegion'],
                       config['service'],
                       session_token=credentials.token
                       )

    logger.info("Received event: " + json.dumps(event, indent=2))

    try:
        bucket = event['Records'][0]['s3']['bucket']['name']
        key = urllib.parse.unquote_plus(
            event['Records'][0]['s3']['object']['key'])
        print(bucket)
        print(key)

        obj = s3.get_object(Bucket=bucket, Key=key)

    except Exception as e:
        logger.error('ERROR: {0}'.format(str(e)))
        logger.error(
            'ERROR: Unable able to GET object:{0} from S3 Bucket:{1}. Verify object exists.'.format(key, bucket))

    if (key.endswith('.gz')) or (key.endswith('.tar.gz')):
        mycontentzip = gzip.GzipFile(
            fileobj=BytesIO(obj['Body'].read())).read()
        lines = mycontentzip.decode("utf-8").replace("'", '"')

    if (key.endswith('.json')):
        es_Url = 'https://search-cs218-p2-opensearch-oelikcatbh4kzskv3swf4524kq.us-west-1.es.amazonaws.com/ec2-infra-logs/_doc'
        lines = json.loads(obj['Body'].read().decode("utf-8").replace("'", '"'))
        for line in lines:
            insert_to_es(es_Url, awsauth, line)
        logger.info('File processing comlplete. Check logs for index status')
        return
    else:
        lines = obj['Body'].read().decode("utf-8").replace("'", '"')

    logger.info('SUCCESS: Retrieved object from S3')

    lines = lines.splitlines()
    if (isinstance(lines, str)):
        lines = [lines]

    es_dict = {}
    es_dict['objectKey'] = str(key)
    es_dict['createdDate'] = str(obj['LastModified'])
    es_dict['content_type'] = str(obj['ContentType'])
    es_dict['content_length'] = str(obj['ContentLength'])

    for line in lines:
        field = line.split()
        if (field[4].strip() == ""):
            target_ip = ""
            target_port = ""

        else:
            target_ip = field[4].split(':')[0]
            target_port = field[4].split(':')[1]

        elb_dict = {"type": field[0],
                    "timestamp": field[1],
                    "elb": field[2],
                    "client_ip": field[3].split(':')[0],
                    "client_port": field[3].split(':')[1],
                    "target_ip": target_ip,
                    "target_port": target_port,
                    "request_processing_time": field[5],
                    "target_processing_time": field[6],
                    "response_processing_time": field[7],
                    "elb_status_code": field[8]
                    }

        es_dict.update(elb_dict)
        print(es_dict)
        es_Url = 'https://search-cs218-p2-opensearch-oelikcatbh4kzskv3swf4524kq.us-west-1.es.amazonaws.com/s3-to-es/_doc'
        insert_to_es(es_Url, awsauth, es_dict)
    logger.info('Completed file processing. Check logs filtering the index in the elastic search search')


if __name__ == '__main__':
    lambda_handler(None, None)
