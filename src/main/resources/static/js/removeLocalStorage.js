    document.addEventListener('DOMContentLoaded', function () {
    const logoutButton = document.getElementById('logoutButton');
    if (logoutButton) {
    logoutButton.addEventListener('click', () => {
    localStorage.removeItem('playbackState');
    console.log("Logged out and localStorage cleared.");
});
}
});

