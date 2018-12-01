package org.product.service.controller;

import org.domain.model.Product;
import org.product.service.dao.ProductDao;
import org.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product", produces = "application/json")
@ResponseBody
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(consumes = "application/json")
    public Product save(@RequestBody @Valid ProductDao productDao,
                        @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(token, productDao);
    }

    @GetMapping("/category/{id}")
    public List<Product> findByCategoryId(@PathVariable @NotBlank String id) {
        return service.findByCategoryId(new Long(id));
    }

    @GetMapping
    public List<Product> search(@RequestParam("search") @NotBlank String query) {
        return service.search(query);
    }
}
