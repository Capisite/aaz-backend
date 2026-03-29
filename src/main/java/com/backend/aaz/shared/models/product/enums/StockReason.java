package com.backend.aaz.shared.models.product.enums;

public enum StockReason {
    SALE("Venda"),
    VOID_RESTORE("Restauração de void"),
    IMPORT("Importação"),
    MANUAL_ADJUSTMENT("Ajuste manual");

    private final String stockReason;

    StockReason(String stockReason) {
        this.stockReason = stockReason;
    }

    String getStockReason() {
        return stockReason;
    }
}