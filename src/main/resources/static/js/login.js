$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('logout') && urlParams.get('logout') === 'true') {
        const $logoutRow = $('#logout-row');
        $logoutRow.show();
        setTimeout(() => {
            $logoutRow.hide();
        }, 5_000);
    }
});