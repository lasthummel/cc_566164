# Aufgabe - SQL-JOINs, Unterabfragen und Datenmanipulation

## Datenmodell

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

## A1 – INNER JOIN

1. Geben Sie BestellID, Name des Kunden und Gesamtbetrag jeder Bestellung aus.

**Lösung**  
```sql
SELECT b.BestellID, k.Name, b.Gesamtbetrag
FROM KUNDE AS k
INNER JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID;
```

Ergebnis: sechs Zeilen, da alle sechs Bestellungen einen gültigen Kunden besitzen.

Ein INNER JOIN gibt nur die Zeilen zurück, bei denen es in beiden Tabellen eine Übereinstimmung gibt.

2. Geben Sie alle Bestellungen von Kunden aus Köln aus. Zeigen Sie Name, BestellID und Status.

**Lösung**  
```sql
SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
INNER JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE k.Ort = 'Köln';
```

Ergebnis: Mira Hansen mit BestellID 1001 und 1002 sowie Samira Yilmaz mit BestellID 1004.


3. Erklären Sie die Bedeutung der Bedingung `ON b.KundenID = k.KundenID`.

**Lösung**  
 
Die ON-Bedingung legt fest, welche Zeilen der Tabellen miteinander verknüpft werden. Hier werden Bestellungen mit den Kunden verbunden, deren KundenID übereinstimmt.

Bedeutung von ON b.KundenID = k.KundenID:Das ist die Verknüpfungsregel (Join-Bedingung). Sie sagt der Datenbank: „Klebe eine Bestellung immer nur genau an den Kunden, dessen KundenID (Primärschlüssel) mit der KundenID in der Bestellung (Fremdschlüssel) übereinstimmt.“ Ohne diese Regel würde SQL blind jede Bestellung mit jedem Kunden kombinieren (ein riesiges Datenchaos, das man Kreuzprodukt nennt).

## A2 – INNER JOIN oder LEFT JOIN?

1. Formulieren Sie eine Abfrage, die alle Kunden mit ihren BestellIDs ausgibt. Kunden ohne Bestellung müssen ebenfalls erscheinen.

**Lösung**  

```sql
SELECT k.KundenID, k.Name, b.BestellID
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID;
```

Kunde 5 erscheint mit NULL bei BestellID. Kunden mit mehreren Bestellungen erscheinen mehrfach – einmal je passender Bestellung.

Ein LEFT JOIN gibt alle Zeilen aus der linken Tabelle (KUNDE) zurück, plus die passenden Zeilen aus der rechten Tabelle (BESTELLUNG). Gibt es rechts keine Übereinstimmung, wird NULL angezeigt.

2. Formulieren Sie eine Abfrage, die ausschließlich Kunden ohne Bestellung ausgibt.

**Lösung**  

```sql
SELECT k.KundenID, k.Name
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE b.BestellID IS NULL;
```

Alternativ:

```sql
SELECT k.KundenID, k.Name
FROM KUNDE AS k
WHERE NOT EXISTS (
    SELECT 1
    FROM BESTELLUNG AS b
    WHERE b.KundenID = k.KundenID
);
```

Ergebnis: Tarek Özdemir (KundenID 5).


3. Erklären Sie den Unterschied zwischen INNER JOIN und LEFT JOIN anhand von Kunde 5.

**Lösung**  
INNER JOIN gibt nur Kunden mit mindestens einer passenden Bestellung aus. LEFT JOIN erhält alle Kunden der linken Tabelle. Für Kunde 5 existiert keine passende Bestellung; daher ist er beim INNER JOIN nicht vorhanden, beim LEFT JOIN wird er mit NULL in den Bestellspalten ausgegeben.

Oder:

INNER JOIN: Sucht zu jedem Kunden eine passende Bestellung. Da Tarek keine einzige Bestellung hat, findet er keinen Partner – er fliegt komplett aus dem Ergebnis raus.
LEFT JOIN: Die linke Tabelle (KUNDE) ist der Chef – jeder Kunde muss im Ergebnis auftauchen. Da Tarek rechts keine Bestellung hat, bleibt seine Zeile trotzdem drin, und in den Spalten von BESTELLUNG (wie BestellID) trägt die Datenbank einfach NULL ein.


## A3 – ON und WHERE bei LEFT JOIN

Betrachten Sie die folgenden Abfragen:

**Abfrage A**
```sql
SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN';
```

**Abfrage B**
```sql
SELECT k.Name, b.BestellID, b.Status
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
WHERE b.Status = 'OFFEN';
```

