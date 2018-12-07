package org.material.service.service;

import org.domain.model.Image;
import org.domain.model.Product;
import org.domain.repository.ImageRepository;
import org.material.service.dao.ImageDao;
import org.material.service.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private FileHandler fileHandler;

    public Image save(ImageDao imageDao, Product product) throws Exception {
        return repository.save(imageDaoToImage(new Image(), imageDao, product));
    }

    public List<Image> saveAll(List<ImageDao> imageDaos, Product product) throws Exception {
        List<Image> images = new LinkedList<>();
        for (ImageDao imageDao : imageDaos) {
            images.add(save(imageDao, product));
        }
        return images;
    }

    public Image findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No image with id " + id));
    }

    public Image deleteById(Long id) throws Exception {
        Image image = findById(id);
        fileHandler.deleteFile(image.getUrl());
        repository.delete(image);
        return image;
    }

    private Image imageDaoToImage(Image image, ImageDao imageDao, Product product) throws Exception {
        image.setUrl(fileHandler.writeFile(imageDao.getData(), imageDao.getExtension()));
        image.setProduct(product);
        return image;
    }
}
