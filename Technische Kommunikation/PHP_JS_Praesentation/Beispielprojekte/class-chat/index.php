<?php
$user_id = $_COOKIE["user_id"];

if (!isset($user_id)) {
    header("Location: /class-chat/auth/login.php");
    exit;
}

require_once("./database/database.php");

$query = "SELECT * FROM users WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("s", $user_id);
$stmt->execute();

$result = $stmt->get_result();

if (!$result->num_rows > 0) {
    header("Location: /class-chat/auth/login.php");
    exit;
}

$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Class Chat</title>
    <link rel="stylesheet" href="./public/styles/style.css">
    <!-- npx tailwindcss -i ./public/styles/input.css -o ./public/styles/style.css --watch -->
</head>

<body class="bg-black text-white">
    <header class="p-5 fixed w-full flex justify-center items-center gap-5">
        <a href="/class-chat/index.php">
            <img src="./public/icons/home-outline.svg" alt="Logout" height="32" width="32" />
        </a>
        <span>|</span>
        <a href="/class-chat/auth/logout.php">
            <img src="./public/icons/log-out-outline.svg" alt="Logout" height="32" width="32" />
        </a>
    </header>
    <main class="h-screen flex flex-col justify-center items-center gap-5 p-5 sm:p-0">
        <div onclick="window.location.href = '/class-chat/chat/join.php'" class="w-full sm:w-1/2 border rounded-lg p-5 flex items-center justify-between cursor-pointer">
            <span class="text-xl font-semibold">Join chat</span>
            <img src="./public/icons/chatbubbles-outline.svg" alt="Join" width="32" height="32">
        </div>
        <div onclick="window.location.href = '/class-chat/chat/create.php'" class="w-full sm:w-1/2 border rounded-lg p-5 flex items-center justify-between cursor-pointer">
            <span class="text-xl font-semibold">Create chat</span>
            <img src="./public/icons/create-outline.svg" alt="Create" width="32" height="32">
        </div>
    </main>
</body>

</html>