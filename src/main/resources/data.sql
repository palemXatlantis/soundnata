INSERT IGNORE INTO song (title, artist, file_path, duration, image_path, lyrics) VALUES
('Telkom Remix', 'Lil Pung', '/songs/telkom_remix.mp3', 200,'/images/question_mark.jpg', ""),
('Tentakel', 'Morgue Vanguard & Mardial', '/songs/tentakel.mp3', 200,'/images/albums/tentakel.jpg', ""),
("Wesley's Theory", 'Kendrick Lamar', '/songs/wesley_theory.mp3', 225, '/images/albums/to_pimp_a_butterfly.png', ""),
('Bohemian Rhapsody', 'Queen', '/songs/bohemian_rhapsody.mp3', 300, '/images/albums/bohemian_rhapsody.jpg', ""),
('Billie Jean', 'Michael Jackson', '/songs/billie_jean.mp3', 200, '/images/albums/thriller.jpg', ""),
('Yowisben', 'Bayu Skak', '/songs/Yowisben.mp3', 200,'/images/fight.jpg', "");

UPDATE song
SET lyrics = CONCAT(
             "No lyrics available", CHAR(13), CHAR(10)
             )
WHERE title = 'Telkom Remix' AND artist = 'Lil Pung';

UPDATE song
SET lyrics = CONCAT(
        "No lyrics available", CHAR(13), CHAR(10)
             )
WHERE title = 'Telkom Remix' AND artist = 'Lil Pung';

UPDATE song
SET lyrics = CONCAT(
        "[Intro]", CHAR(13), CHAR(10),
        "Every n**** is a star", CHAR(13), CHAR(10),
        "Every n**** is a star", CHAR(13), CHAR(10),
        "Hey, when the four corners of this cocoon collide", CHAR(13), CHAR(10),
        "You'll slip through the cracks hopin' that you'll survive", CHAR(13), CHAR(10),
        "Gather your wit, take a deep look inside", CHAR(13), CHAR(10),
        "Are you really who they idolize?", CHAR(13), CHAR(10),
        " "
        "[Verse 1]", CHAR(13), CHAR(10),
        "To pimp a butterfly", CHAR(13), CHAR(10),
        "(My latest, greatest inspiration)", CHAR(13), CHAR(10),
        "I reminisce over Compton and what I missed", CHAR(13), CHAR(10),
        "The palm trees sway, the gangsta shade, it's a trip", CHAR(13), CHAR(10),
        "The 80s was strong, the crack epidemic prevalent", CHAR(13), CHAR(10),
        "Money was the element, separation evident", CHAR(13), CHAR(10),
        "It's to the point where (My latest, greatest inspiration)", CHAR(13), CHAR(10),
        " "
        "[Pre-Chorus]", CHAR(13), CHAR(10),
        "Taxman comin', taxman comin'", CHAR(13), CHAR(10),
        "Taxman comin', taxman comin'", CHAR(13), CHAR(10),
        "Alright", CHAR(13), CHAR(10),
        " "
        "[Chorus]", CHAR(13), CHAR(10),
        "We should've never gave n****s money", CHAR(13), CHAR(10),
        "Go back home", CHAR(13), CHAR(10),
        "Money, go back home", CHAR(13), CHAR(10),
        "We should've never gave n****s money", CHAR(13), CHAR(10),
        "Go back home", CHAR(13), CHAR(10),
        "Money, go back home", CHAR(13), CHAR(10),
        " "
        "[Bridge]", CHAR(13), CHAR(10),
        "Oh, America, you bad b****", CHAR(13), CHAR(10),
        "I picked cotton that made you rich", CHAR(13), CHAR(10),
        "Now my dick ain't free", CHAR(13), CHAR(10),
        "I'ma get my Uncle Sam to fuck you up", CHAR(13), CHAR(10),
        "You ain't no king to me", CHAR(13), CHAR(10),
        "This is the big payback", CHAR(13), CHAR(10),
        " "
        "[Outro]", CHAR(13), CHAR(10),
        "The ghost of Mandela, hope my flows they propel it", CHAR(13), CHAR(10),
        "Let these words be your Earth and moon, you consume every message", CHAR(13), CHAR(10),
        "As I lead this army, make room for mistakes and depression", CHAR(13), CHAR(10),
        "And with that being said, let me ask this question...", CHAR(13), CHAR(10)
)
WHERE title = "Wesley's Theory" AND artist = 'Kendrick Lamar';

