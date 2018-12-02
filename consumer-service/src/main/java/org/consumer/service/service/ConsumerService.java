package org.consumer.service.service;

import org.consumer.service.dao.ConsumerDao;
import org.domain.dao.CredentialDao;
import org.domain.model.Consumer;
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
        return authentication.consumerLogin(credentialDao, repository);
    }

    private Consumer consumerDaoToConsumer(Consumer consumer, ConsumerDao consumerDao) {
        consumer.setName(consumerDao.getName());
        consumer.setUsername(consumerDao.getUsername());
        consumer.setPassword(consumerDao.getPassword());
        return consumer;
    }
}
