package org.domain.repository;

import org.domain.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Long> {

    List<Representative> findByCompanyUsername(String username);

    Representative findByUsernameAndPassword(String username, String password);

    Representative findByUsername(String username);
}
