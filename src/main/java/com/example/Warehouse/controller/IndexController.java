package com.example.Warehouse.controller;

import com.example.Warehouse.model.User;
import com.example.Warehouse.service.BackUpService;
import com.example.Warehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class IndexController {
    private final UserService userService;
    private final BackUpService backUpService;

    @Autowired
    public IndexController(UserService userService, BackUpService backUpService) {
        this.userService = userService;
        this.backUpService = backUpService;
    }

    @GetMapping()
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("details", userDetails);
        return "/Index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/LogInOut/Login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "/LogInOut/Registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/back_up")
    public String backUp() throws SQLException, IOException, ClassNotFoundException {
        backUpService.back();
        return "redirect:/";
    }
}
