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

    public Playlist updatePlaylist(Long playlistId, String newTitle, String newDescription) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        // Validasi user yang sedang login adalah pemilik playlist
        User currentUser = userService.getLoggedInUser();
        if (!playlist.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You don't have permission to update this playlist");
        }

        // Update judul playlist
        playlist.setName(newTitle);
        playlist.setDescription(newDescription);
        return playlistRepository.save(playlist);
    }

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
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found with id: " + id));

        User loggedInUser = userService.getLoggedInUser();
        if (!playlist.getUser().equals(loggedInUser)) {
            throw new IllegalStateException("You don't have permission to delete this playlist");
        }

        playlist.getSongs().clear();
        playlistRepository.save(playlist);

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
