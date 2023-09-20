package com.caio.alura.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.user.User;
import com.caio.alura.api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        this.userRepository.save(user);
    }

    public void inactivateUserById(Long id) {
        var user = this.userRepository.getReferenceById(id);
        user.deactivate();
    }
    
}
