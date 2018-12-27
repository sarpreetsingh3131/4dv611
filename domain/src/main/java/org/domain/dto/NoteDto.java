package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NoteDto {

    @NotNull(message = "manualId is missing")
    private Long manualId;

    @NotNull(message = "note is missing")
    private String note;
}
