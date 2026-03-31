package com.backend.aaz.shared.models.transaction.enums;

public enum TransactionStatus {

    COMPLETED("Completada"),
    VOIDED("Anulada"),
    REFUNDED("Reembolsada");

    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}