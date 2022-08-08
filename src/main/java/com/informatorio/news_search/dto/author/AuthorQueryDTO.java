package com.informatorio.news_search.dto.author;

import javax.validation.constraints.NotBlank;

public class AuthorQueryDTO {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    
    public AuthorQueryDTO() {}
    public AuthorQueryDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
