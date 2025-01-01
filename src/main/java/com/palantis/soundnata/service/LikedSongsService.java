package com.palantis.soundnata.service;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.repository.PlaylistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LikedSongsService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Transactional
    public Playlist getLikedSongsPlaylist(User user) {
        return playlistRepository.findByUserAndName(user, "Liked Songs")
                .orElseGet(() -> createLikedSongsPlaylist(user));
    }

    private Playlist createLikedSongsPlaylist(User user) {
        Playlist likedSongs = new Playlist();
        likedSongs.setName("Liked Songs");
        likedSongs.setDescription("Your liked songs");
        likedSongs.setUser(user);
        return playlistRepository.save(likedSongs);
    }

    @Transactional
    public void toggleLikedSong(User user, Song song) {
        Playlist likedSongs = getLikedSongsPlaylist(user);
        if (likedSongs.getSongs().contains(song)) {
            likedSongs.getSongs().remove(song);
        } else {
            likedSongs.getSongs().add(song);
        }
        playlistRepository.save(likedSongs);
    }

    public boolean isLiked(User user, Song song) {
        Playlist likedSongs = getLikedSongsPlaylist(user);
        return likedSongs.getSongs().contains(song);
    }
}