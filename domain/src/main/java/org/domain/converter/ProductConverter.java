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

    @Autowired
    private CommentConverter commentConverter;

    public ProductWithoutBadgeDto toProductWithoutBadgeDto(Product product) {
        return new ProductWithoutBadgeDto(
                product.getId(), product.getName(), product.getModel(), product.getCategory(),
                product.getPrimaryImage(), product.getSecondaryImages(),
                manualConverter.manualToManualDtos(product.getManuals()), product.getViews()
        );
    }

    public List<ProductWithoutBadgeDto> toProductWithoutBadgeDto(List<Product> products) {
        List<ProductWithoutBadgeDto> productWithoutBadgeDtos = new LinkedList<>();
        products.forEach(product -> productWithoutBadgeDtos.add(toProductWithoutBadgeDto(product)));
        return productWithoutBadgeDtos;
    }

    public ProductWithBadgeDto toProductWithBadgeDto(Product product, Consumer consumer) {
        return new ProductWithBadgeDto(
                product.getId(), product.getName(), product.getModel(), product.getCategory(),
                product.getPrimaryImage(), product.getSecondaryImages(),
                manualConverter.manualToManualWithNoteDtos(product.getManuals(), consumer),
                consumer.getProducts().contains(product), product.getViews(),
                commentConverter.toCommentDto(product)
        );
    }

    private ProductWithSelectionDto toProductWithSelectionDto(Product product, Integer selection) {
        return new ProductWithSelectionDto(
                product.getId(), product.getName(), product.getModel(), product.getPrimaryImage(),
                product.getSecondaryImages(), manualConverter.manualToManualDtos(product.getManuals()),
                selection, product.getViews()
        );
    }

    public List<ProductWithSelectionDto> toProductWithSelectionDto(List<Product> products, List<Consumer> consumers) {
        List<ProductWithSelectionDto> productWithSelectionDtos = new LinkedList<>();
        products.forEach(product ->
                productWithSelectionDtos.add(toProductWithSelectionDto(product, countSelection(product, consumers))));
        return productWithSelectionDtos;
    }

    public Integer countSelection(Product product, List<Consumer> consumers) {
        return (int) consumers.stream()
                .filter(consumer -> consumer.getProducts().contains(product))
                .count();
    }
}
