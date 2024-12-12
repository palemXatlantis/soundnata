package com.palantis.soundnata.service;

import com.palantis.soundnata.repository.PlaylistRepository;
import com.palantis.soundnata.repository.SongRepository;
import com.palantis.soundnata.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    public Map<String, List<?>> search(String keyword) {
        Map<String, List<?>> results = new HashMap<>();
        results.put("songs", songRepository.findByTitleContainingIgnoreCase(keyword));
        results.put("artists", songRepository.findByArtistContainingIgnoreCase(keyword));
        results.put("playlists", playlistRepository.findByNamaContainingIgnoreCase(keyword));
        return results;
    }
}
