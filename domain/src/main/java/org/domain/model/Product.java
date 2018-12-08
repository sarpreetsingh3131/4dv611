package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String model;

    @OneToOne
    private Category category;

    @ManyToOne
    @JsonIgnore
    private Company company;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Image primaryImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Image> secondaryImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Manual> manuals;
}