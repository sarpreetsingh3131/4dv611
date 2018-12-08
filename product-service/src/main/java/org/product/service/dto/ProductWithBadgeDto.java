package org.product.service.dto;

import lombok.Data;
import lombok.NonNull;
import org.domain.model.Category;
import org.domain.model.Image;
import org.domain.model.Manual;

import java.util.List;

@Data
public class ProductWithBadgeDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String model;

    @NonNull
    private Category category;

    @NonNull
    private Image primaryImage;

    @NonNull
    private List<Image> secondaryImages;

    @NonNull
    private List<Manual> manuals;

    @NonNull
    private Boolean hasBadge;
}
