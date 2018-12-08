package org.domain.repository;

import org.domain.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {

    List<Representative> findByCompanyId(Long id);

    Optional<Representative> findByUsernameAndPassword(String username, String password);

    Optional<Representative> findByUsername(String username);
}
