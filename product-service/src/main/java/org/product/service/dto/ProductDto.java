package org.product.service.dto;

import lombok.Data;
import org.material.service.dto.ImageDto;
import org.material.service.dto.ManualDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDto {

    @NotBlank(message = "Product name is missing")
    private String name;

    @NotBlank(message = "Product model is missing")
    private String model;

    @NotNull(message = "Product's category id is missing")
    private Long categoryId;

    @NotNull(message = "Product's primary image is missing")
    private ImageDto primaryImage;

    @NotEmpty(message = "Product's secondary images are missing")
    private List<ImageDto> secondaryImages;

    @NotEmpty(message = "Product's manuals are missing")
    private List<ManualDto> manuals;
}
