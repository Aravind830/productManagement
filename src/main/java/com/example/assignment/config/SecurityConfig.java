package com.example.assignment.config;

import com.example.assignment.enums.Role;
import com.example.assignment.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter filter;

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/v1/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/products/**")
                        .hasRole(Role.ROLE_ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/products/**")
                        .hasRole(Role.ROLE_ADMIN.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}


