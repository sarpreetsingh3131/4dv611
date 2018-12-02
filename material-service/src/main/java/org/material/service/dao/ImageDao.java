package org.material.service.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class ImageDao {

    @Getter
    @Setter
    private Long id;

    @Setter
    private String data;

    @Getter
    @Setter
    private String url;

    public String getData() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
