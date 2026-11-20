# Aufgabe - SQL-Datenabfragen

## Datenmodell

Für die Aufgaben stehen folgende Tabellen zur Verfügung:

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

Alle Tabellenwerte sind Beispieldaten. 
- `NULL` bei EMail bedeutet, dass keine E-Mail-Adresse hinterlegt ist. 
- Abfragen werden in der Aufgabe zunächst je Tabelle formuliert
- JOINs zunächst bitte nicht verwenden.

## A1 – Einfache SELECT-Abfragen

1. Geben Sie alle Spalten und alle Datensätze aus KUNDE aus.

**Lösung**  

```sql
SELECT *
FROM KUNDE;
```

2. Geben Sie nur Name und Ort aller Kunden aus.

**Lösung**  

```sql
SELECT Name, Ort
FROM KUNDE;
```

3. Geben Sie BestellID, Status und Gesamtbetrag aller Bestellungen aus.

**Lösung**  

```sql
SELECT BestellID, Status, Gesamtbetrag
FROM BESTELLUNG;
```

4. Erklären Sie den Unterschied zwischen `SELECT *` und einer expliziten Spaltenliste.

**Lösung**  

`SELECT * ` gibt alle Spalten aus. Eine explizite Spaltenliste beschränkt die Ausgabe auf benötigte Attribute, macht die Abfrage besser lesbar und reduziert unnötige Daten in der Ergebnismenge.


## A2 – WHERE und Vergleichsoperatoren

Formulieren Sie je eine Abfrage:

1. Alle Bestellungen mit Status OFFEN.

**Lösung**  

```sql
SELECT *
FROM BESTELLUNG
WHERE Status = 'OFFEN';
```

2. Alle Bestellungen mit Gesamtbetrag größer oder gleich 80.00.

**Lösung**  

```sql
SELECT *
FROM BESTELLUNG
WHERE Gesamtbetrag >= 80.00;
```

3. Alle Bestellungen mit Status OFFEN und Gesamtbetrag mindestens 100.00.

**Lösung**  

```sql
SELECT *
FROM BESTELLUNG
WHERE Status = 'OFFEN'
   AND Gesamtbetrag >= 100.00;
```

4. Alle Bestellungen, deren Status weder OFFEN noch VERSENDET ist. 

**Lösung**  

```sql
SELECT *
FROM BESTELLUNG
WHERE Status <> 'OFFEN'
   AND Status <> 'VERSENDET';
```

Alternativ ist `WHERE NOT (Status = 'OFFEN' OR Status = 'VERSENDET')` korrekt. Mit den vorgegebenen Daten wird BestellID 1004 ausgegeben. Bei möglichen NULL-Werten wäre zu beachten, dass unbekannte Statuswerte nicht automatisch als "weder noch" gelten.


## A3 – AND, OR, LIKE und NULL

1. Geben Sie Kunden aus Köln oder Bonn aus.

**Lösung**  

```sql
SELECT *
FROM KUNDE
WHERE Ort = 'Köln' OR Ort = 'Bonn';
```

Alternativ: `WHERE Ort IN ('Köln', 'Bonn')`.

2. Geben Sie Kunden aus Köln aus, deren Name mit "M" beginnt.

**Lösung**  

```sql
SELECT *
FROM KUNDE
WHERE Ort = 'Köln'
   AND Name LIKE 'M%';
```

Mit den Beispieldaten wird Mira Hansen ausgegeben.

Erklärung: LIKE nutzt man für Textmuster. Das Prozentzeichen % ist ein Joker für „beliebig viele Zeichen dahinter“ (auch gar keine). 'M%' findet also „Mira Hansen“, „Max“ oder einfach nur „M“

3. Geben Sie Kunden aus, deren E-Mail-Adresse fehlt.

**Lösung**  

```sql
SELECT *
FROM KUNDE
WHERE EMail IS NULL;
```

Mit den Beispieldaten wird Samira Yilmaz ausgegeben.

4. Geben Sie Kunden aus, deren E-Mail-Adresse vorhanden ist.

**Lösung**  

```sql
SELECT *
FROM KUNDE
WHERE EMail IS NOT NULL;
```

5. Erläutern Sie, warum `WHERE EMail = NULL` nicht die korrekte Lösung für Teilaufgabe 3 ist.

**Lösung**  

`NULL` steht für einen fehlenden bzw. unbekannten Wert. Ein Vergleich wie `EMail = NULL` ergibt im SQL-Logikmodell UNKNOWN und nicht TRUE. Eine WHERE-Klausel liefert nur Zeilen, deren Bedingung TRUE ist. Daher müssen fehlende Werte mit `IS NULL` geprüft werden.


## A4 – Sortierung und Aggregatfunktionen

1. Geben Sie alle Bestellungen nach Gesamtbetrag absteigend aus. 

**Lösung**  

```sql
SELECT *
FROM BESTELLUNG
ORDER BY Gesamtbetrag DESC;
```

Wenn bei gleichem Gesamtbetrag-Wert zusätzlich nach BestellID aufsteigende Sortierunf gewünscht ist:

Erklärung: DESC steht für descending (absteigend, also vom größten zum kleinsten Wert). Aufsteigend wäre ASC (ascending).

```sql
SELECT *
FROM BESTELLUNG
ORDER BY Gesamtbetrag DESC, BestellID ASC;
``` 

