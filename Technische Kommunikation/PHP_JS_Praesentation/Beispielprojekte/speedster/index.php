<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Speedster</title>
    <script defer src="./public/scripts/speedtest.js"></script>
    <link rel="shortcut icon" href="./public/icons/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="./public/styles/style.css">
    <!-- npx tailwindcss -i ./public/styles/input.css -o ./public/styles/style.css --watch -->
    <style>
        .remove-touch-square:focus {
            outline: none !important;
            box-shadow: none !important;
            -webkit-tap-highlight-color: transparent !important;
        }
    </style>
</head>

<body class="bg-[#001220] text-white">
    <main class="min-h-screen flex flex-col justify-center gap-20">
        <div class="flex justify-center">
            <button id="status" onclick="startSpeedtest()" class="remove-touch-square border rounded-full p-24 cursor-pointer font-semibold select-none">
                Run
            </button>
        </div>
        <div class="flex justify-center gap-10">
            <div class="flex flex-col items-center gap-3">
                <span class="font-semibold">Download</span>
                <span id="download">-/-</span>
            </div>
            <div class="flex flex-col items-center gap-3">
                <span class="font-semibold">Upload</span>
                <span id="upload">-/-</span>
            </div>
            <div class="flex flex-col items-center gap-3">
                <span class="font-semibold">Ping</span>
                <span id="ping">-/-</span>
            </div>
        </div>
    </main>
</body>

</html>