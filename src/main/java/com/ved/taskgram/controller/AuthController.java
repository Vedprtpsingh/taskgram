package com.ved.taskgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ved.taskgram.entity.User;

@Controller
public class AuthController {
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "/users/register";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "/users/login";
    }
}
