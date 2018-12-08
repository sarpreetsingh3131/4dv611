package org.product.service.controller;

import org.domain.model.Product;
import org.product.service.dto.BadgeDto;
import org.product.service.dto.ProductDto;
import org.product.service.dto.ProductWithBadgeDto;
import org.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product save(@RequestBody @Valid ProductDto productDto,
                        @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(productDto, token);
    }

    @GetMapping(value = "/category/{id}")
    public List<Product> findByCategoryId(@PathVariable @NotNull Long id) {
        return service.findByCategoryId(id);
    }

    @GetMapping
    public List<Product> search(@RequestParam("search") @NotBlank String query) {
        return service.search(query);
    }

    @GetMapping(value = "/{id}")
    public Object findById(@RequestHeader(value = "Authorization", required = false) @NotBlank String token,
                           @PathVariable @NotNull Long id) throws Exception {
        return token == null ? service.findById(id) : service.findById(id, token);
    }

    @GetMapping(value = "/latest")
    public List<Product> findLatest() {
        return service.findLatest();
    }

    @DeleteMapping("/image/{id}")
    public Product deleteImageById(@PathVariable @NotNull Long id,
                                   @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.deleteImageById(id, token);
    }

    @DeleteMapping("/manual/{id}")
    public Product deleteManualById(@PathVariable @NotNull Long id,
                                    @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.deleteManualById(id, token);
    }

    @PostMapping(value = "/badge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto updateBadge(@RequestBody @Valid BadgeDto badgeDto,
                                           @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.updateBadge(badgeDto, token);
    }
}
