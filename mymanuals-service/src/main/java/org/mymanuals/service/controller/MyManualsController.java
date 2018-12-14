package org.mymanuals.service.controller;

import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.model.Manual;
import org.mymanuals.service.service.MyManualsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class MyManualsController {

    @Autowired
    private MyManualsService service;

    @GetMapping(value = "/api/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @GetMapping(value = "/api/product/category/{id}")
    public List<ProductWithoutBadgeDto> findProductsByCategoryId(@PathVariable @NotNull Long id) {
        return service.findProductsByCategoryId(id);
    }

    @GetMapping(value = "/api/product")
    public List<ProductWithoutBadgeDto> searchProducts(@RequestParam("search") @NotBlank String query) {
        return service.searchProducts(query);
    }

    @GetMapping(value = "/api/product/latest")
    public List<ProductWithoutBadgeDto> find10LatestProducts() {
        return service.find10LatestProducts();
    }

    @GetMapping(value = "/api/product/manual/view/{id}")
    public Manual updateManualView(@PathVariable @NotNull Long id) throws Exception {
        return service.updateManualViews(id);
    }
}
