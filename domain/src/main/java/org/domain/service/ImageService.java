package org.domain.service;

import org.domain.dto.CreateImageDto;
import org.domain.model.Image;
import org.domain.model.Product;
import org.domain.model.Representative;
import org.domain.repository.ImageRepository;
import org.domain.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FileHandler fileHandler;

    public Image save(CreateImageDto createImageDto, Product product) throws Exception {
        return imageRepository.save((new Image(fileHandler.writeFile(createImageDto), product)));
    }

    public List<Image> saveAll(List<CreateImageDto> createImageDtos, Product product) throws Exception {
        List<Image> images = new LinkedList<>();
        for (CreateImageDto imageRequestDto : createImageDtos) {
            images.add(save(imageRequestDto, product));
        }
        return images;
    }

    public Image findById(Long id) throws Exception {
        return imageRepository.findById(id)
                .orElseThrow(() -> new Exception("no image with id = " + id));
    }

    public Image deleteById(Long id, Representative representative) throws Exception {
        Image image = findById(id);
        if (imageRepository.findByProductCompanyId(representative.getCompany().getId()).contains(image)) {
            fileHandler.deleteFile(image);
            imageRepository.delete(image);
            return image;
        }
        throw new Exception("unauthorized representative");
    }
}
