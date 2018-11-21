package org.consumer.service.service;

import org.domain.model.Consumer;
import org.domain.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    public Consumer signUp(Consumer consumer) {
        return repository.save(consumer);
    }

    public List<Consumer> findAll() {
        return repository.findAll();
    }
}
