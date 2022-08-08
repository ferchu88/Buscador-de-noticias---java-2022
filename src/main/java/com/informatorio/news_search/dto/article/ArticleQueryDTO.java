package com.informatorio.news_search.dto.article;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ArticleQueryDTO extends ArticleBaseDTO {
    @NotNull
    @Positive
    private Long author;
    @NotNull
    @Positive
    private Long source;
    
    public ArticleQueryDTO() {}
    public ArticleQueryDTO(
        Long id, String title, String description, 
        String url, String urlToImage, 
        String content, LocalDate publishedAt, 
        Long author, Long source
    ) {
        super(
            id, title, description, url, 
            urlToImage, content, publishedAt
        );
        this.author = author;
        this.source = source;
    }

    public Long getAuthor() {
        return author;
    }
    public Long getSource() {
        return source;
    }
}
