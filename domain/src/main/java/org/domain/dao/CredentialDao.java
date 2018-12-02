package org.domain.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CredentialDao {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
