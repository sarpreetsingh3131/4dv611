package org.product.service.service;

import org.category.service.service.CategoryService;
import org.domain.model.Product;
import org.domain.repository.ProductRepository;
import org.material.service.service.ImageService;
import org.material.service.service.ManualService;
import org.product.service.dao.ProductDao;
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

    public Product save(ProductDao productDao, String token) throws Exception {
        String username = representativeService.validateAuthorization(token);
        Product product = repository.save(productDaoToProduct(new Product(), productDao, username));
        product.setImages(imageService.saveAll(productDao.getImages(), product));
        product.setManuals(manualService.saveAll(productDao.getManuals(), product));
        return product;
    }

    public Product findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No product with id = " + id));
    }

    public List<Product> findByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }

    public List<Product> search(String query) {
        return repository
                .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                        query, query, query);
    }

    private Product productDaoToProduct(Product product, ProductDao productDao, String username) throws Exception {
        product.setName(productDao.getName());
        product.setModel(productDao.getModel());
        product.setCategory(categoryService.findById(productDao.getCategoryId()));
        product.setCompany(representativeService.findByUsername(username).getCompany());
        return product;
    }

    public List<Product> findTop10ByOrderByIdDesc() {
        return repository.findTop10ByOrderByIdDesc();
    }

}
