package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class RatingDto {

    @NotNull(message = "Product rating is missing")
    @Min(value = 1, message = "Product rating must be >= 1")
    @Max(value = 5, message = "Product rating must be <= 5")
    private Integer rating;

    @NotNull(message = "Product's manual id is missing")
    private Long manualId;
}
