package org.domain.repository;

import org.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByUsernameAndPassword(String username, String password);

    Optional<Consumer> findByUsername(String username);

    List<Consumer> findBySubscription(Boolean subscription);
}
