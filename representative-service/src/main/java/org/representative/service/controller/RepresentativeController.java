package org.representative.service.controller;

import org.domain.model.Representative;
import org.domain.utils.Credentials;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/representative", produces = "application/json")
@ResponseBody
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @PostMapping(consumes = "application/json")
    public Representative save(@RequestBody @Valid Representative representative,
                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(representative, token);
    }

    @GetMapping
    public List<Representative> findByCompany(@RequestHeader("Authorization") @NotBlank String token)
            throws Exception {
        return service.findByCompany(token);
    }

    @PutMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody @Valid Credentials credentials) throws Exception {
        return service.login(credentials);
    }
}
