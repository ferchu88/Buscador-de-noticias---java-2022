package com.informatorio.news_search.dto.exception;

public class InvalidFieldExceptionDTO {
    private String field;
    private String message;

    public InvalidFieldExceptionDTO() {}
    public InvalidFieldExceptionDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }
    public String getMessage() {
        return message;
    }
}
