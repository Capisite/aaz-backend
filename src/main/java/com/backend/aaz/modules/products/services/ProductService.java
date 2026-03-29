package com.backend.aaz.modules.products.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.aaz.shared.exceptions.CategoryNotFoundException;
import com.backend.aaz.shared.exceptions.ProductNotFoundException;
import com.backend.aaz.shared.models.category.Category;
import com.backend.aaz.shared.models.product.Product;
import com.backend.aaz.shared.models.product.dto.CreateProductDTO;
import com.backend.aaz.shared.models.product.dto.UpdateProductDTO;
import com.backend.aaz.shared.models.product.enums.ProductStatus;
import com.backend.aaz.shared.models.product.enums.StockReason;
import com.backend.aaz.shared.models.stock.StockLedger;
import com.backend.aaz.modules.products.repositories.CategoryRepository;
import com.backend.aaz.modules.products.repositories.ProductRepository;
import com.backend.aaz.modules.products.repositories.StockLedgerRepository;
import com.backend.aaz.modules.products.repositories.StockThresholdRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockLedgerRepository stockLedgerRepository;
    private final StockThresholdRepository stockThresholdRepository;

    public ProductService(
        ProductRepository productRepository, 
        CategoryRepository categoryRepository,
        StockLedgerRepository stockLedgerRepository,
        StockThresholdRepository stockThresholdRepository
    ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.stockLedgerRepository = stockLedgerRepository;
        this.stockThresholdRepository = stockThresholdRepository;
    }

    public List<Product> getAll(String search, UUID categoryId, ProductStatus status) {
        ProductStatus finalStatus = (status == null) ? ProductStatus.ACTIVE : status;
        String searchParam = (search == null || search.isBlank()) ? null : "%" + search + "%";
        return productRepository.findAllBySearch(searchParam, categoryId, finalStatus);
    }

    public Product getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product create(CreateProductDTO data) {
        Product product = getProduct(data);
        return productRepository.save(product);
    }

    private Product getProduct(CreateProductDTO data) {
        Category category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(data.categoryId()));
        
        Product product = new Product();
        product.setName(data.name());
        product.setDescription(data.description());
        product.setCategory(category);
        product.setBarcode(data.barcode());
        product.setUnitOfMeasure(data.unitOfMeasure());
        product.setImageUrl(data.imageUrl());
        product.setSellingPrice(data.sellingPrice());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    public Product update(UUID id, UpdateProductDTO data) {
        Product product = getProductToUpdate(id, data);
        return productRepository.save(product);
    }

    private Product getProductToUpdate(UUID id, UpdateProductDTO data) {
        Product product = getById(id);
        if (data.name() != null) product.setName(data.name());
        if (data.description() != null) product.setDescription(data.description());
        if (data.barcode() != null) product.setBarcode(data.barcode());
        if (data.unitOfMeasure() != null) product.setUnitOfMeasure(data.unitOfMeasure());
        if (data.imageUrl() != null) product.setImageUrl(data.imageUrl());
        if (data.status() != null) product.setStatus(data.status());
        if (data.sellingPrice() != null) product.setSellingPrice(data.sellingPrice());
        
        if (data.categoryId() != null) {
            Category category = categoryRepository.findById(data.categoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(data.categoryId()));
            product.setCategory(category);
        }
        
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    public void archive(UUID id) {
        Product product = getById(id);
        product.setStatus(ProductStatus.ARCHIVED);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public Product addStock(UUID id, int quantity) {
        Product product = getById(id);
        StockLedger entry = new StockLedger(product, (double) quantity, StockReason.MANUAL_ADJUSTMENT, null);
        stockLedgerRepository.save(entry);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Product removeStock(UUID id, int quantity) {
        Product product = getById(id);
        StockLedger entry = new StockLedger(product, (double) -quantity, StockReason.MANUAL_ADJUSTMENT, null);
        stockLedgerRepository.save(entry);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Double getCurrentStock(UUID productId) {
        return stockLedgerRepository.sumDeltaByProductId(productId);
    }

    public Boolean isLowStock(UUID productId) {
        Double currentStock = getCurrentStock(productId);
        return stockThresholdRepository.findById(productId)
                .map(threshold -> currentStock <= threshold.getThreshold())
                .orElse(false);
    }

}