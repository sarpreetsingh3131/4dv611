package org.product.service.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductDao {

    @NotBlank
    private String name;

    @NotBlank
    private String model;

    @NotNull
    private List<ImageDao> images;

    @NotNull
    private List<ManualDao> manuals;

    @NotBlank
    private String categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ImageDao> getImages() {
        return images;
    }

    public void setImages(List<ImageDao> images) {
        this.images = images;
    }

    public List<ManualDao> getManuals() {
        return manuals;
    }

    public void setManuals(List<ManualDao> manuals) {
        this.manuals = manuals;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ProductDao{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", images=" + images +
                ", manuals=" + manuals +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
