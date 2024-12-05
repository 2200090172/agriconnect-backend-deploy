package com.klef.jfsd.springboot.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String name, String displayName) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setDisplayName(displayName);
        return userRepository.save(user);
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
