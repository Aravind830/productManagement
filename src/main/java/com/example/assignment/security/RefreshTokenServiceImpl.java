package com.example.assignment.security;

import com.example.assignment.entity.RefreshToken;
import com.example.assignment.entity.User;
import com.example.assignment.repository.RefreshTokenRepository;
import com.example.assignment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final UserRepository userRepo;

    @Value("jwt.refresh-expiration-ms")
    private String REFRESH_EXPIRATION_MS;

    /**
     *
     * @param username
     * @return
     */
    public RefreshToken createToken(String username) {
        User user = userRepo.findByUsername(username)
                .orElseThrow();

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().plusMinutes(Long.parseLong(REFRESH_EXPIRATION_MS)));

        return repo.save(token);
    }

    /**
     * 
     * @param tokenStr
     * @return
     */
    public RefreshToken verify(String tokenStr) {
        RefreshToken token = repo.findByToken(tokenStr)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if(token.getExpiryDate().isBefore(LocalDateTime.now()))
            throw new RuntimeException("Expired refresh token");

        return token;
    }

    public void delete(RefreshToken token) {
        repo.delete(token);
    }
}

