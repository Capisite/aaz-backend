package com.backend.aaz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.aaz.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}