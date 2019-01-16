package org.system.admin.service.service;

import org.domain.dto.*;
import org.domain.model.*;
import org.domain.repository.*;
import org.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdAgentRepository adAgentRepository;

    @Autowired
    private SystemAdminRepository systemAdminRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    public Company saveCompany(CreateCompanyDto createCompanyDto, String token) throws Exception {
        userService.findSystemAdmin(token);
        userService.verifyUsername(createCompanyDto.getUsername());
        return companyRepository.save(new Company(
                createCompanyDto.getName(), createCompanyDto.getDescription(),
                createCompanyDto.getUsername(), createCompanyDto.getPassword()
        ));
    }

    public List<Company> findAllCompanies(String token) throws Exception {
        userService.findSystemAdmin(token);
        return companyRepository.findAll();
    }

    public Company findCompanyById(Long id, String token) throws Exception {
        userService.findSystemAdmin(token);
        return companyRepository.findById(id)
                .orElseThrow(() -> new Exception("no company with id = " + id));
    }

    public Category saveCategory(CreateCategoryDto createCategoryDto, String token) throws Exception {
        userService.findSystemAdmin(token);
        return categoryRepository.save(new Category(createCategoryDto.getName()));
    }

    public AdAgent saveAdAgent(CreateAdAgentDto createAdAgentDto, String token) throws Exception {
        userService.findSystemAdmin(token);
        userService.verifyUsername(createAdAgentDto.getUsername());
        return adAgentRepository.save(new AdAgent(
                createAdAgentDto.getName(), createAdAgentDto.getUsername(),
                createAdAgentDto.getPassword(), createAdAgentDto.getEmail()
        ));
    }

    public List<AdAgent> findAllAdAgents(String token) throws Exception {
        userService.findSystemAdmin(token);
        return adAgentRepository.findAll();
    }

    public AdAgent findAdAgentById(Long id, String token) throws Exception {
        userService.findSystemAdmin(token);
        return adAgentRepository.findById(id)
                .orElseThrow(() -> new Exception("no ad agent with id = " + id));
    }

    public SystemAdmin saveSystemAdmin(CreateSystemAdminDto createSystemAdminDto) throws Exception {
        userService.verifyUsername(createSystemAdminDto.getUsername());
        return systemAdminRepository.save(new SystemAdmin(
                createSystemAdminDto.getName(), createSystemAdminDto.getUsername(),
                createSystemAdminDto.getPassword(), createSystemAdminDto.getEmail()
        ));
    }

    public String logIn(CredentialDto credentialDto) throws Exception {
        return userService.logInAsSystemAdmin(credentialDto);
    }

    public String logOut(String token) throws Exception {
        return userService.logOutAsSystemAdmin(token);
    }

    public CompaniesAndProductsDto countCompaniesAndProducts(String token) throws Exception {
        userService.findSystemAdmin(token);
        return new CompaniesAndProductsDto(
                companyRepository.findAll().size(), productRepository.findAll().size()
        );
    }

    public List<Advertisement> findAllAdvertisements(String token) throws Exception {
        userService.findSystemAdmin(token);
        return advertisementRepository.findAll();
    }
}
