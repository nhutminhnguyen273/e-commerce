package com.example.user_service.controller;

import com.example.shared_library.response.Response;
import com.example.user_service.dto.auth_dto.LoginDTO;
import com.example.user_service.dto.auth_dto.RegistrationDTO;
import com.example.user_service.response.AuthenticationResponse;
import com.example.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public Response<AuthenticationResponse> register(@RequestBody RegistrationDTO input) {
        var response = authService.register(input);
        return new Response<>("Success", response);
    }

    @PostMapping("/login")
    private Response<AuthenticationResponse> login(@RequestBody LoginDTO input) {
        var response = authService.login(input);
        return new Response<>("Success", response);
    }
}
