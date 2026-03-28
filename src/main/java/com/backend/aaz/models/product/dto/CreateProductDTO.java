package com.backend.aaz.models.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(

    @NotBlank
    String name,
    
    @NotBlank
    String description,

    @NotBlank
    String imageUrl,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal sellingPrice

) {}