package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateProductDto {

    @NotBlank(message = "Product name is missing")
    private String name;

    @NotBlank(message = "Product model is missing")
    private String model;

    @NotNull(message = "Product category id is missing")
    private Long categoryId;

    @NotNull(message = "Product primary image is missing")
    private CreateImageDto primaryImage;

    @NotNull(message = "Product secondary images are missing")
    private List<CreateImageDto> secondaryImages;

    @NotNull(message = "Product manuals are missing")
    private List<CreateManualDto> manuals;
}
