package com.backend.aaz.shared.exceptions;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(UUID id) {
        super("Produto não encontrado: " + id);
    }

}