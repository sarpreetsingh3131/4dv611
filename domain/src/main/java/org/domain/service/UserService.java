package org.domain.service;

import org.domain.dto.CredentialDto;
import org.domain.model.Company;
import org.domain.model.Consumer;
import org.domain.model.Representative;
import org.domain.model.ServiceProvider;
import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.RepresentativeRepository;
import org.domain.repository.ServiceProviderRepository;
import org.domain.utils.JsonWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private JsonWebToken jsonWebToken;

    public String logInAsCompany(CredentialDto credentialDto) throws Exception {
        companyRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String logInAsRepresentative(CredentialDto credentialDto) throws Exception {
        representativeRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String logInAsConsumer(CredentialDto credentialDto) throws Exception {
        consumerRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String logInAsServiceProvider(CredentialDto credentialDto) throws Exception {
        serviceProviderRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String logOutAsCompany(String token) throws Exception {
        findCompany(token);
        return jsonWebToken.revoke(token);
    }

    public String logOutAsRepresentative(String token) throws Exception {
        findRepresentative(token);
        return jsonWebToken.revoke(token);
    }

    public String logOutAsConsumer(String token) throws Exception {
        findConsumer(token);
        return jsonWebToken.revoke(token);
    }

    public String logOutAsServiceProvider(String token) throws Exception {
        findServiceProvider(token);
        return jsonWebToken.revoke(token);
    }

    public Company findCompany(String token) throws Exception {
        return companyRepository.findByUsername(jsonWebToken.parse(token))
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public Representative findRepresentative(String token) throws Exception {
        return representativeRepository.findByUsername(jsonWebToken.parse(token))
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public Consumer findConsumer(String token) throws Exception {
        return consumerRepository.findByUsername(jsonWebToken.parse(token))
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public ServiceProvider findServiceProvider(String token) throws Exception {
        return serviceProviderRepository.findByUsername(jsonWebToken.parse(token))
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public void verifyUsername(String username) throws Exception {
        Optional<Company> company = companyRepository.findByUsername(username);
        Optional<Representative> representative = representativeRepository.findByUsername(username);
        Optional<Consumer> consumer = consumerRepository.findByUsername(username);
        Optional<ServiceProvider> serviceProvider = serviceProviderRepository.findByUsername(username);
        if (company.isPresent() || representative.isPresent() || consumer.isPresent() || serviceProvider.isPresent()) {
            throw new Exception("User already exists with username = " + username);
        }
    }
}
