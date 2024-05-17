CREATE DATABASE IF NOT EXISTS druckdb;
USE druckdb;

DROP TABLE IF EXISTS artikel;
DROP TABLE IF EXISTS warengruppe;
DROP TABLE IF EXISTS bestellung;
DROP TABLE IF EXISTS lieferant;
DROP TABLE IF EXISTS umsatzsteuer;

CREATE TABLE warengruppe (
    gruppenNr VARCHAR(2) NOT NULL PRIMARY KEY,
    gruppenName VARCHAR(20)
);

INSERT INTO warengruppe (gruppenNr, gruppenName)
VALUES
    ('10', 'Offset-Papier'),
    ('20', 'Öko-Papier'),
    ('30', 'DV-Papier');

CREATE TABLE artikel (
    artikelNr INT NOT NULL PRIMARY KEY,
    artikelBezeichnung VARCHAR(30),
    steuerSchluessel INT,
    gruppenNr VARCHAR(2),
    meldeBestand INT,
    istBestand INT,
    CONSTRAINT FOREIGN KEY (gruppenNr) REFERENCES warengruppe(gruppenNr)
);

INSERT INTO artikel (artikelNr, artikelBezeichnung, steuerSchluessel, gruppenNr, meldeBestand, istBestand)
VALUES
    (1120, 'SignaColor 120-A4 weiß', 1, 10, 10000, 5000),
    (1122, 'SignaColor 80-A5 weiß', 1, 10, 40000, 50000),
    (1515, 'SignaColor 70-A4 weiß', 1, 10, 30000, 40000),
    (1517, 'SignaColor 70-A4 hellgrün', 1, 10, 25000, 50000),
    (1616, 'SignaColor 80-A4 weiß', 1, 10, 40000, 30000),
    (1825, 'SignaColor 80-A4 hellgrün', 1, 10, 25000, 20000),
    (2113, 'Öko-Color 80-A4 weiß', 1, 20, 60000, 40000),
    (2920, 'Öko-Color 70-A4 weiß', 1, 20, 50000, 50000),
    (3718, 'Laser-Color 80-A4 hellgrün', 1, 30, 50000, 55000),
    (3721, 'Öko-Color 70-A4 hellgrün', 1, 20, 40000, 30000),
    (4012, 'Laser-Color 70-A4 hellgrün', 1, 30, 50000, 30000),
    (4158, 'Laser-Color 70-A4 weiß', 1, 30, 20000, 20000),
    (4259, 'Laser-Color 80-A4 hellgrün', 1, 30, 90000, 105000),
    (4630, 'Öko-Color 80-A4 hellgrün', 1, 20, 40000, 50000),
    (5241, 'Öko-Color 80-A5 weiß', 1, 20, 80000, 90000),
    (6908, 'Öko-Color 100-A4 hellgrün', 1, 20, 20000, 30000);

CREATE TABLE lieferant (
    lieferantenNr INT NOT NULL PRIMARY KEY,
    lieferantenName VARCHAR(30),
    strasse VARCHAR(30),
    plz VARCHAR(5),
    ort VARCHAR(30),
    telefon VARCHAR(12),
    rabattSatz FLOAT(5,2)
);

INSERT INTO lieferant(lieferantenNr, lieferantenName, strasse, plz, ort, telefon, rabattSatz)
VALUES
    (28447, 'Sauer & Esser', 'Danziger Str. 33', '48683', 'Ahaus', '02561/77-0', 0.14),
    (47544, 'Müller & Söhne', 'Krefelder Str. 19', '41748', 'Viersen', '02162/336-0', 0.15),
    (48771, 'Zwinger GmbH', 'Leipziger Str. 35', '01127', 'Dresden', '0351/8574-0', 0.17),
    (52441, 'Euro Papier', 'Aachener Str 127', '40223', 'Düsseldorf', '0211/1525-0', 0.10),
    (65881, 'Metz & Co', 'Kantstr. 3','99425', 'Weimar', '0364/66851', 0.20),
    (85547, 'Peter Paulsen', 'Lützowstr. 33', '09116', 'Chemnitz', '0371/6955-0', 0.18),
    (88512, 'Xaver Seiler', 'Riedener Str. 157', '81337', 'München', '089/7274-0', 0.10);

CREATE TABLE bestellung (
    bestellNr INT NOT NULL PRIMARY KEY,
    artikelNr VARCHAR(8),
    lieferantenNr INT,
    angebotsPreis DECIMAL(5,2),
    angebotsDatum DATE,
    CONSTRAINT FOREIGN KEY (lieferantenNr) REFERENCES lieferant(lieferantenNr)
);

