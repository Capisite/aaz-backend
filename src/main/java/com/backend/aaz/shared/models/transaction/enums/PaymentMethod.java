package com.backend.aaz.shared.models.transaction.enums;

public enum PaymentMethod {

    CASH("Dinheiro"),
    CARD("Cartão"),
    PIX("Pix"),
    OTHER("Outro");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}