package com.palantis.soundnata.controller;

import com.palantis.soundnata.model.User;
import com.palantis.soundnata.service.SongService;
import com.palantis.soundnata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Return the registration view
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerNewUser(user); // Register the user
        return "redirect:/login"; // Redirect to the login page after registration
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("imageName", "anakkelas.jpg");

        return "login"; // Return the login view
    }

    

}
