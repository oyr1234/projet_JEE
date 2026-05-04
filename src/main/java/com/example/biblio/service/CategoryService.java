package com.example.biblio.service;

import com.example.biblio.model.Category;
import com.example.biblio.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category update(Long id, Category category) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setNom(category.getNom());
        return repository.save(existing);
    }
}