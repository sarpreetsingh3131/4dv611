package org.consumer.service.controller;

import org.consumer.service.service.ConsumerService;
import org.domain.model.Consumer;
import org.domain.utils.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.activity.InvalidActivityException;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/consumer", produces = "application/json")
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping(value = "/signup", consumes = "application/json")
    public Consumer signUp(@RequestBody @Valid Consumer consumer) {
        return service.signUp(consumer);
    }

    @PutMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody @Valid Credentials credentials) throws InvalidActivityException {
        return service.login(credentials);
    }
}
