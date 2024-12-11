package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByTitle(String title);
}
