package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/roles")
    public String userRolePage(Model model){
        model.addAttribute("roles",roleService.findAll());
        return "roles";
    }

    @GetMapping(value = "/role/add")
    public String rolePage(){
        return "role";
    }

    @PostMapping(value = "/role")
    public String createRole(Role role){
        role.setUsers(null);
        roleService.create(role);
        return "redirect:/roles";
    }
}
