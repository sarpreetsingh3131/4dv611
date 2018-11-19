package org.company.service.controller;

import org.domain.model.Company;
import org.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json")
public class Controller {

    @Autowired
    private CompanyRepository repository;

    @PostMapping(consumes = "application/json")
    public Company create(@RequestBody Company company) {
        return repository.save(company);
    }

    @GetMapping
    public List<Company> findAll() {
        return repository.findAll();
    }
}
