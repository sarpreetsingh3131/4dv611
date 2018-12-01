package org.product.service.dao;

import javax.validation.constraints.NotBlank;

public class ManualDao {

    @NotBlank
    private String data;

    @NotBlank
    private String description;

    public String getData() {
        return data.split(",")[1];
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }

    @Override
    public String toString() {
        return "ManualDao{" +
                "data='" + data + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
