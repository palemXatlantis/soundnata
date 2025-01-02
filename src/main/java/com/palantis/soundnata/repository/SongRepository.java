package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByTitle(String title);
    List<Song> findByTitleContainingIgnoreCase(String keyword);
    List<Song> findByArtistContainingIgnoreCase(String keyword);
    List<Song> findByTitleContainingIgnoreCaseOrArtistContainingIgnoreCase(String titleKeyword, String artistKeyword);
    Optional<Song> findByTitleAndArtist(String title, String artist);
}

