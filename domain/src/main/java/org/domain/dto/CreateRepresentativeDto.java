package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateRepresentativeDto {

    @NotBlank(message = "Representative name is missing")
    private String name;

    @NotBlank(message = "Representative username is missing")
    private String username;

    @NotBlank(message = "Representative password is missing")
    private String password;
}
