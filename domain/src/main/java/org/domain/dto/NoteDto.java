package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoteDto {

    @NotNull(message = "Note manual id is missing")
    private Long manualId;

    @NotNull(message = "Note note is missing")
    private String note;
}
