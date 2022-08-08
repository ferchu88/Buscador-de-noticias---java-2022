package com.informatorio.news_search.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "source")
public class SourceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt = LocalDate.now();
    @OneToMany(mappedBy = "source",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleModel> articles = new ArrayList<>();

    public SourceModel() {}
    public SourceModel(String name) {
        this.name = name;
        setCode();
    }
    public SourceModel(
        Long id, String name, 
        LocalDate createdAt, 
        List<ArticleModel> articles
    ) {
        this.id = id;
        this.name = name;
        setCode();
        this.createdAt = createdAt;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setName(String name) {
        this.name = name;
        setCode();
    }
    private void setCode() {
        this.code = this.name
            .toLowerCase()
            .trim()
            .replace(' ', '-');
    }
    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
