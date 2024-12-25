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
    let currentLyrics = ""; // Menyimpan lirik saat ini

    playPauseButton.disabled = true;
    playerProgress.disabled = true;

    // Restore Playback State
    const savedState = JSON.parse(localStorage.getItem('playbackState'));
    if (savedState) {
        audioPlayer.src = savedState.songUrl;
        audioPlayer.currentTime = savedState.currentTime;
        document.getElementById('playerTitle').textContent = savedState.songTitle || 'Unknown Title';
        document.getElementById('playerArtist').textContent = savedState.songArtist || 'Unknown Artist';
        document.getElementById('playerImage').src = savedState.songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';
    }
    // Play/Pause Logic
    audioPlayer.addEventListener('ended', () => {
        isPlaying = false;
        playPauseButton.classList.remove('pause');
    });
    playPauseButton.addEventListener('click', () => {
        if (audioPlayer.ended) {
            audioPlayer.currentTime = 0;
        }

        if (isPlaying) {
            audioPlayer.pause();
            playPauseButton.classList.remove('pause');
        } else {
            audioPlayer.play();
            playPauseButton.classList.add('pause');
        }
        isPlaying = !isPlaying;
    });

    // Progress Bar Logic
    audioPlayer.addEventListener('timeupdate', () => {
        if (audioPlayer.duration) {
            playerProgress.value = (audioPlayer.currentTime / audioPlayer.duration) * 100;

            // Save playback state periodically
            localStorage.setItem('playbackState', JSON.stringify({
                songUrl: audioPlayer.src,
                currentTime: audioPlayer.currentTime,
                songTitle: document.getElementById('playerTitle').textContent,
                songArtist: document.getElementById('playerArtist').textContent,
                songImage: document.getElementById('playerImage').src
            }));
        }
    });

    playerProgress.addEventListener('input', () => {
        audioPlayer.currentTime = (playerProgress.value / 100) * audioPlayer.duration;
    });

    // Volume Control Logic
    volumeControl.addEventListener('input', () => {
        audioPlayer.volume = volumeControl.value / 100;
    });

    // Load and Play Song
    document.querySelectorAll('.play-song').forEach(button => {
        button.addEventListener('click', () => {
            playbackInfo.classList.remove('invisible');
            playPauseButton.disabled = false;
            playerProgress.disabled = false;
            lyricButton.classList.remove('invisible');

            const songUrl = button.getAttribute('data-url');
            const songTitle = button.getAttribute('data-title');
            const songArtist = button.getAttribute('data-artist');
            const songImage = button.getAttribute('data-image');
            const songLyrics = button.getAttribute('data-lyrics');

            // Set audio player attributes
            audioPlayer.src = songUrl;
            audioPlayer.play();
            isPlaying = true;
            playPauseButton.classList.add('pause');

            document.getElementById('playerTitle').textContent = songTitle;
            document.getElementById('playerArtist').textContent = songArtist;
            document.getElementById('playerImage').src = songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';

            // Save selected song immediately
            localStorage.setItem('playbackState', JSON.stringify({
                songUrl: songUrl,
                currentTime: 0,
                songTitle: songTitle,
                songArtist: songArtist,
                songImage: songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1'
            }));
            // Update current lyrics
            currentLyrics = songLyrics || "Lyrics not available."; // Isi default jika lirik kosong

            // Hide lyrics section if switching songs
            lyricsSection.classList.add('hidden');
        });
    });

    // Toggle Lyrics Section
    lyricButton.addEventListener('click', () => {
        if (!lyricsSection.classList.contains('hidden')) {
            lyricsSection.classList.add('hidden'); // Sembunyikan jika sudah tampil
        } else {
            updateLyrics(currentLyrics); // Tampilkan lirik
            lyricsSection.classList.remove('hidden');
        }
    });

    // Update Lyrics Function
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