INSERT INTO bestellung (bestellNr, artikelNr, lieferantenNr, angebotsPreis, angebotsDatum)
VALUES
    (14887, 1120, 47544, 26.80, '2021-04-23'),
    (15466, 3721, 28447, 22.00, '2021-04-22'),
    (15477, 4630, 28447, 26.00, '2021-04-22'),
    (17231, 1515, 65881, 20.00, '2021-02-16'),
    (21, 1122, 88512, 16.50, '2021-06-16'),
    (25441, 1515, 52441, 21.53, '2021-08-30'),
    (26263, 4012, 52441, 26.25, '2021-08-30'),
    (26288, 3718, 52441, 28.35, '2021-08-30'),
    (2652, 1616, 85547, 23.00, '2021-01-24'),
    (4, 1616, 28447, 22.80, '2021-05-25'),
    (477112, 1825, 65881, 24.30, '2021-02-16'),
    (4877, 5241, 65881, 16.00, '2021-02-16'),
    (52, 4630, 52441, 25.20, '2021-08-30'),
    (52451, 3721, 52441, 24.68, '2021-08-30'),
    (543, 5241, 85547, 16.25, '2021-02-19'),
    (544, 2920, 85547, 22.00, '2021-02-19'),
    (545, 2113, 85547, 23.00, '2021-02-19'),
    (55221, 1517, 47544, 22.00, '2021-04-22'),
    (58441, 6908, 48771, 27.00, '2021-04-26'),
    (5884, 1515, 48771, 20.90, '2021-02-13'),
    (847, 4259, 28447, 26.00, '2021-05-25'),
    (848, 4158, 28447, 24.00, '2021-05-25'),
    (85574, 1120, 28447, 26.50, '2021-04-22'),
    (87, 3721, 47544, 23.00, '2021-04-23'),
    (8744, 1825, 48771, 24.10, '2021-04-26'),
    (871, 4630, 47544, 25.00, '2021-04-23'),
    (87544, 1616, 88512, 23.50, '2021-05-13'),
    (88541, 1120, 52441, 28.35, '2021-08-30'),
    (88542, 1122, 52441, 17.12, '2021-08-30'),
    (89541, 4158, 52441, 25.73, '2021-08-30'),
    (89543, 4259, 52441, 27.83, '2021-08-30'),
    (96554, 4012, 47544, 26.00, '2021-04-23');

CREATE TABLE umsatzsteuer (
    steuerSchluessel INT NOT NULL PRIMARY KEY,
    umsatzSteuer INT
);

INSERT INTO umsatzsteuer (steuerSchluessel, umsatzSteuer)
VALUES
    (1, 7),
    (2, 19);

SELECT
    l.lieferantenName,
    a.artikelBezeichnung,
    b.angebotsPreis,
    u.umsatzSteuer,
    w.gruppenName
FROM artikel a
JOIN bestellung b ON a.artikelNr = b.artikelNr
JOIN lieferant l ON b.lieferantenNr = l.lieferantenNr
JOIN umsatzsteuer u ON a.steuerSchluessel = u.steuerSchluessel
JOIN warengruppe w ON a.gruppenNr = w.gruppenNr
ORDER BY lieferantenName LIMIT 13;

SELECT artikelNr, artikelBezeichnung, istBestand FROM artikel WHERE artikelBezeichnung LIKE '%A5%';

SELECT lieferantenName, strasse FROM lieferant WHERE strasse LIKE '_a%' OR strasse LIKE '_e%';

SELECT artikelNr, artikelBezeichnung, gruppenNr, istBestand, meldeBestand
FROM artikel
WHERE (gruppenNr = 10 OR gruppenNr = 30) AND istBestand < meldeBestand;

SELECT
    artikelNr,
    artikelBezeichnung,
    istBestand, meldeBestand,
    IF (istBestand >= meldeBestand, 'Alles in Ordnung', 'Achtung der Bestand ist zu niedrig!') AS bemerkung
FROM artikel;

SELECT
    artikelNr,
    artikelBezeichnung,
    istBestand, meldeBestand,
    IF (istBestand >= meldeBestand, 'Alles in Ordnung',
        IF(istBestand - meldeBestand >= 5000, 'Achtung der Bestand ist extrem niedrig!',
           'Achtung der Bestand ist zu niedrig!')) AS bemerkung
FROM artikel;

SELECT
    artikelNr,
    istBestand,
    @erhoehterMeldebestand := ROUND(meldeBestand * 1.25) AS erhoehterMeldebestand,
    @fehlbestand := ROUND(@erhoehterMeldebestand - istBestand) AS fehlbestand
FROM artikel;

SELECT
    artikelNr,
    istBestand,
    @erhoehterMeldebestand := ROUND(meldeBestand * 1.25) AS erhoehterMeldebestand,
    ROUND(@erhoehterMeldebestand - istBestand) AS fehlbestand
