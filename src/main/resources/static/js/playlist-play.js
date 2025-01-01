document.addEventListener('DOMContentLoaded', function() {
    const audioPlayer = document.getElementById('audioPlayer');
    const playbackInfo = document.getElementById('playbackInfo');
    let playlist = [];
    let currentSongIndex = 0;

    // Initialize playlist from table rows
    function initializePlaylists() {
        playlist = Array.from(document.querySelectorAll('tr[data-song-url]')).map(row => ({
            url: row.getAttribute('data-song-url'),
            title: row.getAttribute('data-song-title'),
            artist: row.getAttribute('data-song-artist'),
            image: row.querySelector('img').src
        }));
    }

    // Add play buttons to each row
    function addPlayButtons() {
        document.querySelectorAll('tr[data-song-url]').forEach((row, index) => {
            const playButton = document.createElement('button');
            playButton.className = 'play-song-button text-white hover:text-green-500 transition-colors mr-2 px-2';
            playButton.textContent = '▶';

            playButton.addEventListener('click', (e) => {
                e.stopPropagation();
                currentSongIndex = index;
                playSongFromPlaylist(index);
            });

            // Insert play button in the first cell
            const firstCell = row.querySelector('td');
            firstCell.insertBefore(playButton, firstCell.firstChild);
        });
    }

    // Add Play All button
    function addPlayAllButton() {
        const playAllButton = document.getElementById("playAllButton");

        playAllButton.addEventListener('click', () => {
            if (playlist.length > 0) {
                currentSongIndex = 0;
                playSongFromPlaylist(0);
            }
        });
    }

    function playSongFromPlaylist(index) {
        const song = playlist[index];
        if (!song) return;

        // Update play buttons visual state
        updatePlayButtonStates(index);

        // Trigger play using existing play-song class functionality
        const playEvent = new CustomEvent('play-song', {
            detail: {
                url: song.url,
                title: song.title,
                artist: song.artist,
                image: song.image
            }
        });
        document.dispatchEvent(playEvent);
    }

    function updatePlayButtonStates(activeIndex) {
        document.querySelectorAll('.play-song-button').forEach((button, index) => {
            if (index === activeIndex) {
                button.classList.add('text-green-500');
                button.textContent = '❚❚'; // pause symbol
            } else {
                button.classList.remove('text-green-500');
                button.textContent = '▶'; // play symbol
            }
        });
    }

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

    // Handle automatic next song when current song ends
    audioPlayer.addEventListener('ended', () => {
        if (playlist.length > 0) {
            currentSongIndex = (currentSongIndex + 1) % playlist.length;
            playSongFromPlaylist(currentSongIndex);
        }
    });

    // Initialize playlist features
    initializePlaylists();
    addPlayButtons();
    addPlayAllButton();

    // Add audio player update listener
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
        audioPlayer.src = url;
        audioPlayer.load();

        // Play and update UI
        audioPlayer.play().then(() => {
            isPlaying = true;
            playPauseButton.classList.add('pause');
            savePlaybackState();
        }).catch(error => console.error('Playback failed:', error));
    });
});