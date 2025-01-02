package com.palantis.soundnata.service;

import com.palantis.soundnata.model.Song;
import com.palantis.soundnata.repository.SongRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;  // Add this import
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@Service
public class SongMetadataService {
    private static final Logger logger = LoggerFactory.getLogger(SongMetadataService.class);

    @Value("${app.song.upload.path}")
    private String songUploadPath;

    @Value("${app.default.image.path:/images/question_mark.jpg}")
    private String defaultImagePath;

    @Autowired
    private SongRepository songRepository;

    @PostConstruct
    @Transactional
    public void initializeSongsFromFiles() {
        try {
            Path uploadPath = Paths.get(songUploadPath);
            logger.info("Starting song metadata initialization from: {}", uploadPath.toAbsolutePath());
            Files.walk(uploadPath)
                    .filter(path -> path.toString().toLowerCase().endsWith(".mp3"))
                    .forEach(this::processSongFile);
        } catch (IOException e) {
            logger.error("Error scanning song directory", e);
        }
    }

    private void processSongFile(Path filePath) {
        try {
            logger.info("Processing file: {}", filePath.toAbsolutePath());
            File file = filePath.toFile();
            AudioFile audioFile = AudioFileIO.read(file);
            Tag tag = audioFile.getTag();
            AudioHeader header = audioFile.getAudioHeader();

            // Get filename without extension
            String fileName = filePath.getFileName().toString();
            String baseFileName = fileName.substring(0, fileName.lastIndexOf('.'));
            String titleFromFileName = baseFileName.replace("_", " ");

            // First check if song exists in database
            String title = getTagFieldOrDefault(tag, FieldKey.TITLE, titleFromFileName);
            String artist = getTagFieldOrDefault(tag, FieldKey.ARTIST, "Unknown Artist");

            Optional<Song> existingSong = songRepository.findByTitleAndArtist(title, artist);

            Song song = existingSong.orElseGet(Song::new);

            // Set basic metadata
            song.setTitle(title);
            song.setArtist(artist);
            song.setFilePath("/songs/" + fileName);
            song.setImagePath(defaultImagePath);
            handleAlbumArtwork(filePath, tag, song);


            // Set other metadata fields...
            setBasicMetadata(song, tag, header);

            // Handle lyrics with detailed logging
            String lyrics = null;

            // Check .lrc file first
            Path lrcPath = filePath.resolveSibling(baseFileName + ".lrc");
            logger.info("Looking for LRC file at: {}", lrcPath.toAbsolutePath());

            if (Files.exists(lrcPath)) {
                try {
                    lyrics = Files.readString(lrcPath);
                    logger.info("Successfully read lyrics from LRC file: {}", lrcPath.getFileName());
                } catch (IOException e) {
                    logger.error("Error reading LRC file: {}", lrcPath, e);
                }
            } else {
                logger.info("No LRC file found at: {}", lrcPath.toAbsolutePath());
            }

            // If no LRC file, try ID3 tag
            if (lyrics == null || lyrics.isEmpty()) {
                try {
                    lyrics = tag.getFirst(FieldKey.LYRICS);
                    if (!lyrics.isEmpty()) {
                        logger.info("Found lyrics in ID3 tag for: {}", fileName);
                    }
                } catch (Exception e) {
                    logger.debug("No lyrics in ID3 tag for: {}", fileName);
                }
            }

            // Set final lyrics
            if (lyrics != null && !lyrics.isEmpty()) {
                song.setLyrics(lyrics);
                logger.info("Set lyrics for: {} - {}", title, artist);
            } else {
                song.setLyrics("No lyrics available");
                logger.info("No lyrics found for: {} - {}", title, artist);
            }

            // Save to database
            songRepository.save(song);
            logger.info("Saved song: {} - {}", song.getTitle(), song.getArtist());

        } catch (Exception e) {
            logger.error("Error processing file: " + filePath, e);
        }
    }

    private void handleAlbumArtwork(Path filePath, Tag tag, Song song) {
        try {
            Artwork artwork = tag.getFirstArtwork();
            if (artwork != null) {
                byte[] imageData = artwork.getBinaryData();

                // Save image to disk
                String imageFileName = filePath.getFileName().toString().replace(".mp3", ".jpg");
                Path imagePath = Paths.get(songUploadPath, "images", imageFileName);

                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, imageData);

                // Set imagePath in song
                song.setImagePath("/images/albums/" + imageFileName);

                logger.info("Album artwork saved to: {}", imagePath.toAbsolutePath());
            } else {
                logger.info("No album artwork found for: {}", filePath.getFileName());
            }
        } catch (Exception e) {
            logger.error("Error processing album artwork for: " + filePath.getFileName(), e);
        }
    }

    private void setBasicMetadata(Song song, Tag tag, AudioHeader header) {
        song.setAlbum(getTagFieldOrDefault(tag, FieldKey.ALBUM, "Unknown Album"));
        song.setAlbumArtist(getTagFieldOrDefault(tag, FieldKey.ALBUM_ARTIST, "Unknown Album Artist"));
        song.setComposer(getTagFieldOrDefault(tag, FieldKey.COMPOSER, "Unknown Composer"));
        song.setGenre(getTagFieldOrDefault(tag, FieldKey.GENRE, "Unknown Genre"));

        try {
            song.setYear(Integer.parseInt(tag.getFirst(FieldKey.YEAR)));
        } catch (Exception ignored) {}

        try {
            song.setTrackNumber(Integer.parseInt(tag.getFirst(FieldKey.TRACK)));
        } catch (Exception ignored) {}

        if (header != null) {
            song.setDuration(header.getTrackLength());
            song.setBitrate(Long.valueOf(header.getBitRateAsNumber()));
            song.setSampleRate(header.getSampleRateAsNumber());
            song.setAudioFormat(header.getFormat());
        }
    }

    private String getTagFieldOrDefault(Tag tag, FieldKey key, String defaultValue) {
        try {
            String value = tag.getFirst(key);
            return value.isEmpty() ? defaultValue : value;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
