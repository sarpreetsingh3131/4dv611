package org.consumer.service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ConsumerDto {

    @NotBlank(message = "Consumer name is missing")
    private String name;

    @NotBlank(message = "Consumer username is missing")
    private String username;

    @NotBlank(message = "Consumer password is missing")
    private String password;

    @NotBlank(message = "Consumer email is missing")
    @Email(message = "Consumer email is invalid")
    private String email;
}
