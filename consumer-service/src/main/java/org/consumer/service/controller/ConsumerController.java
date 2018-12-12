package org.consumer.service.controller;

import org.consumer.service.service.ConsumerService;
import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.dto.SubscriptionDto;
import org.domain.model.Consumer;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/profile")
    public Consumer findById(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return userService.findConsumer(token);
    }

    @PutMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer subscription(@RequestHeader("Authorization") @NotBlank String token,
                                 @RequestBody @Valid SubscriptionDto subscriptionDto) throws Exception {
        return service.subscription(userService.findConsumer(token), subscriptionDto);
    }

    @GetMapping(value = "/products")
    public List<ProductWithoutBadgeDto> findProducts(@RequestHeader("Authorization") @NotBlank String token)
            throws Exception {
        return service.findProducts(userService.findConsumer(token));
    }
}
