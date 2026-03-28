package com.backend.aaz.models.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;

public record UpdateProductDTO(

    String name,
    String description,

    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal sellingPrice

) {}