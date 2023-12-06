<?php

require_once(__DIR__ . "/../config/config.php");

$conn = new mysqli($DB_HOST, $DB_USERNAME, $DB_PASSWORD, $DB_NAME);

if ($conn->connect_error) {
    header('Content-Type: application/json');
    http_response_code(500);
    $response = array('error' => 'Database connection failed: ' . $conn->connect_error);
    echo json_encode($response);
    exit;
}
