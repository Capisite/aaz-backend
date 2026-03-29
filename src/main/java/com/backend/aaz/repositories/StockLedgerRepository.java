package com.backend.aaz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.aaz.models.stock.StockLedger;

public interface StockLedgerRepository extends JpaRepository<StockLedger, UUID> {
    
    @Query("SELECT COALESCE(SUM(s.delta), 0.0) FROM StockLedger s WHERE s.product.id = :productId")
    Double sumDeltaByProductId(@Param("productId") UUID productId);
    
}