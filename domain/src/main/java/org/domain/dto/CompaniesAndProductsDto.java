package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.domain.model.Company;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class CompaniesAndProductsDto {

    @NonNull
    private List<Company> companies;

    @NonNull
    private List<ProductWithoutBadgeDto> products;
}
