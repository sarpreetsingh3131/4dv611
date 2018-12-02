package org.consumer.service.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ConsumerDao {

    @NotBlank(message = "Consumer name is missing")
    private String name;

    @NotBlank(message = "Consumer username is missing")
    private String username;

    @NotBlank(message = "Consumer password is missing")
    private String password;
}
