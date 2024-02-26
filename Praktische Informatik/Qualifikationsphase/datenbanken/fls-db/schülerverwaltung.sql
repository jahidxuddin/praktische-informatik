CREATE DATABASE IF NOT EXISTS schuelerverwaltung;

USE schuelerverwaltung;

DROP TABLE IF EXISTS lehrer;
DROP TABLE IF EXISTS schueler;

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
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Schumacher', 'Wolfram'),
    ('Herr', 'Schlosser', 'Michael'),
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Mrazek', 'Ralf'),
    ('Herr', 'Mrazek', 'Ralf'),
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
    ('Ben', 'Meyr', '2001-04-13', 'm', 'Dr. Karl Hermann May Str. 8', '65321', 'Heidenrod-Kemel', 'ben.meyr@gmail.com', '12-09', 6),
    ('Mia', 'Schmidt', '2001-05-16', 'w', 'Am alten Weinberg 23', '65207', 'Wiesbaden', 'mia.schmidt@gmx.net', '12-09', 7),
    ('Leo', 'Schmidtke', '2000-11-11', 'm', 'Am Weinstock 3', '65205', 'Wiesbaden', 'leo.schmidtke@gmail.com', '12-09', 8),
    ('Anna', 'Schmidt', '2001-03-14', 'w', 'Baumstraße 17', '65187', 'Wiesbaden', 'anna.schmidt@web.de', '12-09', 9),
    ('Paul', 'Schmitt', '2001-04-26', 'm', 'Freiherr vom Stein Str 14', '56348', 'Weisel', 'paul.schmitt@web.de', '12-09', 10),
    ('Lucas', 'Schmidt', '2001-05-23', 'm', 'Pathfesterhof 1', '65391', 'Espenschied', 'lucas.schmidt@t-online.de', '12-08', 1),
    ('Lisa', 'Schmied', '2000-07-17', 'w', 'Alte Brücke 13', '65207', 'Wiesbaden', 'lisa.schmied@web.de', '12-07', 2);

SELECT * FROM schueler ORDER BY nachname;
SELECT vorname, nachname FROM schueler WHERE stadt LIKE '%a%';
SELECT nachname FROM schueler WHERE email LIKE '%gmail%';
SELECT nachname FROM schueler WHERE LENGTH(vorname) = 4;
SELECT * FROM schueler WHERE LEFT(vorname, 1) BETWEEN 'A' AND 'F';
SELECT nachname, vorname FROM schueler WHERE geschlecht = 'm';
SELECT * FROM schueler WHERE MONTH(geburtsdatum) = 5;
SELECT s.*, l.lehrerNr FROM schueler s JOIN lehrer l ON s.lehrerNr = l.lehrerNr WHERE plz LIKE '65%' AND l.nachname LIKE 'M%';
SELECT nachname FROM lehrer WHERE anrede = 'Herr' AND nachname NOT LIKE 'S%';