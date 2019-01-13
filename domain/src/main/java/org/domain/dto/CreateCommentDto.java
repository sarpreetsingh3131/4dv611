package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateCommentDto {

    @NotBlank(message = "text is missing")
    private String text;

    @NotNull(message = "product id is missing")
    private Long productId;
}
