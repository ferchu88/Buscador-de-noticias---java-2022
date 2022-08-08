package com.informatorio.news_search.dto.author;

import java.time.LocalDate;
import java.util.List;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;

public class AuthorDTO extends AuthorQueryDTO {
    private String fullName;
    private LocalDate createdAt;
    private List<ArticleBaseDTO> articles;

    public AuthorDTO() {}
    public AuthorDTO(
        Long id, String firstName, String lastName, 
        String fullName, LocalDate createdAt, 
        List<ArticleBaseDTO> articles
    ) {
        super(id, firstName, lastName);
        this.fullName = fullName;
        this.createdAt = createdAt;
        this.articles = articles;
    }

    public String getFullName() {
        return fullName;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<ArticleBaseDTO> getArticles() {
        return articles;
    }
}
