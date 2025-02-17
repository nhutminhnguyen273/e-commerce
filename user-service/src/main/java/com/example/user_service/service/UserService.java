package com.example.user_service.service;

import com.example.shared_library.exception.custom.NotFoundException;
import com.example.shared_library.response.Response;
import com.example.user_service.dto.user_dto.UpdateUserDTO;
import com.example.user_service.dto.user_dto.UserDTO;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Response<List<UserDTO>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            List<UserDTO> userDTOList = users
                    .stream()
                    .map(userMapper::toUserDTO)
                    .toList();
            return new Response<>("Success", userDTOList);
        } catch (Exception ex) {
            return new Response<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()
            );
        }
    }

    public Response<UserDTO> findUserById(String userId) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("User not found")
            );
            UserDTO userDTO = userMapper.toUserDTO(user);
            return new Response<>("Success", userDTO);
        } catch (Exception ex) {
            return new Response<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()
            );
        }
    }

    public Response<UserDTO> findUserByUsername(String username) {
        try {
            var user = userRepository.findByUsername(username).orElseThrow(
                    () -> new NotFoundException("Username not found")
            );
            UserDTO userDTO = userMapper.toUserDTO(user);
            return new Response<>("Success", userDTO);
        } catch (Exception ex) {
            return new Response<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()
            );
        }
    }

    public Response<User> updateUser(String userId, UpdateUserDTO input) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("User not found")
            );
            User updatedUser = userMapper.toUserEntity(user, input);
            userRepository.save(updatedUser);
            return new Response<>("Success", updatedUser);
        } catch (Exception ex) {
            return new Response<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()
            );
        }
    }

    public Response<String> deleteUser(String userId) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new NotFoundException("User not found")
            );
            userRepository.delete(user);
            return new Response<>(true, HttpStatus.OK.value(), "Deleted successfully");
        } catch (Exception ex) {
            return new Response<>(
                    false,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    ex.getMessage()
            );
        }
    }
}
