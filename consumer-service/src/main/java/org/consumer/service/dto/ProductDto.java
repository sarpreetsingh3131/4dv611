package org.consumer.service.dto;

import lombok.Data;
import org.domain.model.Image;
import org.domain.model.Manual;

import java.util.List;

@Data
public class ProductDto {

    private Long id;
    private String name;
    private String model;
    private Long categoryId;
    private Image primaryImage;
    private List<Image> secondaryImages;
    private List<Manual> manuals;
    private Boolean hasBadge;
}
