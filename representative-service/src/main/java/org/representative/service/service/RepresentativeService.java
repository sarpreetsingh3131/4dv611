package org.representative.service.service;

import org.company.service.service.CompanyService;
import org.domain.dto.CredentialDto;
import org.domain.model.Representative;
import org.domain.repository.RepresentativeRepository;
import org.domain.service.UserService;
import org.domain.utils.Authentication;
import org.representative.service.dto.RepresentativeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentativeService {

    @Autowired
    private RepresentativeRepository repository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Authentication authentication;

    @Autowired
    private UserService userService;

    public Representative save(RepresentativeDto representativeDto, String token) throws Exception {
        userService.verifyUsername(representativeDto.getUsername());
        return repository.save(new Representative(
                representativeDto.getName(), representativeDto.getUsername(),
                representativeDto.getPassword(), companyService.findByToken(token)
        ));
    }

    public List<Representative> findByCompany(String token) throws Exception {
        return repository.findByCompanyId(companyService.findByToken(token).getId());
    }

    public String login(CredentialDto credentialDto) throws Exception {
        return authentication.assignToken(credentialDto, repository);
    }

    public Representative findByToken(String token) throws Exception {
        return (Representative) authentication.findUserByToken(token, repository);
    }
}
