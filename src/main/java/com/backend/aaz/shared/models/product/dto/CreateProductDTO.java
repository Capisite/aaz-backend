package com.backend.aaz.shared.models.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.backend.aaz.shared.models.product.enums.UnitOfMeasure;
import com.backend.aaz.shared.validation.annotations.ValidBarcode;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(

    @NotBlank
    String name,
    
    String description,

    @NotNull
    UUID categoryId,

    @NotBlank
    @ValidBarcode
    String barcode,

    @NotNull
    UnitOfMeasure unitOfMeasure,

    String imageUrl,

    @NotNull
    @DecimalMin(value = "0.01")
    BigDecimal sellingPrice

) {}