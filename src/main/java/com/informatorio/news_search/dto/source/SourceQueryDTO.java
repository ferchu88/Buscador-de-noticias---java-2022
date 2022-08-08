package com.informatorio.news_search.dto.source;

import javax.validation.constraints.NotBlank;

public class SourceQueryDTO {
    private Long id;
    @NotBlank
    private String name;

    public SourceQueryDTO() {}
    public SourceQueryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
