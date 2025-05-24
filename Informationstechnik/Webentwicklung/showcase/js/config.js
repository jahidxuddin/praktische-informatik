// Default config
var def_config = {
	musicVol: 0.5,
	sfxVol: 0.2,
};

// Check if Local Storage is accessable
if (typeof Storage !== "undefined") {
	if (!localStorage.getItem("onliine-settings")) {
		// Stringify the config cuz that's how it is.
		localStorage.setItem("onliine-settings", JSON.stringify(def_config));
		// Reload the page so that everything works.
		location.reload();
	}
} else {
	alert(
		"Local Storage is not support or disabled -- settings will not work!"
	);
}

// User config
var userConfig = JSON.parse(localStorage.getItem("onliine-settings"));
console.log(`user config:`, userConfig);

// Default channels
var def_channels = [
	{
		id: "disc-movie",
		title: "Disc Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		disc: true,
		target: "showcase/watched_and_to-watch_movies/index.html",
	},
	{
		id: "mii",
		title: "Mii Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		target: "showcase/dynamic-quiz/index.html",
	},
	{
		id: "photo",
		title: "Photo Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		target: "showcase/labyrinth/index.html",
	},
	{
		id: "shop",
		title: "Wii Shop Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		target: "showcase/movie-app/index.html",
	},
	{
		id: "news",
		title: "News Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		target: "showcase/temu-web-ssh/sshterm/public/index.html",
	},
	{
		id: "onliine",
		title: "Onliine Channel",
		assets: "assets/channels/",
		channelart: "channelart/",
		target: "showcase/wahlen-studios/index.html",
	},
];

// Set channels if they aren't set
if (!localStorage.getItem("onliine-channels")) {
	localStorage.setItem("onliine-channels", JSON.stringify(def_channels));
}
var userChannels = JSON.parse(localStorage.getItem("onliine-channels"));
console.log(`user channels: `, userChannels);

// Load default config
function resetConfig(confirm) {
	if (confirm == true) {
		// Confirmed! writing...
		localStorage.setItem("onliine-settings", JSON.stringify(def_config));
		userConfig = JSON.parse(localStorage.getItem("onliine-settings"));
		console.log(`user config reset!:`, userConfig);
	} else {
		console.error(
			`loadDefaultConfig: MAKE SURE YOU'D LIKE TO DO THIS BY USING "loadDefaultConfig(true)". THERE'S NO TURNING BACK!!`
		);
	}
}

// Load default channels
function resetChannels(confirm) {
	if (confirm == true) {
		// Confirmed! writing...
		localStorage.setItem("onliine-channels", JSON.stringify(def_channels));
		userChannels = JSON.parse(localStorage.getItem("onliine-channels"));
		console.log(`user channels reset! (reload page to see):`, userChannels);
	} else {
		console.error(
			`loadDefaultChannels: MAKE SURE YOU'D LIKE TO DO THIS BY USING ADDING "true" IN THE FUNCTION. THERE'S NO TURNING BACK!!`
		);
	}
}
