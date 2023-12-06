<?php
header("Content-Type: application/test");

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $attachment_location = "../public/data/data_500mb.test";

    if (file_exists($attachment_location)) {
        header($_SERVER["SERVER_PROTOCOL"] . " 200 OK");
        header("Cache-Control: public"); 
        header("Content-Type: application/test");
        header("Content-Transfer-Encoding: Binary");
        header("Content-Length:" . filesize($attachment_location));
        header("Content-Disposition: attachment; filename=data_500mb.test");

        readfile($attachment_location);
        die();
    } else {
        die("Error: File not found.");
    }
}
