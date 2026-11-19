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
