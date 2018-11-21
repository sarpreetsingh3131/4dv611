package org.consumer.service.controller;

import org.consumer.service.service.ConsumerService;
import org.domain.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<Consumer> findAll() {
        return service.findAll();
    }
}
