package com.backend.aaz.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    // TODO: Implementar GET /api/v1/inventory/thresholds
    @GetMapping("/thresholds")
    public ResponseEntity<Void> getThresholds() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
}