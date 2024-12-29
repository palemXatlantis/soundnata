package com.palantis.soundnata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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

    @Lob // Large Object for handling long text data
    @Column(columnDefinition = "TEXT")
    private String lyrics;

    public String getFormattedDuration() {
        if (duration == null) return "--:--";

        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    @JsonBackReference
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();
}
