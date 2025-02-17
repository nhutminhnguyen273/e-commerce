package com.example.user_service.service;

import com.example.shared_library.exception.custom.NotFoundException;
import com.example.user_service.dto.auth_dto.LoginDTO;
import com.example.user_service.dto.auth_dto.RegistrationDTO;
import com.example.user_service.mapper.AuthMapper;
import com.example.user_service.model.Role;
import com.example.user_service.model.User;
import com.example.user_service.repository.RoleRepository;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.response.AuthenticationResponse;
import com.example.user_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthenticationResponse register(RegistrationDTO input) {
        try {
            if (userRepository.existsByUsername(input.getUsername()))
                return new AuthenticationResponse(input.getUsername() + " already existed");
            if (userRepository.existsByEmail(input.getEmail()))
                return new AuthenticationResponse(input.getEmail() + " already existed");
            if (userRepository.existsByPhone(input.getPhone()))
                return new AuthenticationResponse(input.getPhone() + " already existed");
            if (!input.getConfirmPassword().equals(input.getPassword()))
                return new AuthenticationResponse("Confirm password and password are not the same");
            Role role = roleRepository.findByName("Customer").orElseThrow(
                    () -> new NotFoundException("Customer role not found")
            );
            User newUser = authMapper.toUserEntity(input);
            newUser.setPassword(passwordEncoder.encode(input.getPassword()));
            newUser.setRole(role);
            userRepository.save(newUser);
            var token = jwtUtil.generateToken(newUser);
            return new AuthenticationResponse("Registration successfully", token);
        } catch (Exception ex) {
            return new AuthenticationResponse(ex.getMessage());
        }
    }

    public AuthenticationResponse login(LoginDTO input) {
        try {
            var user = userRepository.findByUsername(input.getUsername()).orElseThrow(
                    () -> new NotFoundException(input.getUsername() + " not found")
            );
            boolean authenticated = passwordEncoder.matches(input.getPassword(), user.getPassword());
            if (!authenticated)
                return new AuthenticationResponse("Password is incorrect");
            var token = jwtUtil.generateToken(user);
            return new AuthenticationResponse("Login successfully", token);
        } catch (Exception ex) {
            return new AuthenticationResponse(ex.getMessage());
        }
    }
}
