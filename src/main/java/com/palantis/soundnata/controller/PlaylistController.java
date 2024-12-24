package com.palantis.soundnata.controller;


import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.service.PlaylistService;
import com.palantis.soundnata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllPlaylists(Model model) {
        List<Playlist> playlists = playlistService.getAllPlaylists();
        model.addAttribute("playlists", playlists);
        return "playlist";
    }

    @GetMapping("/{id}")
    public String getPlaylistById(@PathVariable Long id, Model model) {
        Playlist playlist = playlistService.getPlaylistById(id);
        model.addAttribute("playlist", playlist);
        List<Song> songs = playlistService.getSongsInPlaylist(id);
        List<Playlist> playlists = playlistService.getPlaylistsForLoggedInUser();
        model.addAttribute("playlists", playlists);
        model.addAttribute("songs", songs);
        return "playlist";
    }

    @GetMapping("/{id}/songs")
    @ResponseBody
    public List<Song> getSongsInPlaylist(@PathVariable Long id) {
        return playlistService.getSongsInPlaylist(id);
    }

    @PostMapping("/create")
    public String createPlaylist(Model model) {
        Playlist newPlaylist = playlistService.createPlaylist();

        return "redirect:/playlist/" + newPlaylist.getId();
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

    @PostMapping("/{playlistId}/add-song/{songId}")
    @ResponseBody
    public Map<String, String> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        try {
            playlistService.addSongToPlaylist(playlistId, songId);
            return Map.of("status", "success");
        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }


    @GetMapping("/{id}/remove-lagu/{laguId}")
    public String removeLaguFromPlaylist(@PathVariable Long id, @PathVariable Long laguId) {
        playlistService.removeSongFromPlaylist(id, laguId);
        return "redirect:/playlists/" + id;
    }
}