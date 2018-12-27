package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCompanyDto {

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "description is missing")
    private String description;

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;
}
