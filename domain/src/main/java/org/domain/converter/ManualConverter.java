package org.domain.converter;

import org.domain.dto.ManualDto;
import org.domain.dto.ManualWithNoteDto;
import org.domain.model.Consumer;
import org.domain.model.Manual;
import org.domain.model.Rating;
import org.domain.repository.NoteRepository;
import org.domain.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;

@Component
public class ManualConverter {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private NoteRepository noteRepository;

    public ManualDto manualToManualDto(Manual manual) {
        return new ManualDto(manual.getId(), manual.getUrl(), manual.getDescription(), findRating(manual), manual.getViews());
    }

    public List<ManualDto> manualToManualDtos(List<Manual> manuals) {
        List<ManualDto> manualDtos = new LinkedList<>();
        manuals.forEach(manual -> manualDtos.add(manualToManualDto(manual)));
        return manualDtos;
    }

    public ManualWithNoteDto manualToManualWithNoteDto(Manual manual, Consumer consumer) {
        return new ManualWithNoteDto(manual.getId(), manual.getUrl(),
                manual.getDescription(), findRating(manual), manual.getViews(),
                noteRepository.findByIdAndConsumerId(manual.getId(), consumer.getId()));
    }

    public List<ManualWithNoteDto> manualToManualWithNoteDtos(List<Manual> manuals, Consumer consumer) {
        List<ManualWithNoteDto> manualWithNoteDtos = new LinkedList<>();
        manuals.forEach(manual -> manualWithNoteDtos.add(manualToManualWithNoteDto(manual, consumer)));
        return manualWithNoteDtos;
    }

    private Double findRating(Manual manual) {
        OptionalDouble average = ratingRepository.findByManualId(manual.getId())
                .stream()
                .mapToInt(Rating::getRating)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }
}
