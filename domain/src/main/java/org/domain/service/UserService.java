package org.domain.service;

import org.domain.dto.CreateCompanyDto;
import org.domain.dto.CreateConsumerDto;
import org.domain.dto.CreateRepresentativeDto;
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

import java.util.LinkedList;
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

    public Company save(CreateCompanyDto createCompanyDto) throws Exception {
        verifyUsername(createCompanyDto.getUsername());
        return companyRepository.save(new Company(
                createCompanyDto.getName(), createCompanyDto.getDescription(),
                createCompanyDto.getUsername(), createCompanyDto.getPassword()
        ));
    }

    public Representative save(CreateRepresentativeDto createRepresentativeDto, String token) throws Exception {
        verifyUsername(createRepresentativeDto.getUsername());
        return representativeRepository.save(new Representative(
                createRepresentativeDto.getName(), createRepresentativeDto.getUsername(),
                createRepresentativeDto.getPassword(), findCompany(token)
        ));
    }

    public Consumer signUp(CreateConsumerDto createConsumerDto) throws Exception {
        verifyUsername(createConsumerDto.getUsername());
        return consumerRepository.save(new Consumer(createConsumerDto.getName(), createConsumerDto.getUsername(),
                createConsumerDto.getPassword(), createConsumerDto.getEmail(), false,
                new LinkedList<>()));
    }

    public String loginAsCompany(CredentialDto credentialDto) throws Exception {
        companyRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String loginAsRepresentative(CredentialDto credentialDto) throws Exception {
        representativeRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String loginAsConsumer(CredentialDto credentialDto) throws Exception {
        consumerRepository.findByUsernameAndPassword(credentialDto.getUsername(), credentialDto.getPassword())
                .orElseThrow(() -> new Exception("Invalid credentials"));
        return jsonWebToken.assign(credentialDto);
    }

    public String logOut(String token) {
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

    private void verifyUsername(String username) throws Exception {
        Optional<Company> company = companyRepository.findByUsername(username);
        Optional<Representative> representative = representativeRepository.findByUsername(username);
        Optional<Consumer> consumer = consumerRepository.findByUsername(username);
        if (company.isPresent() || representative.isPresent() || consumer.isPresent()) {
            throw new Exception("User already exists with username = " + username);
        }
    }
}
