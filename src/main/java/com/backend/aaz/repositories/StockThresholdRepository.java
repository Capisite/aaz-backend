package com.backend.aaz.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.models.stock.StockThreshold;

public interface StockThresholdRepository extends JpaRepository<StockThreshold, UUID> {
    
}