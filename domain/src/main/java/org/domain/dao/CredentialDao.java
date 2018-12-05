package org.domain.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialDao {

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;
}
