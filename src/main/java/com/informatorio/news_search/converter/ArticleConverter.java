package com.informatorio.news_search.converter;

import org.springframework.stereotype.Component;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.article.ArticleDTO;
import com.informatorio.news_search.dto.article.ArticleQueryDTO;
import com.informatorio.news_search.model.ArticleModel;
import com.informatorio.news_search.model.AuthorModel;
import com.informatorio.news_search.model.SourceModel;

@Component
public class ArticleConverter {
    public ArticleDTO toDTO(ArticleModel articleModel) {
        return new ArticleDTO(
            articleModel.getId(),
            articleModel.getTitle(),
            articleModel.getDescription(),
            articleModel.getUrl(),
            articleModel.getUrlToImage(),
            articleModel.getContent(),
            articleModel.getPublishedAt(),
            articleModel.getAuthor().getFullName(),
            articleModel.getSource().getCode(),
            articleModel.getSource().getName()
        );
    }

    public ArticleBaseDTO toBaseDTO(ArticleModel articleModel) {
        return new ArticleBaseDTO(
            articleModel.getId(),
            articleModel.getTitle(),
            articleModel.getDescription(),
            articleModel.getUrl(),
            articleModel.getUrlToImage(),
            articleModel.getContent(),
            articleModel.getPublishedAt()
        );
    }

    public ArticleModel toModel(
        ArticleQueryDTO articleQueryDTO, AuthorModel author, 
        SourceModel source
    ) {
        return new ArticleModel(
            articleQueryDTO.getTitle(),
            articleQueryDTO.getDescription(),
            articleQueryDTO.getUrl(),
            articleQueryDTO.getUrlToImage(),
            articleQueryDTO.getContent(),
            articleQueryDTO.getPublishedAt(),
            author,
            source
        );
    }
}
