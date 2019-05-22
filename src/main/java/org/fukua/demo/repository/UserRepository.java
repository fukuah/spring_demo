package org.fukua.demo.repository;

import org.fukua.demo.Entity.CommonEntity;
import org.fukua.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User getById(Long id);
}


