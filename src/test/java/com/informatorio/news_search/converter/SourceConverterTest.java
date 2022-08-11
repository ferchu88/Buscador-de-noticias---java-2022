package com.informatorio.news_search.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.source.SourceDTO;
import com.informatorio.news_search.dto.source.SourceQueryDTO;
import com.informatorio.news_search.model.SourceModel;

@SpringBootTest
public class SourceConverterTest {
    SourceConverter sourceConverter = new SourceConverter();

    @Test
    public void transformAnSourceModelToSourceDto() {
        ArticleBaseDTO articleBaseDTO = new ArticleBaseDTO();
        List<ArticleBaseDTO> articleList = List.of(
            articleBaseDTO, articleBaseDTO, articleBaseDTO, 
            articleBaseDTO, articleBaseDTO, articleBaseDTO
        );
        SourceModel sourceModel = new SourceModel("La Nacion");

        SourceDTO sourceDTO = sourceConverter.toDTO(sourceModel, articleList);

        assertEquals(articleList.size(), sourceDTO.getArticles().size());
        assertEquals("la-nacion", sourceDTO.getCode());
    }

    @Test
    public void transformAnSourceQueryDtoToSourceModel() {
        SourceQueryDTO sourceQueryDTO = new SourceQueryDTO(null, "La Nacion");

        SourceModel sourceModel = sourceConverter.toModel(sourceQueryDTO);

        assertEquals("la-nacion", sourceModel.getCode());
    }
}
