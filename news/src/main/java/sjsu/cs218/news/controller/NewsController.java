package sjsu.cs218.news.controller;

import java.net.URLDecoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//SJSU CS 218 SPRING 2023 TEAM4
import org.springframework.web.bind.annotation.RequestParam;

import sjsu.cs218.news.POJO.NewsArticle;
import sjsu.cs218.news.exception.CrashServerException;
import sjsu.cs218.news.service.NewsServiceImpl;

@Controller
@RequestMapping(value = "/news")
public class NewsController {

    @Autowired
    private NewsServiceImpl newsService;

    Logger logger=LoggerFactory.getLogger(NewsController.class);
    @GetMapping("/search")
    public String postSearchNews(@RequestParam String keyword, Model model){
        List<NewsArticle> list_articles=null;
        try {
            keyword=URLDecoder.decode(keyword.replace("+", "%2B"), "UTF-8").replace("%2B", "+");
            list_articles=newsService.searchNews(keyword);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        
        
        model.addAttribute("list_articles", list_articles);
        return "news";
    }

    @GetMapping("/crash")
    public void getCrashServer(){
        throw new CrashServerException("server crashed.");
    }
}
