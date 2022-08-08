package com.informatorio.news_search.dto.exception;

import java.util.List;

public class InvalidFieldsDTO {
    private Integer totalErrors;
    private List<InvalidFieldExceptionDTO> invalidFields;

    public InvalidFieldsDTO() {}
    public InvalidFieldsDTO(Integer totalErrors, List<InvalidFieldExceptionDTO> invalidFields) {
        this.totalErrors = totalErrors;
        this.invalidFields = invalidFields;
    }

    public Integer getTotalErrors() {
        return totalErrors;
    }
    public List<InvalidFieldExceptionDTO> getInvalidFields() {
        return invalidFields;
    }
}
