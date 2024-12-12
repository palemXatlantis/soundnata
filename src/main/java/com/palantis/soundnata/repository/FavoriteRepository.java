package com.palantis.soundnata.repository;

import com.palantis.soundnata.model.Favorite;
import com.palantis.soundnata.model.User;
import com.palantis.soundnata.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    boolean existsByUserAndSong(User user, Song song);
}
