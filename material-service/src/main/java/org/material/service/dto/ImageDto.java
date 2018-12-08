package org.material.service.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long id;
    private String data;
    private String url;

    public String getBase64Binary() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
