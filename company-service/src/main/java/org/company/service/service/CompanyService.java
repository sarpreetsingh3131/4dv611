package org.company.service.service;

import org.domain.model.Company;
import org.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No company with id = " + id));
    }
}
