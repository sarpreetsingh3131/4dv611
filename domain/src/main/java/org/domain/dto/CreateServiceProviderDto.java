package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateServiceProviderDto {

    @NotBlank(message = "Service provider name is missing")
    private String name;

    @NotBlank(message = "Service provider username is missing")
    private String username;

    @NotBlank(message = "Service provider password is missing")
    private String password;

    @NotNull(message = "Service provider authorization is missing")
    private Boolean authorization;
}
