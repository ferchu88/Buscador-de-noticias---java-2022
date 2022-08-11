package com.informatorio.news_search.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.informatorio.news_search.dto.article.ArticleBaseDTO;
import com.informatorio.news_search.dto.article.ArticleDTO;
import com.informatorio.news_search.dto.article.ArticleQueryDTO;
import com.informatorio.news_search.model.ArticleModel;
import com.informatorio.news_search.model.AuthorModel;
import com.informatorio.news_search.model.SourceModel;

@SpringBootTest
public class ArticleConverterTest {
    ArticleConverter articleConverter = new ArticleConverter();

    @Test
	public void transformAnArticleModelToArticleDto() {
        SourceModel sourceModel = new SourceModel("La Nacion");
        AuthorModel authorModel = new AuthorModel("Ezequiel","Manfredi");
        ArticleModel articleModel = new ArticleModel(
            1L, 
            "titulo", 
            "descripcion", 
            "http://url.com", 
            "http://url.com/img.png", 
            "contenido", 
            LocalDate.of(2022,8,4), 
            authorModel, 
            sourceModel
        );

        ArticleDTO articleDTO = articleConverter.toDTO(articleModel);

        assertEquals(1L, articleDTO.getId());
        assertEquals("titulo", articleDTO.getTitle());
        assertEquals("descripcion", articleDTO.getDescription());
        assertEquals("http://url.com", articleDTO.getUrl());
        assertEquals("http://url.com/img.png", articleDTO.getUrlToImage());
        assertEquals("contenido", articleDTO.getContent());
        assertEquals(LocalDate.of(2022,8,4), articleDTO.getPublishedAt());
        assertEquals("Ezequiel Manfredi", articleDTO.getAuthor());
        assertEquals("la-nacion", articleDTO.getSource().getId());
	}

    @Test
	public void transformAnArticleModelToArticleBaseDto() {
        ArticleModel articleModel = new ArticleModel(
            1L, 
            "titulo", 
            "descripcion", 
            "http://url.com", 
            "http://url.com/img.png", 
            "contenido", 
            LocalDate.of(2022,8,4), 
            null,
            null
        );

        ArticleBaseDTO articleBaseDTO = articleConverter.toBaseDTO(articleModel);

        assertEquals(1L, articleBaseDTO.getId());
        assertEquals("titulo", articleBaseDTO.getTitle());
        assertEquals("descripcion", articleBaseDTO.getDescription());
        assertEquals("http://url.com", articleBaseDTO.getUrl());
        assertEquals("http://url.com/img.png", articleBaseDTO.getUrlToImage());
        assertEquals("contenido", articleBaseDTO.getContent());
        assertEquals(LocalDate.of(2022,8,4), articleBaseDTO.getPublishedAt());
	}

    @Test
    public void transformAnArticleQueryDtoToArticleModel() {
        SourceModel sourceModel = new SourceModel("La Nacion");
        AuthorModel authorModel = new AuthorModel("Ezequiel","Manfredi");
        ArticleQueryDTO articleQueryDTO = new ArticleQueryDTO(
            null, 
            "titulo", 
            "descripcion", 
            "http://url.com", 
            "http://url.com/img.png", 
            "contenido", 
            LocalDate.of(2022,8,4), 
            1L, 
            1L
        );

        ArticleModel articleModel = articleConverter.toModel(
            articleQueryDTO, authorModel, sourceModel
        );

        assertNull(articleModel.getId());
        assertEquals("titulo", articleModel.getTitle());
        assertEquals("descripcion", articleModel.getDescription());
        assertEquals("http://url.com", articleModel.getUrl());
        assertEquals("http://url.com/img.png", articleModel.getUrlToImage());
        assertEquals("contenido", articleModel.getContent());
        assertEquals(LocalDate.of(2022,8,4), articleModel.getPublishedAt());
        assertEquals("Ezequiel Manfredi", articleModel.getAuthor().getFullName());
        assertEquals("la-nacion", articleModel.getSource().getCode());
    }
}
