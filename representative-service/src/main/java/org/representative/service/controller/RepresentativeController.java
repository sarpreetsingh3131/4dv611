package org.representative.service.controller;

import org.domain.dao.CredentialDao;
import org.domain.model.Representative;
import org.representative.service.dao.RepresentativeDao;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping(value = "/api/representative", produces = MediaType.APPLICATION_JSON_VALUE)
@ResponseBody
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Representative save(@RequestBody @Valid RepresentativeDao representativeDao,
                               @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(representativeDao, token);
    }

    @GetMapping
    public List<Representative> findByCompany(@RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.findByCompany(token);
    }

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody @Valid CredentialDao credentialDao) throws Exception {
        return service.login(credentialDao);
    }
}
