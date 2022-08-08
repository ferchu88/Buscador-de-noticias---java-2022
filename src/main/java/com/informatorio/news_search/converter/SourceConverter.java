package com.informatorio.news_search.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.source.SourceDTO;
import com.informatorio.news_search.dto.source.SourceQueryDTO;
import com.informatorio.news_search.model.SourceModel;

@Component
public class SourceConverter {
    public SourceDTO toDTO(SourceModel sourceModel, List<ArticleBaseDTO> articles) {
        return new SourceDTO(
            sourceModel.getId(),
            sourceModel.getName(),
            sourceModel.getCode(),
            sourceModel.getCreatedAt(),
            articles
        );
    }

    public SourceModel toModel(SourceQueryDTO sourceQueryDTO) {
        return new SourceModel(sourceQueryDTO.getName());
    }
}
