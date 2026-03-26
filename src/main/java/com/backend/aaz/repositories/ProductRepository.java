package com.backend.aaz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.models.product.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
}