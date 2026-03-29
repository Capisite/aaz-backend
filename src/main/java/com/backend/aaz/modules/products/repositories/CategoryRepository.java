package com.backend.aaz.modules.products.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.shared.models.category.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}