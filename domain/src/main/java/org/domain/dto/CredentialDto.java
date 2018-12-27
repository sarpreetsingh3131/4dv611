package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialDto {

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;
}
