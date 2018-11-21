package org.representative.service.controller;

import org.domain.model.Representative;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/representative", produces = "application/json")
@ResponseBody
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @PostMapping(value = "/{companyId}", consumes = "application/json")
    public Representative save(@RequestBody @Valid Representative representative, @PathVariable @NotNull String companyId) {
        return service.save(representative, companyId);
    }

    @GetMapping("/{companyId}")
    public List<Representative> findByCompanyId(@PathVariable String companyId) {
        return service.findByCompanyId(companyId);
    }
}
