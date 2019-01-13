package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateSystemAdminDto {

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;

    @NotBlank(message = "email is missing")
    @Email(message = "email is invalid")
    private String email;
}
