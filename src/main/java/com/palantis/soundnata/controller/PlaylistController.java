package com.palantis.soundnata.controller;


import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public String getAllPlaylists(Model model) {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        model.addAttribute("playlists", playlists);
        return "playlists";
    }

    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable Long id, Model model) {
        Playlist playlist = playlistService.getPlaylistById(id);
        model.addAttribute("playlist", playlist);
        return "playlist";
    }

    @GetMapping("/create")
    public String createPlaylist(Model model) {
        model.addAttribute("playlist", new Playlist());
        return "create-playlist";
    }

    @PostMapping("/create")
    public String createPlaylist(@ModelAttribute Playlist playlist) {
        playlistService.createPlaylist(playlist);
        return "redirect:/playlists";
    }

    @GetMapping("/{id}/edit")
    public String editPlaylist(@PathVariable Long id, Model model) {
        Playlist playlist = playlistService.getPlaylistById(id);
        model.addAttribute("playlist", playlist);
        return "edit-playlist";
    }

    @PostMapping("/{id}/edit")
    public String updatePlaylist(@PathVariable Long id, @ModelAttribute Playlist playlist) {
        playlist.setId(id);
        playlistService.updatePlaylist(playlist);
        return "redirect:/playlists";
    }

    @GetMapping("/{id}/delete")
    public String deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return "redirect:/playlists";
    }

    @GetMapping("/{id}/add-lagu")
    public String addLaguToPlaylist(@PathVariable Long id, Model model) {
        Playlist playlist = playlistService.getPlaylistById(id);
        model.addAttribute("playlist", playlist);
        model.addAttribute("lagu", new Song());
        return "add-lagu-to-playlist";
    }

    @PostMapping("/{id}/add-lagu")
    public String addLaguToPlaylist(@PathVariable Long id, @ModelAttribute Song lagu) {
        playlistService.addLaguToPlaylist(id, lagu);
        return "redirect:/playlists/" + id;
    }

    @GetMapping("/{id}/remove-lagu/{laguId}")
    public String removeLaguFromPlaylist(@PathVariable Long id, @PathVariable Long laguId) {
        playlistService.removeLaguFromPlaylist(id, laguId);
        return "redirect:/playlists/" + id;
    }
}