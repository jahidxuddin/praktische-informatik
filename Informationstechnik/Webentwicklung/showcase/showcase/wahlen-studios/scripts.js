// Add interactivity to the website
document.addEventListener('DOMContentLoaded', function () {
    // Add ripple effect to buttons
    const buttons = document.querySelectorAll('.mdl-button');
    buttons.forEach(button => {
        button.addEventListener('click', () => {
            alert('Vielen Dank für Ihre Bestellung!');
        });
    });


        });
