 document.addEventListener('DOMContentLoaded', () => {
    const audioPlayer = document.getElementById('audioPlayer');
    const playPauseButton = document.getElementById('playPauseButton');
    const playerProgress = document.getElementById('playerProgress');
    const volumeControl = document.getElementById('volumeControl');

    let isPlaying = false;

    // Restore Playback State
    const savedState = JSON.parse(localStorage.getItem('playbackState'));
    if (savedState) {
    audioPlayer.src = savedState.songUrl;
    audioPlayer.currentTime = savedState.currentTime;
    document.getElementById('playerTitle').textContent = savedState.songTitle || 'Unknown Title';
    document.getElementById('playerArtist').textContent = savedState.songArtist || 'Unknown Artist';
    document.getElementById('playerImage').src = savedState.songImage || 'https://i.scdn.co/image/ab67616d0000b273cdb645498cd3d8a2db4d05e1';
}
     let isLiked = false; // Status lagu awal

     const likeButton = document.getElementById("likeButton");

     likeButton.addEventListener("click", function () {
         if (!isLiked) {
             likeButton.innerText = "💙"; // Ganti ke hati penuh
             likeButton.classList.remove("text-gray-500"); // Hapus warna abu-abu
             likeButton.classList.add("text-red-500"); // Tambahkan warna merah
         } else {
             likeButton.innerText = "♡"; // Kembali ke hati kosong
             likeButton.classList.remove("text-red-500"); // Hapus warna merah
             likeButton.classList.add("text-gray-500"); // Tambahkan warna abu-abu
         }
         isLiked = !isLiked; // Toggle status suka
     });

     // Play/Pause Logic
     playPauseButton.addEventListener('click', () => {
    if (isPlaying) {
    audioPlayer.pause();
    playPauseButton.textContent = 'Play';
} else {
    audioPlayer.play();
    playPauseButton.textContent = 'Pause';
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
    const songUrl = button.getAttribute('data-url');
    const songTitle = button.getAttribute('data-title');
    const songArtist = button.getAttribute('data-artist');
    const songImage = button.getAttribute('data-image');

    audioPlayer.src = songUrl;
    audioPlayer.play();
    isPlaying = true;
    playPauseButton.textContent = 'Pause';

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
});
});
});
