package service;

import model.Favorite;
import model.Song;
import model.User;
import repository.FavoriteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getFavoritesByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    public void addToFavorites(User user, Song song) {
        if (!favoriteRepository.existsByUserAndSong(user, song)) {
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setSong(song);
            favoriteRepository.save(favorite);
        }
    }

    public void removeFromFavorites(User user, Song song) {
        favoriteRepository.findByUser(user).stream()
            .filter(favorite -> favorite.getSong().equals(song))
            .findFirst()
            .ifPresent(favoriteRepository::delete);
    }
}
