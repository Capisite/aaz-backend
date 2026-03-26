package com.backend.aaz.models.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.backend.aaz.models.product.Product;

public record ProductResponseDTO(

    UUID id,
    String name,
    String description,
    BigDecimal price,
    Integer quantity

) {
    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getQuantity()
        );
    }
}