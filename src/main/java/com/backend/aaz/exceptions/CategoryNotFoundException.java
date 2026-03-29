package com.backend.aaz.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Categoria não encontrada: " + id);
    }
}