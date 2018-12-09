package org.domain.service;

import org.domain.model.Company;
import org.domain.model.Consumer;
import org.domain.model.Representative;
import org.domain.repository.CompanyRepository;
import org.domain.repository.ConsumerRepository;
import org.domain.repository.RepresentativeRepository;
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


    public void verifyUsername(String username) throws Exception {
        Optional<Company> company = companyRepository.findByUsername(username);
        Optional<Representative> representative = representativeRepository.findByUsername(username);
        Optional<Consumer> consumer = consumerRepository.findByUsername(username);
        if (company.isPresent() || representative.isPresent() || consumer.isPresent()) {
            throw new Exception("User already exists with username = " + username);
        }
    }
}
