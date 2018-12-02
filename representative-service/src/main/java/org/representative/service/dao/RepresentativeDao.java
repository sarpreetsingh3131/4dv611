package org.representative.service.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RepresentativeDao {

    @NotBlank(message = "Representative name is missing")
    private String name;

    @NotBlank(message = "Representative username is missing")
    private String username;

    @NotBlank(message = "Representative name is missing")
    private String password;
}