1. Beschreiben Sie den Unterschied der Ergebnismengen.

**Lösung**  
Abfrage A setzt den Statusfilter in die Join-Bedingung. Alle Kunden bleiben erhalten; nur offene Bestellungen gelten als Treffer. Kunden ohne offene Bestellung haben NULL in den Bestellspalten.

Abfrage A – Bedingung in ON:

  | Name | BestellID | Status |
  |---|---|---|
  | Mira Hansen | 1001 | OFFEN |
  | Leon Weber | 1003 | OFFEN |
  | Samira Yilmaz | NULL | NULL |
  | Paul Neumann | 1005 | OFFEN |
  | Tarek Özdemir | NULL | NULL |

Abfrage B führt den LEFT JOIN zunächst anhand der KundenID aus und filtert anschließend mit WHERE auf `b.Status = 'OFFEN'`. Zeilen ohne passende Bestellung haben `b.Status = NULL`; die Bedingung ist nicht TRUE und diese Zeilen werden entfernt. Auch Kunden, die nur versendete oder stornierte Bestellungen haben, erscheinen nicht.

Abfrage B – Bedingung in WHERE:

  | Name | BestellID | Status |
  |---|---|---|
  | Mira Hansen | 1001 | OFFEN |
  | Leon Weber | 1003 | OFFEN |
  | Paul Neumann | 1005 | OFFEN |

2. Welche Abfrage zeigt auch Kunden ohne offene Bestellung? Begründen Sie die Antwort.

**Lösung**  
Abfrage A, weil die Statusbedingung Teil von ON ist und die linke Zeile bei fehlendem Treffer erhalten bleibt.


3. Formulieren Sie eine SQL-Abfrage, die alle Kunden mit ihrer KundenID und ihrem Namen ausgibt, die keine offene Bestellung besitzen. Kunden mit ausschließlich versendeten oder stornierten Bestellungen sollen ebenfalls berücksichtigt werden.

**Lösung**  

```sql
SELECT k.KundenID, k.Name
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN'
WHERE b.BestellID IS NULL;
```

Ergebnis:

3	Samira Yilmaz
5	Tarek Özdemir

- Die ON-Bedingung ordnet ausschließlich offene Bestellungen zu.
- Durch den LEFT JOIN bleiben auch Kunden ohne offene Bestellung erhalten.
- Mit `WHERE b.BestellID IS NULL` werden genau diese Kunden ausgewählt.

Besonders interessant ist dabei Samira Yilmaz: Sie besitzt zwar eine Bestellung, aber keine offene. Left Join behält ihre Bestellung, aber setzt Status auf NULL. Deshalb muss sie im Ergebnis erscheinen.

## A4 – Unterabfragen

1. Geben Sie Bestellungen aus, deren Gesamtbetrag über dem durchschnittlichen Gesamtbetrag aller Bestellungen liegt.

**Lösung**  

```sql
SELECT BestellID, Gesamtbetrag
FROM BESTELLUNG
WHERE Gesamtbetrag > (
    SELECT AVG(Gesamtbetrag)
    FROM BESTELLUNG
);
```

Mit den Beispieldaten beträgt der Durchschnitt 65,00. Ausgegeben werden die Bestellungen 1001 (120,00), 1003 (80,00) und 1005 (120,00).


So rechnet SQL: Die innere Abfrage berechnet den Durchschnitt aller 6 Bestellungen: (120+45+80+25+120+0) / 6 = 65. Danach prüft die äußere Abfrage WHERE Gesamtbetrag > 65.00 und liefert die Bestellungen 1001, 1003 und 1005.

2. Geben Sie die Namen der Kunden aus, die mindestens eine offene Bestellung haben. Verwenden Sie `IN` mit einer Unterabfrage.

**Lösung**  

```sql
SELECT k.Name
FROM KUNDE AS k
WHERE k.KundenID IN (
    SELECT b.KundenID
    FROM BESTELLUNG AS b
    WHERE b.Status = 'OFFEN'
);
```

Ergebnis: Mira Hansen, Leon Weber und Paul Neumann. Jeder Kunde wird hier nur einmal ausgegeben, weil die äußere Abfrage Kunden und nicht Bestellungen auswählt.

So rechnet SQL: Die innere Abfrage liefert die Liste (1, 2, 4). Die äußere Abfrage schaut für jeden Kunden nach: „Steht deine KundenID in dieser Liste?“ -> Mira Hansen, Leon Weber, Paul Neumann.


3. Erklären Sie, warum der Operator `IN` in Teilaufgabe 2 geeignet ist.

