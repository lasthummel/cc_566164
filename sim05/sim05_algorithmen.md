# Prüfungssimulation sim05 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten
**Erreichbare Punkte:** 100
**Aufgaben:** 4 Hauptaufgaben.

**Technische Voraussetzungen:** Java 21; PostgreSQL 16.

## Ausgangssituation

Die fiktive **KommunaSoft GmbH** betreibt das Portal GrünAntrag zur Bearbeitung kommunaler Förderanträge. Zusätzlich entwickelt ein internes Ausbildungsteam ein kleines TicTacToe-Spiel. **Aufgabe 1** behandelt die Auswertung dieses Spiels; **Aufgaben 2 bis 4** behandeln voneinander unabhängige Funktionen des Förderportals. Bei Java-Aufgaben sind nur die ausdrücklich angegebenen Datentypen und Methoden vorausgesetzt.

## Aufgabe 1: Spielzustand bei TicTacToe (30 Punkte)

Für ein einfaches TicTacToe-Spiel soll der Zustand des Spielfelds ausgewertet werden. Das Spielfeld besteht aus **3 Zeilen und 3 Spalten** und wird in Java als `char[][]` übergeben. Die Indizes für Zeilen und Spalten reichen jeweils von `0` bis `2`.

Jede Zelle enthält genau eines der folgenden Zeichen:

- `'X'`: Zeichen von Spieler X.
- `'O'`: Zeichen von Spieler O.
- `' '` (Leerzeichen): freie Zelle.

Spieler X beginnt; anschließend setzen X und O abwechselnd je ein Zeichen auf eine freie Zelle. Ein Spieler gewinnt, wenn drei seiner Zeichen in derselben Zeile, Spalte oder Diagonale stehen. Sind alle Zellen belegt, ohne dass ein Spieler gewonnen hat, endet das Spiel unentschieden. Nach einem Sieg werden keine weiteren Züge ausgeführt.

Beispiel für einen Sieg von X auf der Hauptdiagonale:

```text
|X| | |
|O|X|O|
| | |X|
```

**Voraussetzungen für alle Teilaufgaben:** Übergeben werden ausschließlich regelkonforme, aus einem tatsächlichen Spielverlauf entstandene Spielfelder. Das Array ist nicht `null`, besitzt stets genau die Größe 3 × 3 und enthält keine anderen Zeichen. Die Methoden dürfen das Spielfeld nicht verändern. Schreiben Sie nur die jeweils angeforderten Java-21-Methoden; eine vollständige Anwendung oder Klassendeklaration ist nicht erforderlich.

### 1a – Gewinner ermitteln (12 Punkte)

Implementieren Sie die vollständige Methode:

```java
public static char getWinner(char[][] board)
```

Die Methode soll alle **drei Zeilen, drei Spalten und zwei Diagonalen** auf eine Gewinnlinie prüfen. Verwenden Sie zur Prüfung der Zeilen und Spalten eine Schleife. Die Diagonalen dürfen Sie mit einzelnen Bedingungen prüfen.

- Gibt es eine Gewinnlinie aus `'X'`, ist `'X'` zurückzugeben.
- Gibt es eine Gewinnlinie aus `'O'`, ist `'O'` zurückzugeben.
- Hat niemand gewonnen, ist `' '` zurückzugeben.

Drei leere Zellen dürfen nicht als Gewinnlinie erkannt werden.

### 1b – Spielende erkennen (10 Punkte)

Implementieren Sie die vollständige Methode:

```java
public static boolean isGameOver(char[][] board)
```

Die Methode gibt `true` zurück, wenn **ein Spieler gewonnen hat oder das Spielfeld vollständig belegt ist** (Unentschieden). Andernfalls gibt sie `false` zurück.

Sie dürfen `getWinner(board)` aus Teilaufgabe 1a aufrufen und dabei von einer korrekten Implementierung ausgehen, auch wenn Sie 1a nicht vollständig lösen konnten. Prüfen Sie freie Zellen mithilfe von Schleifen.

### 1c – Spielzustände nachvollziehen (5 Punkte)

Bestimmen Sie für **jedes** der fünf Spielfelder die Rückgabewerte von `getWinner(board)` und `isGameOver(board)`. Tragen Sie jeweils das zurückgegebene Zeichen (`'X'`, `'O'` oder `' '`) und den booleschen Wert (`true` oder `false`) ein.

**Spielfeld A**

```text
|X|O| |
| |X| |
|O| | |
```

**Spielfeld B**

```text
|X|X|X|
|O|O| |
| | | |
```

**Spielfeld C**

```text
|O|X|X|
|O|X| |
|O| | |
```

**Spielfeld D**

```text
|X| | |
|O|X|O|
| | |X|
```

**Spielfeld E**

