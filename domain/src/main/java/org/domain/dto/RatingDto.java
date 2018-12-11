package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RatingDto {

    @NotNull(message = "Product rating is missing")
    private Integer rating;

    @NotNull(message = "Product's manual id is missing")
    private Long manualId;
}
