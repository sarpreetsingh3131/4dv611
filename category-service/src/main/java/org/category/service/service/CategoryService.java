package org.category.service.service;

import org.category.service.dto.CategoryDto;
import org.domain.model.Category;
import org.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(CategoryDto categoryDto) {
        return repository.save((new Category(categoryDto.getName())));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new Exception("No category with id = " + id));
    }
}
