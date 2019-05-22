package org.fukua.demo.service;

import org.fukua.demo.Entity.User;
import org.fukua.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.saveAndFlush(user);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }
}
