<?php

$user_id = $_COOKIE["user_id"];

require_once("../database/database.php");

$query = "SELECT * FROM users WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $user_id);
$stmt->execute();

$result = $stmt->get_result();

if (!$result->num_rows > 0) {
    setcookie('user_id', '', time() - 3600, '/');
    header("Location: /class-chat/auth/login.php");
    exit;
}

$query = "UPDATE users SET is_logged_in = 0 WHERE id = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param("i", $user_id);
$stmt->execute();

setcookie('user_id', '', time() - 3600, '/');

$stmt->close();
$conn->close();

header("Location: /class-chat/auth/login.php");