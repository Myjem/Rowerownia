package com.rowerownia.rowerownia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}