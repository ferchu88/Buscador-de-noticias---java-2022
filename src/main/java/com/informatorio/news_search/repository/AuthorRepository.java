package com.informatorio.news_search.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.informatorio.news_search.model.AuthorModel;

public interface AuthorRepository extends PagingAndSortingRepository<AuthorModel,Long> {
    public Page<AuthorModel> findByFullNameContaining(String fullName, Pageable pageable);
    public Page<AuthorModel> findByCreatedAtGreaterThanEqual(LocalDate createdAt, Pageable pageable);
}
