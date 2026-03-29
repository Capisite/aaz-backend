package com.backend.aaz.modules.user;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.aaz.shared.models.user.User;
import com.backend.aaz.shared.models.user.dto.UpdateUserDTO;
import com.backend.aaz.shared.models.user.dto.UserResponseDTO;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.from(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDTO data) {
        User updatedUser = userService.update(data, id);
        return ResponseEntity.status(HttpStatus.OK).body(UserResponseDTO.from(updatedUser));
    }

}