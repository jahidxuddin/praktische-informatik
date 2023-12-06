<?php
require_once("./database/database.php");

require './vendor/autoload.php';

use Ramsey\Uuid\Uuid;

function sanitize($input)
{
    $input = strip_tags($input);
    return htmlspecialchars($input, ENT_QUOTES, 'UTF-8');
}

function getUserById($conn)
{
    $user_id = $_COOKIE["user_id"];

    if (!isset($user_id)) {
        header("Location: /class-chat/auth/login.php");
        exit;
    }

    $query = "SELECT * FROM users WHERE id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $user_id);
    $stmt->execute();

    $result = $stmt->get_result();

    if (!$result->num_rows > 0) {
        header("Location: /class-chat/auth/login.php");
        exit;
    }

    return $result->fetch_assoc();
}

function getChatById($conn)
{
    if (!isset($_GET["chat_id"])) {
        header("Location: /class-chat/index.php");
        exit;
    }

    $chat_id = $_GET["chat_id"];

    $query = "SELECT * FROM chats WHERE id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();

    $result = $stmt->get_result();

    if (!$result->num_rows > 0) {
        http_response_code(404);
        header("Location: /class-chat/index.php");
        exit;
    }

    return $result->fetch_assoc();
}

function combineMessageWithChat($conn, $chat_id)
{
    $query = "SELECT m.* FROM message m JOIN chats c ON m.chat_id = c.id WHERE c.id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();
}

function getChatMessages($conn, $chat_id)
{
    $query = "SELECT * FROM message WHERE chat_id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();

    $result = $stmt->get_result();

    $messages = array();

    while ($row = $result->fetch_assoc()) {
        $messages[] = $row;
    }

    return $messages;
}

$user_data = getUserById($conn);
$chat_data = getChatById($conn);

$user_id = $user_data["id"];
$chat_id = $chat_data["id"];
$chat_name = $chat_data["chat_name"];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $message = sanitize($_POST['message']);

    if (isset($message) && !empty($message)) {
        $uuid = Uuid::uuid4()->toString();

        $query = "INSERT INTO message (id, message_content, user_id, chat_id) VALUES (?, ?, ?, ?)";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("ssis", $uuid, $message, $user_id, $chat_id);
        $stmt->execute();

        combineMessageWithChat($conn, $chat_id);
    }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php echo $chat_name; ?> | Class Chat</title>
    <link rel="stylesheet" href="./public/styles/style.css">
    <script defer src="./public/scripts/sse.js"></script>
</head>

<body class="bg-black text-white overflow-y-hidden">
    <header class="p-5 fixed w-full flex justify-center items-center gap-5">
        <span class="font-semibold text-xl"><?php echo $chat_name; ?></span>
        <span>|</span>
        <a href="/class-chat/index.php">
            <img src="./public/icons/home-outline.svg" alt="Logout" height="32" width="32" />
        </a>
        <span>|</span>
        <a href="/class-chat/auth/logout.php">
            <img src="./public/icons/log-out-outline.svg" alt="Logout" height="32" width="32" />
        </a>
    </header>
    <main class="w-full h-screen flex flex-col gap-5 p-5 pt-32 sm:pt-20">
        <div id="chat" class="flex-1 flex flex-col gap-5 h-full overflow-y-scroll"></div>
        <form id="message-input-form" class="w-full flex relative" action="/class-chat/chat.php?chat_id=<?php echo urlencode($chat_id); ?>" method="post">
            <textarea autofocus class="rounded-lg resize-none w-full p-5 pr-12 bg-black text-white focus:outline-none border whitespace-normal" placeholder="Send a message..." type="text" name="message" id="message"></textarea>
            <button class="cursor-pointer absolute right-0 top-1/2 transform -translate-x-1/2 -translate-y-1/2" type="submit">
                <img src="./public/icons/send-outline.svg" alt="Send message" height="32" width="32" />
            </button>
        </form>
    </main>

    <script>
        const sendMessage = (event) => {
            if (event.keyCode === 13) {
                event.preventDefault();
                document.querySelector('#message-input-form').submit();
            }
        }

        document.querySelector("#message").addEventListener("keydown", sendMessage);
    </script>
</body>

</html>