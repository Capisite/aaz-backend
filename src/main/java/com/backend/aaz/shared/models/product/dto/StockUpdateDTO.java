package com.backend.aaz.shared.models.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockUpdateDTO(

    @NotNull
    @Min(1)
    Integer quantity

) {}