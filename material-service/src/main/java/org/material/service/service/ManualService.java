package org.material.service.service;

import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.repository.ManualRepository;
import org.material.service.dto.ManualDto;
import org.material.service.utils.FileHandler;
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

    public Manual save(ManualDto manualDto, Product product) throws Exception {
        return repository.save((new Manual(fileHandler.writeFile(manualDto), manualDto.getDescription(), product)));
    }

    public List<Manual> saveAll(List<ManualDto> manualDtos, Product product) throws Exception {
        List<Manual> manuals = new LinkedList<>();
        for (ManualDto manualDto : manualDtos) {
            manuals.add(save(manualDto, product));
        }
        return manuals;
    }

    public Manual findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No manual with id " + id));
    }

    public Manual deleteById(Long id) throws Exception {
        Manual manual = findById(id);
        fileHandler.deleteFile(manual);
        repository.delete(manual);
        return manual;
    }
}
