package org.product.service.service;

import org.category.service.service.CategoryService;
import org.consumer.service.service.ConsumerService;
import org.domain.model.Image;
import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.model.Representative;
import org.domain.repository.ProductRepository;
import org.material.service.service.ImageService;
import org.material.service.service.ManualService;
import org.product.service.dto.BadgeDto;
import org.product.service.dto.ProductDto;
import org.product.service.dto.ProductWithBadgeDto;
import org.product.service.dto.ProductWithSelectionDto;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private RepresentativeService representativeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private ConsumerService consumerService;

    public Product save(ProductDto productDto, String token) throws Exception {
        Product product = repository.save((new Product(
                productDto.getName(), productDto.getModel(),
                categoryService.findById(productDto.getCategoryId()),
                representativeService.findByToken(token).getCompany()
        )));
        try {
            product.setPrimaryImage(imageService.save(productDto.getPrimaryImage(), product));
            product.setSecondaryImages(imageService.saveAll(productDto.getSecondaryImages(), product));
            product.setManuals(manualService.saveAll(productDto.getManuals(), product));
        } catch (Exception e) {
            repository.delete(product);
            throw new Exception(e);
        }
        return product;
    }

    public Product findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No product with id = " + id));
    }

    public ProductWithBadgeDto findById(Long id, String token) throws Exception {
        Product product = findById(id);
        return productToProductWithBadgeDto(product, consumerService.hasBadge(token, product));
    }

    public ProductWithBadgeDto updateBadge(BadgeDto badgeDto, String token) throws Exception {
        Product product = findById(badgeDto.getProductId());
        consumerService.updateBadge(token, product, badgeDto.getBadge());
        return productToProductWithBadgeDto(product, badgeDto.getBadge());
    }

    public List<Product> findByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }

    public List<Product> search(String query) {
        return repository
                .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                        query, query, query);
    }

    public List<Product> findLatest() {
        return repository.findTop10ByOrderByIdDesc();
    }

    public Product deleteImageById(Long id, String token) throws Exception {
        representativeService.findByToken(token);
        Image image = imageService.deleteById(id);
        return findById(image.getProduct().getId());
    }

    public Product deleteManualById(Long id, String token) throws Exception {
        representativeService.findByToken(token);
        Manual manual = manualService.deleteById(id);
        return findById(manual.getProduct().getId());
    }

    public List<ProductWithSelectionDto> countSelection(String token) throws Exception {
        Representative representative = representativeService.findByToken(token);
        List<ProductWithSelectionDto> productWithSelectionDtos = new LinkedList<>();
        for (Product product : repository.findByCategoryId(representative.getCompany().getId())) {
            productWithSelectionDtos.add(new ProductWithSelectionDto(
                    product.getId(), product.getName(), product.getModel(), product.getPrimaryImage(),
                    product.getSecondaryImages(), product.getManuals(), consumerService.countSelection(product)
            ));
        }
        return productWithSelectionDtos;
    }

    private ProductWithBadgeDto productToProductWithBadgeDto(Product product, Boolean hasBadge) {
        return new ProductWithBadgeDto(
                product.getId(), product.getName(), product.getModel(), product.getCategory(),
                product.getPrimaryImage(), product.getSecondaryImages(), product.getManuals(), hasBadge
        );
    }
}
