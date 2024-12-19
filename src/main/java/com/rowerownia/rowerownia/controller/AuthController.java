package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.DTO.LoginRequest;
import com.rowerownia.rowerownia.DTO.RegisterRequest;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import com.rowerownia.rowerownia.service.AuthService;
import com.rowerownia.rowerownia.service.UserService;
import jakarta.validation.Valid;
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
    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User registerUser = authService.register(registerRequest);
        if (registerUser == null) {
            return ResponseEntity.badRequest().body("User already exists");
        } else {
            return ResponseEntity.ok(registerUser);
        }
    }

//    @GetMapping("/login")
//    public String login(@RequestParam(value = "session", required = false) String sessionStatus) {
//        if ("expired".equals(sessionStatus)) {
//            return "Your session has expired. Please log in again.";
//        }
//        if ("invalid".equals(sessionStatus)) {
//            return "Your session is invalid. Please log in again.";
//        }
//        return "Login page";
//    }


}
