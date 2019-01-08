package org.ad.agent.service.controller;

import org.domain.dto.*;
import org.ad.agent.service.service.AdAgentService;
import org.domain.model.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/ad-agent", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class AdAgentController {

    @Autowired
    private AdAgentService service;

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @PostMapping(value = "/advertisement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Advertisement saveAdvertisement(@RequestBody @Valid CreateAdvertisementDto createAdvertisementDto,
                                           @RequestHeader("Authorization")
                                           @NotBlank String token) throws Exception {
        return service.saveAdvertisement(createAdvertisementDto, token);
    }
}
