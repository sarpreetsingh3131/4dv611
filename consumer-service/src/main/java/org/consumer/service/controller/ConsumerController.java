package org.consumer.service.controller;

import org.consumer.service.dao.ConsumerDao;
import org.consumer.service.dao.SubscriptionDao;
import org.consumer.service.service.ConsumerService;
import org.domain.dao.CredentialDao;
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
    public Consumer signUp(@RequestBody @Valid ConsumerDao consumerDao) {
        return service.signUp(consumerDao);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody @Valid CredentialDao credentialDao) throws Exception {
        return service.login(credentialDao);
    }

    @GetMapping(value = "/profile")
    public Consumer findById(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findProfile(token);
    }

    @PutMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer subscription(@RequestHeader("Authorization") @NotBlank String token,
                                 @RequestBody @Valid SubscriptionDao subscriptionDao) throws Exception {
        return service.subscription(token, subscriptionDao);
    }
}
