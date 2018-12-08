package org.representative.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RepresentativeDto {

    @NotBlank(message = "Representative name is missing")
    private String name;

    @NotBlank(message = "Representative username is missing")
    private String username;

    @NotBlank(message = "Representative name is missing")
    private String password;
}
