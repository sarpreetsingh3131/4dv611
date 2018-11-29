package org.company.service.controller;

import org.company.service.service.CompanyService;
import org.domain.model.Company;
import org.domain.utils.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.activity.InvalidActivityException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json")
@ResponseBody
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping(consumes = "application/json")
    public Company save(@RequestBody @Valid Company company) {
        return service.save(company);
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable @NotBlank String id) {
        return service.findById(id);
    }

    @PutMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody @Valid Credentials credentials) throws InvalidActivityException {
        return service.login(credentials);
    }
}