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

    @GetMapping(path="/user/me")
    public UserDto getLoggedUser() {
        return userService.getLoggedUser();
    }

    @PutMapping(path="/user/me/update")
    public void updateLoggedUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        userService.updateLoggedUser(name, surname);
    }

    @DeleteMapping(path="/user/me/delete")
    public void deleteLoggedUser() {
        userService.deleteLoggedUser();
    }

    @PutMapping(path = "/worker/user/{userId}/update")
    public void updateUser(
            @PathVariable("userId") Integer userId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname) {
        userService.updateUser(userId, name, surname);
    }

    @GetMapping(path="/worker/user/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(path="/worker/user/add")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path="/worker/user/{userId}/delete")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/worker/unblock/{userId}")
    public void unblockUser(@PathVariable("userId") Integer userId) {
        userService.unlockUser(userId);
    }

    @GetMapping(path = "/worker/user/{userId}/block")
    public boolean isBlocked(@PathVariable("userId") Integer userId) {
        return userService.isBlocked(userId);
    }

    @GetMapping(path = "/worker/user/{userId}/fail")
    public Integer getFailedAttempts(@PathVariable("userId") Integer userId) {
        return userService.getFailedAttempts(userId);
    }

    @PutMapping(path = "/worker/user/{userId}/{accessLevel}")
    public void changeAccessLevel(@PathVariable("userId") Integer userId, @PathVariable("accessLevel") String accessLevel) {
        userService.accessLevel(userId, accessLevel);
    }


}