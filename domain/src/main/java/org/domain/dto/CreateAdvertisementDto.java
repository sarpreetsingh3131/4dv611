package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAdvertisementDto {

    @NotNull(message = "image is missing")
    private CreateImageDto image;

    @NotBlank(message = "title is missing")
    private String title;
}
