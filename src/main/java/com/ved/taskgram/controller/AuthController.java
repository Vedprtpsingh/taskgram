package com.ved.taskgram.controller;
import org.springframework.validation.BindingResult;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ved.taskgram.dto.UserDto;
import com.ved.taskgram.entity.User;
import com.ved.taskgram.service.UserService;

import jakarta.validation.Valid;
@Controller
public class AuthController {
    private UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user=new UserDto();
        model.addAttribute("user", user);
        return "/users/register";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model){
        // User user=new User();
        // model.addAttribute("user", user);
        return "/users/login";
    }
    @PostMapping("/authenticate")
    public String authenticateUser(){
        return "";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,Model model){
        // Check the duplicate user
        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with this mail!");
        }
        //form validation
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "users/register";
        }
        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model){
        List<UserDto> users=userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/user_list";
    }
}