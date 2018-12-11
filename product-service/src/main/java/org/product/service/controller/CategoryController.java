package org.product.service.controller;

import org.domain.dto.CreateCategoryDto;
import org.domain.model.Category;
import org.product.service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/category", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category save(@RequestBody @Valid CreateCategoryDto createCategoryDto) {
        return service.save(createCategoryDto);
    }

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable @NotNull Long id) throws Exception {
        return service.findById(id);
    }
}
