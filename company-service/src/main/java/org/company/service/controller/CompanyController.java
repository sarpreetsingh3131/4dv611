package org.company.service.controller;

import org.company.service.dao.CompanyDao;
import org.company.service.service.CompanyService;
import org.domain.dao.CredentialDao;
import org.domain.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = "application/json")
@ResponseBody
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PostMapping(consumes = "application/json")
    public Company save(@RequestBody @Valid CompanyDao companyDao) {
        return service.save(companyDao);
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable @NotNull Long id) throws Exception {
        return service.findById(id);
    }

    @PutMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody @Valid CredentialDao credentialDao) throws Exception {
        return service.login(credentialDao);
    }
}
