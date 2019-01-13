package org.system.admin.service.controller;

import org.domain.dto.*;
import org.domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.system.admin.service.service.SystemAdminService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/system-admin", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class SystemAdminController {

    @Autowired
    private SystemAdminService service;

    @PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company saveCompany(@RequestBody @Valid CreateCompanyDto createCompanyDto,
                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.saveCompany(createCompanyDto, token);
    }

    @GetMapping(value = "/company/{id}")
    public Company findCompanyById(@PathVariable @NotNull Long id,
                                   @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findCompanyById(id, token);
    }

    @GetMapping(value = "/companies")
    public List<Company> findAllCompanies(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findAllCompanies(token);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Category saveCategory(@RequestBody @Valid CreateCategoryDto createCompanyDto,
                                 @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.saveCategory(createCompanyDto, token);
    }

    @GetMapping(value = "/category/{id}")
    public Category findCategoryById(@PathVariable @NotNull Long id,
                                     @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findCategoryById(id, token);
    }

    @GetMapping(value = "/categories")
    public List<Category> findAllCategories(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findAllCategories(token);
    }

    @PostMapping(value = "/ad-agent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AdAgent saveAdAgent(@RequestBody @Valid CreateAdAgentDto createAdAgentDto,
                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.saveAdAgent(createAdAgentDto, token);
    }

    @GetMapping(value = "/ad-agent/{id}")
    public AdAgent findAdAgentById(@PathVariable @NotNull Long id,
                                   @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findAdAgentById(id, token);
    }

    @GetMapping(value = "/ad-agents")
    public List<AdAgent> findAllAdAgents(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findAllAdAgents(token);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public SystemAdmin saveSystemAdmin(@RequestBody @Valid CreateSystemAdminDto createSystemAdminDto) throws Exception {
        return service.saveSystemAdmin(createSystemAdminDto);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @GetMapping(value = "/companies-products")
    public CompaniesAndProductsDto findAllCompaniesAndProducts(@RequestHeader("Authorization")
                                                               @NotBlank String token) throws Exception {
        return service.findAllCompaniesAndProducts(token);
    }

    @GetMapping(value = "/advertisements")
    public List<Advertisement> findAllAdvertisements(@RequestHeader("Authorization")
                                                     @NotBlank String token) throws Exception {
        return service.findAllAdvertisements(token);
    }
}
