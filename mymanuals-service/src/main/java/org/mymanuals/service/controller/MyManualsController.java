package org.mymanuals.service.controller;

import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.model.Advertisement;
import org.domain.model.Category;
import org.domain.model.Manual;
import org.mymanuals.service.service.MyManualsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/public", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class MyManualsController {

    @Autowired
    private MyManualsService service;

    @GetMapping(value = "/products/category/{id}")
    public List<ProductWithoutBadgeDto> findProductsByCategoryId(@PathVariable @NotNull Long id) {
        return service.findProductsByCategoryId(id);
    }

    @GetMapping(value = "/product/{id}")
    public ProductWithoutBadgeDto findProductById(@PathVariable @NotNull Long id) throws Exception {
        return service.findProductWithoutBadge(id);
    }

    @GetMapping(value = "/products")
    public List<ProductWithoutBadgeDto> searchProducts(@RequestParam("search") @NotBlank String query) {
        return service.searchProducts(query);
    }

    @GetMapping(value = "/products/latest")
    public List<ProductWithoutBadgeDto> find10LatestProducts() {
        return service.find10LatestProducts();
    }

    @GetMapping(value = "/product/manual/view/{id}")
    public Manual updateManualView(@PathVariable @NotNull Long id) throws Exception {
        return service.updateManualViews(id);
    }

    @GetMapping(value = "/advertisement")
    public Advertisement findRandomAdvertisement() throws Exception {
        return service.findRandomAdvertisement();
    }

    @GetMapping(value = "/category/{id}")
    public Category findCategoryById(@PathVariable @NotNull Long id) throws Exception {
        return service.findCategoryById(id);
    }

    @GetMapping(value = "/categories")
    public List<Category> findAllCategories() {
        return service.findAllCategories();
    }
}
