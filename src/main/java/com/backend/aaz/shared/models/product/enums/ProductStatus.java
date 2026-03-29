package com.backend.aaz.shared.models.product.enums;

public enum ProductStatus {
    ACTIVE("Ativo"),
    ARCHIVED("Arquivado");

    private String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}