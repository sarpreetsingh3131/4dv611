package org.product.service.service;

import org.domain.model.Image;
import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.repository.ImageRepository;
import org.domain.repository.ManualRepository;
import org.domain.repository.ProductRepository;
import org.domain.repository.RepresentativeRepository;
import org.domain.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.security.sasl.AuthenticationException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ManualRepository manualRepository;

    @Autowired
    private Authentication authentication;

    public Product save(String token, Product product) throws AuthenticationException {
        String username = authentication.validateRepresentativeAuthorization(token, representativeRepository);
        product.setCompany(representativeRepository.findByUsername(username).getCompany());
        product = productRepository.save(product);
        final Product savedProduct = product;
        product.getImages()
                .forEach(image -> image.setId(imageRepository.save(new Image(image, savedProduct)).getId()));
        product.getManuals()
                .forEach(manual -> manual.setId(manualRepository.save(new Manual(manual, savedProduct)).getId()));
        return product;
    }
   
  public List<Product> findByCategoryId(Long id){
        return productRepository.findByCategoryId(id);	  
  }
}
