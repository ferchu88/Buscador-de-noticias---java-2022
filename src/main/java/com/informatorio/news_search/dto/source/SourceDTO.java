package com.informatorio.news_search.dto.source;

import java.time.LocalDate;
import java.util.List;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;

public class SourceDTO extends SourceQueryDTO {
    private String code;
    private LocalDate createdAt;
    private List<ArticleBaseDTO> articles;

    public SourceDTO() {}
    public SourceDTO(
        Long id, String name, 
        String code, LocalDate createdAt, 
        List<ArticleBaseDTO> articles
    ) {
        super(id, name);
        this.code = code;
        this.createdAt = createdAt;
        this.articles = articles;
    }

    public String getCode() {
        return code;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<ArticleBaseDTO> getArticles() {
        return articles;
    }
}
