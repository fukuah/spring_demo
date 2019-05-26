package org.fukua.demo.repository;

import org.fukua.demo.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser getByUsername(String username);

}