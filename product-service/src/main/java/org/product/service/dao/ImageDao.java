package org.product.service.dao;

import javax.validation.constraints.NotBlank;

public class ImageDao {

    @NotBlank
    private String data;

    public String getData() {
        return data.split(",")[1];
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getExtension() {
        return "." + data.split(",")[0].split("/")[1].split(";")[0];
    }

    @Override
    public String toString() {
        return "ImageDao{" +
                "data='" + data + '\'' +
                '}';
    }
}
