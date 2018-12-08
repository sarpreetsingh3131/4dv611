package org.product.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadgeDto {

    @NotNull(message = "badge is missing")
    private Boolean badge;

    @NotNull(message = "product id is missing")
    private Long productId;
}
