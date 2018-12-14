package org.service.provider.service.controller;

import org.domain.dto.CredentialDto;
import org.service.provider.service.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService service;

    @PutMapping(value = "/api/serviceprovider/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }
}
