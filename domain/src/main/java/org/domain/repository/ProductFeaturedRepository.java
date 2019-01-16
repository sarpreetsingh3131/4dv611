package org.domain.repository;

import org.domain.model.ProductFeatured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductFeaturedRepository extends JpaRepository<ProductFeatured, Long> {

    Optional<ProductFeatured> findFirstById();
}
