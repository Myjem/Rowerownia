package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public  void addNewUser(User user) {
        Optional<User> userByLogin = userRepository.findUserByLogin(user.getLogin());
        if(userByLogin.isPresent()){
            throw new IllegalStateException("login taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(Integer userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("user with id " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }
}
