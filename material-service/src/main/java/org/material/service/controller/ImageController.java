package org.material.service.controller;

import org.domain.model.Image;
import org.material.service.dao.ImageDao;
import org.material.service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/api/image", produces = "application/json")
@ResponseBody
public class ImageController {

    @Autowired
    private ImageService service;

    @PostMapping(consumes = "application/json")
    public Image save(@RequestBody @Valid ImageDao imageDao,
                      @RequestHeader("Authorization") @NotBlank String token) throws Exception {
        return service.save(imageDao, token);
    }

    @GetMapping(value = "/product/{id}")
    public List<Image> findByProductId(@PathVariable @NotNull Long id) {
        return service.findByProductId(id);
    }
}
