// Titles: http://www.omdbapi.com/?apikey=56ec57d1&s=[title]
// details: http://www.omdbapi.com/?apikey=56ec57d1&i=[imdbID]

const omdbApiKey = "56ec57d1";
const movieInput = document.getElementById("search-bar");
const searchList = document.querySelector(".search-list");

async function loadMovies(search) {
    const url = `http://www.omdbapi.com/?apikey=${omdbApiKey}&s=${search}`;
    const response = await fetch(url);
    const data = await response.json();
    
    if (data.Response === "True") {
        displayMovies(data.Search);
    } else {
        searchList.innerHTML = "<p>    Keine Filme gefunden.</p>";
    }
}

function findMovie() {
    let search = movieInput.value.trim();
    if (search.length > 0) {
        searchList.classList.remove("hide-search-list"); 
        loadMovies(search);
    } else {
        searchList.classList.add("hide-search-list"); 
    }
}

function displayMovies(movies) {
    searchList.innerHTML = "";
    
    movies.forEach((movie) => {
        let movieListItem = document.createElement("div");
        movieListItem.dataset.id = movie.imdbID;
        movieListItem.classList.add("search-list-item");

        let moviePoster = movie.Poster !== "N/A" ? movie.Poster : "https://via.placeholder.com/150";

        movieListItem.innerHTML = `
            <div class="search-item-cover">
                <img src="${moviePoster}">
            </div>
            <div class="search-item-title">
                <h3>${movie.Title}</h3>
                <p>${movie.Year}</p>
            </div>
        `;

        movieListItem.addEventListener('click', () => showMoviePopup(movie.imdbID));
        searchList.appendChild(movieListItem);
    });
}

async function getMovieDetails(imdbID) {
    const url = `http://www.omdbapi.com/?apikey=${omdbApiKey}&i=${imdbID}`;
    const response = await fetch(url);
    const data = await response.json();
    return data;
}

async function showMoviePopup(imdbID) {
    const movieDetails = await getMovieDetails(imdbID);
    const popup = document.getElementById('moviePopup');
    const popupContent = popup.querySelector('.popup-movie-details');

    popupContent.innerHTML = `
        <img src="${movieDetails.Poster !== 'N/A' ? movieDetails.Poster : 'https://via.placeholder.com/150'}" 
             alt="${movieDetails.Title}" 
             class="popup-movie-poster">
        <h2>${movieDetails.Title}</h2>
        <p><em>Jahr:</em> ${movieDetails.Year}</p>
        <p><em>Genre:</em> ${movieDetails.Genre}</p>
        <p><em>Rating:</em> ${movieDetails.imdbRating}</p>
        <p><em>${movieDetails.Plot}<em></p>
        <div class="popup-buttons">
            <button id="add-watched" class="popup-button" onclick="addToWatched('${imdbID}')">Gesehen</button>
            <button id="add-to-watch" class="popup-button" onclick="addToWatchlist('${imdbID}')">Zur Watchlist</button>
        </div>
    `;

    popup.style.display = 'flex';
}

function closePopup() {
    const popup = document.getElementById('moviePopup');
    popup.style.display = 'none';
}

function addToWatched(imdbID) {
    var list = localStorage.getItem('WatchedList') || ""; // Sicherstellen, dass counter ein Wert ist
    var watchList = localStorage.getItem("Watchlist") || "";
    // Überprüfe, ob die imdbID bereits in der Liste existiert
    if (list.includes(imdbID) || watchList.includes(imdbID)) {
        console.log("Movie already in watchlist:", imdbID);
        closePopup(); // Verhindert das Hinzufügen eines Duplikats
        return;
    }

    // Füge die imdbID der Liste hinzu und füge nur ein Komma hinzu, wenn die Liste nicht leer ist
    list = list ? list + imdbID + "," : imdbID + ","; 

    localStorage.setItem('WatchedList',list);
    console.log('Added to watched:', imdbID);
    closePopup();
}


function addToWatchlist(imdbID) {
    // Hole die Watchlist oder initialisiere eine leere Liste
    var list = localStorage.getItem("Watchlist") || "";
    var watchedList = localStorage.getItem('WatchedList') || ""; 

    // Überprüfe, ob die imdbID bereits in der Liste existiert
    if (list.includes(imdbID) || watchedList.includes(imdbID)) {
        console.log("Movie already in watchlist:", imdbID);
        closePopup(); // Verhindert das Hinzufügen eines Duplikats
        return;
    }

    // Füge die imdbID der Liste hinzu und füge nur ein Komma hinzu, wenn die Liste nicht leer ist
    list = list ? list + imdbID + "," : imdbID + ","; 

    // Speichere die neue Watchlist zurück in localStorage
    localStorage.setItem('Watchlist', list);
    console.log(list);

    console.log('Added to watchlist:', imdbID);
    closePopup();
}

document.querySelector('.popup-close').addEventListener('click', closePopup);

document.querySelector('.popup-overlay').addEventListener('click', (e) => {
    if (e.target.classList.contains('popup-overlay')) {
        closePopup();
    }
});
