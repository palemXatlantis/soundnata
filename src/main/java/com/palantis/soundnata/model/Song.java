package com.palantis.soundnata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import java.util.HashSet;

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

    private String filePath;

    private String imagePath;

    private Integer duration;

    private String album = "Unknown Album";  // Default value
    private Integer year = null;  // Default value
    private String genre = "Unknown Genre";  // Default value
    private Integer trackNumber = null;  // Default value
    private String albumArtist = "Unknown Album Artist";  // Default value
    private String composer = "Unknown Composer";  // Default value
    private Long bitrate = 0L;  // Default value
    private Integer sampleRate = 0;  // Default value
    private String audioFormat = "Unknown Format";  // Default value

    @Lob
    @Column(name = "album_image")
    private byte[] albumImage;

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

    @ElementCollection
    @CollectionTable(name = "song_tags", joinColumns = @JoinColumn(name = "song_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();
}
