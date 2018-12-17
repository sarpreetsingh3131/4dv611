package org.company.service.controller;

import org.company.service.service.CompanyService;
import org.domain.dto.CreateRepresentativeDto;
import org.domain.dto.CreateServiceProviderDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Representative;
import org.domain.model.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class CompanyController {

    @Autowired
    private CompanyService service;

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @PostMapping(value = "/representative", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Representative saveRepresentative(@RequestBody @Valid CreateRepresentativeDto createRepresentativeDto,
                                             @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.saveRepresentative(createRepresentativeDto, token);
    }

    @GetMapping(value = "/representatives")
    public List<Representative> findAllRepresentatives(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findAllRepresentatives(token);
    }

    @PostMapping(value = "/service-provider", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceProvider saveServiceProvider(@RequestBody @Valid CreateServiceProviderDto createServiceProviderDto,
                                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.saveServiceProvider(createServiceProviderDto, token);
    }

    @GetMapping(value = "/service-providers")
    public List<ServiceProvider> findAllServiceProviders(@RequestHeader("Authorization")
                                                         @NotBlank String token) throws Exception {
        return service.findAllServiceProviders(token);
    }
}
