package org.system.admin.service.controller;

import org.domain.dto.CreateCategoryDto;
import org.domain.dto.CreateCompanyDto;
import org.domain.model.Category;
import org.domain.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.system.admin.service.service.SystemAdminService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class SystemAdminController {

    @Autowired
    private SystemAdminService service;

    @PostMapping(value = "/api/company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company saveCompany(@RequestBody @Valid CreateCompanyDto createCompanyDto) throws Exception {
        return service.saveCompany(createCompanyDto);
    }

    @GetMapping(value = "/api/company/{id}")
    public Company findCompanyById(@PathVariable @NotNull Long id) throws Exception {
        return service.findCompanyById(id);
    }

    @GetMapping(value = "/api/company")
    public List<Company> findAllCompanies() {
        return service.findAllCompanies();
    }

    @PostMapping(value = "/api/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category saveCategory(@RequestBody @Valid CreateCategoryDto createCompanyDto) {
        return service.saveCategory(createCompanyDto);
    }

    @GetMapping(value = "/api/category/{id}")
    public Category findCategoryById(@PathVariable @NotNull Long id) throws Exception {
        return service.findCategoryById(id);
    }

    @GetMapping(value = "/api/category")
    public List<Category> findAllCategories() {
        return service.findAllCategories();
    }
}
