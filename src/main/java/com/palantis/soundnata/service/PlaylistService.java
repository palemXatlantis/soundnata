package com.palantis.soundnata.service;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.repository.PlaylistRepository;
import com.palantis.soundnata.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserService userService;

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    public Playlist createPlaylist() {
        User loggedInUser = userService.getLoggedInUser();
        String playlistName = "My Playlist #" + (playlistRepository.countByUser(loggedInUser) + 1);

        Playlist playlist = new Playlist();
        playlist.setName(playlistName);
        playlist.setDescription("");
        playlist.setUser(loggedInUser);

        return playlistRepository.save(playlist);
    }

    public Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = getPlaylistById(playlistId);
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new ExpressionException("Song not found"));

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        }
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            Song song = songRepository.findById(songId).orElse(null);
            if (song != null) {
                playlist.getSongs().remove(song);
                playlistRepository.save(playlist);
            }
        }
    }

    public int countSongsInPlaylist(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            return playlist.getSongs().size();
        }
        return 0;
    }

    public List<Playlist> getPlaylistsForLoggedInUser() {
        User user = userService.getLoggedInUser();
        return playlistRepository.findByUser(user);
    }

    public List<Song> getSongsInPlaylist(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            return playlist.getSongs();
        }
        return new ArrayList<>();
    }

}
