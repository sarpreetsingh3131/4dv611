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
@RequestMapping(value = "/api/system-admin", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class SystemAdminController {

    @Autowired
    private SystemAdminService service;

    @PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company saveCompany(@RequestBody @Valid CreateCompanyDto createCompanyDto) throws Exception {
        return service.saveCompany(createCompanyDto);
    }

    @GetMapping(value = "/company/{id}")
    public Company findCompanyById(@PathVariable @NotNull Long id) throws Exception {
        return service.findCompanyById(id);
    }

    @GetMapping(value = "/companies")
    public List<Company> findAllCompanies() {
        return service.findAllCompanies();
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category saveCategory(@RequestBody @Valid CreateCategoryDto createCompanyDto) {
        return service.saveCategory(createCompanyDto);
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
