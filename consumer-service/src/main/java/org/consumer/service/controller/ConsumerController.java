package org.consumer.service.controller;

import org.consumer.service.service.ConsumerService;
import org.domain.dto.*;
import org.domain.model.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping(value = "/api/consumer/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer signUp(@RequestBody @Valid CreateConsumerDto createConsumerDto) throws Exception {
        return service.signUp(createConsumerDto);
    }

    @PutMapping(value = "/api/consumer/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/api/consumer/profile")
    public Consumer findProfile(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findProfile(token);
    }

    @PutMapping(value = "/api/consumer/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer updateSubscription(@RequestHeader("Authorization") @NotBlank String token,
                                       @RequestBody @Valid SubscriptionDto subscriptionDto) throws Exception {
        return service.updateSubscription(subscriptionDto, token);
    }

    @GetMapping(value = "/api/consumer/products")
    public List<ProductWithoutBadgeDto> findInterestedProducts(@RequestHeader("Authorization")
                                                               @NotBlank String token) throws Exception {
        return service.findInterestedProducts(token);
    }

    @PostMapping(value = "/api/product/badge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto updateBadge(@RequestHeader("Authorization") @NotBlank String token,
                                           @RequestBody @Valid BadgeDto badgeDto) throws Exception {
        return service.updateBadge(badgeDto, token);
    }

    @GetMapping(value = "/api/product/id")
    public Object findProductById(@RequestHeader(value = "Authorization", required = false) @NotBlank String token,
                                  @PathVariable @NotNull Long id) throws Exception {
        return token == null ? service.findProductWithoutBadge(id) : service.findProductWithBadge(id, token);
    }

    @PostMapping(value = "/api/product/manual/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto rateAManual(@RequestHeader("Authorization") @NotBlank String token,
                                           @RequestBody @Valid RatingDto ratingDto) throws Exception {
        return service.rateAManual(ratingDto, token);
    }

    @PostMapping(value = "/api/product/manual/note", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto addNoteOnManual(@RequestHeader("Authorization") @NotBlank String token,
                                               @RequestBody @Valid NoteDto noteDto) throws Exception {
        return service.addNoteOnManual(noteDto, token);
    }
}
