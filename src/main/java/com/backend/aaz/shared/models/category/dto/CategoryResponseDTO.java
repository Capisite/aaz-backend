package com.backend.aaz.shared.models.category.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.aaz.shared.models.category.Category;

public record CategoryResponseDTO(
    
    UUID id,
    String name,
    LocalDateTime createdAt

) {
    public static CategoryResponseDTO from(Category category) {
        return new CategoryResponseDTO(
            category.getId(),
            category.getName(),
            category.getCreatedAt()
        );
    }
}