package org.material.service.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
public class ImageDao {

    @NotBlank(message = "Image data is missing")
    @Setter
    private String data;

    @Getter
    @Setter
    @NotNull(message = "Image's product id is missing")
    private Long productId;

    public String getData() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
