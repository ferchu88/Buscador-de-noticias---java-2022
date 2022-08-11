package com.informatorio.news_search.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.author.AuthorDTO;
import com.informatorio.news_search.dto.author.AuthorQueryDTO;
import com.informatorio.news_search.model.AuthorModel;

@SpringBootTest
public class AuthorConverterTest {
    AuthorConverter authorConverter = new AuthorConverter();

    @Test
    public void transformAnAuthorModelToAuthorDto() {
        ArticleBaseDTO articleBaseDTO = new ArticleBaseDTO();
        List<ArticleBaseDTO> articleList = List.of(
            articleBaseDTO, articleBaseDTO, articleBaseDTO, 
            articleBaseDTO, articleBaseDTO, articleBaseDTO
        );
        AuthorModel authorModel = new AuthorModel("Ezequiel", "Manfredi");

        AuthorDTO authorDTO = authorConverter.toDTO(authorModel, articleList);

        assertEquals(articleList.size(), authorDTO.getArticles().size());
        assertEquals("Ezequiel Manfredi", authorDTO.getFullName());
    }

    @Test
    public void transformAnAuthorQueryDtoToAuthorModel() {
        AuthorQueryDTO authorQueryDTO = new AuthorQueryDTO(
            null, "Ezequiel", "Manfredi"
        );

        AuthorModel authorModel = authorConverter.toModel(authorQueryDTO);

        assertEquals("Ezequiel Manfredi", authorModel.getFullName());
    }
}
