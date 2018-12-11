package org.domain.service;

import org.domain.dto.CredentialDto;
import org.domain.model.Company;
import org.domain.model.Consumer;
import org.domain.model.Representative;
import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.RepresentativeRepository;
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
    private JsonWebToken jsonWebToken;


    public void verifyUsername(String username) throws Exception {
        Optional<Company> company = companyRepository.findByUsername(username);
        Optional<Representative> representative = representativeRepository.findByUsername(username);
        Optional<Consumer> consumer = consumerRepository.findByUsername(username);
        if (company.isPresent() || representative.isPresent() || consumer.isPresent()) {
            throw new Exception("User already exists with username = " + username);
        }
    }

    public String loginAsCompany(CredentialDto credentialDto) throws Exception {
        companyRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.build(credentialDto);
    }

    public String loginAsRepresentative(CredentialDto credentialDto) throws Exception {
        representativeRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.build(credentialDto);
    }

    public String loginAsConsumer(CredentialDto credentialDto) throws Exception {
        consumerRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.build(credentialDto);
    }

    public Company findCompany(String token) throws Exception {
        return companyRepository.findByUsername(jsonWebToken.parse(token).getId())
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public Representative findRepresentative(String token) throws Exception {
        return representativeRepository.findByUsername(jsonWebToken.parse(token).getId())
                .orElseThrow(() -> new Exception("Invalid token"));
    }

    public Consumer findConsumer(String token) throws Exception {
        return consumerRepository.findByUsername(jsonWebToken.parse(token).getId())
                .orElseThrow(() -> new Exception("Invalid token"));
    }
}