FROM artikel WHERE ROUND(ROUND(meldeBestand * 1.25) - istBestand) > 0;

SELECT AVG(rabattSatz) FROM lieferant;

SELECT lieferantenName, rabattSatz, (SELECT AVG(rabattSatz) FROM lieferant)
FROM lieferant
WHERE rabattSatz < (SELECT AVG(rabattSatz) FROM lieferant);

SELECT a.artikelBezeichnung, l.lieferantenName, angebotsPreis, a.gruppenNr
FROM artikel a
JOIN bestellung b ON b.artikelNr = a.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE a.artikelBezeichnung LIKE '%A4 weiß'
AND b.angebotspreis < (
SELECT MIN(angebotsPreis) FROM bestellung
JOIN artikel ON artikel.artikelNr = bestellung.artikelNr
WHERE artikel.gruppenNr = 30
);

SELECT
    a.artikelNr,
    l.lieferantenNr,
    @vorschlagsmenge := 2 * a.meldeBestand - a.istBestand AS vorschlagsMenge,
    (@vorschlagsmenge * b.angebotsPreis / 1000) * (1 - l.rabattSatz) AS einkaufsPreis
FROM artikel a
JOIN bestellung b ON a.artikelNr = b.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE a.istBestand < a.meldeBestand;

SELECT
    a.artikelNr,
    MIN(
        (@vorschlagsmenge * b.angebotsPreis / 1000) * (l.rabattSatz / 100)
    ) AS niedrigsterEinkaufsPreis
FROM artikel a
JOIN bestellung b ON a.artikelNr = b.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE a.istBestand < a.meldeBestand
GROUP BY a.artikelNr;

SELECT
    a.artikelNr,
    l.lieferantenNr,
    @vorschlagsmenge := 2 * a.meldeBestand - a.istBestand AS vorschlagsMenge,
    (@vorschlagsmenge * b.angebotsPreis / 1000) * (l.rabattSatz / 100) AS einkaufsPreis
FROM artikel a
JOIN bestellung b ON a.artikelNr = b.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE a.istBestand < a.meldeBestand;

DELETE FROM artikel WHERE artikelNr = 1616;

SELECT
    a.artikelNr,
    MAX(
        (2 * a.meldeBestand - a.istBestand) * b.angebotsPreis / 1000 * (l.rabattSatz / 100) * 1.2
    ) AS maximalerPreis
FROM artikel a
JOIN bestellung b ON a.artikelNr = b.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE a.istBestand < a.meldeBestand AND a.artikelBezeichnung LIKE '%DIN-A4 weiß'
GROUP BY a.artikelNr
HAVING MAX((2 * a.meldeBestand - a.istBestand) * b.angebotsPreis / 1000 * (l.rabattSatz / 100) * 1.2) > 1000
ORDER BY maximalerPreis DESC;

SELECT l.lieferantenName, l.rabattSatz AS rabattsatz
FROM lieferant l ORDER BY rabattSatz DESC LIMIT 3;

SELECT l.lieferantenName, l.rabattSatz AS rabattsatz
FROM lieferant l ORDER BY rabattSatz DESC LIMIT 1,2;

SELECT b.bestellNr, a.artikelBezeichnung, l.lieferantenName, MONTH(b.angebotsDatum) AS monat, YEAR(b.angebotsDatum) AS jahr
FROM bestellung b
JOIN artikel a ON a.artikelNr = b.artikelNr
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE MONTH(b.angebotsDatum) = 2;

SELECT DISTINCT l.lieferantenName, MONTH(b.angebotsDatum) AS monat, YEAR(b.angebotsDatum) AS jahr
FROM bestellung b
JOIN lieferant l ON l.lieferantenNr = b.lieferantenNr
WHERE MONTH(b.angebotsDatum) BETWEEN 1 AND 3 ORDER BY l.lieferantenName;

INSERT INTO lieferant (lieferantenNr, lieferantenName, strasse, plz, ort)
VALUES
    (50000, 'FLS GmbH', 'Brunhildenstr 142', '65189', 'Wiesbaden'),
    (50001, 'FES OHG', 'Balthasar-Neumann-Straße 1', '65189', 'Wiesbaden');

UPDATE lieferant
SET telefon = '0611/315-100'
WHERE lieferantenName = 'FLS GmbH';

SELECT l.lieferantenNr, l.lieferantenName, l.strasse, l.plz, l.ort, l.telefon, l.rabattSatz
FROM lieferant l  WHERE l.rabattSatz IS NULL;

DELETE FROM lieferant WHERE lieferantenName IN ('FLS GmbH', 'FES OHG');
