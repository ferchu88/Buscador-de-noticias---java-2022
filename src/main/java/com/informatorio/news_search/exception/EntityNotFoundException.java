package com.informatorio.news_search.exception;

public class EntityNotFoundException extends RuntimeException {
    private String entity = "entity";
    private String error = "notFound";

    public EntityNotFoundException() {}
    public EntityNotFoundException(String entity, String error) {
        super(error);
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
