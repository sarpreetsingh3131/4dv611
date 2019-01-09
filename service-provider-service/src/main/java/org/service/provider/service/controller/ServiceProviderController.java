package org.service.provider.service.controller;

import org.domain.dto.CredentialDto;
import org.domain.dto.EmailDto;
import org.domain.dto.UpdateServiceProviderDto;
import org.domain.model.ServiceProvider;
import org.service.provider.service.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/service-provider", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService service;

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String sendEmail(@RequestBody @Valid EmailDto emailDto,
                            @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.sendEmail(emailDto, token);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ServiceProvider updateEmailAndPhone(@RequestBody @Valid UpdateServiceProviderDto updateServiceProviderDto,
                                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.updateEmailAndPhone(updateServiceProviderDto, token);
    }

    @GetMapping("/profile")
    public ServiceProvider findProfile(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findProfile(token);
    }
}
