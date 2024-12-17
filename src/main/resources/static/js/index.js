$(document).ready(function() {
    $('.idea-card').click(function() {
        window.location.href = $(this).attr('data-url');
    });
});