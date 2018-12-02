package org.category.service.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDao {

    @NotBlank(message = "Category name is missing")
    private String name;
}
