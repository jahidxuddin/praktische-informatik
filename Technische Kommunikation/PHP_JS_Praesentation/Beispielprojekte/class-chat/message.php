<?php 
require_once("./database/database.php");

header('Content-Type: text/event-stream');
header('Cache-Control: no-cache');

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

$chat_id = $_GET['chat_id'];

while (true) {
    // Generate or fetch the data you want to send
    $data = getChatMessages($conn, $chat_id);

    // Send the data to the client
    echo "data: " . json_encode($data) . "\n\n";
    // Flush the output buffer to ensure data is sent immediately
    ob_flush();
    flush();

    // Delay for a specific period (e.g., 1 second) before sending the next event
    sleep(1);
}