package org.consumer.service.service;

import org.consumer.service.dao.ConsumerDao;
import org.domain.dao.CredentialDao;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.domain.repository.ConsumerRepository;
import org.domain.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private Authentication authentication;

    public Consumer signUp(ConsumerDao consumerDao) {
        return repository.save(consumerDaoToConsumer(new Consumer(), consumerDao));
    }

    public String login(CredentialDao credentialDao) throws Exception {
        return authentication.login(credentialDao, repository);
    }

    public Consumer findByUsername(String username) throws Exception {
        return repository.findByUsername(username)
                .orElseThrow(() -> new Exception("No consumer with username = " + username));
    }

    public Consumer save(Consumer consumer) {
        return repository.save(consumer);
    }

    public String validateAuthorization(String token) throws Exception {
        return authentication.validateAuthorization(token, repository);
    }

    public Boolean hasBadge(String username, Product product) throws Exception {
        return findByUsername(username)
                .getProducts()
                .contains(product);
    }

    public Consumer findProfile(String token) throws Exception {
        String username = validateAuthorization(token);
        return findByUsername(username);
    }

    private Consumer consumerDaoToConsumer(Consumer consumer, ConsumerDao consumerDao) {
        consumer.setName(consumerDao.getName());
        consumer.setUsername(consumerDao.getUsername());
        consumer.setPassword(consumerDao.getPassword());
        consumer.setEmail(consumerDao.getEmail());
        return consumer;
    }
}
