package com.example.assignment.security;

import com.example.assignment.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken createToken(String username);

    RefreshToken verify(String tokenStr);

    void delete(RefreshToken token);
}
