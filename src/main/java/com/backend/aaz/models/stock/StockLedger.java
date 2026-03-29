package com.backend.aaz.models.stock;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.aaz.models.product.Product;
import com.backend.aaz.models.product.enums.StockReason;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_ledger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockLedger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Double delta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockReason reason;

    @Column
    private UUID referenceId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public StockLedger(Product product, Double delta, StockReason reason, UUID referenceId) {
        this.product = product;
        this.delta = delta;
        this.reason = reason;
        this.referenceId = referenceId;
        this.createdAt = LocalDateTime.now();
    }
    
}