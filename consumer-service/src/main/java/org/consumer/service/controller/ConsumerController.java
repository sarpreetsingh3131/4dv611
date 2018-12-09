package org.consumer.service.controller;

import org.consumer.service.dto.ConsumerDto;
import org.consumer.service.dto.SubscriptionDto;
import org.consumer.service.service.ConsumerService;
import org.domain.dto.CredentialDto;
import org.domain.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer signUp(@RequestBody @Valid ConsumerDto consumerDto) throws Exception {
        return service.signUp(consumerDto);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.login(credentialDto);
    }

    @GetMapping(value = "/profile")
    public Consumer findById(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findProfile(token);
    }

    @PutMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer subscription(@RequestHeader("Authorization") @NotBlank String token,
                                 @RequestBody @Valid SubscriptionDto subscriptionDto) throws Exception {
        return service.subscription(token, subscriptionDto);
    }
}
