package org.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.domain.model.Note;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
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
