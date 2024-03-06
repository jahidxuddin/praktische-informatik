CREATE DATABASE IF NOT EXISTS schuelerverwaltung;

USE schuelerverwaltung;

DROP TABLE IF EXISTS schueler_kurs;
DROP TABLE IF EXISTS kurs;
DROP TABLE IF EXISTS schueler;
DROP TABLE IF EXISTS lehrer;

CREATE TABLE IF NOT EXISTS lehrer (
    lehrerNr INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    anrede VARCHAR(30),
    nachname VARCHAR(30),
    vorname VARCHAR(30)
);

INSERT INTO lehrer (anrede, nachname, vorname)
VALUES
    ('Herr', 'Schlosser', 'Michael'),
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Hardt', 'Micheal'),
    ('Herr', 'Schumacher', 'Wolfram'),
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Schumacher', 'Wolfram');

CREATE TABLE IF NOT EXISTS schueler (
    schuelerNr INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    vorname VARCHAR(30),
    nachname VARCHAR(30),
    geburtsdatum DATE,
    geschlecht CHAR(1),
    straße VARCHAR(255),
    plz CHAR(5),
    stadt VARCHAR(255),
    email VARCHAR(255),
    klasse CHAR(5),
    lehrerNr INT,
    FOREIGN KEY (lehrerNr) REFERENCES lehrer(lehrerNr)
);

INSERT INTO schueler (vorname, nachname, geburtsdatum, geschlecht, straße, plz, stadt, email, klasse, lehrerNr)
VALUES
    ('Max', 'Meier', '2001-02-16', 'm', 'Feldstraße 24a', '65183', 'Wiesbaden', 'max.meier@gmail.com', '12-07', 1),
    ('Tom', 'Maier', '2001-06-06', 'm', 'Friedrichstraße 18A', '65232', 'Taunusstein', 'tom.maier@web.de', '12-09', 2),
    ('Jan', 'Mayer', '2000-10-30', 'm', 'Tulpenweg 41', '65201', 'Wiesbaden', 'jan.mayer@gmx.net', '12-09', 3),
    ('Emma', 'Meyer', '2001-05-06', 'w', 'Engenhahnertrasse 24', '65527', 'Niedernhausen', 'emma.meyer@web.de', '12-09', 4),
    ('Tim', 'Mayr', '2001-12-31', 'm', 'Unterster Zwerchweg 37', '60599', 'Frankfurt', 'tim.mayr@gmail.com', '12-08', 5),
    ('Ben', 'Meyr', '2001-04-13', 'm', 'Dr. Karl Hermann May Str. 8', '65321', 'Heidenrod-Kemel', 'ben.meyr@gmail.com', '12-09', 3),
    ('Mia', 'Schmidt', '2001-05-16', 'w', 'Am alten Weinberg 23', '65207', 'Wiesbaden', 'mia.schmidt@gmx.net', '12-09', 1),
    ('Leo', 'Schmidtke', '2000-11-11', 'm', 'Am Weinstock 3', '65205', 'Wiesbaden', 'leo.schmidtke@gmail.com', '12-09', 2),
    ('Anna', 'Schmidt', '2001-03-14', 'w', 'Baumstraße 17', '65187', 'Wiesbaden', 'anna.schmidt@web.de', '12-09', 3),
    ('Paul', 'Schmitt', '2001-04-26', 'm', 'Freiherr vom Stein Str 14', '56348', 'Weisel', 'paul.schmitt@web.de', '12-09', 5),
    ('Lucas', 'Schmidt', '2001-05-23', 'm', 'Pathfesterhof 1', '65391', 'Espenschied', 'lucas.schmidt@t-online.de', '12-08', 1),
    ('Lisa', 'Schmied', '2000-07-17', 'w', 'Alte Brücke 13', '65207', 'Wiesbaden', 'lisa.schmied@web.de', '12-07', 2);

CREATE TABLE IF NOT EXISTS fachbezeichnung (
    fachbezeichnungNr INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fachbezeichnung varchar(30)
);

INSERT INTO fachbezeichnung(fachbezeichnung)
VALUES
    ('Praktische Informatik'),
    ('Religion'),
    ('Sport'),
    ('Ethik'),
    ('Chemie'),
    ('Politik und Wirtschaft'),
    ('Mathematik'),
    ('Geschichte'),
    ('Deutsch');

CREATE TABLE IF NOT EXISTS kurs (
    kursNr INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    kursbezeichnung varchar(30),
    fachbezeichnungNr INT,
    lehrerNr INT,
    FOREIGN KEY (lehrerNr) REFERENCES lehrer(lehrerNr),
    FOREIGN KEY (fachbezeichnungNr) REFERENCES fachbezeichnung(fachbezeichnungNr)
);

