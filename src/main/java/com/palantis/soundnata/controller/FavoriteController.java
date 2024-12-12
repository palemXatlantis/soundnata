package controller;

import model.Song;
import model.User;
import service.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public String showFavorites(@SessionAttribute("currentUser") User user, Model model) {
        model.addAttribute("favorites", favoriteService.getFavoritesByUser(user));
        return "favorites";
    }

    @PostMapping("/add")
    public String addFavorite(@SessionAttribute("currentUser") User user, @RequestParam Long songId) {
        Song song = new Song(); // Assume Song entity is fetched by ID
        song.setId(songId);
        favoriteService.addToFavorites(user, song);
        return "redirect:/favorites";
    }

    @PostMapping("/remove")
    public String removeFavorite(@SessionAttribute("currentUser") User user, @RequestParam Long songId) {
        Song song = new Song();
        song.setId(songId);
        favoriteService.removeFromFavorites(user, song);
        return "redirect:/favorites";
    }
}
