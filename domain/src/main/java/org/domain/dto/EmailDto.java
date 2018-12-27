package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailDto {

    @NotNull(message = "subject is missing")
    private String subject;

    @NotNull(message = "body is missing")
    private String body;
}
