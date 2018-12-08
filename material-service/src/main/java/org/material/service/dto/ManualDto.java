package org.material.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManualDto {

    private Long id;
    private String data;
    private String url;

    @NotBlank(message = "Manual description is missing")
    private String description;

    public String getBase64Binary() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
