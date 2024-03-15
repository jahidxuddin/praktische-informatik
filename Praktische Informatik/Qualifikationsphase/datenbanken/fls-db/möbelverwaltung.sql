CREATE DATABASE IF NOT EXISTS moebeldb;
USE moebeldb;

DROP TABLE IF EXISTS auftrag;
DROP TABLE IF EXISTS produkt;
DROP TABLE IF EXISTS produktgruppe;
DROP TABLE IF EXISTS kunde;

CREATE TABLE kunde (
    kundenNr INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    anrede VARCHAR(15),
    nachname VARCHAR(25),
    vorname VARCHAR(20),
    strasse VARCHAR(30),
    plz VARCHAR(5),
    ort VARCHAR(40),
    telefon VARCHAR(20),
    fax VARCHAR(20)
);

INSERT INTO kunde (kundenNr, anrede, nachname, vorname, strasse, plz, ort, telefon, fax)
VALUES
    (1,'Herr', 'Ebers', 'Eberhard', 'Erlenallee 23', '24233', 'Eisenburg', '02411/19786', '0241/19787') ,
    (2, 'Herr', 'Böse', 'Benno', 'Bärenweg 12', '97845', 'Burghausen', '09871/112344', '09871/112345')  ,
    (3, 'Frau', 'Fröhlich', 'Frieda', 'Findorfstr. 234', '45666', 'Feuerbach', '0234/1234', '0234/1234') ,
    (4, 'Frau', 'Mobius', 'Martina', 'Malvengarten 77', '65443', 'Marienstadt', '06345/7070', '06345/7071'),
    (5, 'Herr', 'Hayermann', 'Hubert', 'Helenenstr. 22', '12342', 'Hollerbach', '03344/123389', '03344/23445'),
    (6, 'Frau', 'Zaworsky', 'Ute', 'Zeppelinstr. 12', '99087', 'Zehlendorf', '08766/43477', '08766/83478'),
    (7, 'Frau', 'Reimann', 'Renate', 'Rosenweg 14', '99087', 'Zehlendorf', '08766/33445', '08766/33475');

CREATE TABLE produktgruppe (
    produktgruppenNr INT NOT NULL PRIMARY KEY,
    gruppenbezeichnung VARCHAR(30)
);

INSERT INTO produktgruppe (produktgruppenNr,gruppenbezeichnung)
VALUES
    (1, 'Stühle'),
    (2, 'Tische'),
    (3, 'Regale');

CREATE TABLE produkt (
    produktNr INT NOT NULL PRIMARY KEY,
    produktbezeichnung varchar(50),
    lagerbestand INT,
    aktuellerpreis DECIMAL(6,2),
    stand DATE,
    produktgruppenNr INT,
    CONSTRAINT FOREIGN KEY (produktgruppenNr) REFERENCES produktgruppe (produktgruppenNr)
);

INSERT INTO produkt (produktNr, produktbezeichnung, lagerbestand, aktuellerpreis, stand, produktgruppenNr)
VALUES
    (10, 'Küchenstuhl', 7, 85.00, '2016-12-31', 1),
    (11, 'Melkschemel', 10, 52.00, '2017-06-04', 1),
    (13, 'Liegestuhl', 8, 145.00, '2017-07-28', 1),
    (20, 'Küchentisch', 12, 450.00, '2017-08-29', 2),
    (21, 'Gartentisch', 2, 27.00, '2017-12-23', 2),
    (30, 'Bücherregal', 12, 230.00, '2017-12-23', 3),
    (31, 'Küchenregal', 4, 175.00, '2017-12-23', 3);

CREATE TABLE auftrag (
    auftragsNr INT PRIMARY KEY,
    menge INT,
    bestelldatum DATE,
    rabattsatz DECIMAL(5,2),
    produktNr INT,
    kundenNr INT,
    CONSTRAINT FOREIGN KEY (produktNr) REFERENCES produkt (produktNr),
    CONSTRAINT FOREIGN KEY (kundenNr) REFERENCES kunde (kundenNr)
);

INSERT INTO auftrag (auftragsNr, menge, bestelldatum, rabattsatz, produktNr, kundenNr)
VALUES
    (1, 5, '2017-10-03', 0.15, 10, 2),
    (2, 4, '2017-01-05', 0.00, 31, 2),
    (3, 5, '2017-01-03', 0.15, 30, 1),
    (4, 1, '2017-01-07', 0.00, 21, 3),
    (5, 3, '2017-01-09', 0.15, 30, 4),
    (6, 2, '2017-02-05', 0.15, 31, 4),
    (7, 4, '2017-02-05', 0.15, 10, 4),
    (8, 1, '2017-02-10', 0.00, 21, 3),
    (9, 2, '2017-02-20', 0.15, 30, 1),
    (10, 1, '2017-02-28', 0.00, 20, 2);

SELECT produktNr, produktbezeichnung, aktuellerpreis FROM produkt;

SELECT produktNr, produktbezeichnung, aktuellerpreis
FROM produkt
WHERE produktbezeichnung LIKE 'K%';

SELECT * FROM kunde WHERE plz LIKE '45%';

SELECT p.*, pg.gruppenbezeichnung
FROM produkt p
JOIN produktgruppe pg ON p.produktgruppenNr = pg.produktgruppenNr
ORDER BY gruppenbezeichnung, aktuellerpreis DESC;

SELECT a.*, p.produktNr, p.produktbezeichnung, p.aktuellerpreis
FROM produkt p
JOIN auftrag a ON p.produktNr = a.produktNr
JOIN kunde k ON a.kundenNr = k.kundenNr ORDER BY auftragsNr;

SELECT k.nachname, k.vorname, p.produktbezeichnung
FROM kunde k
JOIN auftrag a ON k.kundenNr = a.kundenNr
JOIN produkt p ON a.produktNr = p.produktNr
WHERE plz LIkE '9%' AND p.produktbezeichnung LIKE 'Küchen%';

SELECT
    k.*,
    p.*,
    a.menge,
    a.bestelldatum,
    a.rabattsatz,
    @netto := ROUND(p.aktuellerpreis * a.menge * (1 - a.rabattsatz), 2) AS nettoumsatz,
    @mwst := ROUND(@netto * 0.19, 2) AS mwst,
    ROUND(@netto + @mwst) AS bruttoumsatz
FROM kunde k
JOIN auftrag a on k.kundenNr = a.kundenNr
JOIN produkt p on a.produktNr = p.produktNr
ORDER BY k.kundenNr;