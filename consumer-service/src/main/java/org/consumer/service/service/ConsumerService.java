package org.consumer.service.service;

import org.consumer.service.dao.BadgeDao;
import org.consumer.service.dao.ConsumerDao;
import org.domain.dao.CredentialDao;
import org.domain.model.Consumer;
import org.domain.model.Product;
import org.domain.repository.ConsumerRepository;
import org.domain.utils.Authentication;
import org.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private Authentication authentication;

    public Consumer signUp(ConsumerDao consumerDao) {
        return repository.save(consumerDaoToConsumer(new Consumer(), consumerDao));
    }

    public String login(CredentialDao credentialDao) throws Exception {
        return authentication.login(credentialDao, repository);
    }

    private Consumer consumerDaoToConsumer(Consumer consumer, ConsumerDao consumerDao) {
        consumer.setName(consumerDao.getName());
        consumer.setUsername(consumerDao.getUsername());
        consumer.setPassword(consumerDao.getPassword());
        return consumer;
    }

    public Consumer validateAuthorizationAndGet(String token) throws Exception {
        String username = authentication.validateAuthorization(token, repository);
        Optional<Consumer> optionalConsumer = repository.findByUsername(username);
        return optionalConsumer.get();
    }

    public Product badge(BadgeDao badgeDao, String token) throws Exception {
        Consumer consumer = this.validateAuthorizationAndGet(token);
        Product product = productService.findById(badgeDao.getProductId());
        Boolean badge = badgeDao.getBadge();
        if(badge){
            consumer.getProducts().add(product);
        }
        else{
            consumer.getProducts().remove(product);
        }
        repository.save(consumer);
        return product;
    }
}
