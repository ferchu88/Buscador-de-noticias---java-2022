package com.informatorio.news_search.dto.article;

import java.time.LocalDate;

import com.informatorio.news_search.dto.source.SourceArticleDTO;

public class ArticleDTO extends ArticleBaseDTO {
    private String author;
    private SourceArticleDTO source;
    
    public ArticleDTO() {}
    public ArticleDTO(
        Long id, String title, String description, 
        String url, String urlToImage, 
        String content, LocalDate publishedAt, 
        String author, String sourceCode, String sourceName
    ) {
        super(
            id, title, description, url, 
            urlToImage, content, publishedAt
        );
        this.author = author;
        this.source = new SourceArticleDTO(sourceCode, sourceName);
    }

    public String getAuthor() {
        return author;
    }
    public SourceArticleDTO getSource() {
        return source;
    }
}
