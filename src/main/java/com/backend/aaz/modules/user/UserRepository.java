package com.backend.aaz.modules.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.aaz.shared.models.user.User;


public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);

    UserDetails findByEmail(String email);

}