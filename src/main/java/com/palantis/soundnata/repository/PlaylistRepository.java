package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Playlist;
import com.palantis.soundnata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByNameContainingIgnoreCase(String keyword);
    List<Playlist> findByUser(User user);
}