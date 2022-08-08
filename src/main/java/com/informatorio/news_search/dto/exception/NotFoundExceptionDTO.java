package com.informatorio.news_search.dto.exception;

public class NotFoundExceptionDTO {
    private String entity;
    private String error;

    public NotFoundExceptionDTO() {}
    public NotFoundExceptionDTO(String entity, String error) {
        this.entity = entity;
        this.error = error;
    }
    
    public String getEntity() {
        return entity;
    }
    public String getError() {
        return error;
    }
}
