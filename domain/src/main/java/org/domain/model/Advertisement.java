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
public class Advertisement {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String url;

    @NonNull
    @Column(nullable = false)
    private String advertisementText;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private AdAgent adAgent;
}
