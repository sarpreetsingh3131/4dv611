package org.domain.service;

import org.domain.dto.CreateAdvertisementDto;
import org.domain.model.AdAgent;
import org.domain.model.Advertisement;
import org.domain.repository.AdvertisementRepository;
import org.domain.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private FileHandler fileHandler;

    public Advertisement save(CreateAdvertisementDto createAdvertisementDto, AdAgent adAgent) throws Exception {
        return advertisementRepository.save(new Advertisement(
                fileHandler.writeFile(createAdvertisementDto.getImage()),
                createAdvertisementDto.getTitle(), 0, adAgent
        ));
    }

    public List<Advertisement> findByAgentId(Long id) {
        return advertisementRepository.findByAdAgentId(id);
    }

    public Advertisement findRandomAdvertisement() throws Exception {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        if (advertisements.isEmpty()) {
            throw new Exception("no advertisement found");
        }
        Advertisement advertisement = advertisements.get(new Random().nextInt(advertisements.size()));
        advertisement.setViews(advertisement.getViews() + 1);
        return advertisementRepository.save(advertisement);
    }
}
