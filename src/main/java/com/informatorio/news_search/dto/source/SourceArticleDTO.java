package com.informatorio.news_search.dto.source;

public class SourceArticleDTO {
    private String id;
    private String name;

    public SourceArticleDTO() {}
    public SourceArticleDTO(String code, String name) {
        this.id = code;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
