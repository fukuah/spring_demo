package org.fukua.demo.service;

import org.fukua.demo.Entity.Authorities;
import org.fukua.demo.Entity.Users;
import org.fukua.demo.repository.AuthoritiesRepository;
import org.fukua.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    public List<Users> getAllUser(){
        return usersRepository.findAll();
    }

    public Users createUser(Users user){
        return usersRepository.saveAndFlush(user);
    }

    public Users getUserByLogin(String username) {
        return usersRepository.getByUsername(username);
    }


    // There is the bug reported on https://hibernate.atlassian.net/browse/HHH-11537 which hibernate has.
    // That's why it's done in that dumb way instead of using relationships
    public void createUserAndAssignAuthorities(Users user, List<Authorities> roles) {
        usersRepository.save(user);

        authoritiesRepository.saveAll(roles);
    }
}
