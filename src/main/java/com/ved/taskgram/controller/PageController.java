package com.ved.taskgram.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
    @GetMapping({"/index","/"})
    public String showIndexPage(){
        return "index";
    }
}
