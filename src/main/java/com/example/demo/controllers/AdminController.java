package com.example.demo.controllers;


import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userList")
    public String userList(Model model) {
        Iterable<User> users =userRepository.findAll();
        model.addAttribute("User", users);
        return "userList";
    }

    @PostMapping("/userList")
    public String findRole(User user ,Model model){
        System.out.println(user.getUsername());
        return "";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "error";
    }
}
