package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title","Page about us");
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "");
        return "login";
    }

    @GetMapping("/personal_account")
    public String hello(Model model) {
        model.addAttribute("title", "");
        return "personal_account";
    }



}