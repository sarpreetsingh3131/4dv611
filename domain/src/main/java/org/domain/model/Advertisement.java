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
public class Advertisement {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String imageUrl;

    @NonNull
    @Column(nullable = false)
    private String title;

    @NonNull
    @Column(nullable = false)
    private Integer views;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private AdAgent adAgent;
}
