package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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
