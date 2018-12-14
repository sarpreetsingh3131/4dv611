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
public class ServiceProvider {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(unique = true, nullable = false)
    private String username;

    @NonNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @NonNull
    @Column(nullable = false)
    private Boolean authorization;

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Company company;
}
