package org.material.service.service;

import org.domain.model.Image;
import org.domain.model.Product;
import org.domain.repository.ImageRepository;
import org.material.service.dto.ImageDto;
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

    public Image save(ImageDto imageDto, Product product) throws Exception {
        return repository.save((new Image(fileHandler.writeFile(imageDto), product)));
    }

    public List<Image> saveAll(List<ImageDto> imageDtos, Product product) throws Exception {
        List<Image> images = new LinkedList<>();
        for (ImageDto imageDto : imageDtos) {
            images.add(save(imageDto, product));
        }
        return images;
    }

    public Image findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No image with id " + id));
    }

    public Image deleteById(Long id) throws Exception {
        Image image = findById(id);
        fileHandler.deleteFile(image);
        repository.delete(image);
        return image;
    }
}
