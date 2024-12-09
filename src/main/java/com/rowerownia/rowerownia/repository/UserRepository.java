package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository 
        extends JpaRepository<User,Integer> {

    Optional<User> findUserByLogin(String login);
    //(same like a @Query("SELECT u FROM User u WHERE u.login = ?1 "))

}
