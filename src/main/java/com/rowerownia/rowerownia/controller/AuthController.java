package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.LoginRequest;
import com.rowerownia.rowerownia.DTO.RegisterRequest;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import com.rowerownia.rowerownia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        User registerUser = authService.register(registerRequest);
        if(registerUser == null) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        else {
            return ResponseEntity.ok(registerUser);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User loginUser=authService.login(loginRequest);
        if(loginUser==null){
            return ResponseEntity.badRequest().body("User not found");
        }
        else{
            return ResponseEntity.ok(loginUser);
        }
    }



}
