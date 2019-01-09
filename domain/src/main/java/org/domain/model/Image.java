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
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String url;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Product product;
}