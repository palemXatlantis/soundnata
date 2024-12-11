package com.palantis.soundnata.controller;

import com.palantis.soundnata.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private SongService songService;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("songs", songService.getAllSongs());
        return "home"; // Return the home view
    }
}
