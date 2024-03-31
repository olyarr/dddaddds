package com.example.Warehouse.controller;

import com.example.Warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getEmployees(Model model){
        model.addAttribute("count", userService.getCountUsers());
        model.addAttribute("employees", userService.getUsers());
        return "/Employees/Index";
    }
}
