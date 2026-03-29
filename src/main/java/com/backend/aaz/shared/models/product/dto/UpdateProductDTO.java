package com.backend.aaz.shared.models.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.backend.aaz.shared.models.product.enums.ProductStatus;
import com.backend.aaz.shared.models.product.enums.UnitOfMeasure;
import com.backend.aaz.shared.validation.annotations.ValidBarcode;

import jakarta.validation.constraints.DecimalMin;

public record UpdateProductDTO(

    String name,
    String description,
    UUID categoryId,
    
    @ValidBarcode
    String barcode,
    
    UnitOfMeasure unitOfMeasure,
    String imageUrl,
    ProductStatus status,

    @DecimalMin(value = "0.01")
    BigDecimal sellingPrice

) {}