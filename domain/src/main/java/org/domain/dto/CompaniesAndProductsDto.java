package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class CompaniesAndProductsDto {

    @NonNull
    private Integer companies;

    @NonNull
    private Integer products;
}
