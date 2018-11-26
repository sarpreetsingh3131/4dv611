package org.category.service.controller;

import org.category.service.service.CategoryService;
import org.domain.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/category", produces = "application/json")
@ResponseBody
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(consumes = "application/json")
    public Category save(@RequestBody @Valid Category category) {
        return service.save(category);
    }

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable @NotBlank String id) {
        return service.findById(id);
    }
}
