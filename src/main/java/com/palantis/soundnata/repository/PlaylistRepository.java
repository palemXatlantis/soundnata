package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}