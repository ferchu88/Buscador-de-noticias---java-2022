package com.informatorio.news_search.dto.article;

import java.util.List;

public class ArticlePageDTO {
    private Long totalResult;
    private List<ArticleDTO> articles;

    public ArticlePageDTO() {}
    public ArticlePageDTO(Long totalResult, List<ArticleDTO> articles) {
        this.totalResult = totalResult;
        this.articles = articles;
    }
    
    public Long getTotalResult() {
        return totalResult;
    }
    public List<ArticleDTO> getArticles() {
        return articles;
    }
}
