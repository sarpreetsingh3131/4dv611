package org.domain.repository;

import org.domain.model.FeaturedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturedProductRepository extends JpaRepository<FeaturedProduct, Long> {

}
