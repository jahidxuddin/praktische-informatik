const inputFieldElement = document.getElementById("todo-input");
const todoList = document.getElementById("todo-list");

function addTodo(event) {
    event.preventDefault(); // Verhindert das Neuladen der Seite

    const inputFieldElement = document.getElementById("todo-input");
    const inputFieldValue = inputFieldElement.value;
    if (inputFieldValue === "") {
        return;
    }

    // Der Prüfling muss die folgende Zeile ergänzen
    // todoList.appendChild(...);

    // Eingabefeld zurücksetzen
    // Der Prüfling muss hier den Reset des Eingabefelds implementieren
}

function createTodoItem(id, text) {
    const li = document.createElement("li");
    li.className = "flex items-center justify-between";

    const div = document.createElement("div");
    div.className = "flex items-center gap-3";

    // Der Prüfling muss hier die Checkbox erstellen
    // ...

    // Der Prüfling muss hier das Label erstellen
    // ...

    const button = document.createElement("button");
    button.className = "bg-red-500 text-white p-2 rounded-lg font-semibold cursor-pointer";
    button.textContent = "Entfernen";
    
    // Event-Listener zum Entfernen des Todo-Elements
    button.addEventListener("click", () => {
        // Der Prüfling muss die folgende Zeile ergänzen
        // ...
    });

    // Der Prüfling muss die Elemente zusammenfügen
    // ...

    return li;
}

function getRandomNumber(min, max) {
    // Der Prüfling muss diese Funktion implementieren
}

const contactForm = document.getElementById("contact-form");

contactForm.addEventListener("submit", (event) => {
    event.preventDefault();

    // Der Prüfling muss hier die Werte der Eingabefelder abrufen
    // ...

    if (/* Der Prüfling muss die Bedingung ergänzen */) {
        alert(
            `Kontaktanfrage eingegangen!\n\nName: ${/* Variable einsetzen */}\nE-Mail: ${/* Variable einsetzen */}\nNachricht: ${/* Variable einsetzen */}`
        );
        // Formular zurücksetzen
        // Der Prüfling muss das Formular-Reset implementieren
    } else {
        alert("Bitte alle Felder ausfüllen!");
    }
});

const cells = document.querySelectorAll('[id^="cell-"]');
let currentPlayer = "X";
let gameBoard = ["", "", "", "", "", "", "", "", ""];
let gameOver = false;

const checkWinner = () => {
    const winPatterns = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]
    ];

    for (const pattern of winPatterns) {
        const [a, b, c] = pattern;
        if (gameBoard[a] && gameBoard[a] === gameBoard[b] && gameBoard[a] === gameBoard[c]) {
            return gameBoard[a];
        }
    }

    if (!gameBoard.includes("")) {
        return "Tie";
    }

    return null;
};

const handleClick = (e, index) => {
    if (gameBoard[index] !== "" || gameOver) return;

    // Der Prüfling muss hier den aktuellen Spieler setzen
    // ...

    // Überprüfen auf einen Gewinner
    const winner = checkWinner();
    if (winner) {
        gameOver = true;
        alert(winner === "Tie" ? "Es ist ein Unentschieden!" : `${winner} hat gewonnen!`);
    } else {
        // Der Prüfling muss hier den Spielerwechsel implementieren
        // ...
    }
};

// Der Prüfling muss hier die Klick-Listener für jedes Spielfeld hinzufügen
// ...
