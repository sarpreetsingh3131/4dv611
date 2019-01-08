package org.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAdvertisementDto {

    @NotBlank(message = "data is missing")
    private String data;

    @NotBlank(message = "text is missing")
    private String advertisementText;

    public String getBase64Binary() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
