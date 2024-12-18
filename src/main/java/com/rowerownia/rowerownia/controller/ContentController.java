package com.rowerownia.rowerownia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {

    @GetMapping("/")
    public String getIndex() {
        System.out.println("ContentController.getIndex -> index");
        return "index";
    }

    @GetMapping("/home")
    public String getHome() {
        System.out.println("ContentController.getHome -> home");
        return "home";
    }

    @GetMapping("/login")
    public String getLogin() {
        System.out.println("ContentController.getLogin -> login");
        return "login";
    }
    @PostMapping("/logout")
    public String getLogout() {
        System.out.println("ContentController.getLogout -> logout");
        return "logout";
    }
}