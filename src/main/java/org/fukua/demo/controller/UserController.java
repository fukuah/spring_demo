package org.fukua.demo.controller;

import org.fukua.demo.Entity.User;
import org.fukua.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user = userService.createUser(user);

        return user;
    }
}
