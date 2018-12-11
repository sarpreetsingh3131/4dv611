package org.domain.converter;

import org.domain.dto.ManualDto;
import org.domain.model.Manual;
import org.domain.model.Rating;
import org.domain.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

@Component
public class ManualConverter {

    @Autowired
    private RatingRepository repository;

    public ManualDto manualToManualDto(Manual manual) {
        return new ManualDto(manual.getId(), manual.getUrl(), manual.getDescription(), findRating(manual));
    }

    public List<ManualDto> manualToManualDtos(List<Manual> manuals) {
        List<ManualDto> manualDtos = new LinkedList<>();
        for (Manual manual : manuals) {
            manualDtos.add(manualToManualDto(manual));
        }
        return manualDtos;
    }

    private Double findRating(Manual manual) {
        OptionalDouble average = repository.findByManualId(manual.getId())
                .stream()
                .mapToInt(Rating::getRating)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
