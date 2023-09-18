package com.caio.alura.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.caio.alura.api.model.user.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Rest Project")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error trying to create the token", exception);
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Rest Project")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token invalid or expired", exception);
        }
    }

    private Instant expireDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }

}