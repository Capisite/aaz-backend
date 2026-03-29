package com.backend.aaz.shared.models.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.aaz.shared.models.user.User;
import com.backend.aaz.shared.models.user.enums.UserRole;

public record UserResponseDTO(

    UUID id,
    String name,
    String email,
    UserRole role,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
    
) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getIsActive(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}