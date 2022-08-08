package com.informatorio.news_search.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.informatorio.news_search.converter.ArticleConverter;
import com.informatorio.news_search.converter.SourceConverter;
import com.informatorio.news_search.dto.source.SourceDTO;
import com.informatorio.news_search.dto.source.SourcePageDTO;
import com.informatorio.news_search.dto.source.SourceQueryDTO;
import com.informatorio.news_search.exception.EntityNotFoundException;
import com.informatorio.news_search.model.SourceModel;
import com.informatorio.news_search.repository.SourceRepository;

@Service
public class SourceService {
    @Autowired
    SourceRepository sourceRepository;
    @Autowired
    SourceConverter sourceConverter;
    @Autowired
    ArticleConverter articleConverter;

    public SourcePageDTO getAll(Integer page, Integer size, String query) {
        Pageable pageable = PageRequest.of(page-1,size);
        Page<SourceModel> filterPage;

        if(query != null) {
            filterPage = sourceRepository
                .findByNameContaining(query, pageable);
        }else {
            filterPage = sourceRepository
                .findAll(pageable);
        }
        Page<SourceDTO> sourcePage = filterPage
            .map(sourceModel -> sourceConverter.toDTO(
                sourceModel, 
                sourceModel
                    .getArticles()
                    .stream()
                    .map(articleModel -> articleConverter.toBaseDTO(articleModel))
                    .collect(Collectors.toList())
            ));
        
        return new SourcePageDTO(
            sourcePage.getTotalElements(),
            sourcePage.getContent()
        );
    }

    public void create(SourceQueryDTO sourceQueryDTO) {
        SourceModel sourceModel = sourceConverter.toModel(sourceQueryDTO);
        sourceRepository.save(sourceModel);
    }

    public SourceDTO get(Long id) {
        Optional<SourceModel> sourceModel = sourceRepository.findById(id);
        if(sourceModel.isPresent()) {
            return sourceConverter.toDTO(
                sourceModel.get(), 
                sourceModel.get()
                    .getArticles()
                    .stream()
                    .map(articleModel -> articleConverter.toBaseDTO(articleModel))
                    .collect(Collectors.toList()
            ));
        } else {
            throw new EntityNotFoundException("fuente","no encontrada");
        }
    }

    public void delete(Long id) {
        Optional<SourceModel> sourceModel = sourceRepository.findById(id);
        if(sourceModel.isPresent()) {
            sourceRepository.delete(sourceModel.get());
        } else {
            throw new EntityNotFoundException("fuente","no encontrada");
        }
    }

    public void modify(Long id, SourceQueryDTO sourceQueryDTO) {
        Optional<SourceModel> sourceModel = sourceRepository.findById(id);
        if(sourceModel.isPresent()) {
            SourceModel sourceToModify = sourceModel.get();
            sourceToModify.setName(sourceQueryDTO.getName());
            sourceRepository.save(sourceToModify);
        } else {
            throw new EntityNotFoundException("fuente","no encontrada");
        }
    }
}
