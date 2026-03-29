package com.backend.aaz.modules.products.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.aaz.shared.exceptions.CategoryNotFoundException;
import com.backend.aaz.modules.products.repositories.CategoryRepository;
import com.backend.aaz.shared.models.category.Category;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category create(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    public Category update(UUID id, String name) {
        Category category = getById(id);
        category.setName(name);
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }
    
}