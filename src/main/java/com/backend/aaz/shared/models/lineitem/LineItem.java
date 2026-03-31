package com.backend.aaz.shared.models.lineitem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import com.backend.aaz.shared.models.product.Product;
import com.backend.aaz.shared.models.transaction.Transaction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "line_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false, updatable = false) // FKs geralmente não mudam
    private Transaction transaction;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, length = 120, name = "product_name", updatable = false)
    private String productName;

    @Column(nullable = false, precision = 10, scale = 2, name = "unit_price", updatable = false)
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.001", message = "Quantidade mínima é 0.001")
    @Max(value = 999, message = "Quantidade máxima é 999")
    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal quantity;

    @Column(nullable = false, precision = 10, scale = 2, name = "line_total")
    private BigDecimal lineTotal;

    @PrePersist
    public void ensureSnapshotAndCalculate() {
        if (this.productName == null && this.product != null) {
            this.productName = this.product.getName();
        }
        if (this.unitPrice == null && this.product != null) {
            this.unitPrice = this.product.getSellingPrice();
        }
        calculateTotal();
    }

    @PreUpdate
    public void calculateTotal() {
        if (unitPrice != null && quantity != null) {
            this.lineTotal = unitPrice.multiply(quantity)
                                      .setScale(2, RoundingMode.HALF_UP);
        }
    }
}