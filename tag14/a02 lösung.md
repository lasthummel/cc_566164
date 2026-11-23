# Integrierte Aufgabe

Ein Shop möchte seine offenen Bestellungen analysieren.
Folgende Tabellen liegen vor:

**KUNDE**

| KundenID | Name | EMail | Ort |
|---:|---|---|---|
| 1 | Mira Hansen | mira@example.de | Köln |
| 2 | Leon Weber | leon@example.de | Bonn |
| 3 | Samira Yilmaz | NULL | Köln |
| 4 | Paul Neumann | paul@beispiel.de | Essen |
| 5 | Tarek Özdemir | tarek@example.de | Berlin |

**BESTELLUNG**

| BestellID | KundenID | Status | Gesamtbetrag |
|---:|---:|---|---:|
| 1001 | 1 | OFFEN | 120.00 |
| 1002 | 1 | VERSENDET | 45.00 |
| 1003 | 2 | OFFEN | 80.00 |
| 1004 | 3 | STORNIERT | 25.00 |
| 1005 | 4 | OFFEN | 120.00 |
| 1006 | 2 | VERSENDET | 0.00 |

Primärschlüssel: `KUNDE.KundenID`, `BESTELLUNG.BestellID`. Fremdschlüssel: `BESTELLUNG.KundenID` verweist auf `KUNDE.KundenID`. Kunde 5 hat keine Bestellung.

## A1. 
Geben Sie Kundenname, BestellID und Gesamtbetrag aller offenen Bestellungen aus, sortiert nach Gesamtbetrag absteigend.

**Lösung**  
```sql
SELECT k.Name, b.BestellID, b.Gesamtbetrag
FROM KUNDE AS k
INNER JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE b.Status = 'OFFEN'
ORDER BY b.Gesamtbetrag DESC;
```

Ergebnis: Mira Hansen (1001, 120,00), Paul Neumann (1005, 120,00), Leon Weber (1003, 80,00). Die Reihenfolge der beiden 120-Euro-Bestellungen ist ohne weiteres Sortierkriterium nicht festgelegt.

## A2. 
Ermitteln Sie die Namen der Kunden, die keine Bestellung haben. Verwenden Sie entweder LEFT JOIN oder NOT EXISTS.

**Lösung**  
Mögliche Lösung mit LEFT JOIN:

```sql
SELECT k.Name
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE b.BestellID IS NULL;
```

Alternativ ist die NOT-EXISTS-Lösung aus Aufgabe 2 korrekt. Ergebnis: Tarek Özdemir.


## A3. 
Formulieren Sie eine UPDATE-Anweisung, die alle offenen Bestellungen mit einem Gesamtbetrag von 0,00 auf den Status "PRÜFEN" setzt. Schreiben Sie zusätzlich eine SELECT-Abfrage, mit der Sie vorab die betroffenen Zeilen kontrollieren.

**Lösung**  

Vorprüfung:

```sql
SELECT BestellID, KundenID, Status, Gesamtbetrag
FROM BESTELLUNG
WHERE Status = 'OFFEN'
  AND Gesamtbetrag = 0.00;
```

Änderung:

```sql
UPDATE BESTELLUNG
SET Status = 'PRÜFEN'
WHERE Status = 'OFFEN'
  AND Gesamtbetrag = 0.00;
```

Mit den angegebenen Beispieldaten ist Bestellung 1006 zwar 0,00, ihr Status lautet aber VERSENDET. Daher wird in diesem konkreten Datensatz keine Zeile geändert. Das ist beabsichtigt: Die Lernenden sollen die fachliche Bedingung präzise lesen und nicht automatisch davon ausgehen, dass eine Zeile passt.

## A4. 
Nennen Sie zwei Schutzmaßnahmen, die vor einer Datenänderung sinnvoll sind.

**Lösung**  
Mögliche Antworten:
- Zielmenge vorab mit SELECT und derselben WHERE-Bedingung prüfen;
- Schlüssel bzw. möglichst präzise Filterbedingungen verwenden;
- Änderungen in einer Übungsdatenbank testen;
- Transaktion nutzen und vor COMMIT prüfen, sofern DBMS und Umgebung dies unterstützen;
- Backup/Wiederherstellbarkeit und Berechtigungen berücksichtigen;
- betroffene Zeilenanzahl kontrollieren.

### Manuelle Transaktionskontrolle (schematisch):

```sql
BEGIN;

-- Daten ändern (INSERT, UPDATE oder DELETE)

-- Ergebnis mit SELECT kontrollieren

-- Bei korrektem Ergebnis:
COMMIT;

-- Andernfalls statt COMMIT:
-- ROLLBACK;
```

