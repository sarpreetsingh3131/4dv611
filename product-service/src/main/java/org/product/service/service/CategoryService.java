package org.product.service.service;

import org.domain.dto.CreateCategoryDto;
import org.domain.model.Category;
import org.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(CreateCategoryDto createCategoryDto) {
        return repository.save((new Category(createCategoryDto.getName())));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No category with id = " + id));
    }
}
