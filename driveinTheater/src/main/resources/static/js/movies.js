document.addEventListener("DOMContentLoaded", function() {
    const addMovieButton = document.querySelector(".btn-primary");
    const closeButton = document.querySelector(".close-btn");
    const modal = document.getElementById("add-movie-modal");

    // "+"
    addMovieButton.onclick = function() {
        modal.style.display = "block";
    };

    // close
    closeButton.onclick = function() {
        modal.style.display = "none";
    };
});
