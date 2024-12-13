package com.palantis.soundnata.service;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.repository.PlaylistRepository;
import com.palantis.soundnata.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public Map<String, List<?>> search(String keyword) {
//        Map<String, List<?>> results = new HashMap<>();
//        results.put("songs", songRepository.findByTitleContainingIgnoreCase(keyword));
//        results.put("artists", songRepository.findByArtistContainingIgnoreCase(keyword));
//        results.put("playlists", playlistRepository.findByNamaContainingIgnoreCase(keyword));
//        return results;
        Map<String, List<?>> results = new HashMap<>();

        // Pencarian berdasarkan lagu dan artis secara bersamaan.
        List<Song> songs = songRepository.findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(keyword, keyword);
        results.put("songs", songs);

        // Pencarian playlist (opsional, bisa menampilkan playlist jika keyword terkait).
        List<Playlist> playlists = playlistRepository.findByNameContainingIgnoreCase(keyword);
        results.put("playlists", playlists);

        return results;
    }
}
