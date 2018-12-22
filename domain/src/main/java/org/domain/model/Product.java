package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(unique = true, nullable = false)
    private String model;

    @NonNull
    @OneToOne
    private Category category;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Company company;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Image primaryImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Image> secondaryImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Manual> manuals;

    @NonNull
    @Column(nullable = false)
    private Integer views;
}