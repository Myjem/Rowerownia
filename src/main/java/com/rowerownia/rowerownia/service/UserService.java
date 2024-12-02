package com.rowerownia.rowerownia.service;

import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.entity.enums;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
@Service
public class UserService {
    @GetMapping
    public List<User> getUser() {
        return List.of(
                new User("login",
                        "password",
                        LocalDate.of(2000,10,12),
                        "name",
                        "surname",
                        enums.level.USER
                )
        );
    }
}
