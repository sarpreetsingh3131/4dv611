package org.domain.repository;

import org.domain.model.AdAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdAgentRepository extends JpaRepository<AdAgent, Long> {

}
