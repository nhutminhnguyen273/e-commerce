package com.example.user_service.controller;

import com.example.shared_library.response.Response;
import com.example.user_service.dto.user_dto.UpdateUserDTO;
import com.example.user_service.dto.user_dto.UserDTO;
import com.example.user_service.model.User;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public Response<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public Response<UserDTO> findUserById(@PathVariable("userId") String userId) {
        return userService.findUserById(userId);
    }

    @PutMapping("/{userId}")
    public Response<User> updateUser(@PathVariable("userId") String userId, UpdateUserDTO input) {
        return userService.updateUser(userId, input);
    }

    @DeleteMapping("/{userId}")
    public Response<String> deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }
}
