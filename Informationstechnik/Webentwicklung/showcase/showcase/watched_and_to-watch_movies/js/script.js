// Titles: http://www.omdbapi.com/?apikey=56ec57d1&s=[title]
// details: http://www.omdbapi.com/?apikey=56ec57d1&i=[imdbID]

const omdbApiKey = '56ec57d1'; 
const movieInput = document.getElementById('movie-input');
const searchResults = document.getElementById('search-results');

async function  loadMovies(params) {
    const url = `http://www.omdbapi.com/?apikey=${omdbApiKey}&s=${params}`;
    const response = await fetch(url);
    const data = await response.json();
    if(data.response == true) displayMovies(data.Search);
}

function findMovie() {
let params = (movieInput.value).trim(); 
if(params.length > 0){
    console.log(loadMovies(params));
}
}

function displayMovies(movies) {

}
