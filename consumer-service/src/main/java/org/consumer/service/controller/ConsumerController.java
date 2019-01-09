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
@RequestMapping(value = "/api/consumer", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer signUp(@RequestBody @Valid CreateConsumerDto createConsumerDto) throws Exception {
        return service.signUp(createConsumerDto);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String logIn(@RequestBody @Valid CredentialDto credentialDto) throws Exception {
        return service.logIn(credentialDto);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.logOut(token);
    }

    @GetMapping(value = "/profile")
    public Consumer findProfile(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findProfile(token);
    }

    @PutMapping(value = "/subscription", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Consumer updateSubscription(@RequestHeader("Authorization") @NotBlank String token,
                                       @RequestBody @Valid SubscriptionDto subscriptionDto) throws Exception {
        return service.updateSubscription(subscriptionDto, token);
    }

    @GetMapping(value = "/products")
    public List<ProductWithoutBadgeDto> findInterestedProducts(@RequestHeader("Authorization")
                                                               @NotBlank String token) throws Exception {
        return service.findInterestedProducts(token);
    }

    @PostMapping(value = "/product/badge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto updateBadge(@RequestHeader("Authorization") @NotBlank String token,
                                           @RequestBody @Valid BadgeDto badgeDto) throws Exception {
        return service.updateBadge(badgeDto, token);
    }

    @GetMapping(value = "/product/{id}")
    public ProductWithBadgeDto findProductById(@RequestHeader("Authorization") @NotBlank String token,
                                               @PathVariable @NotNull Long id) throws Exception {
        return service.findProductWithBadge(id, token);
    }

    @PostMapping(value = "/product/manual/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto rateAManual(@RequestHeader("Authorization") @NotBlank String token,
                                           @RequestBody @Valid RatingDto ratingDto) throws Exception {
        return service.rateAManual(ratingDto, token);
    }

    @PostMapping(value = "/product/manual/note", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto addNoteOnManual(@RequestHeader("Authorization") @NotBlank String token,
                                               @RequestBody @Valid NoteDto noteDto) throws Exception {
        return service.addNoteOnManual(noteDto, token);
    }

    @GetMapping(value = "/product/{id}/service-providers")
    public List<ServiceProviderDto> findServiceProvidersByProductId(@RequestHeader("Authorization")
                                                                    @NotBlank String token,
                                                                    @PathVariable @NotNull Long id) throws Exception {
        return service.findServiceProvidersByProductId(id, token);
    }
}
