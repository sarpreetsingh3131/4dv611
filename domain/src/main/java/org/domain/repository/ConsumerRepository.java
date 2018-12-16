package org.domain.repository;

import org.domain.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {

    Optional<Consumer> findByUsernameAndPassword(String username, String password);

    Optional<Consumer> findByUsername(String username);

    @Query("select distinct CONSUMER.EMAIL from CONSUMER inner join CONSUMER_PRODUCTS CP on CONSUMER.ID = CP.CONSUMER_ID\n" +
            " inner join PRODUCT P on CP.PRODUCTS_ID = P.ID\n" +
            " where CONSUMER.SUBSCRIPTION=true and P.COMPANY_ID=?1")
    List<String> findConsumerEmailsByProductsAndSubscriptionByCompanyId(String company_id);
}
