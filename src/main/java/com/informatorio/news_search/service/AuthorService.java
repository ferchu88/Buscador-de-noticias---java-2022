package com.informatorio.news_search.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.informatorio.news_search.converter.ArticleConverter;
import com.informatorio.news_search.converter.AuthorConverter;
import com.informatorio.news_search.dto.author.AuthorDTO;
import com.informatorio.news_search.dto.author.AuthorPageDTO;
import com.informatorio.news_search.dto.author.AuthorQueryDTO;
import com.informatorio.news_search.exception.EntityNotFoundException;
import com.informatorio.news_search.model.AuthorModel;
import com.informatorio.news_search.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorConverter authorConverter;
    @Autowired
    ArticleConverter articleConverter;

    public AuthorPageDTO getAll(Integer page, Integer size, LocalDate date, String query) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<AuthorModel> filterPage;

        if(date != null) {
            filterPage = authorRepository
                .findByCreatedAtGreaterThanEqual(date, pageable);
        } else if(query != null) {
            filterPage = authorRepository
                .findByFullNameContaining(query, pageable);
        } else {
            filterPage = authorRepository
                .findAll(pageable);
        }
        Page<AuthorDTO> authorPage = filterPage
            .map(authorModel -> authorConverter.toDTO(
                authorModel, 
                authorModel
                    .getArticles()
                    .subList(0, 5)
                    .stream()
                    .map(articleModel -> articleConverter.toBaseDTO(articleModel))
                    .collect(Collectors.toList())
            ));

        return new AuthorPageDTO(
            authorPage.getTotalElements(),
            authorPage.getContent()
        );
    }

    public void create(AuthorQueryDTO authorQueryDTO) {
        AuthorModel authorModel = authorConverter.toModel(authorQueryDTO);
        authorRepository.save(authorModel);
    }

    public AuthorDTO get(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.findById(id);
        if(authorModel.isPresent()) {
            return authorConverter.toDTO(
                authorModel.get(), 
                authorModel.get()
                    .getArticles()
                    .subList(0, 5)
                    .stream()
                    .map(articleModel -> articleConverter.toBaseDTO(articleModel))
                    .collect(Collectors.toList()
            ));
        } else {
            throw new EntityNotFoundException("autor","no encontrado");
        }
    }

    public void delete(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.findById(id);
        if(authorModel.isPresent()) {
            authorRepository.delete(authorModel.get());
        } else {
            throw new EntityNotFoundException("autor","no encontrado");
        }
    }

    public void modify(Long id, AuthorQueryDTO authorQueryDTO) {
        Optional<AuthorModel> authorModel = authorRepository.findById(id);
        if(authorModel.isPresent()) {
            AuthorModel authorToModify = authorModel.get();
            authorToModify.setFirstName(authorQueryDTO.getFirstName());
            authorToModify.setLastName(authorQueryDTO.getLastName());
            authorRepository.save(authorToModify);
        } else {
            throw new EntityNotFoundException("autor","no encontrado");
        }
    }
}
