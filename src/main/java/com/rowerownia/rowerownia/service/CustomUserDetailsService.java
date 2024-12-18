package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
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
        if (useropt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = useropt.get();
        if (user.getIsBlocked()) {
            throw new LockedException("User blocked");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getAccessLevel().name())
                .build();
    }

    public void handleFailedLogin(String username) {
        System.out.println("Handling failed login for username: " + username);

        Optional<User> userOpt = userRepository.findUserByLogin(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            System.out.println("User found: " + user.getLogin());
            userService.plusFailedAttempts(user);
            System.out.println("Failed login attempts: " + user.getFailedLoginAttempts());
        } else {
            System.out.println("User not found for username: " + username);
        }
    }




    public void handleSuccessfulLogin(String username) {
        Optional<User> useropt = userRepository.findUserByLogin(username);
        User user = useropt.get();
        userService.resetFailedAttempts(user);
        System.out.println("User " + username + " logged in successfully.");


    }
}