INSERT INTO kurs(kursbezeichnung, fachbezeichnungNr)
VALUES
    ('Datenbanken', 1),
    ('Kichliche Dogmen', 2),
    ('Handball', 3),
    ('Moral', 4),
    ('Organische Chemie', 5),
    ('Demokratiesysteme', 6),
    ('Analysis', 7),
    ('Römisches Reich', 8),
    ('Sturm und Drang', 9);

CREATE TABLE schueler_kurs(
    schuelerKursNr INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    schuelerNr INT,
    kursNr INT,
    note SMALLINT,
    FOREIGN KEY (schuelerNr) REFERENCES schueler(schuelerNr),
    FOREIGN KEY (kursNr) REFERENCES kurs(kursNr)
);

INSERT INTO schueler_kurs(schuelerNr, kursNr, note)
VALUES
    (1, 1, 6),
    (2, 1, 15),
    (1, 2, 8),
    (2, 2, 11),
    (1, 3, 11),
    (7, 3, 7),
    (1, 4, 10),
    (2, 4, 12),
    (3, 4, 5),
    (1, 5, 13),
    (2, 5, 12),
    (1, 6, 14),
    (9, 6, 8),
    (1, 7, 10),
    (12, 7, 6),
    (6, 8, 12),
    (10 , 9, 7);

SELECT * FROM schueler ORDER BY nachname;
SELECT vorname, nachname FROM schueler WHERE stadt LIKE '%a%';
SELECT nachname FROM schueler WHERE email LIKE '%@gmail.com';
SELECT nachname FROM schueler WHERE LENGTH(vorname) = 4;
SELECT * FROM schueler WHERE LEFT(vorname, 1) BETWEEN 'A' AND 'F';
SELECT nachname, vorname FROM schueler WHERE geschlecht = 'm';
SELECT * FROM schueler WHERE MONTH(geburtsdatum) = 5;
SELECT s.*, l.lehrerNr FROM schueler s JOIN lehrer l ON s.lehrerNr = l.lehrerNr WHERE plz LIKE '65%' AND l.nachname LIKE 'M%';
SELECT nachname FROM lehrer WHERE anrede = 'Herr' AND nachname NOT LIKE 'S%';

SELECT s.vorname, s.nachname, l.vorname, l.nachname FROM schueler s JOIN lehrer l on s.lehrerNr = l.lehrerNr;

SELECT s.vorname, s.nachname, f.fachbezeichnung, k.kursbezeichnung, sk.note, l.nachname
FROM schueler_kurs sk
JOIN schueler s ON sk.schuelerNr = s.schuelerNr
JOIN kurs k ON sk.kursNr = k.kursNr
JOIN fachbezeichnung f on k.fachbezeichnungNr = f.fachbezeichnungNr
JOIN lehrer l ON s.lehrerNr = l.lehrerNr;

SELECT s.vorname, s.nachname, sk.note, l.nachname
FROM schueler_kurs sk
JOIN schueler s on sk.schuelerNr = s.schuelerNr
JOIN lehrer l on l.lehrerNr = s.lehrerNr
JOIN kurs k ON sk.kursNr = k.kursNr
WHERE k.kursbezeichnung = 'Datenbanken';

SELECT s.vorname, s.nachname, sk.note, l.nachname
FROM schueler_kurs sk
JOIN schueler s ON s.schuelerNr = sk.schuelerNr
JOIN lehrer l ON l.lehrerNr = s.lehrerNr
JOIN kurs k ON sk.kursNr = k.kursNr WHERE sk.note > 5;

SELECT s.vorname, s.nachname
FROM schueler_kurs sk
JOIN schueler s ON s.schuelerNr = sk.schuelerNr
JOIN kurs k ON sk.kursNr = k.kursNr WHERE sk.note BETWEEN 5 AND 9;

SELECT s.vorname, s.nachname
FROM schueler_kurs sk
JOIN schueler s ON sk.schuelerNr = s.schuelerNr
JOIN kurs k ON sk.kursNr = k.kursNr
JOIN fachbezeichnung f on k.fachbezeichnungNr = f.fachbezeichnungNr
WHERE f.fachbezeichnung = 'Chemie' OR f.fachbezeichnung = 'Geschichte' AND sk.note >= 12;

SELECT s.vorname, s.nachname, k.kursbezeichnung, f.fachbezeichnung, l.anrede, l.nachname, sk.note
FROM schueler_kurs sk
JOIN schueler s ON sk.schuelerNr = s.schuelerNr
JOIN kurs k ON sk.kursNr = k.kursNr
JOIN fachbezeichnung f on k.fachbezeichnungNr = f.fachbezeichnungNr
JOIN lehrer l ON s.lehrerNr = l.lehrerNr;
