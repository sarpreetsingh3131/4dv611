package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateRepresentativeDto {

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;
}
