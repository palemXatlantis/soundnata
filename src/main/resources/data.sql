INSERT IGNORE INTO song (title, artist, file_path, duration, image_path, lyrics) VALUES
('Telkom Remix', 'Lil Pung', '/songs/telkom_remix.mp3', 200,'/images/question_mark.jpg', "No Lyrics"),
('Tentakel', 'Morgue Vanguard & Mardial', '/songs/tentakel.mp3', 200,'/images/albums/tentakel.jpg', ""),
("Wesley's Theory", 'Kendrick Lamar', '/songs/wesley_theory.mp3', 225, '/images/albums/to_pimp_a_butterfly.png', ""),
('Bohemian Rhapsody', 'Queen', '/songs/bohemian_rhapsody.mp3', 300, '/images/albums/bohemian_rhapsody.jpg', ""),
('Billie Jean', 'Michael Jackson', '/songs/billie_jean.mp3', 200, '/images/albums/thriller.jpg', ""),
('Yowisben', 'Bayu Skak', '/songs/Yowisben.mp3', 200,'/images/fight.jpg', "");

UPDATE song
SET lyrics = CONCAT(
        "[Verse 1]", CHAR(13), CHAR(10),
        "She was more like a beauty queen from a movie scene", CHAR(13), CHAR(10),
        "I said, \"Don't mind, but what do you mean, I am the one", CHAR(13), CHAR(10),
        "Who will dance on the floor in the round?\""
             )
WHERE title = 'Billie Jean' AND artist = 'Michael Jackson';

