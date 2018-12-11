package org.consumer.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.CreateConsumerDto;
import org.domain.dto.ProductWithoutBadgeDto;
import org.domain.dto.SubscriptionDto;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.domain.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ProductConverter converter;

    public Consumer signUp(CreateConsumerDto createConsumerDto) {
        return repository.save(new Consumer(createConsumerDto.getName(), createConsumerDto.getUsername(),
                createConsumerDto.getPassword(), createConsumerDto.getEmail(), false,
                new LinkedList<>()));
    }

    public Consumer subscription(Consumer consumer, SubscriptionDto subscriptionDto) {
        consumer.setSubscription(subscriptionDto.getSubscription());
        return repository.save(consumer);
    }

    public List<ProductWithoutBadgeDto> findProducts(Consumer consumer) {
        return converter.toProductWithoutBadgeDto(consumer.getProducts());
    }

    public Consumer updateBadge(Consumer consumer, Product product, Boolean badge) {
        if (badge) {
            consumer.getProducts().add(product);
        } else {
            consumer.getProducts().remove(product);
        }
        return repository.save(consumer);
    }

    public List<Consumer> findAll() {
        return repository.findAll();
    }
}
