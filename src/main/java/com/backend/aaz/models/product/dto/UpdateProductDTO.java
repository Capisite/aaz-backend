package com.backend.aaz.models.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.backend.aaz.models.product.enums.ProductStatus;
import com.backend.aaz.models.product.enums.UnitOfMeasure;

import jakarta.validation.constraints.DecimalMin;

public record UpdateProductDTO(

    String name,
    String description,
    UUID categoryId,
    String barcode,
    UnitOfMeasure unitOfMeasure,
    String imageUrl,
    ProductStatus status,

    @DecimalMin(value = "0.01")
    BigDecimal sellingPrice

) {}