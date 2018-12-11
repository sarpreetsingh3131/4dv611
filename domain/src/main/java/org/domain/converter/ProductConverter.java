package org.domain.converter;

import org.domain.dto.ProductWithBadgeDto;
import org.domain.dto.ProductWithSelectionDto;
import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    private ManualConverter manualConverter;


    public ProductWithoutBadgeDto toProductWithoutBadgeDto(Product product) {
        return new ProductWithoutBadgeDto(
                product.getId(), product.getName(), product.getModel(), product.getCategory(),
                product.getPrimaryImage(), product.getSecondaryImages(),
                manualConverter.manualToManualDtos(product.getManuals()));
    }

    public List<ProductWithoutBadgeDto> toProductWithoutBadgeDto(List<Product> products) {
        List<ProductWithoutBadgeDto> productWithoutBadgeDtos = new LinkedList<>();
        for (Product product : products) {
            productWithoutBadgeDtos.add(toProductWithoutBadgeDto(product));
        }
        return productWithoutBadgeDtos;
    }

    public ProductWithBadgeDto toProductWithBadgeDto(Product product, Boolean hasBadge) {
        return new ProductWithBadgeDto(
                product.getId(), product.getName(), product.getModel(), product.getCategory(),
                product.getPrimaryImage(), product.getSecondaryImages(),
                manualConverter.manualToManualDtos(product.getManuals()), hasBadge);
    }

    private ProductWithSelectionDto toProductWithSelectionDto(Product product, Integer selection) {
        return new ProductWithSelectionDto(
                product.getId(), product.getName(), product.getModel(), product.getPrimaryImage(),
                product.getSecondaryImages(), manualConverter.manualToManualDtos(product.getManuals()),
                selection
        );
    }

    public List<ProductWithSelectionDto> toProductWithSelectionDto(List<Product> products, List<Consumer> consumers) {
        List<ProductWithSelectionDto> productWithSelectionDtos = new LinkedList<>();
        for (Product product : products) {
            productWithSelectionDtos.add(toProductWithSelectionDto(product, countSelection(product, consumers)));
        }
        return productWithSelectionDtos;
    }


    public Integer countSelection(Product product, List<Consumer> consumers) {
        int count = 0;
        for (Consumer consumer : consumers) {
            if (consumer.getProducts().contains(product)) {
                count++;
            }
        }
        return count;
    }
}
