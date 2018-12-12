package org.representative.service.controller;

import org.domain.model.Representative;
import org.domain.service.UserService;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/representative", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<Representative> findByCompany(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findByCompany(userService.findCompany(token));
    }
}
