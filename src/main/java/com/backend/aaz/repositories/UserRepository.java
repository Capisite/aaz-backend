package com.backend.aaz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.backend.aaz.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsername(String username);

}