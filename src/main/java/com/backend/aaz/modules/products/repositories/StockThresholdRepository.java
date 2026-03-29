package com.backend.aaz.modules.products.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.shared.models.stock.StockThreshold;

public interface StockThresholdRepository extends JpaRepository<StockThreshold, UUID> {
    
}