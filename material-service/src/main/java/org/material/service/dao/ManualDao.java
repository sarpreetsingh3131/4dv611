package org.material.service.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@NoArgsConstructor
public class ManualDao {

    @Getter
    @Setter
    private Long id;

    @Setter
    @NotBlank(message = "Manual data is missing")
    private String data;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    @NotBlank(message = "Manual description is missing")
    private String description;

    public String getData() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
