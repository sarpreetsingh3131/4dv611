package org.company.service.controller;

import org.company.service.service.CompanyService;
import org.domain.dto.CreateCompanyDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Company;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class CompanyController {

    @Autowired
    private CompanyService service;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company save(@RequestBody @Valid CreateCompanyDto createCompanyDto) throws Exception {
        userService.verifyUsername(createCompanyDto.getUsername());
        return service.save(createCompanyDto);
    }

    @GetMapping
    public List<Company> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Company findById(@PathVariable @NotNull Long id) throws Exception {
        return service.findById(id);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return userService.loginAsCompany(credentialDto);
    }
}
