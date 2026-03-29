package com.backend.aaz.shared.models.stock;

import java.time.LocalDateTime;
import java.util.UUID;

import com.backend.aaz.shared.models.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock_thresholds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockThreshold {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @Column(nullable = false)
    private Double threshold;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public StockThreshold(Product product, Double threshold) {
        this.productId = product.getId();
        this.product = product;
        this.threshold = threshold;
        this.updatedAt = LocalDateTime.now();
    }
    
}