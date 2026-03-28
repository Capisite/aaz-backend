package com.backend.aaz.models.product.enums;

import lombok.Getter;

@Getter
public enum UnitOfMeasure {

    KG("Quilograma"),
    UNIT("Unidade"),
    LITRE("Litro");

    private String description;

    UnitOfMeasure(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}