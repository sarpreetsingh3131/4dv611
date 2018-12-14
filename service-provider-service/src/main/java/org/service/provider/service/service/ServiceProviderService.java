package org.service.provider.service.service;

import org.domain.dto.CredentialDto;
import org.domain.repository.ServiceProviderRepository;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private UserService userService;

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsServiceProvider(credentialDto);
    }
}
