package com.example.driveintheater.controller;

import com.example.driveintheater.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRegistrationService registrationService;

    @Autowired
    public UserController(UserRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam String email,
                                  Model model) {

        String result = registrationService.register(username, password, email);

        if ("username_exists".equals(result)) {
            model.addAttribute("error", "Uživatelské jméno je již obsazeno.");
            return "register";
        }

        if ("email_exists".equals(result)) {
            model.addAttribute("error", "Email je již použit.");
            return "register";
        }

        return "redirect:/login";
    }
}
