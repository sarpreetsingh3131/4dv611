package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmailDto {

    @NotNull(message = "Subject is missing")
    private String subject;

    @NotNull(message = "Body is missing")
    private String body;
}
