package com.backend.aaz.modules.products.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.aaz.shared.models.product.Product;
import com.backend.aaz.shared.models.product.enums.ProductStatus;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    
    @Query("SELECT p FROM Product p WHERE " +
       "(:status IS NULL OR p.status = :status) AND " +
       "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
       "(:search IS NULL OR LOWER(p.name) LIKE LOWER(CAST(:search AS string)) " +
       "OR LOWER(p.barcode) LIKE LOWER(CAST(:search AS string)))")
    List<Product> findAllBySearch(
        @Param("search") String search, 
        @Param("categoryId") UUID categoryId, 
        @Param("status") ProductStatus status
    );
    
}