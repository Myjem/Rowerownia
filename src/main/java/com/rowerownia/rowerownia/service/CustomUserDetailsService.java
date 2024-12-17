package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> useropt = userRepository.findUserByLogin(username);
        User user = useropt.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getAccessLevel().name())
                .build();
    }

    public void handleFailedLogin(String username) {
        Optional<User> useropt = userRepository.findUserByLogin(username);
        User user = useropt.get();
        userService.plusFailedAttempts(user);
        System.out.println("User " + username + " failed to log in.");
    }

    public void handleSuccessfulLogin(String username) {
        Optional<User> useropt = userRepository.findUserByLogin(username);
        User user = useropt.get();
        userService.resetFailedAttempts(user);
        System.out.println("User " + username + " logged in successfully.");


    }
}


