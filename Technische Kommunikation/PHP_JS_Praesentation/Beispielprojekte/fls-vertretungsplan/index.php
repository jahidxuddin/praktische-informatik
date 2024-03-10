<?php
require_once('vendor/autoload.php');

use Goutte\Client;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();

$crawler = $client->request('GET', 'https://www.fls-wiesbaden.de/vplan');

$edited = $crawler->filter("p.stand")->text();
$vplans = $crawler->filter(".vplan")->filter("table");

function createTbodyData($tbodys)
{
    $tbody_data = [];
    $id = 0;

    foreach ($tbodys as $tbody) {
        $theadCrawler = new Crawler($tbody);
        $trs = $theadCrawler->filter("tr");

        $class = [];

        foreach ($trs as $tr) {
            $tr = new Crawler($tr);

            if ($tr->attr("class") === "vplan_blank") {
                continue;
            } else if ($tr->attr("class") === "vplan_class_title") {
                if (!empty($class)) {
                    $tbody_data[] = $class;
                }
                $class = [];

                $td = $tr->filter("td");
                $class_name = substr($td->filter(".class_name")->text(), strlen("Klasse: "));
                $school_name = $td->filter(".school_name")->text();
            } else {
                $tds = $tr->filter("td");

                $position = $tds->filter(".position")->text();
                $teacher = $tds->filter(".teacher")->text();
                $subject = $tds->filter(".subject")->text();
                $room  = $tds->filter(".room")->text();
                $vteacher = $tds->filter(".vteacher")->text();
                $vsubject = $tds->filter(".vsubject")->text();
                $vroom = $tds->filter(".vroom")->text();
                $merkmal = $tds->filter(".merkmal")->text();
                $info = $tds->filter(".info")->text();

                $row = array(
                    "class_name" => $class_name,
                    "school_name" => $school_name,
                    "position" => $position,
                    "teacher" => $teacher,
                    "subject" => $subject,
                    "room" => $room,
                    "vteacher" => $vteacher,
                    "vsubject" => $vsubject,
                    "vroom" => $vroom,
                    "merkmal" => $merkmal,
                    "info" => $info
                );

                $class[] = $row;
            }
        }

        if (!empty($class)) {
            $tbody_data[] = $class;
        }
    }

    return $tbody_data;
}

if ($vplans->count() > 0) {
    $data = [];

    foreach ($vplans as $vplan) {
        $tableCrawler = new Crawler($vplan);

        $pattern = "/Stand:\s+(\d{2}.\d{2}.\d{4}),\s+(\d{2}:\d{2})\s+Uhr/";
        if (preg_match($pattern, $edited, $matches)) {
            $data["edited"] = $matches[1] . " " . $matches[2];
        } else {
            $data["edited"] = "Unknown";
        }

        $summary = $tableCrawler->attr('summary');

        preg_match('/\d{2}\.\d{2}\.\d{4}/', $summary, $matches);
        $date = isset($matches[0]) ? $matches[0] : null;
        
        $tbodys = $tableCrawler->filter("tbody");

        $tbody_data = createTbodyData($tbodys);

        $data[$date] = $tbody_data;

    }

    header('Content-Type: application/json');
    echo json_encode($data, JSON_PRETTY_PRINT);
    exit;
}
