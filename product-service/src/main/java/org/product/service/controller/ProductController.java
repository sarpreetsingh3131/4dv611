package org.product.service.controller;

import org.domain.dto.*;
import org.domain.service.UserService;
import org.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithoutBadgeDto save(@RequestBody @Valid CreateProductDto createProductDto,
                                       @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(createProductDto, userService.findRepresentative(token));
    }

    @GetMapping(value = "/category/{id}")
    public List<ProductWithoutBadgeDto> findByCategoryId(@PathVariable @NotNull Long id) {
        return service.findByCategoryId(id);
    }

    @GetMapping
    public List<ProductWithoutBadgeDto> search(@RequestParam("search") @NotBlank String query) {
        return service.search(query);
    }

    @GetMapping(value = "/{id}")
    public Object findById(@RequestHeader(value = "Authorization", required = false) @NotBlank String token,
                           @PathVariable @NotNull Long id) throws Exception {
        return token == null
                ? service.findByIdWithoutBadge(id)
                : service.findByIdWithBadge(id, userService.findConsumer(token));
    }

    @GetMapping(value = "/latest")
    public List<ProductWithoutBadgeDto> findLatest() {
        return service.findLatest();
    }

    @DeleteMapping("/image/{id}")
    public ProductWithoutBadgeDto deleteImageById(@PathVariable @NotNull Long id,
                                                  @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.deleteImageById(id, userService.findRepresentative(token));
    }

    @DeleteMapping("/manual/{id}")
    public ProductWithoutBadgeDto deleteManualById(@PathVariable @NotNull Long id,
                                                   @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.deleteManualById(id, userService.findRepresentative(token));
    }

    @PostMapping(value = "/badge", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto updateBadge(@RequestBody @Valid BadgeDto badgeDto,
                                           @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.updateBadge(badgeDto, userService.findConsumer(token));
    }

    @GetMapping(value = "/selection")
    public List<ProductWithSelectionDto> findWithSelection(@RequestHeader("Authorization")
                                                           @NotBlank String token) throws Exception {
        return service.findWithSelection(userService.findRepresentative(token));
    }

    @PostMapping(value = "/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductWithBadgeDto rating(@RequestHeader("Authorization") @NotBlank String token,
                                      @RequestBody @Valid RatingDto ratingDto) throws Exception {
        return service.addRating(userService.findConsumer(token), ratingDto);
    }
}
