package org.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class LatestProductsDto {

    private ProductWithoutBadgeDto featuredProduct;
    private List<ProductWithoutBadgeDto> products;
}
