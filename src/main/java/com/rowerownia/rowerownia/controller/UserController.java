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


import java.time.LocalDate;
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

    @DeleteMapping(path="/worker/user/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path="/user/update")
    public void updateLoggedUser(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate dataBirth){
        userService.updateLoggedUser(name, surname,dataBirth);
    }

    @GetMapping(path="/worker/user/all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path="/user/me")
    public UserDto getLoggedUser() {
        return userService.getLoggedUser();
    }

    @DeleteMapping(path="/user/delete")
    public void deleteLoggedUser() {
        userService.deleteLoggedUser();
    }

    @PutMapping(path = "/worker/unblock/{userId}")
    public void unblockUser(@PathVariable("userId") Integer userId) {
        userService.unlockUser(userId);
    }

    @PutMapping(path = "/worker/user/level/{userId}/{accessLevel}")
    public void changeAccessLevel(@PathVariable("userId") Integer userId, @PathVariable("accessLevel") String accessLevel) {
        userService.accessLevel(userId, accessLevel);
    }


//    @PostMapping(path="/worker/user/add")
//    public void addNewUser(@RequestBody User user) {
//        userService.addNewUser(user);
//    }

//    @GetMapping(path = "/worker/user/{userId}/block")
//    public boolean isBlocked(@PathVariable("userId") Integer userId) {
//        return userService.isBlocked(userId);
//    }
//
//    @GetMapping(path = "/worker/user/{userId}/fail")
//    public Integer getFailedAttempts(@PathVariable("userId") Integer userId) {
//        return userService.getFailedAttempts(userId);
//    }

}