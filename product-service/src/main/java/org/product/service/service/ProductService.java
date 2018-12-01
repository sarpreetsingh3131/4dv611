package org.product.service.service;

import org.domain.model.Product;
import org.domain.repository.CategoryRepository;
import org.domain.repository.CompanyRepository;
import org.domain.repository.ProductRepository;
import org.domain.repository.RepresentativeRepository;
import org.domain.utils.Authentication;
import org.product.service.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private Authentication authentication;

    public Product save(String token, ProductDao productDao) throws Exception {
        String username = authentication.validateRepresentativeAuthorization(token, representativeRepository);
        Product product = productRepository.save(productDaoToProduct(productDao, username));
        product.setImages(imageService.save(productDao.getImages(), product));
        product.setManuals(manualService.save(productDao.getManuals(), product));
        return product;
    }

    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public List<Product> search(String query) {
        return productRepository
                .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                        query, query, query);
    }

    private Product productDaoToProduct(ProductDao productDao, String username) throws Exception {
        Product product = new Product();
        product.setName(productDao.getName());
        product.setModel(productDao.getModel());
        product.setCompany(companyRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("No company with username = " + username)));
        product.setCategory(categoryRepository.findById(new Long(productDao.getCategoryId()))
                .orElseThrow(() -> new Exception("No category with id = " + productDao.getCategoryId())));
        return product;
    }
}
