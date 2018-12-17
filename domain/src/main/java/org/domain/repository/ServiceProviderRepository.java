package org.domain.repository;

import org.domain.model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    Optional<ServiceProvider> findByUsername(String username);

    Optional<ServiceProvider> findByUsernameAndPassword(String username, String password);

    Optional<ServiceProvider> findByIdAndCompanyId(Long id, Long companyId);

    List<ServiceProvider> findByCompanyId(Long id);
}
