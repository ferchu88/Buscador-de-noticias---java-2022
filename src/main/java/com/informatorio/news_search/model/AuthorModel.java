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
@Table(name = "author")
public class AuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate createdAt = LocalDate.now();
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleModel> articles = new ArrayList<>();

    public AuthorModel() {}
    public AuthorModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        setFullName();
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFullName() {
        return fullName;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setFullName();
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
        setFullName();
    }
    private void setFullName() {
        this.fullName = this.firstName + ' ' + this.lastName;
    }
    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}
