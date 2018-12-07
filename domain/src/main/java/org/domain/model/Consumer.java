package org.domain.model;

import lombok.Data;

import javax.persistence.*;
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

    @ManyToMany
    private List<Product> products;
}
