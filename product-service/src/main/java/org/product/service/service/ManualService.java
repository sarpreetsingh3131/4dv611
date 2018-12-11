package org.product.service.service;

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
    private ManualRepository repository;

    @Autowired
    private FileHandler fileHandler;

    public Manual save(CreateManualDto createManualDto, Product product) throws Exception {
        return repository.save((new Manual(
                fileHandler.writeFile(createManualDto), createManualDto.getDescription(), product)));
    }

    public List<Manual> saveAll(List<CreateManualDto> createManualDtos, Product product) throws Exception {
        List<Manual> manuals = new LinkedList<>();
        for (CreateManualDto manualRequestDto : createManualDtos) {
            manuals.add(save(manualRequestDto, product));
        }
        return manuals;
    }

    public Manual findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No manual with id " + id));
    }

    public Manual deleteById(Long id, Representative representative) throws Exception {
        Manual manual = findById(id);
        if (!repository.findByProductCompanyId(representative.getCompany().getId()).contains(manual)) {
            throw new Exception("Unauthorized representative");
        }
        fileHandler.deleteFile(manual);
        repository.delete(manual);
        return manual;
    }
}
