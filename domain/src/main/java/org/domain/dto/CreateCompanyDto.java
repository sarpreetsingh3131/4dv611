package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCompanyDto {

    @NotBlank(message = "Company name is missing")
    private String name;

    @NotBlank(message = "Company description is missing")
    private String description;

    @NotBlank(message = "Company username is missing")
    private String username;

    @NotBlank(message = "Company password is missing")
    private String password;
}
