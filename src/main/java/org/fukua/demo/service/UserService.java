package org.fukua.demo.service;

import org.fukua.demo.Entity.MyUser;
import org.fukua.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<MyUser> getAllUser(){
        return userRepository.findAll();
    }

    public MyUser createUser(MyUser user){
        return userRepository.saveAndFlush(user);
    }

    public MyUser getUserByLogin(String username) {
        return userRepository.getByUsername(username);
    }

//    public boolean login(String username, String password) {
//        MyUser user = userRepository.getByUsername(username);
//
//        return user.getPassword().equals(password);
//    }
}
