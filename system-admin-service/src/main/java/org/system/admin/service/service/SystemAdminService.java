package org.system.admin.service.service;

import org.domain.dto.CreateCategoryDto;
import org.domain.dto.CreateCompanyDto;
import org.domain.model.Category;
import org.domain.model.Company;
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
                .orElseThrow(() -> new Exception("No company with id = " + id));
    }

    public Category saveCategory(CreateCategoryDto createCategoryDto) {
        return categoryRepository.save(new Category(createCategoryDto.getName()));
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) throws Exception {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new Exception("No category with id = " + id));
    }
}
