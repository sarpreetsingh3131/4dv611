package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateServiceProviderDto {

    @NotBlank(message = "email is missing")
    @Email(message = "email is invalid")
    private String email;

    @NotNull(message = "phone is missing")
    private Integer phone;
}
