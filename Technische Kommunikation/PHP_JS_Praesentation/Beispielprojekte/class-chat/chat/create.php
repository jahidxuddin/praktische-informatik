<?php
require '../vendor/autoload.php';

use Ramsey\Uuid\Uuid;

$user_id = $_COOKIE["user_id"];

if (!isset($user_id)) {
    header("Location: /class-chat/auth/login.php");
    exit;
}

require_once("../database/database.php");

$query = "SELECT * FROM users WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("s", $user_id);
$stmt->execute();

$result = $stmt->get_result();

if (!$result->num_rows > 0) {
    header("Location: /class-chat/auth/test.php");
    exit;
}

function sanitize($input)
{
    $input = strip_tags($input);
    return htmlspecialchars($input, ENT_QUOTES, 'UTF-8');
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $name = sanitize($_POST["name"]);

    if (empty($name)) {
        header('Content-Type: application/json');
        http_response_code(400);
        echo json_encode(array("error" => "Empty name"));
        exit;
    }

    $uuid = Uuid::uuid4()->toString();

    $query = "INSERT INTO chats (id, chat_name) VALUES (?,?)";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("ss", $uuid, $name);
    $stmt->execute();

    $chat_id = $uuid;

    $query = "INSERT INTO user_chat (user_id, chat_id) VALUES (?, ?)";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("is", $user_id, $chat_id);
    $stmt->execute();

    header("Location: /class-chat/chat.php?chat_id=" . $chat_id);
}

$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create | Class Chat</title>
    <link rel="stylesheet" href="../public/styles/style.css">
</head>

<body class="bg-black text-white">
    <header class="p-5 fixed w-full flex justify-center items-center gap-5">
        <a href="/class-chat/index.php">
            <img src="../public/icons/home-outline.svg" alt="Logout" height="32" width="32" />
        </a>
        <span>|</span>
        <a href="/class-chat/auth/logout.php">
            <img src="../public/icons/log-out-outline.svg" alt="Logout" height="32" width="32" />
        </a>
    </header>
    <main class="h-screen flex justify-center items-center">
        <form class="flex flex-col gap-5 p-5 sm:p-0 w-full sm:w-1/2" action="/class-chat/chat/create.php" method="post">
            <input maxlength="255" required class="focus:outline-none p-5 rounded-lg bg-black border" type="text" name="name" id="name" placeholder="Chat Name">
            <input class="text-xl cursor-pointer p-5 rounded-lg border" type="submit" value="Create">
        </form>
    </main>
</body>

</html>