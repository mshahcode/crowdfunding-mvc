document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('logout') && urlParams.get('logout') === 'true') {
        const logoutRow = document.getElementById('logout-row');
        if (logoutRow) {
            logoutRow.style.display = 'block';
            setTimeout(() => {
                logoutRow.style.display = 'none';
            }, 5000);
        }
    }
});
