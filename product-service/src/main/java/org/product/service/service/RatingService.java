package org.product.service.service;

import org.domain.dto.RatingDto;
import org.domain.model.Consumer;
import org.domain.model.Rating;
import org.domain.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository repository;

    @Autowired
    private ManualService manualService;

    public Rating save(Consumer consumer, RatingDto ratingDto) throws Exception {
        return repository.save(new Rating(ratingDto.getRating(), consumer,
                manualService.findById(ratingDto.getManualId())));
    }
}
