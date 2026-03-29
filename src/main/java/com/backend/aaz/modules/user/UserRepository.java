package com.backend.aaz.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.aaz.shared.models.user.User;


public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

}