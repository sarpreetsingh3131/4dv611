package org.domain.controller;

import org.domain.dto.CreateCompanyDto;
import org.domain.dto.CreateConsumerDto;
import org.domain.dto.CreateRepresentativeDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Company;
import org.domain.model.Consumer;
import org.domain.model.Representative;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class UserController {

    @Autowired
    private UserService service;

    @PutMapping(value = "/company/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String loginAsCompany(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.loginAsCompany(credentialDto);
    }

    @PutMapping(value = "/representative/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String loginAsRepresentative(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.loginAsRepresentative(credentialDto);
    }

    @PutMapping(value = "/consumer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String loginAsConsumer(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.loginAsConsumer(credentialDto);
    }

    @PostMapping(value = "/company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Company save(@RequestBody @Valid CreateCompanyDto createCompanyDto) throws Exception {
        return service.save(createCompanyDto);
    }

    @PostMapping(value = "/representative", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Representative save(@RequestBody @Valid CreateRepresentativeDto createRepresentativeDto,
                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(createRepresentativeDto, token);
    }

    @PostMapping(value = "/consumer/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer signUp(@RequestBody @Valid CreateConsumerDto createConsumerDto) throws Exception {
        return service.signUp(createConsumerDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) {
        return service.logOut(token);
    }
}
