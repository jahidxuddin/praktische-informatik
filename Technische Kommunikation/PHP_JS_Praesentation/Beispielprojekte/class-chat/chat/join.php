<?php
$user_id = $_COOKIE["user_id"];

if (!isset($user_id)) {
    header("Location: /class-chat/auth/login.php");
    exit;
}

require_once("../database/database.php");

$query = "SELECT * FROM users WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $user_id);
$stmt->execute();

$result = $stmt->get_result();

if (!$result->num_rows > 0) {
    header("Location: /class-chat/auth/login.php");
    exit;
}

function getAllChats($conn)
{
    $query = "SELECT * FROM chats";
    $stmt = $conn->prepare($query);
    $stmt->execute();

    $result = $stmt->get_result();

    $chats = array();
    while ($row = $result->fetch_assoc()) {
        $chats[] = $row;
    }

    return $chats;
}

function getChatsFromUser($conn, $user_id)
{
    $query = "SELECT * FROM user_chat WHERE user_id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $user_id);
    $stmt->execute();

    $result = $stmt->get_result();

    $user_chats = array();
    while ($row = $result->fetch_assoc()) {
        $query = "SELECT * FROM chats WHERE id = ?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param("s", $row["chat_id"]);
        $stmt->execute();

        $result = $stmt->get_result();

        while ($row = $result->fetch_assoc()) {
            $user_chats[] = $row;
        }
    }

    return $user_chats;
}

$chats = getAllChats($conn);
$user_chats = getChatsFromUser($conn, $user_id);

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    if (!isset($_POST["chat_id"]) || !isset($_POST["user_id"])) {
        http_response_code(400);
        header("Location: /class-chat/chat/join.php");
        exit;
    }

    $chat_id = $_POST["chat_id"];
    $user_id = $_POST["user_id"];

    $query = "SELECT * FROM user_chat WHERE chat_id = ? AND user_id = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $chat_id, $user_id);
    $stmt->execute();

    $result = $stmt->get_result();

    if (!$result->num_rows > 0) {
        header("Location: /class-chat/chat/join.php");
        exit;
    }

    $stmt = $conn->prepare("DELETE FROM message WHERE chat_id = ?");
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();

    $stmt = $conn->prepare("DELETE FROM user_chat WHERE chat_id = ?");
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();

    $stmt = $conn->prepare("DELETE FROM chats WHERE id = ?");
    $stmt->bind_param("s", $chat_id);
    $stmt->execute();

    header("Location: /class-chat/chat/join.php");
}

$stmt->close();
$conn->close();
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Join | Class Chat</title>
    <link rel="stylesheet" href="../public/styles/style.css">
</head>

<body class="bg-black text-white">
    <header class="p-5 w-full flex justify-center items-center gap-5">
        <a href="/class-chat/index.php">
            <img src="../public/icons/home-outline.svg" alt="Logout" height="32" width="32" />
        </a>
        <span>|</span>
        <a href="/class-chat/auth/logout.php">
            <img src="../public/icons/log-out-outline.svg" alt="Logout" height="32" width="32" />
        </a>
    </header>
    <main class="w-full h-screen flex flex-col gap-10 p-5 mt-5 overflow-x-hidden">
        <div class="overflow-x-auto flex flex-col gap-5">
            <h1 class="text-center font-semibold text-2xl">Your chats</h1>
            <?php if (count($user_chats) > 0) : ?>
                <table class="w-full text-sm text-left">
                    <thead class="text-xs uppercase bg-black">
                        <tr>
                            <th scope="col" class="px-6 py-3">
                                Chat name
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Join
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Delete
                            </th>
                        </tr>
                    </thead>
                    <tbody class="bg-black">
                        <?php foreach ($user_chats as $user_chat) : ?>
                            <tr class="border-b">
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <span class="bg-blue-500 rounded-lg p-2"><?php echo $user_chat["chat_name"]; ?></span>
                                </th>
                                <td class="px-6 py-4">
                                    <a href="/class-chat/chat.php?chat_id=<?php echo $user_chat["id"]; ?>">
                                        <button class="bg-green-500 rounded-lg p-2">
                                            <img src="../public/icons/chatbubbles-outline.svg" alt="Join" width="24" height="24">
                                        </button>
                                    </a>
                                </td>
                                <td class="px-6 py-4">
                                    <form action="/class-chat/chat/join.php" method="POST">
                                        <input hidden type="text" name="chat_id" id="chat_id" value=<?php echo $user_chat["id"]; ?>>
                                        <input hidden type="text" name="user_id" id="user_id" value=<?php echo $user_id; ?>>
                                        <button type="submit" class="bg-red-500 rounded-lg p-2">
                                            <img src="../public/icons/trash-outline.svg" alt="Delete" width="24" height="24">
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            <?php else : ?>
                <span class="text-center">No chats available</span>
            <?php endif; ?>
        </div>
        <div class="overflow-x-auto flex flex-col gap-5">
            <h1 class="text-center font-semibold text-2xl">All chats</h1>
            <?php if (count($chats) > 0) : ?>
                <table class="w-full text-sm text-left">
                    <thead class="text-xs uppercase bg-black">
                        <tr>
                            <th scope="col" class="px-6 py-3">
                                Chat name
                            </th>
                            <th scope="col" class="px-6 py-3">
                                Join
                            </th>
                        </tr>
                    </thead>
                    <tbody class="bg-black">
                        <?php foreach ($chats as $chat) : ?>
                            <tr class="border-b">
                                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                    <span class="bg-blue-500 rounded-lg p-2"><?php echo $chat["chat_name"]; ?></span>
                                </th>
                                <td class="px-6 py-4">
                                    <a href="/class-chat/chat.php?chat_id=<?php echo $chat["id"]; ?>">
                                        <button class="bg-green-500 rounded-lg p-2">
                                            <img src="../public/icons/chatbubbles-outline.svg" alt="Join" width="24" height="24">
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            <?php else : ?>
                <span class="text-center">No chats available</span>
            <?php endif; ?>
        </div>
    </main>
</body>

</html>