package org.company.service.service;

import org.company.service.dao.CompanyDao;
import org.domain.dao.CredentialDao;
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

    public Company save(CompanyDao companyDao) {
        return repository.save(companyDaoToCompany(new Company(), companyDao));
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No company with id = " + id));
    }

    public String login(CredentialDao credentialDao) throws Exception {
        return authentication.login(credentialDao, repository);
    }

    public String validateAuthorization(String token) throws Exception {
        return authentication.validateAuthorization(token, repository);
    }

    public Company findByUsername(String username) throws Exception {
        return repository.findByUsername(username)
                .orElseThrow(() -> new Exception("No company with username = " + username));
    }

    private Company companyDaoToCompany(Company company, CompanyDao companyDao) {
        company.setName(companyDao.getName());
        company.setDescription(companyDao.getDescription());
        company.setUsername(companyDao.getUsername());
        company.setPassword(companyDao.getPassword());
        return company;
    }
}
