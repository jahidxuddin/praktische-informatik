const omdbApiKey = '56ec57d1'; 


var list = localStorage.getItem('Watchlist') || ""; // Sicherstellen, dass counter ein Wert ist
console.log(list);
var array = list.split(',');

for (let index = 0; index < array.length; index++) {
    const element = array[index].trim();
    console.log(array[index]);
    loadMovies(element)
}

async function  loadMovies(params) {
    const url = `http://www.omdbapi.com/?apikey=${omdbApiKey}&i=${params}`;
    const response = await fetch(url);
    const data = await response.json();
    if(data.Response === "True"){
        displayMovies(data);
    }else{
        console.log(data.Error);
    }
}


function displayMovies(movie) {
    const container = document.getElementById("listContainer");

    const movieCard = document.createElement("div");
    movieCard.classList.add("movie-card");

    const movieImage = document.createElement('img');
    movieImage.src = movie.Poster !== "N/A" ? movie.Poster : "https://via.placeholder.com/150";
    movieImage.alt = movie.Title;

    const movieTitle = document.createElement('h3');
    movieTitle.innerText = `${movie.Title} (${movie.Year})`;

    movieCard.addEventListener("click", function(){
        showMoviePopup(movie.imdbID);
    });

    movieCard.appendChild(movieImage);
    movieCard.appendChild(movieTitle);

    container.appendChild(movieCard);
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
            <button id="add-to-watch" class="popup-button" onclick="remove('${imdbID}')">Remove</button>
        </div>
    `;

    popup.style.display = 'flex';
}

function closePopup() {
    const popup = document.getElementById('moviePopup');
    popup.style.display = 'none';
}

document.querySelector('.popup-close').addEventListener('click', closePopup);

document.querySelector('.popup-overlay').addEventListener('click', (e) => {
    if (e.target.classList.contains('popup-overlay')) {
        closePopup();
    }
});

function remove(imdbID) {
    var list = localStorage.getItem('Watchlist') || "";  // Hole die gespeicherte Liste
    var array = list.split(',');  // Teile den String in ein Array
    console.log(array);
    // Finde den Index des Elements, das entfernt werden soll
    var index = array.indexOf(imdbID);
    console.log(index);
    if (index !== -1) {  // Wenn das Element gefunden wurde
        // Entferne das Element aus dem Array
        array.splice(index, 1);

        // Setze die aktualisierte Liste zurück in localStorage
        localStorage.setItem('Watchlist', array.join(','));

        console.log('Removed from watched list:', imdbID);
    } else {
        console.log('Item not found in watched list:', imdbID);
    }
    closePopup();
    window.location.reload();
}
 
function addToWatched(imdbID) {
    var list = localStorage.getItem('WatchedList') || ""; // Sicherstellen, dass counter ein Wert ist
    // Überprüfe, ob die imdbID bereits in der Liste existiert
    if (list.includes(imdbID)) {
        console.log("Movie already in watchlist:", imdbID);
        closePopup(); // Verhindert das Hinzufügen eines Duplikats
        return;
    }

    // Füge die imdbID der Liste hinzu und füge nur ein Komma hinzu, wenn die Liste nicht leer ist
    list = list ? list + imdbID + "," : imdbID + ","; 

    localStorage.setItem('WatchedList',list);
    console.log('Added to watched:', imdbID);
    remove(imdbID);
    
}

