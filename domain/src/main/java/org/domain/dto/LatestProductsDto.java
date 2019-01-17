package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class LatestProductsDto {

    @NonNull
    private ProductWithoutBadgeDto featuredProduct;

    @NonNull
    private List<ProductWithoutBadgeDto> products;
}
