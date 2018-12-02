package org.material.service.controller;

import org.domain.model.Manual;
import org.material.service.dao.ManualDao;
import org.material.service.service.ManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/manual", produces = "application/json")
@ResponseBody
public class ManualController {

    @Autowired
    private ManualService service;

    @PostMapping(consumes = "application/json")
    public Manual save(@RequestBody @Valid ManualDao manualDao,
                       @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(manualDao, token);
    }

    @GetMapping(value = "/product/{id}")
    public List<Manual> findByProductId(@PathVariable @NotNull Long id) {
        return service.findByProductId(id);
    }
}
