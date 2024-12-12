package com.palantis.soundnata.service;

package com.example.playlistmanagement.service;

import com.example.playlistmanagement.model.Song;
import com.example.playlistmanagement.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private SongRepository songRepository;

    // Tambah lagu baru
    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    // Dapatkan semua lagu
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // Dapatkan lagu berdasarkan ID
    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    // Edit lagu
    public Song updateSong(Song song) {
        return songRepository.save(song);
    }

    // Hapus lagu
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    // Hitung total lagu
    public int getTotalSongCount() {
        return songRepository.getTotalSongCount();
    }

    // Hitung total durasi playlist
    public int getTotalPlaylistDuration() {
        return songRepository.getTotalPlaylistDuration();
    }

    // Konversi durasi detik ke format mm:ss
    public String formatDuration(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}