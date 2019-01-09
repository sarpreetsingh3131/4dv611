package org.domain.service;

import org.domain.dto.CreateManualDto;
import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.model.Representative;
import org.domain.repository.ManualRepository;
import org.domain.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ManualService {

    @Autowired
    private ManualRepository manualRepository;

    @Autowired
    private FileHandler fileHandler;

    public Manual save(CreateManualDto createManualDto, Product product) throws Exception {
        return manualRepository.save((new Manual(
                fileHandler.writeFile(createManualDto),
                createManualDto.getDescription(), 0, product)));
    }

    public List<Manual> saveAll(List<CreateManualDto> createManualDtos, Product product) throws Exception {
        List<Manual> manuals = new LinkedList<>();
        for (CreateManualDto manualRequestDto : createManualDtos) {
            manuals.add(save(manualRequestDto, product));
        }
        return manuals;
    }

    public Manual findById(Long id) throws Exception {
        return manualRepository.findById(id)
                .orElseThrow(() -> new Exception("no manual with id " + id));
    }

    public Manual deleteById(Long id, Representative representative) throws Exception {
        Manual manual = findById(id);
        if (manualRepository.findByProductCompanyId(representative.getCompany().getId()).contains(manual)) {
            fileHandler.deleteFile(manual);
            manualRepository.delete(manual);
            return manual;
        }
        throw new Exception("unauthorized representative");
    }

    public Manual updateManualViews(Long id) throws Exception {
        Manual manual = findById(id);
        manual.setViews(manual.getViews() + 1);
        return manualRepository.save(manual);
    }
}
