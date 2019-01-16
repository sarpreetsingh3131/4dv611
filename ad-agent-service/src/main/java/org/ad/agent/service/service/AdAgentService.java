package org.ad.agent.service.service;

import org.domain.dto.CreateAdvertisementDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Advertisement;
import org.domain.service.AdvertisementService;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdAgentService {

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
        return advertisementService.save(createAdvertisementDto, userService.findAdAgent(token));
    }

    public List<Advertisement> findAdvertisementsByAgentId(String token) throws Exception {
        return advertisementService.findByAgentId(userService.findAdAgent(token).getId());
    }
}
