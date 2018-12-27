package org.domain.dto;

import lombok.*;
import org.domain.model.Note;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ManualWithNoteDto {

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

    @NonNull
    private List<Note> notes;
}
