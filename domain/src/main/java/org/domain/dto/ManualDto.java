package org.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
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
