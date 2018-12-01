package org.product.service.service;

import org.domain.model.Manual;
import org.domain.model.Product;
import org.domain.repository.ManualRepository;
import org.domain.utils.FileHandler;
import org.product.service.dao.ManualDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ManualService {

    @Autowired
    private ManualRepository manualRepository;

    public Manual save(ManualDao manualDao, Product product) throws Exception {
        String url = FileHandler.getManualURL(manualDao.getData(), manualDao.getExtension());
        url = "http://localhost:8080" + url.split("public")[1];
        return manualRepository.save(new Manual(url, manualDao.getDescription(), product));
    }

    public List<Manual> save(List<ManualDao> manualDaos, Product product) throws Exception {
        List<Manual> manuals = new LinkedList<>();
        for (ManualDao manualDao : manualDaos) {
            manuals.add(save(manualDao, product));
        }
        return manuals;
    }
}
