package org.representative.service.controller;

import org.domain.model.Representative;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/representative", produces = "application/json")
@ResponseBody
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @PostMapping(consumes = "application/json")
    public Representative save(@RequestBody @Valid Representative representative,
                               @RequestHeader("Authorization") String token) throws AuthenticationException {
        return service.save(representative, token);
    }

    @GetMapping
    public List<Representative> findByCompany(@RequestHeader("Authorization") String token) throws AuthenticationException {
        return service.findByCompany(token);
    }
}
