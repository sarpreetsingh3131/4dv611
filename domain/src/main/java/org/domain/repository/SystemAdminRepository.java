package org.domain.repository;

import org.domain.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin, Long> {

    Optional<SystemAdmin> findByUsername(String username);

    Optional<SystemAdmin> findByUsernameAndPassword(String username, String password);
}
