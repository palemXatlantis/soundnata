package com.palantis.soundnata.service;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.repository.PlaylistRepository;
import com.palantis.soundnata.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String playlistName = "My Playlist#" + (playlistRepository.count() + 1);

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

    public void addSongToPlaylist(Long playlistId, Song song) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            song.setPlaylist(playlist);
            songRepository.save(song);
        }
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            Song song = songRepository.findById(songId).orElse(null);
            if (song != null) {
                song.setPlaylist(null);
                songRepository.save(song);
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
}
