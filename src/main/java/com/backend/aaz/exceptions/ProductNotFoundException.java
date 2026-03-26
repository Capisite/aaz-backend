package com.backend.aaz.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super("Produto não encontrado: " + id);
    }

}