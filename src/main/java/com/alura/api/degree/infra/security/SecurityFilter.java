package com.alura.api.degree.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.api.degree.domain.user.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var token = getTokenFromRequest(request);
                if (token != null) {
                    var subject = tokenService.getSubject(token);
                    var user = userRepository.findByUsernameAndActiveTrue(subject);
                    var userAuthentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(userAuthentication);
                }
                filterChain.doFilter(request, response);
            }

    private String getTokenFromRequest(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token != null)
            token = token.replace("Bearer ", "");
        return token;
    }

}
