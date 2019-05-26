package org.fukua.demo;

import org.fukua.demo.Entity.Authorities;
import org.fukua.demo.Entity.User;
import org.fukua.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class StartupDemoApplicationScenario {

    @Autowired
    UserService userService;

    // Fills tables with data for testing
    public void createUserForBasicAuthentication() {
        String username = "user011", password = "admin", role = "ROLE_USER";

        System.out.println("---------------------------------APPLICATION AFTER LAUNCH SCENARIO----------------------------------------");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        User user = userService.getUserByLogin(username);
        if (userService.getUserByLogin(username) == null) {
            user = new User();
            user.setEnabled(true);
            user.setUsername(username);
            user.setPassword(password);
            // create Authorities for new user
            Authorities auths = new Authorities();
            auths.setAuthority(role);
            auths.setUsername(username);
            // set Authorities to new user
            List<Authorities> roles = new ArrayList<>();
            roles.add(auths);
            user.setRoles(roles);

            userService.createUser(user);

            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------TESTING DATA IS PROVIDED---------------------------------------------");
            System.out.println("---------USE FOR AUTHENTICATION:----------------------------------------------------------------------------");
            System.out.println("-----------------USERNAME: " + username + "----------------------------------------------------------------------------");
            System.out.println("-----------------PASSWORD: " + password + "----------------------------------------------------------------------------");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------TESTING DATA IS PROVIDED---------------------------------------------");
            System.out.println("---------USE FOR AUTHENTICATION:----------------------------------------------------------------------------");
            System.out.println("-----------------USERNAME: " + user.getUsername() + "---------------------------------------------------------------------------");
            System.out.println("-----------------PASSWORD: " + user.getPassword() + "----------------------------------------------------------------------------");
        }
    }
}