package com.caio.alura.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.caio.alura.api.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsernameAndActiveTrue(String username);
    
}
