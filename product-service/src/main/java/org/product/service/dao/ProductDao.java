package org.product.service.dao;

import lombok.Data;
import org.material.service.dao.ImageDao;
import org.material.service.dao.ManualDao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDao {

    @NotBlank(message = "Product name is missing")
    private String name;

    @NotBlank(message = "Product model is missing")
    private String model;

    @NotNull(message = "Product's category id is missing")
    private Long categoryId;

    @NotNull(message = "Product's primary image is missing")
    private ImageDao primaryImage;

    @NotNull(message = "Product's secondary images are missing")
    private List<ImageDao> secondaryImages;

    @NotNull(message = "Product's manuals are missing")
    private List<ManualDao> manuals;
}
