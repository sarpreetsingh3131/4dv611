package org.domain.repository;

import org.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByUsernameAndPassword(String username, String password);

    Company findByUsername(String username);
}