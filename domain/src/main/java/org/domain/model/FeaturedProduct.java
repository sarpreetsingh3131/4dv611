package org.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class FeaturedProduct {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NonNull
    private Product product;
}
