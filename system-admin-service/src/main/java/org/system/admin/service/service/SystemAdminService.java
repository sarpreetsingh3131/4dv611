package org.system.admin.service.service;

import org.domain.dto.CreateAdAgentDto;
import org.domain.dto.CreateCategoryDto;
import org.domain.dto.CreateCompanyDto;
import org.domain.model.AdAgent;
import org.domain.model.Category;
import org.domain.model.Company;
import org.domain.repository.AdAgentRepository;
import org.domain.repository.CategoryRepository;
import org.domain.repository.CompanyRepository;
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
    private UserService userService;

    public Company saveCompany(CreateCompanyDto createCompanyDto) throws Exception {
        userService.verifyUsername(createCompanyDto.getUsername());
        return companyRepository.save(new Company(
                createCompanyDto.getName(), createCompanyDto.getDescription(),
                createCompanyDto.getUsername(), createCompanyDto.getPassword()));
    }

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public Company findCompanyById(Long id) throws Exception {
        return companyRepository.findById(id)
                .orElseThrow(() -> new Exception("no company with id = " + id));
    }

    public Category saveCategory(CreateCategoryDto createCategoryDto) {
        return categoryRepository.save(new Category(createCategoryDto.getName()));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("no category with id = " + id));
    }

    public AdAgent saveAdAgent(CreateAdAgentDto createAdAgentDto) throws Exception {
        userService.verifyUsername(createAdAgentDto.getUsername());
        return adAgentRepository.save(new AdAgent(
           createAdAgentDto.getName(), createAdAgentDto.getUsername(),
           createAdAgentDto.getPassword(), createAdAgentDto.getEmail()));
    }

    public List<AdAgent> findAllAdAgents() {
        return  adAgentRepository.findAll();
    }

    public AdAgent findAdAgentById(Long id) throws Exception {
        return adAgentRepository.findById(id)
                .orElseThrow(() -> new Exception("no ad agent with id = " + id));
    }
}
