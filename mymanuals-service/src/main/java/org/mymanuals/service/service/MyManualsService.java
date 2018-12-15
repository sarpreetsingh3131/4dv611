package org.mymanuals.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.model.Consumer;
import org.domain.model.Manual;
import org.domain.repository.ProductRepository;
import org.domain.service.ManualService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sendgrid.*;

@Service
public class MyManualsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductConverter converter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManualService manualService;

    public String logOut(String token) throws Exception {
        return userService.logOut(token);
    }

    public List<ProductWithoutBadgeDto> findProductsByCategoryId(Long id) {
        return converter.toProductWithoutBadgeDto(productRepository.findByCategoryId(id));
    }

    public List<ProductWithoutBadgeDto> searchProducts(String query) {
        return converter.toProductWithoutBadgeDto(
                productRepository
                        .findTop10ByNameIgnoreCaseContainingOrModelIgnoreCaseContainingOrCategoryNameIgnoreCaseContaining(
                                query, query, query));

    }

    public List<ProductWithoutBadgeDto> find10LatestProducts() {
        return converter.toProductWithoutBadgeDto(productRepository.findTop10ByOrderByIdDesc());
    }

    public Manual updateManualViews(Long id) throws Exception {
        Manual manual = manualService.findById(id);
        manual.setViews(manual.getViews() + 1);
        return manualService.update(manual);
    }
}
