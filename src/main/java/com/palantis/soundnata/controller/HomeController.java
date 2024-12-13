package com.palantis.soundnata.controller;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.service.PlaylistService;
import com.palantis.soundnata.service.SearchService;
import com.palantis.soundnata.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private SongService songService;

    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("songs", songService.getAllSongs());
        List<Playlist> playlists = playlistService.getPlaylistsForLoggedInUser();
        model.addAttribute("playlists", playlists);
        return "home";
    }

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        Map<String, List<?>> results = searchService.search(query);
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        List<Playlist> playlists = playlistService.getPlaylistsForLoggedInUser();
        model.addAttribute("playlists", playlists);
        return "search";
    }
}
