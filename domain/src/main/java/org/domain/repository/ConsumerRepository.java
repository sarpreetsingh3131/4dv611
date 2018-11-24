package org.domain.repository;

import org.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Consumer findByUsernameAndPassword(String username, String password);

    Consumer findByUsername(String username);
}
