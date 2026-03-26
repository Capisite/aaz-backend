package com.backend.aaz.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.aaz.models.product.Product;
import com.backend.aaz.models.product.dto.CreateProductDTO;
import com.backend.aaz.models.product.dto.ProductResponseDTO;
import com.backend.aaz.models.product.dto.StockUpdateDTO;
import com.backend.aaz.models.product.dto.UpdateProductDTO;
import com.backend.aaz.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> products = productService.getAll()
                .stream()
                .map(ProductResponseDTO::from)
                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable UUID id) {
        Product product = productService.getById(id);
        ProductResponseDTO response = ProductResponseDTO.from(product);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO dto) {
        Product product = productService.create(dto);
        ProductResponseDTO response = ProductResponseDTO.from(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid UpdateProductDTO dto) {
        Product product = productService.update(id, dto);
        ProductResponseDTO response = ProductResponseDTO.from(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock/add")
    public ResponseEntity<ProductResponseDTO> addStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDTO dto) {
        Product product = productService.addStock(id, dto.quantity());
        ProductResponseDTO response = ProductResponseDTO.from(product);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/stock/remove")
    public ResponseEntity<ProductResponseDTO> removeStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDTO dto) {
        Product product = productService.removeStock(id, dto.quantity());
        ProductResponseDTO response = ProductResponseDTO.from(product);
        return ResponseEntity.ok(response);
    }

}