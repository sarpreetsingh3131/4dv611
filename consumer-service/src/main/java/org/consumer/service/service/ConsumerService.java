package org.consumer.service.service;

import org.domain.model.Consumer;
import org.domain.repository.ConsumerRepository;
import org.domain.utils.Authentication;
import org.domain.utils.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private Authentication authentication;

    public Consumer signUp(Consumer consumer) {
        return repository.save(consumer);
    }

    public String login(Credentials credentials) {
        return authentication.consumerLogin(credentials, repository);
    }
}
