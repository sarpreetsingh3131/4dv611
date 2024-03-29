package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.domain.model.Image;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
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

    @NonNull
    private Integer views;
}
