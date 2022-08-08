package com.informatorio.news_search.dto.author;

import java.util.List;

public class AuthorPageDTO {
    private Long totalResult;
    private List<AuthorDTO> authors;

    public AuthorPageDTO() {}
    public AuthorPageDTO(Long totalResult, List<AuthorDTO> authors) {
        this.totalResult = totalResult;
        this.authors = authors;
    }
    
    public Long getTotalResult() {
        return totalResult;
    }
    public List<AuthorDTO> getAuthors() {
        return authors;
    }
}
