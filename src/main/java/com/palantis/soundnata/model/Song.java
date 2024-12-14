package com.palantis.soundnata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(
        name = "song",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "artist"})
)
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Artist is required")
    private String artist;

    @NotBlank(message = "File path is required")
    private String filePath;

    @NotBlank(message = "File path is required")
    private String imagePath;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
