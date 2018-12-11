package org.representative.service.service;

import org.domain.dto.CreateRepresentativeDto;
import org.domain.model.Company;
import org.domain.model.Representative;
import org.domain.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativeRepository repository;


    public Representative save(CreateRepresentativeDto createRepresentativeDto, Company company) {
        return repository.save(new Representative(
                createRepresentativeDto.getName(), createRepresentativeDto.getUsername(),
                createRepresentativeDto.getPassword(), company
        ));
    }

    public List<Representative> findByCompany(Company company) {
        return repository.findByCompanyId(company.getId());
    }
}
