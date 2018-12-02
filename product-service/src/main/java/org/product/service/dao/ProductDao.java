package org.product.service.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDao {

    @NotBlank(message = "Product name is missing")
    private String name;

    @NotBlank(message = "Product model is missing")
    private String model;

    @NotNull(message = "Product's category id is missing")
    private Long categoryId;
}
