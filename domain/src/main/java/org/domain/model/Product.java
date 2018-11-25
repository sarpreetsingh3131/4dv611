package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String model;

    @NotNull
    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @NotNull
    @OneToMany(mappedBy = "product")
    private List<Manual> manuals;

    @NotNull
    @OneToOne
    private Category category;

    @ManyToOne
    @JsonIgnore
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Manual> getManuals() {
        return manuals;
    }

    public void setManuals(List<Manual> manuals) {
        this.manuals = manuals;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", images=" + images +
                ", manuals=" + manuals +
                ", category=" + category +
                ", company=" + company +
                '}';
    }
}