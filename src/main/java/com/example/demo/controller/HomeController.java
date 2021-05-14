package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.model.CarOrder;
import com.example.demo.service.CarOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CarOrderService carOrderService;

    @GetMapping
    public String defaultPage(){
        return "hello";
    }

    @GetMapping(value = "/main")
    public String secondDefaultPage(){
        return "main";
    }

    @GetMapping(value = "/error")
    public String errorPage(){
        return "redirect:car_rent";
    }

    /*@GetMapping("/personal_account")
    public String profilePage(Model model) {
        model.addAttribute("title", "");
        return "personal_account";
    }*/

    @GetMapping(value = "/personal_account/{username}")
    public String profilePage(@PathVariable(value = "username") String username , Car car, Model model) {
        List<CarOrder> carOrders = carOrderService.findAll();
        List<CarOrder> your = new ArrayList<>();
        for (int i = 0; i < carOrders.size() ; i++) {
            if(carOrders.get(i).getUsername().equals(username)){
                your.add(carOrders.get(i));
            }
        }
        model.addAttribute("list",your);
        return "personal_account";
    }

}
