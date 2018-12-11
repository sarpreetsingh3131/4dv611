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
