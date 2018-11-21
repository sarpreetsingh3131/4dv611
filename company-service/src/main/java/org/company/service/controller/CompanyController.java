package org.company.service.controller;

import org.company.service.service.CompanyService;
import org.domain.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json")
@ResponseBody
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping(consumes = "application/json")
    public Company save(@RequestBody Company company) {
        return service.save(company);
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable String id) {
        return service.findById(id);
    }
}
