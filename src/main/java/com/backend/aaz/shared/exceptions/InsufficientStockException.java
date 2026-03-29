package com.backend.aaz.shared.exceptions;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(int available, int requested) {
        super("Estoque insuficiente. Disponível: " + available + ", Solicitado: " + requested);
    }

}