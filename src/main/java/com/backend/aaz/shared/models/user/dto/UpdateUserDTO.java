package com.backend.aaz.shared.models.user.dto;

import com.backend.aaz.shared.models.user.enums.UserRole;

public record UpdateUserDTO(

    String name,
    String email,
    String password,
    UserRole role,
    Boolean isActive
    
) {}