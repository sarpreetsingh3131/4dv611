package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    @ManyToOne
    @JsonIgnore
    private Product product;
}