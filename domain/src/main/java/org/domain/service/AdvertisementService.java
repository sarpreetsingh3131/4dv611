package org.domain.service;

import org.domain.dto.CreateAdvertisementDto;
import org.domain.model.AdAgent;
import org.domain.model.Advertisement;
import org.domain.repository.AdvertisementRepository;
import org.domain.utils.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private FileHandler fileHandler;

    public Advertisement save(CreateAdvertisementDto createAdvertisementDto, AdAgent adAgent) throws Exception {
        return advertisementRepository.save((new Advertisement(
                fileHandler.writeFile(createAdvertisementDto),
                createAdvertisementDto.getAdvertisementText(),
                adAgent)
        ));
    }

    public List<Advertisement> getAllAgentAdvertisements(AdAgent adAgent) throws Exception {
        return advertisementRepository.findByAdAgentId(adAgent.getId());
    }
}
