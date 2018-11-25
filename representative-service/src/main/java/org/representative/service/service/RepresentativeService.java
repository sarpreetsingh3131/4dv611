package org.representative.service.service;

import org.domain.model.Representative;
import org.domain.repository.CompanyRepository;
import org.domain.repository.RepresentativeRepository;
import org.domain.utils.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private Authentication authentication;

    public Representative save(Representative representative, String token) throws AuthenticationException {
        String companyUsername = authentication.validateCompanyAuthorization(token, companyRepository);
        representative.setCompany(companyRepository.findByUsername(companyUsername));
        return representativeRepository.save(representative);
    }

    public List<Representative> findByCompany(String token) throws AuthenticationException {
        String companyUsername = authentication.validateCompanyAuthorization(token, companyRepository);
        return representativeRepository.findByCompanyUsername(companyUsername);
    }
}
