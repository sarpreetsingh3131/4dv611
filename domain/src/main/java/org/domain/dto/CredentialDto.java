package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialDto {

    @NotBlank(message = "Username is missing")
    private String username;

    @NotBlank(message = "Password is missing")
    private String password;
}
