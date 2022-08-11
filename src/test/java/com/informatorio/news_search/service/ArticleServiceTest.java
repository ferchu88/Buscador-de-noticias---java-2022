package com.informatorio.news_search.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.informatorio.news_search.converter.ArticleConverter;
import com.informatorio.news_search.dto.article.ArticleDTO;
import com.informatorio.news_search.dto.article.ArticlePageDTO;
import com.informatorio.news_search.dto.article.ArticleQueryDTO;
import com.informatorio.news_search.exception.EntityNotFoundException;
import com.informatorio.news_search.model.ArticleModel;
import com.informatorio.news_search.model.AuthorModel;
import com.informatorio.news_search.model.SourceModel;
import com.informatorio.news_search.repository.ArticleRepository;
import com.informatorio.news_search.repository.AuthorRepository;
import com.informatorio.news_search.repository.SourceRepository;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    @Mock
    ArticleRepository articleRepository;
    @Mock
    ArticleConverter articleConverter;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    SourceRepository sourceRepository;
    @InjectMocks
    ArticleService articleService;
    
    @Test
    public void GivenAnInexistentKeyWordWhenGetArticlesThenReturnFullAmountOfThem() {
        final ArticleModel articleModel = mock(ArticleModel.class);
        final List<ArticleModel> articles = List.of(
            articleModel, articleModel, articleModel
        );

        when(articleRepository.findByPublishedAtNotNull(any(Pageable.class)))
            .thenReturn(new PageImpl<ArticleModel>(articles));
        when(articleConverter.toDTO(any(ArticleModel.class)))
            .thenReturn(mock(ArticleDTO.class));

        final ArticlePageDTO articlePageDTO = articleService.getAll(1, 10, null);

        assertEquals(articles.size(), articlePageDTO.getArticles().size());
    }

    @Test
    public void GivenAKeyWordWhenFilterArticlesByItThenReturnAListOfThem() {
        final ArticleModel articleModel = mock(ArticleModel.class);
        final List<ArticleModel> articles = List.of(articleModel,articleModel);

        when(
            articleRepository.findByPublishedAtNotNullAndTitleContainingOrPublishedAtNotNullAndDescriptionContaining
                (any(String.class), any(String.class), any(Pageable.class))
        ).thenReturn(new PageImpl<ArticleModel>(articles));
        when(articleConverter.toDTO(any(ArticleModel.class)))
            .thenReturn(mock(ArticleDTO.class));
        final ArticlePageDTO articlePageDTO = articleService.getAll(1, 10, "keyword");

        assertEquals(articles.size(), articlePageDTO.getArticles().size());
    }

    @Test
    public void GivenAnArticleQueryWhenCreateANewArticleThenDoNotThrowAnyError() {
        final ArticleQueryDTO articleQueryDTO = mock(ArticleQueryDTO.class);
        Boolean isError = false;

        when(authorRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(mock(AuthorModel.class)));
        when(sourceRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(mock(SourceModel.class)));
        when(articleRepository.save(any(ArticleModel.class)))
            .thenReturn(mock(ArticleModel.class));
        when(articleConverter.toModel(
            any(ArticleQueryDTO.class), any(AuthorModel.class), any(SourceModel.class)
        ))
            .thenReturn(mock(ArticleModel.class));

        try {
            articleService.create(articleQueryDTO);
        } catch (final Exception e) {
            isError = true;
        }
        assertFalse(isError);
    }

    @Test
    public void GivenAnInexistentAuthorWhenCreateANewArticleThenThrowError() {
        final ArticleQueryDTO articleQueryDTO = mock(ArticleQueryDTO.class);
        Boolean isError = false;

        when(authorRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());

        try {
            articleService.create(articleQueryDTO);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertTrue(isError);
    }

    @Test
    public void GivenAnInexistentSourceWhenCreateANewArticleThenThrowError() {
        final ArticleQueryDTO articleQueryDTO = mock(ArticleQueryDTO.class);
        Boolean isError = false;

        when(authorRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(mock(AuthorModel.class)));
        when(sourceRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());

        try {
            articleService.create(articleQueryDTO);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertTrue(isError);
    }

    @Test
    public void GivenAnIdWhenFindByItThenReturnTheArticle() {
        Boolean isError = false;

        when(articleRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(mock(ArticleModel.class)));
        when(articleConverter.toDTO(any(ArticleModel.class)))
            .thenReturn(mock(ArticleDTO.class));

        try {
            articleService.get(1L);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertFalse(isError);
    }

    @Test
    public void GivenAnInexistentIdWhenFindByItThenThrowError() {
        Boolean isError = false;

        when(articleRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());

        try {
            articleService.get(1L);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertTrue(isError);
    }

    @Test
    public void GivenAnIdWhenFindByItThenRemoveArticle() {
        Boolean isError = false;

        when(articleRepository.findById(any(Long.class)))
            .thenReturn(Optional.of(mock(ArticleModel.class)));
        doNothing().when(articleRepository).delete(any(ArticleModel.class));

        try {
            articleService.delete(1L);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertFalse(isError);
    }

    @Test
    public void GivenAnInexistentIdWhenFindByItForDeleteThenThrowError() {
        Boolean isError = false;

        when(articleRepository.findById(any(Long.class)))
            .thenReturn(Optional.empty());

        try {
            articleService.delete(1L);
        } catch (final EntityNotFoundException e) {
            isError = true;
        }
        assertTrue(isError);
    }

    // modify
}
