const inputFieldElement = document.getElementById("todo-input");
const todoList = document.getElementById("todo-list");

function addTodo(event) {
	event.preventDefault(); // Verhindert das Neuladen der Seite

	const inputFieldElement = document.getElementById("todo-input");
	const inputFieldValue = inputFieldElement.value;
	if (inputFieldValue === "") {
		return;
	}

	todoList.appendChild(
		createTodoItem(getRandomNumber(1, 1000), inputFieldValue)
	);
	inputFieldElement.value = "";
}

function createTodoItem(id, text) {
	// Erstellt das <li>-Element
	const li = document.createElement("li");
	li.className = "flex items-center justify-between";

	// Erstellt das div für Checkbox und Label
	const div = document.createElement("div");
	div.className = "flex items-center gap-3";

	// Erstellt die Checkbox
	const checkbox = document.createElement("input");
	checkbox.type = "checkbox";
	checkbox.className = "h-4 w-4";
	checkbox.id = `todo-${id}`;
	checkbox.addEventListener("change", () => {
		if (checkbox.checked) {
			label.style.textDecoration = "line-through";
		} else {
			label.style.textDecoration = "none";
		}
	});

	// Erstellt das Label
	const label = document.createElement("label");
	label.htmlFor = `todo-${id}`;
	label.className = "text-xl";
	label.textContent = text;

	// Erstellt den Entfernen-Button
	const button = document.createElement("button");
	button.className =
		"bg-red-500 text-white p-2 rounded-lg font-semibold cursor-pointer";
	button.textContent = "Entfernen";

	// Event-Listener zum Entfernen des Todo-Elements
	button.addEventListener("click", () => {
		li.remove();
	});

	// Elemente zusammenfügen
	div.appendChild(checkbox);
	div.appendChild(label);
	li.appendChild(div);
	li.appendChild(button);

	return li;
}

function getRandomNumber(min, max) {
	return Math.floor(Math.random() * (max - min + 1)) + min;
}

const contactForm = document.getElementById("contact-form");

contactForm.addEventListener("submit", (event) => {
	event.preventDefault(); // Verhindert das Neuladen der Seite

	const name = document.getElementById("name").value;
	const email = document.getElementById("email").value;
	const message = document.getElementById("message").value;

	if (name && email && message) {
		alert(
			`Kontaktanfrage eingegangen!\n\nName: ${name}\nE-Mail: ${email}\nNachricht: ${message}`
		);
		contactForm.reset(); // Formular nach dem Absenden zurücksetzen
	} else {
		alert("Bitte alle Felder ausfüllen!");
	}
});

const cells = document.querySelectorAll('[id^="cell-"]');
let currentPlayer = "X"; // Spieler X beginnt
let gameBoard = ["", "", "", "", "", "", "", "", ""]; // Initialisierung des Spielfelds
let gameOver = false;

// Funktion zum Überprüfen auf einen Gewinner
const checkWinner = () => {
	const winPatterns = [
		[0, 1, 2],
		[3, 4, 5],
		[6, 7, 8], // horizontale Reihen
		[0, 3, 6],
		[1, 4, 7],
		[2, 5, 8], // vertikale Reihen
		[0, 4, 8],
		[2, 4, 6], // diagonale Reihen
	];

	for (const pattern of winPatterns) {
		const [a, b, c] = pattern;
		if (
			gameBoard[a] &&
			gameBoard[a] === gameBoard[b] &&
			gameBoard[a] === gameBoard[c]
		) {
			return gameBoard[a]; // Rückgabe des Gewinners ('X' oder 'O')
		}
	}

	// Überprüfen auf ein Unentschieden (wenn alle Felder besetzt sind)
	if (!gameBoard.includes("")) {
		return "Tie"; // Unentschieden
	}

	return null; // Kein Gewinner oder Unentschieden
};

// Funktion für das Klicken auf ein Feld
const handleClick = (e, index) => {
	if (gameBoard[index] !== "" || gameOver) return; // Wenn das Feld bereits belegt oder das Spiel vorbei ist, nichts tun

	// Setzen des aktuellen Spielers in das Spielfeld
	gameBoard[index] = currentPlayer;
	e.target.textContent = currentPlayer;

	// Überprüfen auf einen Gewinner
	const winner = checkWinner();
	if (winner) {
		gameOver = true;
		if (winner === "Tie") {
			alert("Es ist ein Unentschieden!");
		} else {
			alert(`${winner} hat gewonnen!`);
		}
	} else {
		// Wechseln des Spielers
		currentPlayer = currentPlayer === "X" ? "O" : "X";
	}
};

// Hinzufügen der Klick-Listener für jedes Spielfeld
cells.forEach((cell, index) => {
	cell.addEventListener("click", (e) => handleClick(e, index));
});
