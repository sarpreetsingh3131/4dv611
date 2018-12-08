package org.category.service.controller;

import org.category.service.dto.CategoryDto;
import org.category.service.service.CategoryService;
import org.domain.model.Category;
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
    public Category save(@RequestBody @Valid CategoryDto categoryDto) {
        return service.save(categoryDto);
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
