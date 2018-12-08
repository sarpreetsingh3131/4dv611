package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
public class Manual {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String url;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Product product;
}
