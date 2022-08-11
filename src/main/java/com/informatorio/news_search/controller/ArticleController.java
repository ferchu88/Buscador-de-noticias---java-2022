package com.informatorio.news_search.controller;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.news_search.dto.article.ArticleDTO;
import com.informatorio.news_search.dto.article.ArticlePageDTO;
import com.informatorio.news_search.dto.article.ArticleQueryDTO;
import com.informatorio.news_search.service.ArticleService;

@RestController
@RequestMapping(value = "/api/v1/articles")
@Validated
public class ArticleController {
    @Autowired
    ArticleService articleService;
    
    @GetMapping(value = {""})
    @ResponseStatus(HttpStatus.OK)
    public ArticlePageDTO allArticles(
        @RequestParam(required = false) @Size(min = 3) String query, 
        @RequestParam(required = false, defaultValue = "1") @Positive Integer page, 
        @RequestParam(required = false, defaultValue = "10") @Positive Integer size
    ) {
        if(query != null) {
            return articleService.getAll(page, size, query);
        }
        return articleService.getAll(page, size, null);
    }

    @PostMapping(value = {""})
    @ResponseStatus(HttpStatus.CREATED)
    public void createArticle(@RequestBody @Valid ArticleQueryDTO articleQueryDTO) {
        articleService.create(articleQueryDTO);
    }

    @GetMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO getArticle(@PathVariable Long id) {
        return articleService.get(id);
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
    }

    @PutMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void modifyArticle(@PathVariable Long id, @RequestBody @Valid ArticleQueryDTO articleQueryDTO) {
        articleService.modify(id, articleQueryDTO);
    }
}
