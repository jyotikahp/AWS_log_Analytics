//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.service;

import java.util.List;

import sjsu.cs218.news.POJO.NewsArticle;

public interface NewsService {
    public abstract List<NewsArticle> searchNews(String keyword);
}
