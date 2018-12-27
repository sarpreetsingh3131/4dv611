package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateManualDto {

    @NotBlank(message = "data is missing")
    private String data;

    @NotBlank(message = "description is missing")
    private String description;

    public String getBase64Binary() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
