package org.product.service.service;

import org.domain.model.Image;
import org.domain.model.Product;
import org.domain.repository.ImageRepository;
import org.domain.utils.FileHandler;
import org.product.service.dao.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(ImageDao imageDao, Product product) throws Exception {
        String url = FileHandler.getImageURL(imageDao.getData(), imageDao.getExtension());
        url = "http://localhost:8080" + url.split("public")[1];
        return imageRepository.save(new Image(url, product));
    }

    public List<Image> save(List<ImageDao> imageDaos, Product product) throws Exception {
        List<Image> images = new LinkedList<>();
        for (ImageDao imageDao : imageDaos) {
            images.add(save(imageDao, product));
        }
        return images;
    }
}
