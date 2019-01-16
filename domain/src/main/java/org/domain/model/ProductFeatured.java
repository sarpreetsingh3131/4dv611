package org.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
public class ProductFeatured {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @OneToOne
    @NonNull
    private Product productId;
}
