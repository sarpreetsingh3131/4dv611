package org.company.service.service;

import org.company.service.dto.CompanyDto;
import org.domain.dto.CredentialDto;
import org.domain.model.Company;
import org.domain.repository.CompanyRepository;
import org.domain.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private Authentication authentication;

    public Company save(CompanyDto companyDto) {
        return repository.save(new Company(
                companyDto.getName(), companyDto.getDescription(),
                companyDto.getUsername(), companyDto.getPassword()
        ));
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No company with id = " + id));
    }

    public String login(CredentialDto credentialDto) throws Exception {
        return authentication.assignToken(credentialDto, repository);
    }

    public Company findByToken(String token) throws Exception {
        return (Company) authentication.findUserByToken(token, repository);
    }
}
