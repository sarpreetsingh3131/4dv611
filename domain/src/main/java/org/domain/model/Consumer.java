package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Consumer {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean subscription = false;

    @ManyToMany
    @JsonIgnore
    private List<Product> products = new LinkedList<>();
}
