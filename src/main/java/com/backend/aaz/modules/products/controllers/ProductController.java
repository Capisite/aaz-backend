package com.backend.aaz.modules.products.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.aaz.shared.models.product.Product;
import com.backend.aaz.shared.models.product.dto.CreateProductDTO;
import com.backend.aaz.shared.models.product.dto.ProductResponseDTO;
import com.backend.aaz.shared.models.product.dto.StockUpdateDTO;
import com.backend.aaz.shared.models.product.dto.UpdateProductDTO;
import com.backend.aaz.shared.models.product.enums.ProductStatus;
import com.backend.aaz.modules.products.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(
        @RequestParam(required = false) UUID categoryId,
        @RequestParam(required = false) ProductStatus status,
        @RequestParam(required = false) String search
    ) {
        List<ProductResponseDTO> products = productService.getAll(search, categoryId, status)
                .stream()
                .map(product -> ProductResponseDTO.from(
                    product, 
                    productService.getCurrentStock(product.getId()), 
                    productService.isLowStock(product.getId())
                ))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable UUID id) {
        Product product = productService.getById(id);
        ProductResponseDTO response = ProductResponseDTO.from(
            product, 
            productService.getCurrentStock(id), 
            productService.isLowStock(id)
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO dto) {
        Product product = productService.create(dto);
        ProductResponseDTO response = ProductResponseDTO.from(product, 0.0, false);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid UpdateProductDTO dto) {
        Product product = productService.update(id, dto);
        ProductResponseDTO response = ProductResponseDTO.from(
            product, 
            productService.getCurrentStock(id), 
            productService.isLowStock(id)
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> archive(@PathVariable UUID id) {
        productService.archive(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stock/add")
    public ResponseEntity<ProductResponseDTO> addStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDTO dto) {
        Product product = productService.addStock(id, dto.quantity());
        ProductResponseDTO response = ProductResponseDTO.from(
            product, 
            productService.getCurrentStock(id), 
            productService.isLowStock(id)
        );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/stock/remove")
    public ResponseEntity<ProductResponseDTO> removeStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDTO dto) {
        Product product = productService.removeStock(id, dto.quantity());
        ProductResponseDTO response = ProductResponseDTO.from(
            product, 
            productService.getCurrentStock(id), 
            productService.isLowStock(id)
        );
        return ResponseEntity.ok(response);
    }

    // TODO: Implementar POST /api/v1/products/import
    // @PostMapping("/import")
    // public ResponseEntity<Void> importProducts(...) { ... }

    // TODO: Implementar GET /api/v1/products/export
    // @GetMapping("/export")
    // public ResponseEntity<byte[]> exportProducts() { ... }

}