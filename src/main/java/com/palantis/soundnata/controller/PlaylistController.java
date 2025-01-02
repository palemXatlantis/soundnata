package com.palantis.soundnata.controller;


import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.service.PlaylistService;
import com.palantis.soundnata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/{playlistId}/remove-song/{songId}")
    @ResponseBody
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        try {
            playlistService.removeSongFromPlaylist(playlistId, songId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/delete")
    public String deletePlaylist(@PathVariable Long id, Model model) {
        try {
            playlistService.deletePlaylist(id);
            return "redirect:/"; // Redirect ke halaman daftar playlist
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error"; // Redirect ke halaman error jika ada masalah
        }
    }

    @PostMapping("/{id}/update")
    @ResponseBody
    public ResponseEntity<?> updatePlaylistTitle(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            String newTitle = payload.get("title");
            String newDescription = payload.get("description");
            if (newTitle == null || newTitle.trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "error",
                        "message", "Title cannot be empty"
                ));
            }

            Playlist updatedPlaylist = playlistService.updatePlaylist(id, newTitle.trim(), newDescription.trim());
            return ResponseEntity.ok().body(Map.of(
                    "status", "success",
                    "title", updatedPlaylist.getName()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

}