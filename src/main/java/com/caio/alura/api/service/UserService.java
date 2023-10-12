package com.caio.alura.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.alura.api.model.user.User;
import com.caio.alura.api.repository.UserRepository;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        logger.info(".saveUser: Saving user {}", user.getUsername());

        this.userRepository.save(user);

        logger.info(".saveUser: User {} saved successfully", user.getUsername());
    }

    public void inactivateUserById(Long id) {
        logger.info(".inactivateUserById: Inactivating user with id {}", id);

        var user = this.userRepository.getReferenceById(id);
        user.deactivate();

        logger.info(".inactivateUserById: User {} inactivated successfully", user.getUsername());
    }
    
}
