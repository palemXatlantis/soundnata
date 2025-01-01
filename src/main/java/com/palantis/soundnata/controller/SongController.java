package com.palantis.soundnata.controller;

import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.service.LikedSongsService;
import com.palantis.soundnata.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class SongController {
    @Autowired
    private LikedSongsService likedSongsService;


    @Autowired
    private SongService songService;

    @GetMapping("/song/is-liked")
    @ResponseBody
    public boolean isLiked(
            @AuthenticationPrincipal User user,
            @RequestParam Long songId
    ) {
        Optional<Song> song = songService.getSongById(songId);
        return likedSongsService.isLiked(user, song.orElse(null));
    }

    // Make sure you have the toggle-like endpoint from the previous example
    @PostMapping("/song/toggle-like")
    @ResponseBody
    public String toggleLike(
            @AuthenticationPrincipal User user,
            @RequestParam Long songId
    ) {
        Optional<Song> song = songService.getSongById(songId);
        likedSongsService.toggleLikedSong(user, song.orElse(null));
        boolean isLiked = likedSongsService.isLiked(user, song.orElse(null));
        return isLiked ? "liked" : "unliked";
    }
}
