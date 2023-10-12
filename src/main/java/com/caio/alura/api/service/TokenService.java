package com.caio.alura.api.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.caio.alura.api.model.user.User;

@Service
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Value("${api.security.token.secret}")
    private String secret;

    public String createToken(User user) {
        logger.info(".createToken: Creating token for user " + user.getId());

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            var token = JWT.create()
                    .withIssuer("API Rest Project")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expireDate())
                    .sign(algorithm);
            
            logger.info(".createToken: Token was created successfully for user " + user.getUsername());
            return token;
        } catch (JWTCreationException exception) {
            logger.error(".createToken: Error trying to create token for user " + user.getUsername());
            throw new RuntimeException("Error trying to create token", exception);
        }
    }

    public String getSubject(String token) {
        logger.info(".getSubject: Verifing token");

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            var username = JWT.require(algorithm)
                    .withIssuer("API Rest Project")
                    .build()
                    .verify(token)
                    .getSubject();
            
            logger.info(".getSubject: Token verified successfully for user " + username);
            return username;
        } catch (JWTVerificationException exception) {
            logger.error(".getSubject: Token invalid or expired");
            throw new RuntimeException("Token invalid or expired", exception);
        }
    }

    private Instant expireDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }

}