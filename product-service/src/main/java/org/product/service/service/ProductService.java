package org.product.service.service;

import org.consumer.service.service.ConsumerService;
import org.domain.converter.ProductConverter;
import org.domain.dto.*;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.domain.model.Rating;
import org.domain.model.Representative;
import org.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ProductConverter converter;


    public ProductWithoutBadgeDto save(CreateProductDto createProductDto, Representative representative) throws Exception {
        Product product = repository.save((new Product(
                createProductDto.getName(), createProductDto.getModel(),
                categoryService.findById(createProductDto.getCategoryId()), representative.getCompany())));
        try {
            product.setPrimaryImage(imageService.save(createProductDto.getPrimaryImage(), product));
            product.setSecondaryImages(imageService.saveAll(createProductDto.getSecondaryImages(), product));
            product.setManuals(manualService.saveAll(createProductDto.getManuals(), product));
        } catch (Exception e) {
            repository.delete(product);
            throw new Exception(e);
        }
        return converter.toProductWithoutBadgeDto(product);
    }

    private Product findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No product with id = " + id));
    }

    public ProductWithoutBadgeDto findByIdWithoutBadge(Long id) throws Exception {
        return converter.toProductWithoutBadgeDto(findById(id));
    }

    public ProductWithBadgeDto findByIdWithBadge(Long id, Consumer consumer) throws Exception {
        Product product = findById(id);
        return converter.toProductWithBadgeDto(product, consumer.getProducts().contains(product));
    }

    public ProductWithBadgeDto updateBadge(BadgeDto badgeDto, Consumer consumer) throws Exception {
        Product product = findById(badgeDto.getProductId());
        consumerService.updateBadge(consumer, product, badgeDto.getBadge());
        return converter.toProductWithBadgeDto(product, badgeDto.getBadge());
    }

    public List<ProductWithoutBadgeDto> findByCategoryId(Long id) {
        return converter.toProductWithoutBadgeDto(repository.findByCategoryId(id));
    }

    public List<ProductWithoutBadgeDto> search(String query) {
        return converter.toProductWithoutBadgeDto(
                repository
                        .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                                query, query, query));

    }

    public List<ProductWithoutBadgeDto> findLatest() {
        return converter.toProductWithoutBadgeDto(repository.findTop10ByOrderByIdDesc());
    }

    public ProductWithoutBadgeDto deleteImageById(Long id, Representative representative) throws Exception {
        return converter.toProductWithoutBadgeDto(imageService.deleteById(id, representative).getProduct());
    }

    public ProductWithoutBadgeDto deleteManualById(Long id, Representative representative) throws Exception {
        return converter.toProductWithoutBadgeDto(manualService.deleteById(id, representative).getProduct());
    }

    public List<ProductWithSelectionDto> findWithSelection(Representative representative) {
        return converter.toProductWithSelectionDto(
                repository.findByCompanyId(representative.getCompany().getId()),
                consumerService.findAll());
    }

    public ProductWithBadgeDto addRating(Consumer consumer, RatingDto ratingDto) throws Exception {
        Rating rating = ratingService.save(consumer, ratingDto);
        return converter.toProductWithBadgeDto(rating.getManual().getProduct(),
                consumer.getProducts().contains(rating.getManual().getProduct()));
    }
}
