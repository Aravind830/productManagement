package com.example.assignment.service;

import com.example.assignment.dto.AuthRequest;
import com.example.assignment.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest req);

    AuthResponse refresh(String refreshToken);
}
