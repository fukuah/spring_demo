package org.fukua.demo.controller;

import org.fukua.demo.Entity.MyUser;
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
    public List<MyUser> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public MyUser createUser(@RequestBody MyUser user) {
        user = userService.createUser(user);

        return user;
    }

    @RequestMapping(value="{username}", method={RequestMethod.GET})
    public MyUser getUser(@PathVariable("username") String username) { return userService.getUserByLogin(username); }

//    @RequestMapping(value="login", method={RequestMethod.POST})
//    public ResponseEntity<Map> login(@RequestBody String username, @RequestBody String password){
//        Map<String, String> response = new HashMap<>();
//
//        if (userService.login(username, password)) {
//            response.put("Login success", "username: " + username);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//
//        response.put("Login failed", "check your username and password");
//        return  new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
