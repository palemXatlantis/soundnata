package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByTitle(String title);
    List<Song> findByTitleContainingIgnoreCase(String keyword);
}

