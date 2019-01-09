package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @Column(unique = true, nullable = false)
    private String username;

    @NonNull
    @Column(nullable = false)
    @JsonIgnore
    private String password;
}
