package com.backend.aaz.shared.models.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(

    @NotBlank
    @Size(min = 1, max = 60)
    String name
    
) {}