package com.backend.aaz.modules.products.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.aaz.modules.products.services.CategoryService;
import com.backend.aaz.shared.models.category.Category;
import com.backend.aaz.shared.models.category.dto.CategoryRequestDTO;
import com.backend.aaz.shared.models.category.dto.CategoryResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAll() {
        List<CategoryResponseDTO> categories = categoryService.getAll()
                .stream()
                .map(CategoryResponseDTO::from)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable UUID id) {
        Category category = categoryService.getById(id);
        return ResponseEntity.ok(CategoryResponseDTO.from(category));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CategoryRequestDTO data) {
        Category category = categoryService.create(data.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryResponseDTO.from(category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CategoryRequestDTO data) {
        Category category = categoryService.update(id, data.name());
        return ResponseEntity.ok(CategoryResponseDTO.from(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}