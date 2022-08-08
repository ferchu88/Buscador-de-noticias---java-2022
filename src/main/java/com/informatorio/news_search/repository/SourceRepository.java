package com.informatorio.news_search.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.informatorio.news_search.model.SourceModel;

public interface SourceRepository extends PagingAndSortingRepository<SourceModel,Long> {
    public Page<SourceModel> findByNameContaining(String name, Pageable pageable);
}
