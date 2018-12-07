package org.consumer.service.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ConsumerDao {

    @NotBlank(message = "Consumer name is missing")
    private String name;

    @NotBlank(message = "Consumer username is missing")
    private String username;

    @NotBlank(message = "Consumer password is missing")
    private String password;

    @NotBlank(message = "Consumer email is misssing")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Consumer email is invalid")
    private String email;
}
