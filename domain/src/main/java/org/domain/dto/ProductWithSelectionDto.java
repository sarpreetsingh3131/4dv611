package org.domain.dto;

import lombok.*;
import org.domain.model.Image;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ProductWithSelectionDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String model;

    @NonNull
    private Image primaryImage;

    @NonNull
    private List<Image> secondaryImages;

    @NonNull
    private List<ManualDto> manuals;

    @NonNull
    private Integer selection;
}
