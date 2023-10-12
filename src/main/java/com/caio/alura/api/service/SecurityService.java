package com.caio.alura.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caio.alura.api.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(".loadUserByUsername: Loading user by username {}", username);
        UserDetails user = this.userRepository.findByUsernameAndActiveTrue(username);
        
        if (user == null) {
            logger.error(".loadUserByUsername: User not found for username {}", username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        logger.info(".loadUserByUsername: User loaded successfully for username {}", username);
        return user;
    }
}
