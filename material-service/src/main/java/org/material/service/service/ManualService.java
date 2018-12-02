package org.material.service.service;

import org.domain.model.Manual;
import org.domain.repository.ManualRepository;
import org.material.service.dao.ManualDao;
import org.material.service.utils.FileHandler;
import org.product.service.service.ProductService;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManualService {

    @Autowired
    private ManualRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RepresentativeService representativeService;

    public Manual save(ManualDao manualDao, String token) throws Exception {
        representativeService.validateAuthorization(token);
        return repository.save(manualDaoToManual(new Manual(), manualDao));
    }

    public List<Manual> findByProductId(Long id) {
        return repository.findByProductId(id);
    }

    private Manual manualDaoToManual(Manual manual, ManualDao manualDao) throws Exception {
        manual.setUrl(FileHandler.writeFile(manualDao.getData(), manualDao.getExtension()));
        manual.setDescription(manualDao.getDescription());
        manual.setProduct(productService.findById(manualDao.getProductId()));
        return manual;
    }
}
