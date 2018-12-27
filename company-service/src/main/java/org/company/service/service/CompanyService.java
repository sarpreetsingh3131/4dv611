package org.company.service.service;

import org.domain.dto.CreateRepresentativeDto;
import org.domain.dto.CreateServiceProviderDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Representative;
import org.domain.model.ServiceProvider;
import org.domain.repository.RepresentativeRepository;
import org.domain.repository.ServiceProviderRepository;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private UserService userService;

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsCompany(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsCompany(token);
    }

    public Representative saveRepresentative(CreateRepresentativeDto createRepresentativeDto,
                                             String token) throws Exception {
        userService.verifyUsername(createRepresentativeDto.getUsername());
        return representativeRepository.save(new Representative(
                createRepresentativeDto.getName(), createRepresentativeDto.getUsername(),
                createRepresentativeDto.getPassword(), userService.findCompany(token)));
    }

    public List<Representative> findAllRepresentatives(String token) throws Exception {
        return representativeRepository.findByCompanyId(userService.findCompany(token).getId());
    }

    public ServiceProvider saveServiceProvider(CreateServiceProviderDto createServiceProviderDto,
                                               String token) throws Exception {
        userService.verifyUsername(createServiceProviderDto.getUsername());
        return serviceProviderRepository.save(new ServiceProvider(
                createServiceProviderDto.getName(), createServiceProviderDto.getUsername(),
                createServiceProviderDto.getPassword(), createServiceProviderDto.getAuthorization(),
                userService.findCompany(token), createServiceProviderDto.getEmail(),
                createServiceProviderDto.getPhone()));
    }

    public List<ServiceProvider> findAllServiceProviders(String token) throws Exception {
        return serviceProviderRepository.findByCompanyId(userService.findCompany(token).getId());
    }
}
