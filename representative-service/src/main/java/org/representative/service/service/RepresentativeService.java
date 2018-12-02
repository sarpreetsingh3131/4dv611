package org.representative.service.service;

import org.company.service.service.CompanyService;
import org.domain.dao.CredentialDao;
import org.domain.model.Representative;
import org.domain.repository.RepresentativeRepository;
import org.domain.utils.Authentication;
import org.representative.service.dao.RepresentativeDao;
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

    public Representative save(RepresentativeDao representativeDao, String token) throws Exception {
        String username = companyService.validateAuthorization(token);
        Representative representative = representativeDaoToRepresentative(new Representative(), representativeDao, username);
        return repository.save(representative);
    }

    public List<Representative> findByCompany(String token) throws Exception {
        String username = companyService.validateAuthorization(token);
        return repository.findByCompanyUsername(username);
    }

    public String login(CredentialDao credentialDao) throws Exception {
        return authentication.login(credentialDao, repository);
    }

    public Representative findByUsername(String username) throws Exception {
        return repository.findByUsername(username)
                .orElseThrow(() -> new Exception("No representative with username = " + username));
    }

    public String validateAuthorization(String token) throws Exception {
        return authentication.validateAuthorization(token, repository);
    }

    private Representative representativeDaoToRepresentative(Representative representative,
                                                             RepresentativeDao representativeDao,
                                                             String username) throws Exception {
        representative.setName(representativeDao.getName());
        representative.setUsername(representativeDao.getUsername());
        representative.setPassword(representativeDao.getPassword());
        representative.setCompany(companyService.findByUsername(username));
        return representative;
    }
}
