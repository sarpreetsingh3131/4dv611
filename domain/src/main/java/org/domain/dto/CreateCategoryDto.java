package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategoryDto {

    @NotBlank(message = "name is missing")
    private String name;
}