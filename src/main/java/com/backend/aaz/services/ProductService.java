package com.backend.aaz.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.aaz.exceptions.ProductNotFoundException;
import com.backend.aaz.models.product.Product;
import com.backend.aaz.models.product.dto.CreateProductDTO;
import com.backend.aaz.models.product.dto.UpdateProductDTO;
import com.backend.aaz.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    public Product create(CreateProductDTO data) {
        Product product = getProduct(data);
        return productRepository.save(product);
    }

    private Product getProduct(CreateProductDTO data) {
        Product product = new Product();
        product.setName(data.name());
        product.setDescription(data.description());
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
        if (data.sellingPrice() != null) product.setSellingPrice(data.sellingPrice());
        product.setUpdatedAt(LocalDateTime.now());
        return product;
    }

    public void delete(UUID id) {
        getById(id);
        productRepository.deleteById(id);
    }

    public Product addStock(UUID id, int quantity) {
        Product product = getById(id);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Product removeStock(UUID id, int quantity) {
        Product product = getById(id);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

}