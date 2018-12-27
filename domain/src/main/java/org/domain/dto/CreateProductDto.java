package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateProductDto {

    @NotBlank(message = "name is missing")
    private String name;

    @NotBlank(message = "model is missing")
    private String model;

    @NotNull(message = "categoryId is missing")
    private Long categoryId;

    @NotNull(message = "primaryImage is missing")
    private CreateImageDto primaryImage;

    @NotNull(message = "secondaryImages are missing")
    private List<CreateImageDto> secondaryImages;

    @NotNull(message = "manuals are missing")
    private List<CreateManualDto> manuals;
}
