package org.domain.model;

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
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;
}
