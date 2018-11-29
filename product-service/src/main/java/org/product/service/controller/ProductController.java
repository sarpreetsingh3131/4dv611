package org.product.service.controller;

import org.domain.model.Product;
import org.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
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
    public Product save(@RequestBody @Valid Product product,
                        @RequestHeader("Authorization") @NotBlank String token) throws AuthenticationException {
        return service.save(token, product);
    }

    @GetMapping
    public List<Product> findByCategoryId(@RequestParam("categoryId") @NotBlank String id) {
        return service.findByCategoryId(new Long(id));
    }
}
