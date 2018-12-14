package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadgeDto {

    @NotNull(message = "Badge is missing")
    private Boolean badge;

    @NotNull(message = "Product id is missing")
    private Long productId;
}
