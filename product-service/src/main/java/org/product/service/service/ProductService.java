package org.product.service.service;

import org.category.service.service.CategoryService;
import org.consumer.service.service.ConsumerService;
import org.domain.model.*;
import org.domain.repository.ProductRepository;
import org.material.service.service.ImageService;
import org.material.service.service.ManualService;
import org.product.service.dao.BadgeDao;
import org.product.service.dao.ProductDao;
import org.product.service.dto.ProductDto;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product save(ProductDao productDao, String token) throws Exception {
        String username = representativeService.validateAuthorization(token);
        Representative representative = representativeService.findByUsername(username);
        Product product = repository.save(productDaoToProduct(new Product(), productDao, representative));
        try {
            product.setImages(imageService.saveAll(productDao.getImages(), product));
            product.setManuals(manualService.saveAll(productDao.getManuals(), product));
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

    public ProductDto findById(Long id, String token) throws Exception {
        Product product = findById(id);
        String username = consumerService.validateAuthorization(token);
        return productToProductDto(product, consumerService.hasBadge(username, product));
    }

    public ProductDto badge(BadgeDao badgeDao, String token) throws Exception {
        String username = consumerService.validateAuthorization(token);
        Consumer consumer = consumerService.findByUsername(username);
        Product product = findById(badgeDao.getProductId());
        if (badgeDao.getBadge()) {
            consumer.getProducts().add(product);
        } else {
            consumer.getProducts().remove(product);
        }
        consumerService.save(consumer);
        return productToProductDto(product, badgeDao.getBadge());
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
        representativeService.validateAuthorization(token);
        Image image = imageService.deleteById(id);
        return findById(image.getProduct().getId());
    }

    public Product deleteManualById(Long id, String token) throws Exception {
        representativeService.validateAuthorization(token);
        Manual manual = manualService.deleteById(id);
        return findById(manual.getProduct().getId());
    }

    private Product productDaoToProduct(Product product, ProductDao productDao,
                                        Representative representative) throws Exception {
        product.setName(productDao.getName());
        product.setModel(productDao.getModel());
        product.setCategory(categoryService.findById(productDao.getCategoryId()));
        product.setCompany(representative.getCompany());
        return product;
    }

    private ProductDto productToProductDto(Product product, Boolean hasBadge) throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setModel(product.getModel());
        productDto.setCategory(categoryService.findById(product.getCategory().getId()));
        productDto.setImages(product.getImages());
        productDto.setManuals(product.getManuals());
        productDto.setHasBadge(hasBadge);
        return productDto;
    }
}
