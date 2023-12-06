<?php
/*
 * DEPRECATED SOON
*/
require_once('vendor/autoload.php');

use Goutte\Client;
use Symfony\Component\DomCrawler\Crawler;

$client = new Client();

$crawler = $client->request('GET', 'https://www.fls-wiesbaden.de/vplan');

$vplans = $crawler->filter(".vplan")->filter("table");

function createTheadData($thead_data, $theads, $summary)
{
    foreach ($theads as $thead) {
        $theadCrawler = new Crawler($thead);
        $ths = $theadCrawler->filter("tr")->filter("th");

        foreach ($ths as $th) {
            $th = new Crawler($th);
            array_push($thead_data, $th->text());
        }
    }

    return $thead_data;
}

function createTbodyData($tbodys, $summary)
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
                    $tbody_data[++$id] = $class;
                }
                $class = [];

                $td = $tr->filter("td");
                $class_name = $td->filter(".class_name")->text();
                $school_name = $td->filter(".school_name")->text();
                $class_title = array($class_name, $school_name);

                $class[] = $class_title;
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

                $row = array($position, $teacher, $subject, $room, $vteacher, $vsubject, $vroom, $merkmal, $info);

                $class[] = $row;
            }
        }
    }

    return $tbody_data;
}


if ($vplans->count() > 0) {
    $data = array();

    foreach ($vplans as $vplan) {
        $tableCrawler = new Crawler($vplan);
        $summary = $tableCrawler->attr('summary');
        $thead_data = array();
        $theads = $tableCrawler->filter("thead");
        $tbodys = $tableCrawler->filter("tbody");

        $thead_data = createTheadData($thead_data, $theads, $summary);
        $tbody_data = createTbodyData($tbodys, $summary);

        $vplan_data = array($thead_data, $tbody_data);

        $data[$summary] = $vplan_data;
    }

    header('Content-Type: application/json');
    echo json_encode($data);
    exit;
}
