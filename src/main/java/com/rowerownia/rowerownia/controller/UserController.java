package com.rowerownia.rowerownia.controller;


import com.rowerownia.rowerownia.DTO.UserDto;
import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.repository.UserRepository;
import com.rowerownia.rowerownia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/auth")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping(path="/worker/user/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path="/worker/user/add")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path="/user/{userId}/delete")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/user/{userId}/update")
    public void updateUser(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        userService.updateUser(userId, name, surname);
    }
    @PutMapping(path = "/worker/unblock/{userId}")
    public void unblockUser(@PathVariable("userId") Integer userId) {
        userService.unlockUser(userId);
    }


}