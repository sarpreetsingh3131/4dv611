package org.product.service.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BadgeDao {

    @NotNull(message = "badge is missing")
    private Boolean badge;

    @NotNull(message = "product id is missing")
    private Long productId;
}
