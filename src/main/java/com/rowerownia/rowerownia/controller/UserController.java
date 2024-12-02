package com.rowerownia.rowerownia.controller;


import com.rowerownia.rowerownia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final com.rowerownia.rowerownia.service.userService userService;

    @Autowired

    public UserController(com.rowerownia.rowerownia.service.userService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUser() {
        return userService.getUser();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

}