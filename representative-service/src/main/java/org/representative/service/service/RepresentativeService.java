package org.representative.service.service;

import org.domain.model.Company;
import org.domain.model.Representative;
import org.domain.repository.RepresentativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativeRepository representativeRepository;


    public Representative save(Representative representative, String companyId) {
        representative.setCompany(new Company(new Long(companyId)));
        return representativeRepository.save(representative);
    }

    public List<Representative> findByCompanyId(String companyId) {
        return representativeRepository.findByCompanyId(new Long(companyId));
    }
}
