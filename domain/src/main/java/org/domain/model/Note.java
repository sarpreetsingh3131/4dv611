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
public class Note {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String note;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Manual manual;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Consumer consumer;
}
