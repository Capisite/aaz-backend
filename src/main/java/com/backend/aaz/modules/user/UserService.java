package com.backend.aaz.modules.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.backend.aaz.shared.exceptions.UserNotFoundException;
import com.backend.aaz.shared.models.user.User;
import com.backend.aaz.shared.models.user.dto.UpdateUserDTO;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UpdateUserDTO data, UUID id) {
        User userToUpdate = getUserAndUpdate(data, id);
        return userRepository.save(userToUpdate);
    }

    private User getUserAndUpdate(UpdateUserDTO data, UUID id) {
        User userToUpdate = userRepository.getReferenceById(id);
        if (data.name() != null) {
            userToUpdate.setName(data.name());
        }
        if (data.email() != null) {
            userToUpdate.setEmail(data.email());
        }
        if (data.password() != null) {
            userToUpdate.setPassword(data.password());
        }
        if (data.isActive() != null) {
            userToUpdate.setIsActive(data.isActive());
        }
        if (data.role() != null) {
            userToUpdate.setRole(data.role());
        }
        userToUpdate.setUpdatedAt(LocalDateTime.now());
        return userToUpdate;
    }

}