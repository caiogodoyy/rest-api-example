package com.caio.alura.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.user.User;
import com.caio.alura.api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository repository;

    public void save(User user) {
        repository.save(user);
    }

    public void inactivateUserById(Long id) {
        var user = repository.getReferenceById(id);
        user.deactivate();
    }
    
}
