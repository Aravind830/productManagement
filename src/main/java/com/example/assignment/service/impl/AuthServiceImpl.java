package com.example.assignment.service.impl;

import com.example.assignment.security.JwtUtil;
import com.example.assignment.dto.AuthRequest;
import com.example.assignment.dto.AuthResponse;
import com.example.assignment.entity.RefreshToken;
import com.example.assignment.entity.User;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.service.AuthService;
import com.example.assignment.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;
    private final RefreshTokenService refreshService;

    /**
     *
     * @param req
     * @return
     */
    public AuthResponse login(AuthRequest req) {

        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow();

        if(!encoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid password");

        String access = jwt.generateAccessToken(user);
        RefreshToken refresh = refreshService.createToken(user.getUsername());

        return new AuthResponse(access, refresh.getToken());
    }

    /**
     *
     * @param refreshToken
     * @return
     */
    public AuthResponse refresh(String refreshToken) {

        RefreshToken token = refreshService.verify(refreshToken);
        User user = token.getUser();

        refreshService.delete(token); // rotation
        RefreshToken newToken = refreshService.createToken(user.getUsername());

        String access = jwt.generateAccessToken(user);

        return new AuthResponse(access, newToken.getToken());
    }
}
