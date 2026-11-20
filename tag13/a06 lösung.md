# Integrierte Aufgabe

Ein Shop möchte eine kurze Auswertung der Bestellungen erstellen.
Folgende Tabellen stehen zur Verfügung:

**KUNDE**

| KundenID | Name | EMail | Ort |
|---:|---|---|---|
| 1 | Mira Hansen | mira@example.de | Köln |
| 2 | Leon Weber | leon@example.de | Bonn |
| 3 | Samira Yilmaz | NULL | Köln |
| 4 | Paul Neumann | paul@beispiel.de | Essen |

**BESTELLUNG**

| BestellID | KundenID | Status | Gesamtbetrag |
|---:|---:|---|---:|
| 1001 | 1 | OFFEN | 120.00 |
| 1002 | 1 | VERSENDET | 45.00 |
| 1003 | 2 | OFFEN | 80.00 |
| 1004 | 3 | STORNIERT | 25.00 |
| 1005 | 4 | OFFEN | 120.00 |
| 1006 | 2 | VERSENDET | 0.00 |


## A1. 
Ermitteln Sie die Anzahl der Bestellungen mit Status OFFEN. 

**Lösung**  

```sql
SELECT COUNT(*) AS AnzahlOffenerBestellungen
FROM BESTELLUNG
WHERE Status = 'OFFEN';
```

Ergebnis: 3.

## A2. 
Ermitteln Sie den durchschnittlichen Betrag aller Bestellungen mit Status VERSENDET.

**Lösung**  

```sql
SELECT AVG(Gesamtbetrag) AS Durchschnitt
FROM BESTELLUNG
WHERE Status = 'VERSENDET';
```

Ergebnis: 22.50.

## A3. 
Geben Sie die Statusgruppen aus, deren Bestellanzahl größer als eins ist. Sortieren Sie nach Status alphabetisch.

**Lösung**  

```sql
SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status
HAVING COUNT(*) > 1
ORDER BY Status ASC;
```

Ergebnis:

| Status | Anzahl |
|---|---:|
| OFFEN | 3 |
| VERSENDET | 2 |


## A4. 
Ein Kollege schreibt folgende Abfrage:

```sql
SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
WHERE COUNT(*) > 1
GROUP BY Status;
```

Erklären Sie den Fehler und korrigiere die Abfrage.

**Lösung**  
Fehler: `WHERE` filtert einzelne Zeilen vor der Gruppierung. `COUNT(*)` wird aber je Gruppe berechnet; diese Bedingung gehört daher in `HAVING`.

Korrektur:

```sql
SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status
HAVING COUNT(*) > 1;
```
