package org.domain.repository;

import org.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByUsernameAndPassword(String username, String password);

    Optional<Company> findByUsername(String username);
}
