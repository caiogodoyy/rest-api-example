package com.alura.api.degree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.api.degree.model.user.User;
import com.alura.api.degree.repository.UserRepository;

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
