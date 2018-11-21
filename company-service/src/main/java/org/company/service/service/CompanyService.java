package org.company.service.service;

import org.domain.model.Company;
import org.domain.repository.CompanyRepository;
import org.domain.utils.Authentication;
import org.domain.utils.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private Authentication authentication;

    public Company save(Company company) {
        return repository.save(company);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(String id) {
        return repository.findById(new Long(id))
                .orElseThrow(() -> new EntityNotFoundException("No company with id = " + id));
    }

    public String login(Credentials credentials) {
        return authentication.companyLogin(credentials, repository);
    }
}