2. Ermitteln Sie die Anzahl aller Bestellungen.

**Lösung**  

```sql
SELECT COUNT(*) AS AnzahlBestellungen
FROM BESTELLUNG;
```

Ergebnis: 6.


3. Ermitteln Sie die Summe aller Bestellbeträge.

**Lösung**  

```sql
SELECT SUM(Gesamtbetrag) AS Summe
FROM BESTELLUNG;
```

Ergebnis: 390.00.


4. Ermitteln Sie den durchschnittlichen Bestellbetrag.

**Lösung**  

```sql
SELECT AVG(Gesamtbetrag) AS Durchschnitt
FROM BESTELLUNG;
```

Ergebnis: 65.00.

5. Ermitteln Sie den kleinsten und den größten Bestellbetrag.

**Lösung**  

```sql
SELECT MIN(Gesamtbetrag) AS Minimum,
         MAX(Gesamtbetrag) AS Maximum
FROM BESTELLUNG;
```

Ergebnis: Minimum 0.00, Maximum 120.00.

6. Erklären Sie den Unterschied zwischen `COUNT(*)` und `COUNT(EMail)`, wenn EMail NULL-Werte enthalten kann.

**Lösung**  

`COUNT(*)` zählt alle Zeilen, unabhängig davon, ob EMail NULL ist. `COUNT(EMail)` zählt nur Zeilen, in denen EMail nicht NULL ist. In der KUNDE-Beispieltabelle ist `COUNT(*) = 4` und `COUNT(EMail) = 3`.

## A5 – GROUP BY

1. Ermitteln Sie für jeden Status die Anzahl der Bestellungen. Geben Sie Status und Anzahl aus.

**Lösung**  

```sql
SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status;
```

Erwartete Gruppen:

| Status | Anzahl |
|---|---:|
| OFFEN | 3 |
| STORNIERT | 1 |
| VERSENDET | 2 |


2. Ermitteln Sie für jeden Status die Summe der Gesamtbeträge.

**Lösung**  

```sql
SELECT Status, SUM(Gesamtbetrag) AS Summe
FROM BESTELLUNG
GROUP BY Status;
```

Erwartete Summen:

| Status | Summe |
|---|---:|
| OFFEN | 320.00 |
| STORNIERT | 25.00 |
| VERSENDET | 45.00 |


3. Sortieren Sie die Ergebnisse aus Teilaufgabe 1 nach der Anzahl absteigend.

**Lösung**  

```sql
SELECT Status, COUNT(*) AS Anzahl
FROM BESTELLUNG
GROUP BY Status
ORDER BY Anzahl DESC;
```

Erwartete Reihenfolge: OFFEN (3), VERSENDET (2), STORNIERT (1).


## A6 – WHERE oder HAVING?

Gesucht sind Statusgruppen, bei denen die Summe der Bestellbeträge mindestens 100.00 beträgt. Berücksichtigen Sie dabei nur Bestellungen mit einem Gesamtbetrag von mindestens 20.00.

1. Formulieren Sie die SQL-Abfrage.

**Lösung**  

```sql
SELECT Status, SUM(Gesamtbetrag) AS Summe
FROM BESTELLUNG
WHERE Gesamtbetrag >= 20.00
GROUP BY Status
HAVING SUM(Gesamtbetrag) >= 100.00;
```

Mit den Beispieldaten:
- OFFEN: 120 + 80 + 120 = 320.00
- VERSENDET: 45.00
- STORNIERT: 25.00

Ausgegeben wird nur OFFEN mit Summe 320.00.

2. Erklären Sie die Auswirkung von WHERE, HAVING, GROUP BY und SUM, wenn Sie sie in der Abfrage verwenden.

**Lösung**  

- `WHERE Gesamtbetrag >= 20.00` entfernt einzelne Bestellzeilen, bevor gruppiert wird.
- `GROUP BY Status` fasst die verbleibenden Zeilen je Status zusammen.
- `HAVING SUM(Gesamtbetrag) >= 100.00` behält nur Gruppen, deren Summe mindestens 100 beträgt.
- `SUM(Gesamtbetrag)` ist ein Aggregat über eine Gruppe. Die Gruppen existieren erst nach der Gruppierung; deshalb gehört diese Bedingung in HAVING, nicht als gewöhnlicher Zeilenfilter in WHERE.

ODER:

WHERE (1. Türsteher – filtert einzelne Zeilen vor dem Gruppieren):Zuerst wirft WHERE Gesamtbetrag >= 20.00 alle einzelnen Bestellungen raus, die zu klein sind (in unseren Beispieldaten fliegt Bestellung 1006 mit 0,00 € sofort raus).
GROUP BY (Stapel bilden):Die übrigen 5 Bestellungen werden nach Status in drei Stapel sortiert (OFFEN, VERSENDET, STORNIERT).
SUM (Rechnen pro Stapel):Für jeden Stapel wird der Gesamtbetrag addiert:
OFFEN: 120 + 80 + 120 = 320,00
VERSENDET: 45,00
STORNIERT: 25,00
HAVING (2. Türsteher – filtert fertige Gruppen nach dem Rechnen):Erst jetzt prüft HAVING SUM(Gesamtbetrag) >= 100.00, welche Stapel das Ziel erreichen. VERSENDET (45) und STORNIERT (25) fliegen raus – übrig bleibt im Endergebnis nur die Gruppe OFFEN mit 320,00.
