package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
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
    @Column(nullable = false)
    private Integer views;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Product product;
}
