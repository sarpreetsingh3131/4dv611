package org.material.service.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
public class ManualDao {

    @Setter
    @NotBlank(message = "Manual data is missing")
    private String data;

    @Getter
    @Setter
    @NotBlank(message = "Manual description is missing")
    private String description;

    @Getter
    @Setter
    @NotNull(message = "Manuals's product id is missing")
    private Long productId;

    public String getData() {
        return data.split(",")[1];
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }
}
