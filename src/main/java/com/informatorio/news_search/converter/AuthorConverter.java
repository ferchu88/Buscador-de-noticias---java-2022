package com.informatorio.news_search.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.author.AuthorDTO;
import com.informatorio.news_search.dto.author.AuthorQueryDTO;
import com.informatorio.news_search.model.AuthorModel;

@Component
public class AuthorConverter {
    public AuthorDTO toDTO(AuthorModel authorModel, List<ArticleBaseDTO> articleBaseDTO) {
        return new AuthorDTO(
            authorModel.getId(),
            authorModel.getFirstName(),
            authorModel.getLastName(),
            authorModel.getFullName(),
            authorModel.getCreatedAt(),
            articleBaseDTO
        );
    }

    public AuthorModel toModel(AuthorQueryDTO authorQueryDTO) {
        return new AuthorModel(
            authorQueryDTO.getFirstName(),
            authorQueryDTO.getLastName()
        );
    }
}
