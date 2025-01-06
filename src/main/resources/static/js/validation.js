$(document).ready(function () {
    const allInputs = $('input, textarea');
    allInputs.each(function () {
        const thisInput = $(this);
        const errors = thisInput.siblings('div').find('ul');
        if (errors.children().length > 0) {
            thisInput.addClass('error');
        }
    });


});