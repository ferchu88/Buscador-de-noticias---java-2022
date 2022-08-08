package com.informatorio.news_search.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class ArticleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String content;
    private LocalDate publishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private AuthorModel author;
    @ManyToOne(fetch = FetchType.LAZY)
    private SourceModel source;

    public ArticleModel() {}
    public ArticleModel(
        String title, String description, String url, 
        String urlToImage, String content, 
        LocalDate publishedAt, AuthorModel author, 
        SourceModel source
    ) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.source = source;
    }
    public ArticleModel(
        Long id, String title, String description, 
        String url, String urlToImage, String content, 
        LocalDate publishedAt, AuthorModel author, 
        SourceModel source
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.source = source;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public String getUrlToImage() {
        return urlToImage;
    }
    public String getContent() {
        return content;
    }
    public LocalDate getPublishedAt() {
        return publishedAt;
    }
    public AuthorModel getAuthor() {
        return author;
    }
    public SourceModel getSource() {
        return source;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }
    public void setAuthor(AuthorModel author) {
        this.author = author;
    }
    public void setSource(SourceModel source) {
        this.source = source;
    }
}
