package com.backend.aaz.models.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.backend.aaz.models.product.Product;
import com.backend.aaz.models.product.enums.ProductStatus;
import com.backend.aaz.models.product.enums.UnitOfMeasure;

public record ProductResponseDTO(

    UUID id,
    String name,
    String barcode,
    UnitOfMeasure unitOfMeasure,
    BigDecimal sellingPrice,
    Double currentStock,
    Boolean isLowStock,
    ProductStatus status

) {
    public static ProductResponseDTO from(Product product, Double currentStock, boolean isLowStock) {
        return new ProductResponseDTO(
            product.getId(),
            product.getName(),
            product.getBarcode(),
            product.getUnitOfMeasure(),
            product.getSellingPrice(),
            currentStock,
            isLowStock,
            product.getStatus()
        );
    }
}