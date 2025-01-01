document.addEventListener('DOMContentLoaded', () => {
    const audioPlayer = document.getElementById('audioPlayer');
    const playPauseButton = document.getElementById('playPauseButton');
    const playerProgress = document.getElementById('playerProgress');
    const volumeControl = document.getElementById('volumeControl');
    const playbackInfo = document.getElementById('playbackInfo');
    const lyricButton = document.getElementById('lyricButton');
    const lyricsSection = document.getElementById('lyricsSection');
    const lyricsContent = document.getElementById('lyricsContent');
    let isPlaying = false;
    let currentLyrics = "";
    let currentSongUrl = "";

    // Initialize controls
    playPauseButton.disabled = true;
    playerProgress.disabled = true;

    // Save state function
    const savePlaybackState = () => {
        const state = {
            songUrl: audioPlayer.src,
            currentTime: 0, // Always save with time 0
            songTitle: document.getElementById('playerTitle').textContent,
            songArtist: document.getElementById('playerArtist').textContent,
            songImage: document.getElementById('playerImage').src,
            isPlaying: false // Never save as playing
        };
        localStorage.setItem('playbackState', JSON.stringify(state));
    };

    document.addEventListener('play-song', (event) => {
        const { url, title, artist, image } = event.detail;

        // Show controls
        const playbackInfo = document.getElementById('playbackInfo');
        const playPauseButton = document.getElementById('playPauseButton');
        const playerProgress = document.getElementById('playerProgress');
        const lyricButton = document.getElementById('lyricButton');

        playbackInfo.classList.remove('invisible');
        playPauseButton.disabled = false;
        playerProgress.disabled = false;
        lyricButton.classList.remove('invisible');

        // Update display info
        document.getElementById('playerTitle').textContent = title;
        document.getElementById('playerArtist').textContent = artist;
        document.getElementById('playerImage').src = image;

        // Set up audio
        const audioPlayer = document.getElementById('audioPlayer');
        audioPlayer.src = url;
        audioPlayer.load();

        // Play and update UI
        audioPlayer.play().then(() => {
            isPlaying = true;
            playPauseButton.classList.add('pause');
            savePlaybackState();
        }).catch(error => console.error('Playback failed:', error));
    });

    // Load and Play Song
    document.querySelectorAll('.play-song').forEach(button => {
        button.addEventListener('click', () => {
            const songUrl = button.getAttribute('data-url');
            const songTitle = button.getAttribute('data-title');
            const songArtist = button.getAttribute('data-artist');
            const songImage = button.getAttribute('data-image');
            const songLyrics = button.getAttribute('data-lyrics');

            // Reset player state
            audioPlayer.currentTime = 0;
            playerProgress.value = 0;
            currentSongUrl = songUrl;

            // Show controls
            playbackInfo.classList.remove('invisible');
            playPauseButton.disabled = false;
            playerProgress.disabled = false;
            lyricButton.classList.remove('invisible');

            // Update display info
            document.getElementById('playerTitle').textContent = songTitle;
            document.getElementById('playerArtist').textContent = songArtist;
            document.getElementById('playerImage').src = songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';

            // Set up audio
            audioPlayer.src = songUrl;
            audioPlayer.load();

            // Play and update UI
            audioPlayer.play().then(() => {
                isPlaying = true;
                playPauseButton.classList.add('pause');
                savePlaybackState();
            }).catch(error => console.error('Playback failed:', error));

            currentLyrics = songLyrics || "Lyrics not available.";
            lyricsSection.classList.add('hidden');
        });
    });

    // Restore Playback State
    const savedState = JSON.parse(localStorage.getItem('playbackState'));
    if (savedState && savedState.songUrl) {
        // Show controls
        playbackInfo.classList.remove('invisible');
        playPauseButton.disabled = false;
        playerProgress.disabled = false;
        lyricButton.classList.remove('invisible');

        // Restore display info
        currentSongUrl = savedState.songUrl;
        audioPlayer.src = savedState.songUrl;
        document.getElementById('playerTitle').textContent = savedState.songTitle || 'Unknown Title';
        document.getElementById('playerArtist').textContent = savedState.songArtist || 'Unknown Artist';
        document.getElementById('playerImage').src = savedState.songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';

        // Just load the audio without playing
        audioPlayer.load();
        audioPlayer.currentTime = 0;
        playerProgress.value = 0;

        // Update UI to paused state
        isPlaying = false;
        playPauseButton.classList.remove('pause');
    }

    // Regular playback events
    audioPlayer.addEventListener('timeupdate', () => {
        if (audioPlayer.duration) {
            playerProgress.value = (audioPlayer.currentTime / audioPlayer.duration) * 100;
        }
    });

    // Play/Pause button logic
    playPauseButton.addEventListener('click', () => {
        if (!isPlaying) {
            audioPlayer.play().then(() => {
                isPlaying = true;
                playPauseButton.classList.add('pause');
            }).catch(error => console.error('Playback failed:', error));
        } else {
            audioPlayer.pause();
            isPlaying = false;
            playPauseButton.classList.remove('pause');
        }
        savePlaybackState();
    });

    // Progress bar seek
    playerProgress.addEventListener('input', () => {
        const time = (playerProgress.value / 100) * audioPlayer.duration;
        audioPlayer.currentTime = time;
    });

    // Volume control
    volumeControl.addEventListener('input', () => {
        audioPlayer.volume = volumeControl.value / 100;
        localStorage.setItem('volumeLevel', volumeControl.value);
    });

    // Restore volume
    const savedVolume = localStorage.getItem('volumeLevel');
    if (savedVolume !== null) {
        audioPlayer.volume = savedVolume / 100;
        volumeControl.value = savedVolume;
    }

    // Lyrics Toggle Logic
    lyricButton.addEventListener('click', () => {
        if (!lyricsSection.classList.contains('hidden')) {
            lyricsSection.classList.add('hidden');
        } else {
            updateLyrics(currentLyrics);
            lyricsSection.classList.remove('hidden');
        }
    });

    function updateLyrics(lyrics) {
        if (!lyrics || lyrics.trim() === '') {
            lyricsContent.innerHTML = '<p>Lyrics not available.</p>';
            return;
        }
        const lyricsLines = lyrics.split('\n');
        lyricsContent.innerHTML = lyricsLines
            .map(line => `<p>${line.trim()}</p>`)
            .join('');
    }
});