UPDATE song
SET lyrics = CONCAT(
        "[Verse 1]", CHAR(13), CHAR(10),
        "She was more like a beauty queen from a movie scene", CHAR(13), CHAR(10),
        "I said, \"Don't mind, but what do you mean, I am the one", CHAR(13), CHAR(10),
        "Who will dance on the floor in the round?\"", CHAR(13), CHAR(10),
        "She said I am the one", CHAR(13), CHAR(10),
        "Who will dance on the floor in the round", CHAR(13), CHAR(10),
        "She told me her name was Billie Jean as she caused a scene", CHAR(13), CHAR(10),
        "Then every head turned with eyes that dreamed of bein' the one", CHAR(13), CHAR(10),
        "Who will dance on the floor in the round\"", CHAR(13), CHAR(10),
        " "
        "[Pre-Chorus]", CHAR(13), CHAR(10),
        "People always told me, \"Be careful of what you do", CHAR(13), CHAR(10),
        "Don't go around breakin' young girls' hearts\"", CHAR(13), CHAR(10),
        "And mother always told me, \"Be careful of who you love", CHAR(13), CHAR(10),
        "And be careful of what you do 'cause the lie becomes the truth\"", CHAR(13), CHAR(10),
        " "
        "[Chorus]", CHAR(13), CHAR(10),
        "Billie Jean is not my lover", CHAR(13), CHAR(10),
        "She's just a girl who claims that I am the one", CHAR(13), CHAR(10),
        "But the kid is not my son", CHAR(13), CHAR(10),
        "She says I am the one", CHAR(13), CHAR(10),
        "But the kid is not my son", CHAR(13), CHAR(10),
        " "
        "[Verse 2]", CHAR(13), CHAR(10),
        "For forty days and for forty nights", CHAR(13), CHAR(10),
        "The law was on her side", CHAR(13), CHAR(10),
        "But who can stand when she's in demand?", CHAR(13), CHAR(10),
        "Her schemes and plans", CHAR(13), CHAR(10),
        "'Cause we danced on the floor in the round", CHAR(13), CHAR(10),
        "So take my strong advice", CHAR(13), CHAR(10),
        "Just remember to always think twice", CHAR(13), CHAR(10),
        "(Don't think twice!)", CHAR(13), CHAR(10),
        " "
        "[Bridge]", CHAR(13), CHAR(10),
        "She told, \"My baby, we'd danced 'til three\"", CHAR(13), CHAR(10),
        "Then she looked at me", CHAR(13), CHAR(10),
        "Then showed a photo of a baby cryin'", CHAR(13), CHAR(10),
        "His eyes were like mine", CHAR(13), CHAR(10),
        "(Oh, no!)", CHAR(13), CHAR(10),
        "'Cause we danced on the floor in the round", CHAR(13), CHAR(10),
        " "
        "[Pre-Chorus]", CHAR(13), CHAR(10),
        "People always told me, \"Be careful of what you do", CHAR(13), CHAR(10),
        "And don't go around breakin' young girls' hearts\"", CHAR(13), CHAR(10),
        "(Don't break no hearts!)", CHAR(13), CHAR(10),
        "But she came and stood right by me", CHAR(13), CHAR(10),
        "Just the smell of sweet perfume", CHAR(13), CHAR(10),
        "This happened much too soon", CHAR(13), CHAR(10),
        "She called me to her room", CHAR(13), CHAR(10),
        " "
        "[Chorus]", CHAR(13), CHAR(10),
        "Billie Jean is not my lover", CHAR(13), CHAR(10),
        "She's just a girl who claims that I am the one", CHAR(13), CHAR(10),
        "But the kid is not my son", CHAR(13), CHAR(10),
        "(No, no, no!)", CHAR(13), CHAR(10),
        "She says I am the one", CHAR(13), CHAR(10),
        "But the kid is not my son"
             )
WHERE title = 'Billie Jean' AND artist = 'Michael Jackson';

