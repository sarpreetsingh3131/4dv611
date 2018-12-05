package org.consumer.service.controller;

import org.consumer.service.dao.BadgeDao;
import org.consumer.service.dao.ConsumerDao;
import org.consumer.service.service.ConsumerService;
import org.domain.dao.CredentialDao;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/consumer", produces = "application/json")
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping(value = "/signup", consumes = "application/json")
    public Consumer signUp(@RequestBody @Valid ConsumerDao consumerDao) {
        return service.signUp(consumerDao);
    }

    @PutMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody @Valid CredentialDao credentialDao) throws Exception {
        return service.login(credentialDao);
    }

    @PostMapping(value = "/badge", consumes = "application/json")
    public Product badge(@RequestBody @Valid BadgeDao badgeDao,
                         @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.badge(badgeDao, token);
    }
}
