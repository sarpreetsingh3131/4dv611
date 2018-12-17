package org.consumer.service.service;

import org.domain.converter.ProductConverter;
import org.domain.dto.*;
import org.domain.model.*;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.NoteRepository;
import org.domain.repository.ProductRepository;
import org.domain.repository.RatingRepository;
import org.domain.service.ManualService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ManualService manualService;

    @Autowired
    private ProductConverter converter;

    public Consumer signUp(CreateConsumerDto createConsumerDto) throws Exception {
        userService.verifyUsername(createConsumerDto.getUsername());
        return consumerRepository.save(new Consumer(
                createConsumerDto.getName(), createConsumerDto.getUsername(),
                createConsumerDto.getPassword(), createConsumerDto.getEmail(),
                false, new LinkedList<>()));
    }

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsConsumer(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsConsumer(token);
    }

    public Consumer findProfile(String token) throws Exception {
        return userService.findConsumer(token);
    }

    public Consumer updateSubscription(SubscriptionDto subscriptionDto, String token) throws Exception {
        Consumer consumer = userService.findConsumer(token);
        consumer.setSubscription(subscriptionDto.getSubscription());
        return consumerRepository.save(consumer);
    }

    public List<ProductWithoutBadgeDto> findInterestedProducts(String token) throws Exception {
        return converter.toProductWithoutBadgeDto(userService.findConsumer(token).getProducts());
    }

    public ProductWithBadgeDto updateBadge(BadgeDto badgeDto, String token) throws Exception {
        Consumer consumer = userService.findConsumer(token);
        Product product = findProductById(badgeDto.getProductId());
        if (badgeDto.getBadge()) {
            consumer.getProducts().add(product);
        } else {
            consumer.getProducts().remove(product);
        }
        consumerRepository.save(consumer);
        return converter.toProductWithBadgeDto(product, consumer);
    }

    public ProductWithBadgeDto findProductWithBadge(Long id, String token) throws Exception {
        Consumer consumer = userService.findConsumer(token);
        Product product = findProductById(id);
        return converter.toProductWithBadgeDto(product, consumer);
    }

    public ProductWithBadgeDto rateAManual(RatingDto ratingDto, String token) throws Exception {
        Consumer consumer = userService.findConsumer(token);
        Rating rating = ratingRepository.save(new Rating(ratingDto.getRating(), consumer,
                manualService.findById(ratingDto.getManualId())));
        return converter.toProductWithBadgeDto(rating.getManual().getProduct(), consumer);
    }

    public ProductWithBadgeDto addNoteOnManual(NoteDto noteDto, String token) throws Exception {
        Consumer consumer = userService.findConsumer(token);
        Manual manual = manualService.findById(noteDto.getManualId());
        noteRepository.save(new Note(noteDto.getNote(), manual, consumer));
        return converter.toProductWithBadgeDto(manual.getProduct(), consumer);
    }

    private Product findProductById(Long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception("No product with id = " + id));
    }
}
