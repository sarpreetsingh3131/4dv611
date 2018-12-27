package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadgeDto {

    @NotNull(message = "badge is missing")
    private Boolean badge;

    @NotNull(message = "productId is missing")
    private Long productId;
}
