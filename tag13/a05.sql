CREATE TABLE KUNDE (
    KundenID INT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    EMail VARCHAR(150) NULL,
    Ort VARCHAR(100) NOT NULL
);

CREATE TABLE BESTELLUNG (
    BestellID INT PRIMARY KEY,
    KundenID INT NOT NULL,
    Status VARCHAR(50) NOT NULL,
    Gesamtbetrag DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_bestellung_kunde 
        FOREIGN KEY (KundenID) 
        REFERENCES KUNDE(KundenID)
);

INSERT INTO KUNDE (KundenID, Name, EMail, Ort) VALUES
(1, 'Mira Hansen', 'mira@example.de', 'Köln'),
(2, 'Leon Weber', 'leon@example.de', 'Bonn'),
(3, 'Samira Yilmaz', NULL, 'Köln'),
(4, 'Paul Neumann', 'paul@beispiel.de', 'Essen');

INSERT INTO BESTELLUNG (BestellID, KundenID, Status, Gesamtbetrag) VALUES
(1001, 1, 'OFFEN', 120.00),
(1002, 1, 'VERSENDET', 45.00),
(1003, 2, 'OFFEN', 80.00),
(1004, 3, 'STORNIERT', 25.00),
(1005, 4, 'OFFEN', 120.00),
(1006, 2, 'VERSENDET', 0.00);

SELECT *
FROM KUNDE;

SELECT Name, Ort
FROM KUNDE;

SELECT BestellID, Status, Gesamtbetrag
FROM BESTELLUNG;

SELECT *
FROM BESTELLUNG
WHERE Status = 'OFFEN';

SELECT *
FROM BESTELLUNG
WHERE Gesamtbetrag >= 80.00;

SELECT *
FROM BESTELLUNG
WHERE Status = 'OFFEN'
   AND Gesamtbetrag >= 100.00;

SELECT * FROM BESTELLUNG
WHERE Status != 'OFFEN' AND Status != 'VERSENDET';

SELECT *
FROM BESTELLUNG
WHERE Status <> 'OFFEN'
   AND Status <> 'VERSENDET';

SELECT *
FROM KUNDE
WHERE Ort = 'Köln' OR Ort = 'Bonn';

SELECT *
FROM KUNDE
WHERE Ort = 'Köln'
   AND Name LIKE 'M%';

SELECT *
FROM KUNDE
WHERE EMail IS NULL;

SELECT *
FROM KUNDE
WHERE EMail IS NOT NULL;

SELECT COUNT(*) FROM BESTELLUNG;

SELECT SUM(Gesamtbetrag) AS Summe
FROM BESTELLUNG;

SELECT AVG(Gesamtbetrag) AS Durchschnitt
FROM BESTELLUNG;

SELECT MIN(Gesamtbetrag) AS Minimum,
         MAX(Gesamtbetrag) AS Maximum
FROM BESTELLUNG;

SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status;

SELECT Status, SUM(Gesamtbetrag) AS Summe
FROM BESTELLUNG
GROUP BY Status;

SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status
ORDER BY Anzahl DESC;

SELECT Status, SUM(Gesamtbetrag) AS Gesamtsumme
FROM BESTELLUNG
WHERE Gesamtbetrag >= 20.00
GROUP BY Status
HAVING SUM(Gesamtbetrag) >= 100.00;