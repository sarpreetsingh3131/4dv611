package org.mymanuals.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.model.*;
import org.domain.repository.CategoryRepository;
import org.domain.repository.FeaturedProductRepository;
import org.domain.repository.ProductRepository;
import org.domain.service.AdvertisementService;
import org.domain.service.ManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyManualsService {

    @Autowired
    private ProductConverter converter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FeaturedProductRepository featuredProductRepository;

    public List<ProductWithoutBadgeDto> findProductsByCategoryId(Long id) {
        return converter.toProductWithoutBadgeDto(productRepository.findByCategoryId(id));
    }

    public List<ProductWithoutBadgeDto> searchProducts(String query) {
        return converter.toProductWithoutBadgeDto(
                productRepository
                        .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                                query, query, query
                        )
        );
    }

    public List<ProductWithoutBadgeDto> find10LatestProducts() {
        List<Product> productList = productRepository.findTop10ByOrderByIdDesc();
        List<FeaturedProduct> featuredProducts = featuredProductRepository.findAll();
        if (!featuredProducts.isEmpty()) {
            productList.remove(0);
            productList.add(0, featuredProducts.get(0).getProduct());
        }
        return converter.toProductWithoutBadgeDto(productList);
    }

    public Manual updateManualViews(Long id) throws Exception {
        return manualService.updateManualViews(id);
    }

    public ProductWithoutBadgeDto findProductWithoutBadge(Long id) throws Exception {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("no product with id = " + id));
        product.setViews(product.getViews() + 1);
        return converter.toProductWithoutBadgeDto(productRepository.save(product));
    }

    public Advertisement findRandomAdvertisement() throws Exception {
        return advertisementService.findRandomAdvertisement();
    }


    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("no category with id = " + id));
    }
}
