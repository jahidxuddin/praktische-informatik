<?php
header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $requestData = json_decode(file_get_contents('php://input'), true);

    echo json_encode(['message' => 'File arrived successfully']);
}