```text
|X|O|X|
|X|O|O|
|O|X|X|
```

| Spielfeld | `getWinner(board)` | `isGameOver(board)` |
|---|---|---|
| A | | |
| B | | |
| C | | |
| D | | |
| E | | |

### 1d – Logischen Fehler erkennen (3 Punkte)

Ein Entwickler prüft mit der folgenden Methode, ob die angegebene Zeile vollständig aus drei gleichen Spielerzeichen besteht. Der Parameter `zeile` hat immer einen gültigen Index von `0` bis `2`.

```java
public static boolean hatGewinnzeile(char[][] board, int zeile) {
    return board[zeile][0] == board[zeile][1]
            && board[zeile][1] == board[zeile][2];
}
```

**I)** Benennen Sie den logischen Fehler.  

**II)** Erläutern Sie anhand des folgenden Spielfelds, bei welchem Zeilenindex die Methode ein falsches Ergebnis liefert.  

```text
|X| | |
| | | |
| | | |
```

**III)** Korrigieren Sie den vollständigen `return`-Ausdruck.  


## Aufgabe 2: Fehleranalyse und Testdaten (20 Punkte)

Eine Prüfziffer soll fehlerhafte Eingaben erkennen. Die **fachlich verbindliche, vereinfachte Prüfregel** lautet: Die Kennung besteht aus **genau vier ASCII-Ziffern** `0` bis `9`. Die ersten drei Ziffern werden mit den Gewichten 3, 1 und 3 multipliziert. Die Summe plus vierte Ziffer muss durch 10 teilbar sein. Für jede ungültige Kennung liefert die Methode `false`.

Folgender Java-Code enthält **mindestens vier unterschiedliche fachliche oder technische Fehlerstellen**:

```java
public static boolean gueltig(String kennung) {
    if (kennung.length() != 4) return false;
    int summe = 0;
    for (int i = 0; i <= 4; i++) {
        int ziffer = kennung.charAt(i) - '0';
        if (ziffer < 0 && ziffer > 9) return false;
        int gewicht = (i % 2 == 0) ? 3 : 1;
        summe += ziffer * gewicht;
    }
    return summe % 9 == 0;
}
```

### 2a – 8 Punkte

Benennen Sie **vier unterschiedliche Fehlerstellen** und erläutern Sie jeweils ihre mögliche Auswirkung. Beachten Sie, dass ein Fehler die Auswirkung eines anderen Fehlers zur Laufzeit verdecken kann.

### 2b – 8 Punkte

Schreiben Sie die **vollständig korrigierte Methode** in Java 21. Null-Eingaben sind ungültig.

### 2c – 4 Punkte

Geben Sie vier Eingaben samt erwartetem `boolean`-Ergebnis an: eine gültige Kennung, dieselbe Kennung mit **ausschließlich geänderter vierter Ziffer**, sodass die Prüfziffer ungültig wird, eine nichtnumerische Eingabe und `null`.

## Aufgabe 3: Datenmodell und Normalisierung (22 Punkte)

In der Verwaltung werden Auszahlungen bisher in einer gemeinsamen Tabelle `Auszahlungsliste` geführt:

| AntragID | AntragstellerName | ProgrammID | ProgrammTitel | ZahlungID | Zahlungsdatum | Betrag |
|---:|---|---:|---|---:|---|---:|
| 51 | Lea Kern | 7 | Gründach | 901 | 2026-10-01 | 900.00 |
| 51 | Lea Kern | 7 | Gründach | 903 | 2026-10-15 | 200.00 |
| 52 | Omar Saleh | 8 | Fassadengrün | 902 | 2026-10-03 | 600.00 |

Es gelten folgende Regeln: `AntragID`, `ProgrammID` und `ZahlungID` identifizieren jeweils einen Antrag, ein Förderprogramm bzw. eine Zahlung eindeutig. Jeder Antrag gehört genau einer antragstellenden Person und genau einem Förderprogramm. Eine Person und ein Förderprogramm können jeweils **null bis beliebig viele Anträge** haben und somit auch ohne Antrag gespeichert sein. Ein Antrag kann **null bis beliebig viele Teilzahlungen** erhalten; jede Zahlung gehört genau einem Antrag. `ProgrammTitel` hängt von `ProgrammID` ab, `AntragstellerName` von `AntragstellerID`. Personennamen sind nicht eindeutig. Die Ausgangstabelle enthält keine `AntragstellerID`.

### 3a – 6 Punkte

Erläutern Sie jeweils an einem **konkreten Beispiel** eine Änderungs-, eine Einfüge- und eine Löschanomalie.

### 3b – 12 Punkte

