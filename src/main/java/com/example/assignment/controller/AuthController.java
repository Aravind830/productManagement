package com.example.assignment.controller;

import com.example.assignment.dto.AuthRequest;
import com.example.assignment.dto.AuthResponse;
import com.example.assignment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        return service.login(req);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody Map<String,String> body) {
        return service.refresh(body.get("refreshToken"));
    }
}

