package org.ad.agent.service.service;

import org.domain.dto.CreateAdvertisementDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Advertisement;
import org.domain.repository.AdvertisementRepository;
import org.domain.service.AdvertisementService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdAgentService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private UserService userService;

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsAdAgent(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsAdAgent(token);
    }

    public Advertisement saveAdvertisement(CreateAdvertisementDto createAdvertisementDto, String token) throws Exception {
        Advertisement advertisement = advertisementService.save(createAdvertisementDto, userService.findAdAgent(token));
        return advertisement;
    }

    public List<Advertisement> getAllAdAgentAdvertisements(String token) throws Exception {
        return advertisementService.getAllAgentAdvertisements(userService.findAdAgent(token));
    }
}
