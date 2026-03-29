package com.backend.aaz.shared.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> handleProductNotFound(ProductNotFoundException e, HttpServletRequest request) {
        String errorCode = "PRODUCT_NOT_FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), errorCode, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<StandardError> handleCategoryNotFound(CategoryNotFoundException e, HttpServletRequest request) {
        String errorCode = "CATEGORY_NOT_FOUND";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), errorCode, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<StandardError> handleInsufficientStock(InsufficientStockException e, HttpServletRequest request) {
        String errorCode = "INSUFFICIENT_STOCK";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), errorCode, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleConflict(DataIntegrityViolationException e, HttpServletRequest request) {
        String errorCode = "CONFLICT";
        HttpStatus status = HttpStatus.CONFLICT;
        String message = "Conflito de integridade de dados (possível código de barras duplicado)";        
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), errorCode, message, request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> handleGenericError(RuntimeException e, HttpServletRequest request) {
        String errorCode = "INTERNAL_SERVER_ERROR";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(LocalDateTime.now(), status.value(), errorCode, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}