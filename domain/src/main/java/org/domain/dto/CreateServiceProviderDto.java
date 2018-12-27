package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateServiceProviderDto {

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "username is missing")
    private String username;

    @NotBlank(message = "password is missing")
    private String password;

    @NotNull(message = "authorization is missing")
    private Boolean authorization;

    @NotBlank(message = "email is missing")
    @Email(message = "email is invalid")
    private String email;

    @NotNull(message = "phone is missing")
    private Integer phone;
}
