(function() {
	var questions = [{
	  question: "Was ist ein CSS-Framework?",
	  choices: ["A) Ein Werkzeug, um Webseiten einfacher zu gestalten", "B) Eine Programmiersprache",
		 "C) Eine Art von Computer", "D) Ein Bildbearbeitungsprogramm", "E) Ein Musikprogramm"],
	  correctAnswer: 0
	}, {
	  question: "Warum nutzen Entwickler CSS-Frameworks?",
	  choices: ["A) Damit Webseiten schöner aussehen", "B) Damit Webseiten schneller gebaut werden können",
		 "C) Damit das Design einheitlich bleibt", "D) Alle oben genannten", "E) Ich weiß es nicht"],
	  correctAnswer: 3
	}, {
	  question: "Was ist ein JavaScript-Framework?",
	  choices: ["A) Ein Framework für Bilder", "B) Ein Zeichenprogramm", "C) Eine Sammlung von fertigen Funktionen, um Web-Anwendungen schneller zu entwickeln",
		 "D) Eine neue Art von CSS", "E) Ein Webbrowser"],
	  correctAnswer: 2
	}, {
	  question: "Welches dieser JavaScript-Frameworks ist besonders beliebt?",
	  choices: ["A) Excel", "B) React", "C) HTML", "D) WhatsApp", "E) CSS"],
	  correctAnswer: 1
	}, {
	  question: "Was ist der Hauptzweck von HTML?",
	  choices: ["A) Webseiten stylen", "B) Webseiten strukturieren", "C) Webseiten animieren", 
		"D) Webseiten programmieren", "E) Webseiten sichern"],
	  correctAnswer: 1
	},

	{
		question: "Welches dieser CSS-Frameworks basiert auf einem "+"Utility-First-Ansatz"+", bei dem viele kleine Klassen verwendet werden?",
		choices: ["A) Bootstrap", "B) Bulma", "C) Materialize", 
		  "D) Foundation", "E) Tailwind CSS"],
		correctAnswer: 4
	  }, {
		question: "Welches HTML-Tag wird verwendet, um einen Link zu erstellen?",
		choices: ["A) a", "B) link", "C) p", "D) h1", "E) div"],
		correctAnswer: 0
	  }, {
		question: "Um Webseiten interaktiv zu machen",
		choices: ["A) Um Webseiten interaktiv zu machen", "B) Um Webseiten bunter zu gestalten", 
			"C) Um Texte auf Webseiten zu schreiben", "D) Um Bilder zu bearbeiten", "E) Um Videos zu erstellen"],
		correctAnswer: 0
	  }, {
		question: "Welches dieser JavaScript-Frameworks wurde von Google entwickelt?",
		choices: ["E) Bootstrap", "B) React", "C) Vue.js", "D) Angular", "E) Tailwind CSS"],
		correctAnswer: 3
	  }, {
		question: "Warum werden HTML, CSS und JavaScript oft zusammen verwendet?",
		choices: ["A) Weil sie alle von Microsoft entwickelt wurden", "B) Weil Webseiten ohne sie nicht geladen werden können",
			 "C) Weil HTML für die Struktur, CSS für das Design und JavaScript für die Interaktivität zuständig ist", "D) Weil sie nur in Google Chrome funktionieren", 
			 "E) Weil sie die gleichen Funktionen haben"],
		correctAnswer: 2
	  }];
	
	var questionCounter = 0; //Tracks question number
	var selections = []; //Array containing user choices
	var quiz = $('#quiz'); //Quiz div object
	
	// Display initial question
	displayNext();
	
	// Click handler for the 'next' button
	$('#next').on('click', function (e) {
	  e.preventDefault();
	  
	  // Suspend click listener during fade animation
	  if(quiz.is(':animated')) {        
		return false;
	  }
	  choose();
	  
	  // If no user selection, progress is stopped
	  if (isNaN(selections[questionCounter])) {
		alert('Treffe deine Wahl!');
	  } else {
		questionCounter++;
		displayNext();
	  }
	});
	
	// Click handler for the 'prev' button
	$('#prev').on('click', function (e) {
	  e.preventDefault();
	  
	  if(quiz.is(':animated')) {
		return false;
	  }
	  choose();
	  questionCounter--;
	  displayNext();
	});
	
	// Click handler for the 'Start Over' button
	$('#start').on('click', function (e) {
	  e.preventDefault();
	  
	  if(quiz.is(':animated')) {
		return false;
	  }
	  questionCounter = 0;
	  selections = [];
	  displayNext();
	  $('#start').hide();
	});
	
	// Animates buttons on hover
	$('.button').on('mouseenter', function () {
	  $(this).addClass('active');
	});
	$('.button').on('mouseleave', function () {
	  $(this).removeClass('active');
	});
	
	// Creates and returns the div that contains the questions and 
	// the answer selections
	function createQuestionElement(index) {
	  var qElement = $('<div>', {
		id: 'question'
	  });
	  
	  var header = $('<h2>Question ' + (index + 1) + ':</h2>');
	  qElement.append(header);
	  
	  var question = $('<p>').append(questions[index].question);
	  qElement.append(question);
	  
	  var radioButtons = createRadios(index);
	  qElement.append(radioButtons);
	  
	  return qElement;
	}
	
	// Creates a list of the answer choices as radio inputs
	function createRadios(index) {
	  var radioList = $('<ul>');
	  var item;
	  var input = '';
	  for (var i = 0; i < questions[index].choices.length; i++) {
		item = $('<li>');
		input = '<input type="radio" name="answer" value=' + i + ' />';
		input += questions[index].choices[i];
		item.append(input);
		radioList.append(item);
	  }
	  return radioList;
	}
	
	// Reads the user selection and pushes the value to an array
	function choose() {
	  selections[questionCounter] = +$('input[name="answer"]:checked').val();
	}
	
	// Displays next requested element
	function displayNext() {
	  quiz.fadeOut(function() {
		$('#question').remove();
		
		if(questionCounter < questions.length){
		  var nextQuestion = createQuestionElement(questionCounter);
		  quiz.append(nextQuestion).fadeIn();
		  if (!(isNaN(selections[questionCounter]))) {
			$('input[value='+selections[questionCounter]+']').prop('checked', true);
		  }
		  
		  // Controls display of 'prev' button
		  if(questionCounter === 1){
			$('#prev').show();
		  } else if(questionCounter === 0){
			
			$('#prev').hide();
			$('#next').show();
		  }
		}else {
		  var scoreElem = displayScore();
		  quiz.append(scoreElem).fadeIn();
		  $('#next').hide();
		  $('#prev').hide();
		  $('#start').show();
		}
	  });
	}
	
	// Computes score and returns a paragraph element to be displayed
	function displayScore() {
	  var score = $('<p>',{id: 'question'});
	  
	  var numCorrect = 0;
	  for (var i = 0; i < selections.length; i++) {
		if (selections[i] === questions[i].correctAnswer) {
		  numCorrect++;
		}
	  }
	  
	  score.append('Du hast ' + numCorrect + ' Fragen von ' +
				   questions.length + ' richtig beantwortet ');
	  return score;
	}
  })();