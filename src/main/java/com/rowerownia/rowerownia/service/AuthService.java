package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.DTO.LoginRequest;
import com.rowerownia.rowerownia.DTO.RegisterRequest;
import com.rowerownia.rowerownia.entity.Enums;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession httpSession;

    public User register(RegisterRequest registerRequest) {
        if (userRepository.findUserByLogin(registerRequest.getLogin()).isPresent()) {
            return null;
        }
        User user = new User();
        user.setLogin(registerRequest.getLogin());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setBirthDate(LocalDate.parse(registerRequest.getBirthDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        return userRepository.save(user);

    }



}

