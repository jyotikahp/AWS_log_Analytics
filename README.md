# AWS_log_analytics
Leveraged AWS Log Analytics and ElasticSearch to proactively monitor, troubleshoot, and alert a Java Spring Boot-based news application hosted on AWS, efficiently streaming AWS CloudWatch, S3, and CloudTrail logs.

# Proposal
To use the logs generated by a cloud application on AWS for monitoring, securing, analyzing, and troubleshooting the application with AWS ELK and generating dashboards. We will build a web application to provide access to the latest news from around the globe. This application will be hosted on AWS and the generated logs will be analyzed and visualized using the ELK stack to gather insights about the application performance, users, and AWS environment. The following features will be implemented:
The news application is hosted on EC2 instances and we utilized ELB service (to distribute load across multiple AZs) and AutoScaling (to optimize performance and cost). 
We aggregate, monitor and visualize the logs published to AWS CloudWatch with AWS ElasticSearch and OpenSearch dashboards. To do so, we stream logs that get published on CloudWatch to AWS ElasticSearch. 
Our other source of logs is AWS CloudTrail, which records all activities in the AWS environment. By default, these logs are aggregated by region and stored in S3 buckets. 
We created a Lambda to parse the ELB logs stored in S3 so that our logs are indexed (in ElasticSearch) such that they are searchable and aggregatable

# AWS_log_Analytics
