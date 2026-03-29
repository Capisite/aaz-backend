package com.backend.aaz.shared.models.user.enums;

public enum UserRole {
 
    MANAGER("Gerente"),
    OPERATOR("Operador"),
    VIEWER("Visualizador");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    
}