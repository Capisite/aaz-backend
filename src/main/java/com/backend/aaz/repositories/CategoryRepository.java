package com.backend.aaz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.models.category.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    
}