**Lösung**  

Die Unterabfrage liefert eine Menge von KundenIDs. `IN` prüft, ob die KundenID des äußeren Datensatzes in dieser Ergebnismenge enthalten ist. Ein einfacher Gleichheitsvergleich mit einer Unterabfrage, die mehrere Werte liefern kann, wäre ungeeignet.


4. Zusatz: Formulieren Sie Teilaufgabe 2 alternativ mit `EXISTS`.

**Lösung**  
```sql
SELECT k.Name
FROM KUNDE AS k
WHERE EXISTS (
    SELECT 1
    FROM BESTELLUNG AS b
    WHERE b.KundenID = k.KundenID
      AND b.Status = 'OFFEN'
);
```

Die Unterabfrage ist korreliert, da sie auf `k.KundenID` Bezug nimmt.

Erklärung: EXISTS stellt für jeden einzelnen Kunden die Ja/Nein-Frage: „Gibt es in BESTELLUNG irgendeine Zeile, die zu diesem Kunden gehört und OFFEN ist?“ Sobald SQL eine findet, gibt EXISTS „Wahr“ zurück. (Warum SELECT 1? Weil es EXISTS egal ist, welche Spalte drinsteht – es zählt nur, ob überhaupt eine Zeile gefunden wird.)

## A5 – INSERT, UPDATE und DELETE

Formulieren Sie jeweils eine SQL-Anweisung.

1. Fügen Sie einen Kunden mit KundenID 6, Name "Nora Fischer", E-Mail `nora@example.de` und Ort "Dortmund" ein.

**Lösung**  

```sql
INSERT INTO KUNDE (KundenID, Name, EMail, Ort)
VALUES (6, 'Nora Fischer', 'nora@example.de', 'Dortmund');
```


2. Änderen Sie den Status der Bestellung 1001 in "VERSENDET".

**Lösung**  

```sql
UPDATE BESTELLUNG
SET Status = 'VERSENDET'
WHERE BestellID = 1001;
```


3. Erhöhen Sie den Gesamtbetrag der Bestellung 1003 um 10,00.

**Lösung**  

```sql
UPDATE BESTELLUNG
SET Gesamtbetrag = Gesamtbetrag + 10.00
WHERE BestellID = 1003;
```

Die Berechnung in SET verwendet den bisherigen Wert der jeweiligen Zeile.


4. Löschen Sie die Bestellung 1006.

**Lösung**  

```sql
DELETE FROM BESTELLUNG
WHERE BestellID = 1006;
```


5. Erklären Sie die mögliche Folge, wenn bei UPDATE oder DELETE die WHERE-Klausel fehlt.

**Lösung**  
Ohne WHERE werden grundsätzlich alle Zeilen der Tabelle geändert bzw. gelöscht. Bei UPDATE können z. B. alle Bestellungen denselben Status erhalten; bei DELETE können sämtliche Bestellzeilen entfernt werden. Die Tabelle selbst bleibt bestehen. Berechtigungen, Constraints oder Trigger können zusätzliche Auswirkungen haben, ändern aber nicht die grundlegende Gefahr.


## A6 – Fehleranalyse

Eine Auszubildende schreibt:

```sql
SELECT k.Name, b.BestellID
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
WHERE b.KundenID = k.KundenID
  AND b.Status = 'OFFEN';
```

1. Benennen Sie den Syntaxfehler.

**Lösung**  

Nach `LEFT JOIN BESTELLUNG AS b` fehlt die erforderliche ON-Klausel mit der Verknüpfungsbedingung. Die Bedingung `b.KundenID = k.KundenID` wurde stattdessen in die WHERE-Klausel geschrieben.


2. Erklären Sie, warum die Platzierung der Bedingungen die Absicht "alle Kunden, auch ohne offene Bestellung" nicht erfüllen würde.

**Lösung**  
Selbst wenn die Join-Bedingung korrekt in ON ergänzt würde, würde `WHERE b.Status = 'OFFEN'` Kunden ohne offene Bestellung aus dem Ergebnis entfernen. NULL-Werte erfüllen die Bedingung nicht als TRUE. Die Anforderung "alle Kunden" wäre damit nicht erfüllt.


3. Korrigieren Sie die Abfrage so, dass alle Kunden erscheinen und nur offene Bestellungen zugeordnet werden.

**Lösung**  

```sql
SELECT k.Name, b.BestellID
FROM KUNDE AS k
LEFT JOIN BESTELLUNG AS b
    ON b.KundenID = k.KundenID
   AND b.Status = 'OFFEN';
```


