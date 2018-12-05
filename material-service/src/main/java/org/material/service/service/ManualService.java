package org.material.service.service;

import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.repository.ManualRepository;
import org.material.service.dao.ManualDao;
import org.material.service.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ManualService {

    @Autowired
    private ManualRepository repository;

    public Manual save(ManualDao manualDao, Product product) throws Exception {
        return repository.save(manualDaoToManual(new Manual(), manualDao, product));
    }

    public List<Manual> saveAll(List<ManualDao> manualDaos, Product product) throws Exception {
        List<Manual> manuals = new LinkedList<>();
        for (ManualDao manualDao : manualDaos) {
            manuals.add(save(manualDao, product));
        }
        return manuals;
    }

    public Manual findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No manual with id " + id));
    }

    public Manual deleteById(Long id) throws Exception {
        Manual manual = findById(id);
        FileHandler.deleteFile(manual.getUrl());
        repository.delete(manual);
        return manual;
    }

    private Manual manualDaoToManual(Manual manual, ManualDao manualDao, Product product) throws Exception {
        manual.setUrl(FileHandler.writeFile(manualDao.getData(), manualDao.getExtension()));
        manual.setDescription(manualDao.getDescription());
        manual.setProduct(product);
        return manual;
    }
}
