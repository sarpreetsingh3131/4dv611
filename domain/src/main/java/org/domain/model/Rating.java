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
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Integer rating;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Consumer consumer;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Manual manual;
}
