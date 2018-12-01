package org.category.service.service;

import org.domain.model.Category;
import org.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(String id) throws Exception {
        return repository.findById(new Long(id))
                .orElseThrow(() -> new Exception("No category with id = " + id));
    }
}
