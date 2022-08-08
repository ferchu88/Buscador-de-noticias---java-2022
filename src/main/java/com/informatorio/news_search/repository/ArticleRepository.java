package com.informatorio.news_search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.informatorio.news_search.model.ArticleModel;

public interface ArticleRepository extends PagingAndSortingRepository<ArticleModel,Long> {
    public Page<ArticleModel> findByTitleContainingOrDescriptionContaining
        (String title,String description,Pageable pageable);
}
