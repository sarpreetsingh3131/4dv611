package org.domain.repository;

import org.domain.model.AdAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdAgentRepository extends JpaRepository<AdAgent, Long> {

    Optional<AdAgent> findByUsername(String username);
}
