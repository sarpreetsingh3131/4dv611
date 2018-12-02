package org.material.service.service;

import org.domain.model.Image;
import org.domain.repository.ImageRepository;
import org.material.service.dao.ImageDao;
import org.material.service.utils.FileHandler;
import org.product.service.service.ProductService;
import org.representative.service.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private RepresentativeService representativeService;

    public Image save(ImageDao imageDao, String token) throws Exception {
        representativeService.validateAuthorization(token);
        return repository.save(imageDaoToImage(new Image(), imageDao));
    }

    public List<Image> findByProductId(Long id) {
        return repository.findByProductId(id);
    }

    private Image imageDaoToImage(Image image, ImageDao imageDao) throws Exception {
        image.setUrl(FileHandler.writeFile(imageDao.getData(), imageDao.getExtension()));
        image.setProduct(productService.findById(imageDao.getProductId()));
        return image;
    }
}
