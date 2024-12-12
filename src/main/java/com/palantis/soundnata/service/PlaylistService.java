package com.palantis.soundnata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private LaguRepository laguRepository;

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist updatePlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    public void addLaguToPlaylist(Long playlistId, Lagu lagu) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            lagu.setPlaylist(playlist);
            laguRepository.save(lagu);
        }
    }

    public void removeLaguFromPlaylist(Long playlistId, Long laguId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            Lagu lagu = laguRepository.findById(laguId).orElse(null);
            if (lagu != null) {
                lagu.setPlaylist(null);
                laguRepository.save(lagu);
            }
        }
    }

    public int countLagusInPlaylist(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            return playlist.getLagus().size();
        }
        return 0;
    }
}
