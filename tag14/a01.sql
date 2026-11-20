# Achtung! Die befehle unten haben den 
# Ausgabszustand der Tabellen geändert! 

INSERT INTO KUNDE (KundenID, Name, EMail, Ort) VALUES
(5, 'Tarek Özdemir', 'tarek@example.de', 'Berlin');

SELECT b.BestellID, k.Name, b.Gesamtbetrag
FROM KUNDE AS k
INNER JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID;

SELECT b.BestellID, k.Name, b.Gesamtbetrag
FROM BESTELLUNG AS b
INNER JOIN KUNDE AS k ON b.KundenID = k.KundenID;

SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
INNER JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE k.Ort = 'Köln';

SELECT k.KundenID, k.Name, b.BestellID
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID;

SELECT k.KundenID, k.Name
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b ON b.KundenID = k.KundenID
WHERE b.BestellID IS NULL;

SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN';

SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE b.Status = 'OFFEN';

SELECT k.KundenID, k.Name
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN'
WHERE b.BestellID IS NULL;

SELECT *
FROM BESTELLUNG
WHERE Gesamtbetrag > (
    SELECT AVG(Gesamtbetrag)
    FROM BESTELLUNG
);

SELECT Name
FROM KUNDE
WHERE KundenID IN (
    SELECT KundenID
    FROM BESTELLUNG
    WHERE Status = 'OFFEN'
);

SELECT k.Name
FROM KUNDE AS k
WHERE EXISTS (
    SELECT 1
    FROM BESTELLUNG AS b
    WHERE b.KundenID = k.KundenID
      AND b.Status = 'OFFEN'
);

INSERT INTO KUNDE (KundenID, Name, EMail, Ort)
VALUES (6, 'Nora Fischer', 'nora@example.de', 'Dortmund');

UPDATE BESTELLUNG
SET Status = 'VERSENDET'
WHERE BestellID = 1001;

UPDATE BESTELLUNG
SET Gesamtbetrag = Gesamtbetrag + 10.00
WHERE BestellID = 1003;

DELETE FROM BESTELLUNG
WHERE BestellID = 1006;

SELECT k.Name, b.BestellID
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN';