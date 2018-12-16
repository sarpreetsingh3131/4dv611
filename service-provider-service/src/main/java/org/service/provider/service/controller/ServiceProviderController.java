package org.service.provider.service.controller;

import org.domain.dto.CredentialDto;
import org.domain.dto.EmailDto;
import org.service.provider.service.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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

    @PostMapping(value = "/api/serviceprovider/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sendEmail(@RequestBody @Valid EmailDto emailDto,
                            @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.sendEmail(emailDto, token);
    }
}
