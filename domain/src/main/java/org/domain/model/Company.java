package org.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Company name cannot be null or empty")
    @Column(unique = true)
    private String name;

    @NotNull(message = "Company description cannot be null or empty")
    private String description;

    @NotNull(message = "Company username cannot be null or empty")
    @Column(unique = true)
    private String username;

    @NotNull(message = "Company password cannot be null or empty")
    private String password;

    public Company() {

    }

    public Company(String name, String description, String username, String password) {
        this.name = name;
        this.description = description;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
