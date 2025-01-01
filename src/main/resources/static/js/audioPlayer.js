document.addEventListener('DOMContentLoaded', function() {
    const audioPlayer = document.getElementById('audioPlayer');
    const playPauseButton = document.getElementById('playPauseButton');
    const playerProgress = document.getElementById('playerProgress');
    const volumeControl = document.getElementById('volumeControl');
    const playbackInfo = document.getElementById('playbackInfo');
    const lyricButton = document.getElementById('lyricButton');
    const lyricsSection = document.getElementById('lyricsSection');
    const lyricsContent = document.getElementById('lyricsContent');
    const likeButton = document.getElementById('likeButton');

    // Player state
    let isPlaying = false;
    let currentLyrics = "";
    let currentSongUrl = "";
    let currentSongId = null;
    let playlist = [];
    let currentSongIndex = 0;

    // Initialize controls
    playPauseButton.disabled = true;
    playerProgress.disabled = true;

    // Initialize playlist from table rows
    function initializePlaylist() {
        // Try to get playlist from table rows first (playlist page)
        const tableRows = document.querySelectorAll('tr[data-song-url]');
        if (tableRows.length > 0) {
            playlist = Array.from(tableRows).map(row => ({
                url: row.getAttribute('data-song-url'),
                title: row.getAttribute('data-song-title'),
                artist: row.getAttribute('data-song-artist'),
                image: row.querySelector('img')?.src,
                songId: row.getAttribute('data-song-id')
            }));
        } else {
            // Try to get playlist from cards (home page)
            const playButtons = document.querySelectorAll('.play-song');
            if (playButtons.length > 0) {
                playlist = Array.from(playButtons).map(button => ({
                    url: button.getAttribute('data-url'),
                    title: button.getAttribute('data-title'),
                    artist: button.getAttribute('data-artist'),
                    image: button.getAttribute('data-image'),
                    songId: button.getAttribute('data-id'),
                    lyrics: button.getAttribute('data-lyrics')
                }));
            }
        }
    }
    // Add play buttons to each row
    function initializePlayButtons() {
        // For table rows (playlist page)
        document.querySelectorAll('tr[data-song-url]').forEach((row, index) => {
            const playButton = document.createElement('button');
            playButton.className = 'play-song-button text-white hover:text-green-500 transition-colors mr-2 px-2';
            playButton.textContent = '▶';

            playButton.addEventListener('click', (e) => {
                e.stopPropagation();
                currentSongIndex = index;
                playSongFromPlaylist(index);
            });

            const firstCell = row.querySelector('td');
            if (firstCell) {
                firstCell.insertBefore(playButton, firstCell.firstChild);
            }
        });

        // For cards (home page)
        document.querySelectorAll('.play-song').forEach((button, index) => {
            button.addEventListener('click', (e) => {
                e.preventDefault();
                currentSongIndex = index;
                playSongFromPlaylist(index);
            });
        });

        // Initialize Play All button if it exists
        const playAllButton = document.getElementById('playAllButton');
        if (playAllButton) {
            playAllButton.addEventListener('click', () => {
                if (playlist.length > 0) {
                    currentSongIndex = 0;
                    playSongFromPlaylist(0);
                }
            });
        }
    }


    function updatePlayButtonStates(activeIndex) {
        // Update table row buttons
        document.querySelectorAll('.play-song-button').forEach((button, index) => {
            button.textContent = index === activeIndex ? '❚❚' : '▶';
            button.classList.toggle('text-green-500', index === activeIndex);
        });

        // Update card buttons
        document.querySelectorAll('.play-song').forEach((button, index) => {
            const iconDiv = button.querySelector('.icon');
            if (iconDiv) {
                iconDiv.classList.toggle('playing', index === activeIndex);
            }
        });
    }

    function playSongFromPlaylist(index) {
        const song = playlist[index];
        if (!song) return;

        updatePlayButtonStates(index);

        const playEvent = new CustomEvent('play-song', {
            detail: {
                url: song.url,
                title: song.title,
                artist: song.artist,
                image: song.image,
                songId: song.songId,
                lyrics: song.lyrics
            }
        });
        document.dispatchEvent(playEvent);
        currentLyrics = song.lyrics;
    }

    function updateLikeButton(isLiked) {
        likeButton.textContent = isLiked ? '❤️' : '🤍';
        likeButton.classList.toggle('text-red-500', isLiked);
        likeButton.classList.toggle('text-gray-500', !isLiked);
    }

    async function checkLikeStatus(songId) {
        try {
            const response = await fetch(`/song/is-liked?songId=${songId}`);
            const isLiked = await response.json();
            updateLikeButton(isLiked);
        } catch (error) {
            console.error('Error checking like status:', error);
            updateLikeButton(false);
        }
    }

    // Save playback state function
    const savePlaybackState = () => {
        const state = {
            songUrl: audioPlayer.src,
            currentTime: 0, // Always save with time 0
            songTitle: document.getElementById('playerTitle').textContent,
            songArtist: document.getElementById('playerArtist').textContent,
            songImage: document.getElementById('playerImage').src,
            songId: currentSongId,
            isPlaying: false // Never save as playing
        };
        localStorage.setItem('playbackState', JSON.stringify(state));
    };

    // Event Listeners
    document.addEventListener('play-song', (event) => {
        const { url, title, artist, image, songId, lyrics } = event.detail;
        currentSongId = songId;
        currentLyrics = lyrics;

        playbackInfo.classList.remove('invisible');
        playPauseButton.disabled = false;
        playerProgress.disabled = false;
        lyricButton.classList.remove('invisible');

        document.getElementById('playerTitle').textContent = title;
        document.getElementById('playerArtist').textContent = artist;
        document.getElementById('playerImage').src = image;

        checkLikeStatus(songId);

        audioPlayer.src = url;
        audioPlayer.load();
        audioPlayer.play().then(() => {
            isPlaying = true;
            playPauseButton.classList.add('pause');
            savePlaybackState();
        }).catch(error => console.error('Playback failed:', error));
    });

    // Handle next/previous from audio player controls
    document.getElementById('nextButton').addEventListener('click', () => {
        if (playlist.length === 0) return;
        currentSongIndex = (currentSongIndex + 1) % playlist.length;
        playSongFromPlaylist(currentSongIndex);
    });

    document.getElementById('prevButton').addEventListener('click', () => {
        if (playlist.length === 0) return;
        currentSongIndex = (currentSongIndex - 1 + playlist.length) % playlist.length;
        playSongFromPlaylist(currentSongIndex);
    });

    // Like button click handler
    likeButton.addEventListener('click', async () => {
        if (!currentSongId) return;

        try {
            const response = await fetch(`/song/toggle-like?songId=${currentSongId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').content
                }
            });
            const result = await response.text();
            updateLikeButton(result === 'liked');
        } catch (error) {
            console.error('Error toggling like:', error);
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

    // Progress bar and volume control
    audioPlayer.addEventListener('timeupdate', () => {
        if (audioPlayer.duration) {
            playerProgress.value = (audioPlayer.currentTime / audioPlayer.duration) * 100;
        }
    });

    playerProgress.addEventListener('input', () => {
        const time = (playerProgress.value / 100) * audioPlayer.duration;
        audioPlayer.currentTime = time;
    });

    volumeControl.addEventListener('input', () => {
        audioPlayer.volume = volumeControl.value / 100;
        localStorage.setItem('volumeLevel', volumeControl.value);
    });

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

    // Handle automatic next song when current song ends
    audioPlayer.addEventListener('ended', () => {
        if (playlist.length > 0) {
            currentSongIndex = (currentSongIndex + 1) % playlist.length;
            playSongFromPlaylist(currentSongIndex);
        }
    });

    // Restore volume
    const savedVolume = localStorage.getItem('volumeLevel');
    if (savedVolume !== null) {
        audioPlayer.volume = savedVolume / 100;
        volumeControl.value = savedVolume;
    }

    // Restore Playback State
    const savedState = JSON.parse(localStorage.getItem('playbackState'));
    if (savedState && savedState.songUrl) {
        // Show controls
        playbackInfo.classList.remove('invisible');
        playPauseButton.disabled = false;
        playerProgress.disabled = false;
        lyricButton.classList.remove('invisible');

        // Restore display info
        currentSongId = savedState.songId;
        currentSongUrl = savedState.songUrl;
        audioPlayer.src = savedState.songUrl;
        document.getElementById('playerTitle').textContent = savedState.songTitle || 'Unknown Title';
        document.getElementById('playerArtist').textContent = savedState.songArtist || 'Unknown Artist';
        document.getElementById('playerImage').src = savedState.songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';

        if (currentSongId) {
            checkLikeStatus(currentSongId);
        }

        // Just load the audio without playing
        audioPlayer.load();
        audioPlayer.currentTime = 0;
        playerProgress.value = 0;

        // Update UI to paused state
        isPlaying = false;
        playPauseButton.classList.remove('pause');
    }

    // Initialize playlist features
    initializePlaylist();
    initializePlayButtons();
});