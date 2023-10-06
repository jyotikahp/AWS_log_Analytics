//SJSU CS 218 SPRING 2023 TEAM4
package sjsu.cs218.news.POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class NewsArticle implements Serializable{
    private String title;
    private String description;
    private String content;
    private String url;
    private String image;
    private Date publishedAt;


    public NewsArticle() {
    }

    public NewsArticle(String title, String description, String content, String url, String image, Date publishedAt) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.image = image;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishedAt() {
        return this.publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public NewsArticle title(String title) {
        setTitle(title);
        return this;
    }

    public NewsArticle description(String description) {
        setDescription(description);
        return this;
    }

    public NewsArticle content(String content) {
        setContent(content);
        return this;
    }

    public NewsArticle url(String url) {
        setUrl(url);
        return this;
    }

    public NewsArticle image(String image) {
        setImage(image);
        return this;
    }

    public NewsArticle publishedAt(Date publishedAt) {
        setPublishedAt(publishedAt);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NewsArticle)) {
            return false;
        }
        NewsArticle newsArticle = (NewsArticle) o;
        return Objects.equals(title, newsArticle.title) && Objects.equals(description, newsArticle.description) && Objects.equals(content, newsArticle.content) && Objects.equals(url, newsArticle.url) && Objects.equals(image, newsArticle.image) && Objects.equals(publishedAt, newsArticle.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, content, url, image, publishedAt);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", content='" + getContent() + "'" +
            ", url='" + getUrl() + "'" +
            ", image='" + getImage() + "'" +
            ", publishedAt='" + getPublishedAt() + "'" +
            "}";
    }
    
}
