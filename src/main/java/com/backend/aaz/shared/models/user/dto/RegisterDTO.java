package com.backend.aaz.shared.models.user.dto;

import com.backend.aaz.shared.models.user.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
    
    @NotBlank String fullName,
    @NotBlank String username,
    @NotBlank String password,
    @NotBlank String email,
    @NotNull UserRole role

) {}