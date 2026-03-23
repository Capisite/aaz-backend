package com.backend.aaz.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.backend.aaz.models.User;
import com.backend.aaz.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(User user, Long id) {
        User userToUpdate = getUserAndUpdate(user, id);
        return userRepository.save(userToUpdate);
    }

    private User getUserAndUpdate(User user, Long id) {
        User userToUpdate = userRepository.getReferenceById(id);
        if (user.getName() != null) {
            userToUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(user.getPassword());
        }
        userToUpdate.setUpdatedAt(LocalDateTime.now());
        return userToUpdate;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}