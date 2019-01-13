package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class CommentDto {

    @NonNull
    private Long id;

    @NonNull
    private String text;

    @NonNull
    private String consumerName;
}
