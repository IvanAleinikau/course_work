package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String defaultPage(){
        return "hello";
    }

    @GetMapping(value = "/error")
    public String errorPage(){
        return "redirect:car_rent";
    }

}
