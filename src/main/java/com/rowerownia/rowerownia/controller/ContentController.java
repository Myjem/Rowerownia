package com.rowerownia.rowerownia.controller;

import com.rowerownia.rowerownia.entity.User;
import com.rowerownia.rowerownia.service.CustomUserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @GetMapping("/register")
    public String getRegister() {
        System.out.println("ContentController.getRegister -> register");
        return "register";
    }

    @GetMapping("/home")
    public String getHome() {
        System.out.println("ContentController.getHome -> home");
        return "home";

    }
    @GetMapping("/admin")
    public String getadmin() {
        System.out.println("ContentController.getHome -> admin");
        return "admin";
    }
    @GetMapping("/user")
    public String getuser() {
        System.out.println("ContentController.getHome -> user");
        return "user";
    }
    @GetMapping("/bike")
    public String getbike() {
        System.out.println("ContentController.getHome -> bike");
        return "bike";
    }
    @GetMapping("/repair")
    public String getrepair() {
        System.out.println("ContentController.getHome -> repair");
        return "repair";
    }
    @GetMapping("/worker")
    public String getworker() {
        System.out.println("ContentController.getHome -> worker");
        return "worker";
    }
    @GetMapping("/workermanage")
    public String getworkermanage() {
        System.out.println("ContentController.getHome -> workermanage");
        return "workermanage";
    }
    @GetMapping("/login")
    public String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication.getPrincipal() != "anonymousUser") {
            System.out.println(authentication.getPrincipal());
            UserDetails user = (UserDetails) authentication.getPrincipal();
            System.out.println(user.getUsername());
        }
//        User user = (User) authentication.getPrincipal();
//        System.out.println(user.getName());

//        System.out.println("ContentController.getLogin -> user: " + user.getAccessLevel().name());
//        System.out.println("ContentController.getLogin -> login");
//        if (authentication.isAuthenticated() && user.getAccessLevel().name().equals("WORKER")){
//            return "redirect:/admin";
//        } else if (authentication.isAuthenticated() && user.getAccessLevel().name().equals("USER")){
//            return "redirect:/home";
//        } else {
//            return "login";
//        }
//        return "login";
        if (authentication.isAuthenticated() && authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_WORKER"))) {
            System.out.println("ContentController.getLogin -> admin");
            return "redirect:/";
        } else if (authentication.isAuthenticated() && authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"))) {
            System.out.println("ContentController.getLogin -> home");
            return "redirect:/";
        }

        System.out.println("ContentController.getLogin -> login");
        return "login";
    }
    @PostMapping("/logout")
    public String getLogout() {
        System.out.println("ContentController.getLogout -> logout");
        return "logout";
    }
}