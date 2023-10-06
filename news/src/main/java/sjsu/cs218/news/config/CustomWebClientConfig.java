//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CustomWebClientConfig {
    @Bean
    public WebClient gnewsApiClient(){
        return WebClient.create("https://gnews.io/api/v4");
    }
}
