package org.domain.model;

import lombok.Data;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
public class Manual {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Product product;
}