UPDATE song
SET lyrics = CONCAT(
        "[Intro]", CHAR(13), CHAR(10),
        "Is this the real life? Is this just fantasy?", CHAR(13), CHAR(10),
        "Caught in a landslide, no escape from reality", CHAR(13), CHAR(10),
        "Open your eyes, look up to the skies and see", CHAR(13), CHAR(10),
        "I'm just a poor boy, I need no sympathy", CHAR(13), CHAR(10),
        "Because I'm easy come, easy go, little high, little low", CHAR(13), CHAR(10),
        "Any way the wind blows doesn't really matter to me, to me", CHAR(13), CHAR(10),
        ""
        "[Verse 1]", CHAR(13), CHAR(10),
        "Mama, just killed a man", CHAR(13), CHAR(10),
        "Put a gun against his head, pulled my trigger, now he's dead", CHAR(13), CHAR(10),
        "Mama, life had just begun", CHAR(13), CHAR(10),
        "But now I've gone and thrown it all away", CHAR(13), CHAR(10),
        "Mama, ooh, didn't mean to make you cry", CHAR(13), CHAR(10),
        "If I'm not back again this time tomorrow", CHAR(13), CHAR(10),
        "Carry on, carry on, as if nothing really matters", CHAR(13), CHAR(10),
        ""
        "[Verse 2]", CHAR(13), CHAR(10),
        "Too late, my time has come", CHAR(13), CHAR(10),
        "Sends shivers down my spine, body's aching all the time", CHAR(13), CHAR(10),
        "Goodbye, everybody, I've got to go", CHAR(13), CHAR(10),
        "Gotta leave you all behind and face the truth", CHAR(13), CHAR(10),
        "Mama, ooh, I don't want to die", CHAR(13), CHAR(10),
        "I sometimes wish I'd never been born at all", CHAR(13), CHAR(10),
        " "
        "[Guitar Solo]", CHAR(13), CHAR(10),
        ""
        "[Verse 3]", CHAR(13), CHAR(10),
        "I see a little silhouetto of a man", CHAR(13), CHAR(10),
        "Scaramouche, Scaramouche, will you do the Fandango?", CHAR(13), CHAR(10),
        "Thunderbolt and lightning, very, very frightening me", CHAR(13), CHAR(10),
        "(Galileo) Galileo, (Galileo) Galileo, Galileo Figaro, magnifico", CHAR(13), CHAR(10),
        "I'm just a poor boy, nobody loves me", CHAR(13), CHAR(10),
        "He's just a poor boy from a poor family", CHAR(13), CHAR(10),
        "Spare him his life from this monstrosity", CHAR(13), CHAR(10),
        "Easy come, easy go, will you let me go?", CHAR(13), CHAR(10),
        "Bismillah! No, we will not let you go (Let him go!)", CHAR(13), CHAR(10),
        "Bismillah! We will not let you go (Let him go!)", CHAR(13), CHAR(10),
        "Bismillah! We will not let you go (Let me go!)", CHAR(13), CHAR(10),
        "Will not let you go (Let me go!)", CHAR(13), CHAR(10),
        "Never let you go (Never, never, never, never let me go!)", CHAR(13), CHAR(10),
        "Ah, no, no, no, no, no, no, no", CHAR(13), CHAR(10),
        "Oh, mamma mia, mamma mia (Mamma mia, let me go)", CHAR(13), CHAR(10),
        "Beelzebub has a devil put aside for me, for me, for me!", CHAR(13), CHAR(10),
        ""
        "[Outro]", CHAR(13), CHAR(10),
        "So you think you can stop me and spit in my eye?", CHAR(13), CHAR(10),
        "So you think you can love me and leave me to die?", CHAR(13), CHAR(10),
        "Oh, baby, can't do this to me, baby!", CHAR(13), CHAR(10),
        "Just gotta get out, just gotta get right outta here", CHAR(13), CHAR(10),
        ""
        "[Ending]", CHAR(13), CHAR(10),
        "(Ooh, ooh, ooh, ooh, ooh)", CHAR(13), CHAR(10),
        "Nothing really matters, anyone can see", CHAR(13), CHAR(10),
        "Nothing really matters", CHAR(13), CHAR(10),
        "Nothing really matters to me", CHAR(13), CHAR(10),
        "Any way the wind blows"
             )
WHERE title = 'Bohemian Rhapsody' AND artist = 'Queen';