Entwerfen Sie ein relationales Schema mindestens in **dritter Normalform**. Nennen Sie alle Tabellen mit sinnvollen Attributen sowie Primär- und Fremdschlüsseln. **Falls zusätzliche Identifikationsattribute erforderlich sind, dürfen Sie diese einführen.** Kennzeichnen Sie bei den Fremdschlüsseln die Pflichtzuordnungen mit `NOT NULL` und erläutern Sie, welche Beziehungen optional sind und wie die angegebenen Kardinalitäten abgebildet werden.

### 3c – 4 Punkte

Erläutern Sie zwei fachliche oder technische Vorteile des normalisierten Modells gegenüber der Ausgangstabelle.

## Aufgabe 4: SQL – Auszahlungen und fehlende Vorgänge (28 Punkte)

Verwenden Sie ausschließlich folgendes **vollständiges PostgreSQL-16-Schema**. Die Daten sind verbindlich; `betrag` ist stets positiv.

```sql
CREATE TABLE programm (
  programm_id INTEGER PRIMARY KEY,
  titel VARCHAR(80) NOT NULL
);
CREATE TABLE antragsteller (
  person_id INTEGER PRIMARY KEY,
  name VARCHAR(80) NOT NULL
);
CREATE TABLE antrag (
  antrag_id INTEGER PRIMARY KEY,
  person_id INTEGER NOT NULL REFERENCES antragsteller(person_id),
  programm_id INTEGER NOT NULL REFERENCES programm(programm_id),
  status VARCHAR(20) NOT NULL
);
CREATE TABLE zahlung (
  zahlung_id INTEGER PRIMARY KEY,
  antrag_id INTEGER NOT NULL REFERENCES antrag(antrag_id),
  betrag NUMERIC(10,2) NOT NULL CHECK (betrag > 0),
  bezahlt_am DATE NOT NULL
);
```

| programm | | antragsteller | |
|---|---|---|---|
| programm_id | titel | person_id | name |
| 7 | Gründach | 1 | Lea Kern |
| 8 | Fassadengrün | 2 | Omar Saleh |
| 9 | Regenbeet | 3 | Mila Roth |

| antrag_id | person_id | programm_id | status |
|---:|---:|---:|---|
| 51 | 1 | 7 | BEWILLIGT |
| 52 | 2 | 8 | BEWILLIGT |
| 53 | 1 | 7 | IN_PRUEFUNG |
| 54 | 3 | 9 | BEWILLIGT |

| zahlung_id | antrag_id | betrag | bezahlt_am |
|---:|---:|---:|---|
| 901 | 51 | 900.00 | 2026-10-01 |
| 902 | 52 | 600.00 | 2026-10-03 |
| 903 | 51 | 200.00 | 2026-10-15 |

### 4a – 8 Punkte

Schreiben Sie eine SQL-Abfrage, die für **jeden Antrag mit mindestens einer erfassten Zahlung** die Antragkennung und die **Summe aller zugehörigen Zahlungen** ausgibt. Anträge ohne Zahlung sollen nicht erscheinen. Sortieren Sie aufsteigend nach Antragkennung. Geben Sie zusätzlich die konkrete Ergebnistabelle für die vorliegenden Daten an.

### 4b – 6 Punkte

Ermitteln Sie mit einer SQL-Abfrage alle **bewilligten Anträge ohne irgendeine Zahlung**. Geben Sie Antragkennung und Name der antragstellenden Person aus, sortiert nach Antragkennung. Nennen Sie die Ergebniszeile(n).

### 4c – 6 Punkte

Ermitteln Sie mit SQL die **Programme mit mindestens zwei Anträgen** unabhängig vom Status. Geben Sie Programmkennung, Titel und Antragsanzahl aus, sortiert nach Programmkennung. Nennen Sie die Ergebnismenge.

### 4d – 4 Punkte

Schreiben Sie die SQL-Anweisung, die den Status von Antrag **53** auf `ABGELEHNT` setzt, aber **nur, wenn** sein bisheriger Status `IN_PRUEFUNG` ist.

### 4e – 4 Punkte

Betrachten Sie die folgende SQL-Abfrage:

```sql
SELECT a.antrag_id,
       COUNT(*) AS anzahl_zeilen,
       COUNT(z.zahlung_id) AS anzahl_zahlungen
FROM antrag a
LEFT JOIN zahlung z ON z.antrag_id = a.antrag_id
GROUP BY a.antrag_id
ORDER BY a.antrag_id;
```

Erläutern Sie anhand der gegebenen Daten, warum `COUNT(*)` und `COUNT(z.zahlung_id)` bei Anträgen ohne Zahlung unterschiedliche Werte liefern. Begründen Sie, welcher Ausdruck zum Zählen tatsächlich erfasster Zahlungen geeignet ist.

**Ende des Prüfungsbereichs – 100 Punkte.**
