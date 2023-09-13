package com.alura.api.degree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.api.degree.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByUsernameAndActiveTrue(String username);
    
}
