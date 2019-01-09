package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ManualDto {

    @NonNull
    private Long id;

    @NonNull
    private String url;

    @NonNull
    private String description;

    @NonNull
    private Double rating;

    @NonNull
    private Integer views;
}
