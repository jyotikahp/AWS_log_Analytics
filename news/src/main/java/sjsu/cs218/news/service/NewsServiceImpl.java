//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.service;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import sjsu.cs218.news.POJO.NewsArticle;


@Service
public class NewsServiceImpl implements NewsService{
    @Value("${google.gnews.key}")
    private String gnewsApiKey;

    @Autowired
    private WebClient gnewsApiClient;

    public List<NewsArticle> searchNews(String keyword){
        MultiValueMap<String,String> map_uriVals=new LinkedMultiValueMap<>();
        map_uriVals.add("q",keyword);
        map_uriVals.add("apikey",gnewsApiKey);
        map_uriVals.add("lang","en");
        map_uriVals.add("country","us");
        String str_res=gnewsApiClient.get()
            .uri(uriBuilder->uriBuilder.path("/search")
                .queryParams(map_uriVals)
                .build())
            .retrieve()
            .bodyToMono(String.class)
            .block();
        JsonObject jsonObject=new Gson().fromJson(str_res, JsonObject.class);
        JsonArray jsonArray=jsonObject.getAsJsonArray("articles");
        Type listType=new TypeToken<List<NewsArticle>>(){}.getType();
        List<NewsArticle> list_ret=new Gson().fromJson(jsonArray, listType);

        return list_ret;
    }
}
