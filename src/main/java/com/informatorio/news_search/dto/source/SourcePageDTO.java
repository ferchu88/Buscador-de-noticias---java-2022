package com.informatorio.news_search.dto.source;

import java.util.List;

public class SourcePageDTO {
    private Long totalResult;
    private List<SourceDTO> sources;

    public SourcePageDTO() {}
    public SourcePageDTO(Long totalResult, List<SourceDTO> sources) {
        this.totalResult = totalResult;
        this.sources = sources;
    }
    
    public Long getTotalResult() {
        return totalResult;
    }
    public List<SourceDTO> getSources() {
        return sources;
    }
}
