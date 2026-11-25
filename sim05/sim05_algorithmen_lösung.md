# Prüfungssimulation sim05 – Entwicklung und Umsetzung von Algorithmen (Musterlösungen)

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

**Lösung**

Beispielimplementierung in Java 21:

```java
public static char getWinner(char[][] board) {
    for (int i = 0; i < 3; i++) {
        // Zeile i
        if (board[i][0] != ' '
                && board[i][0] == board[i][1]
                && board[i][1] == board[i][2]) {
            return board[i][0];
        }

        // Spalte i
        if (board[0][i] != ' '
                && board[0][i] == board[1][i]
                && board[1][i] == board[2][i]) {
            return board[0][i];
        }
    }

    // Hauptdiagonale
    if (board[0][0] != ' '
            && board[0][0] == board[1][1]
            && board[1][1] == board[2][2]) {
        return board[0][0];
    }

    // Nebendiagonale
    if (board[0][2] != ' '
            && board[0][2] == board[1][1]
            && board[1][1] == board[2][0]) {
        return board[0][2];
    }

    return ' ';
}
```

**Bewertung (12 Punkte)**

- Alle drei Zeilen mithilfe einer Schleife korrekt prüfen: **3 P.**
- Alle drei Spalten mithilfe einer Schleife korrekt prüfen: **3 P.**
- Beide Diagonalen korrekt prüfen: **2 P.** (je 1 P.)
- Leere Gewinnlinien in allen Prüfungen ausschließen: **2 P.**
- Für einen Gewinn das richtige Zeichen und sonst `' '` zurückgeben: **2 P.**

Fachlich gleichwertige Implementierungen sind anzuerkennen. Da nur gültige Spielstellungen übergeben werden, muss die Methode keine widersprüchlichen Sieger behandeln.

### 1b – Spielende erkennen (10 Punkte)

Implementieren Sie die vollständige Methode:

```java
public static boolean isGameOver(char[][] board)
```

Die Methode gibt `true` zurück, wenn **ein Spieler gewonnen hat oder das Spielfeld vollständig belegt ist** (Unentschieden). Andernfalls gibt sie `false` zurück.

Sie dürfen `getWinner(board)` aus Teilaufgabe 1a aufrufen und dabei von einer korrekten Implementierung ausgehen, auch wenn Sie 1a nicht vollständig lösen konnten. Prüfen Sie freie Zellen mithilfe von Schleifen.

**Lösung**

```java
public static boolean isGameOver(char[][] board) {
    if (getWinner(board) != ' ') {
        return true;
    }

    for (int zeile = 0; zeile < 3; zeile++) {
        for (int spalte = 0; spalte < 3; spalte++) {
            if (board[zeile][spalte] == ' ') {
                return false;
            }
        }
    }

    return true;
}
```

**Begründung:** Ein vorhandener Sieger beendet das Spiel sofort, auch wenn noch freie Zellen existieren. Ohne Sieger bedeutet mindestens eine freie Zelle, dass das Spiel noch läuft. Sind alle Zellen belegt, liegt unter den vorgegebenen gültigen Spielstellungen ein Unentschieden vor.

**Bewertung (10 Punkte)**

- Gewinn über `getWinner` erkennen und bei Sieg `true` liefern: **3 P.**
- Alle neun Zellen mit geeigneten Schleifen prüfen: **3 P.**
- Bei mindestens einer freien Zelle korrekt `false` liefern: **2 P.**
- Bei vollem Spielfeld ohne Sieger korrekt `true` liefern: **2 P.**

Für 1b darf kein Punktabzug allein dadurch entstehen, dass die in 1a geschriebene Methode fehlerhaft oder unvollständig ist, sofern 1b sie mit ihrer vorgesehenen Semantik aufruft.

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

**Lösung**

| Spielfeld | `getWinner(board)` | `isGameOver(board)` | Begründung |
|---|---|---|---|
| A | `' '` | `false` | Keine Gewinnlinie; freie Zellen vorhanden |
| B | `'X'` | `true` | X gewinnt in der ersten Zeile |
| C | `'O'` | `true` | O gewinnt in der ersten Spalte |
| D | `'X'` | `true` | X gewinnt auf der Hauptdiagonale |
| E | `' '` | `true` | Keine Gewinnlinie; alle neun Zellen belegt |

**Bewertung (5 Punkte):** Pro Spielfeld **1 P.**, aufgeteilt in **0,5 P.** für jeden korrekten Rückgabewert.

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


**Lösung**

**I)** Die Methode kontrolliert nicht, ob die drei gleichen Zeichen tatsächlich `'X'` oder `'O'` sind. Auch drei Leerzeichen werden fälschlich als Gewinnzeile gewertet.

**II)** Für `zeile = 1` und ebenso für `zeile = 2` liefert die ursprüngliche Methode fälschlich `true`, obwohl diese Zeilen nur Leerzeichen enthalten.

**III)** Korrigierter Ausdruck:

```java
return board[zeile][0] != ' '
        && board[zeile][0] == board[zeile][1]
        && board[zeile][1] == board[zeile][2];
```

**Bewertung (3 Punkte):** Fehler zutreffend benannt **1 P.**; konkreten falschen Zeilenindex und das Ergebnis `true` angegeben **1 P.**; Nicht-Leer-Prüfung im korrigierten Ausdruck ergänzt **1 P.**.

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

**Lösung**

1. `kennung == null` fehlt: Schon der Zugriff auf `length()` löst eine `NullPointerException` aus.
2. `i <= 4` statt `i < 4`: Der fünfte Schleifendurchlauf greift außerhalb des Strings zu und löst bei jeder vierstelligen Kennung eine `StringIndexOutOfBoundsException` aus.
3. `ziffer < 0 && ziffer > 9` ist unerfüllbar: Zeichen außerhalb der Ziffern 0 bis 9 werden nicht zurückgewiesen; erforderlich ist `||`.
4. `summe % 9 == 0` statt `summe % 10 == 0`: Es wird gegen einen falschen Teiler geprüft; zulässige Kennungen werden gegebenenfalls zurückgewiesen und unzulässige akzeptiert.

Je 1 Punkt für die zutreffende Fehlerstelle und 1 Punkt für ihre mögliche Auswirkung. **Hinweis zur Fehlerüberlagerung:** Bei jeder vierstelligen Eingabe führt die falsche Schleifengrenze (`i <= 4`) zu einer `StringIndexOutOfBoundsException`, bevor die abschließende Modulo-Prüfung erreicht wird. Um deren fachliche Auswirkung isoliert zu untersuchen, muss mindestens die Schleifengrenze korrigiert werden; für einen regulären Aufruf der Methode müssen auch die übrigen Fehler behoben sein. Die Gewichtung im Ausgangscode ist hingegen korrekt und darf nicht als Fehler gewertet werden.

**Bewertung**

Vier unterschiedliche Fehlerstellen mit ihren möglichen Auswirkungen, jeweils 2 Punkte. Die Auswirkungen müssen nicht gleichzeitig im unveränderten Programm beobachtbar sein.

### 2b – 8 Punkte

Schreiben Sie die **vollständig korrigierte Methode** in Java 21. Null-Eingaben sind ungültig.

**Lösung**

Vollständiger Korrekturcode:

```java
public static boolean gueltig(String kennung) {
    if (kennung == null || kennung.length() != 4) return false;
    int summe = 0;
    int[] gewichte = {3, 1, 3, 1};
    for (int i = 0; i < 4; i++) {
        char ch = kennung.charAt(i);
        if (ch < '0' || ch > '9') return false;
        summe += (ch - '0') * gewichte[i];
    }
    return summe % 10 == 0;
}
```

2 P. null/Länge, 2 P. ASCII-Zifferncheck, 2 P. Gewichtung einschließlich Prüfziffer, 2 P. Endprüfung/Rückgabe. Gleichwertige Berechnung mit separater Prüfziffer akzeptieren.

### 2c – 4 Punkte

Geben Sie vier Eingaben samt erwartetem `boolean`-Ergebnis an: eine gültige Kennung, dieselbe Kennung mit **ausschließlich geänderter vierter Ziffer**, sodass die Prüfziffer ungültig wird, eine nichtnumerische Eingabe und `null`.

**Lösung**

`"1236"` → true; `"1237"` → false; `"12a6"` → false; `null` → false. Prüfrechnung für `"1236"`: `1 * 3 + 2 * 1 + 3 * 3 + 6 = 20`, also durch 10 teilbar. Bei `"1237"` ist die Summe 21 und somit nicht durch 10 teilbar; nur die vierte Ziffer wurde geändert. Je 1 P. für ein korrektes Eingabe-Ergebnis-Paar; der Test mit ungültiger Prüfziffer muss sich ausschließlich an der vierten Position von der angegebenen gültigen Kennung unterscheiden. Andere korrekt nachgerechnete Werte zulässig.

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

**Lösung**

Änderung: Der Titel "Gründach" ist für jede Zahlung redundant, eine Umbenennung muss mehrfach erfolgen; unterbleibt eine Zeile, sind die Daten inkonsistent (2 P.). Einfügen: Ein neues Förderprogramm ohne Auszahlung ist in der Zahlungsliste nicht darstellbar, ohne einen fiktiven Zahlungssatz einzutragen (2 P.). Löschen: Entfernt man Zahlung 902, verschwinden zugleich alle dort gespeicherten Informationen zu Antrag 52 und Programm 8, falls sie sonst nirgends stehen (2 P.).

### 3b – 12 Punkte

Entwerfen Sie ein relationales Schema mindestens in **dritter Normalform**. Nennen Sie alle Tabellen mit sinnvollen Attributen sowie Primär- und Fremdschlüsseln. **Falls zusätzliche Identifikationsattribute erforderlich sind, dürfen Sie diese einführen.** Kennzeichnen Sie bei den Fremdschlüsseln die Pflichtzuordnungen mit `NOT NULL` und erläutern Sie, welche Beziehungen optional sind und wie die angegebenen Kardinalitäten abgebildet werden.

**Lösung**

Beispiel in 3NF:

```text
ANTRAGSTELLER(person_id PK, name)
PROGRAMM(programm_id PK, titel)
ANTRAG(antrag_id PK,
       person_id FK NOT NULL -> ANTRAGSTELLER.person_id,
       programm_id FK NOT NULL -> PROGRAMM.programm_id)
ZAHLUNG(zahlung_id PK,
        antrag_id FK NOT NULL -> ANTRAG.antrag_id,
        zahlungsdatum, betrag)
```

Die `person_id` wird zusätzlich eingeführt, weil Namen nicht eindeutig sind. Die vorhandenen Kennungen `antrag_id`, `programm_id` und `zahlung_id` sind Primärschlüssel der jeweiligen Entität.

**Kardinalitäten und Integrität:** Jede Zeile von `ANTRAG` verweist mit einem `NOT NULL`-Fremdschlüssel auf genau eine existierende Person und ein existierendes Programm. Jede Zeile von `ZAHLUNG` verweist mit einem `NOT NULL`-Fremdschlüssel auf genau einen existierenden Antrag. Umgekehrt sind die Zuordnungen optional: Eine Person und ein Programm dürfen jeweils 0..n Anträge haben; ein Antrag darf 0..n Zahlungen haben. Dafür müssen keine künstlichen Antrags- oder Zahlungsdatensätze angelegt werden. Die genannten Fremdschlüssel erzwingen die Existenz der referenzierten Datensätze bei den Pflichtzuordnungen, nicht aber eine Mindestzahl an abhängigen Datensätzen.

**Bewertung (12 Punkte):** vier sachgerechte Tabellen 4 P.; vollständige Primärschlüssel einschließlich neu eingeführter `person_id` 2 P.; drei korrekt gerichtete Fremdschlüssel 3 P.; vollständige, nicht redundante Nutzattribute 2 P.; korrekte Pflicht-/optionale Zuordnungen, insbesondere `NOT NULL` an allen drei Fremdschlüsseln 1 P. Fachlich gleichwertige relationale Schemata akzeptieren; eine konkrete SQL-DDL ist nicht erforderlich.

### 3c – 4 Punkte

Erläutern Sie zwei fachliche oder technische Vorteile des normalisierten Modells gegenüber der Ausgangstabelle.

**Lösung**

**Vorteil 1 (2 P.):** Programm- und Personennamen werden nicht für jede Teilzahlung erneut gespeichert. Damit sinkt die Gefahr widersprüchlicher Angaben bei Änderungen.

**Vorteil 2 (2 P.):** Personen, Programme und Anträge können unabhängig davon erfasst und verwaltet werden, ob bereits Zahlungen existieren. Wird eine einzelne Zahlung gelöscht, bleiben die Informationen zum zugehörigen Antrag, zum Programm und zur antragstellenden Person erhalten. **Nicht** gemeint ist, dass referenzierte Personen, Programme oder Anträge beliebig gelöscht werden dürfen: Dabei sind die Fremdschlüsselbedingungen zu beachten.

Weitere fachlich korrekte, voneinander unterscheidbare Vorteile anerkennen.

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

**Lösung**

```sql
SELECT a.antrag_id,
       SUM(z.betrag) AS gesamtsumme
FROM antrag a
JOIN zahlung z ON z.antrag_id = a.antrag_id
GROUP BY a.antrag_id
ORDER BY a.antrag_id;
```

| antrag_id | gesamtsumme |
|---:|---:|
| 51 | 1100.00 |
| 52 | 600.00 |

Anträge 53 und 54 erscheinen nicht, da für sie keine Zahlungen erfasst wurden.

**Bewertung (8 Punkte)**

| Kriterium | Punkte |
|---|---:|
| Korrekte Verwendung der benötigten Zahlungsdaten (mit oder ohne `JOIN`) | 2 |
| Korrekte Summenbildung mit `SUM(betrag)` bzw. `SUM(z.betrag)` | 2 |
| Korrekte Gruppierung nach der Antragkennung | 2 |
| Aufsteigende Sortierung nach der Antragkennung | 1 |
| Vollständige und korrekte Ergebnistabelle | 1 |
| **Gesamt** | **8** |

**Ebenfalls vollständig korrekt und mit 8 Punkten bewertbar** ist die direkte Aggregation aus der Tabelle `zahlung`, da `antrag_id` dort bereits als nicht-nullbarer Fremdschlüssel vorhanden ist:

```sql
SELECT antrag_id,
       SUM(betrag) AS gesamtsumme
FROM zahlung
GROUP BY antrag_id
ORDER BY antrag_id;
```

Ein `JOIN` ist für die geforderten Spalten und die geforderte Ergebnismenge **nicht erforderlich**. Beide Varianten sowie weitere fachlich gleichwertige SQL-Abfragen sind anzuerkennen. Bei korrekter Abfrage ohne Ergebnistabelle sind maximal 7 Punkte erreichbar.

### 4b – 6 Punkte

Ermitteln Sie mit einer SQL-Abfrage alle **bewilligten Anträge ohne irgendeine Zahlung**. Geben Sie Antragkennung und Name der antragstellenden Person aus, sortiert nach Antragkennung. Nennen Sie die Ergebniszeile(n).

**Lösung**

```sql
SELECT a.antrag_id, s.name
FROM antrag a
JOIN antragsteller s ON s.person_id = a.person_id
WHERE a.status = 'BEWILLIGT'
  AND NOT EXISTS (
      SELECT 1 FROM zahlung z WHERE z.antrag_id = a.antrag_id
  )
ORDER BY a.antrag_id;
```

Ergebnis: `54 | Mila Roth`. 2 P. Status, 2 P. korreliertes `NOT EXISTS` (oder korrektes `LEFT JOIN ... IS NULL`), 1 P. Join und Ausgabe, 1 P. Ergebnis/Sortierung.

### 4c – 6 Punkte

Ermitteln Sie mit SQL die **Programme mit mindestens zwei Anträgen** unabhängig vom Status. Geben Sie Programmkennung, Titel und Antragsanzahl aus, sortiert nach Programmkennung. Nennen Sie die Ergebnismenge.

**Lösung**

```sql
SELECT p.programm_id, p.titel, COUNT(a.antrag_id) AS anzahl
FROM programm p
JOIN antrag a ON a.programm_id = p.programm_id
GROUP BY p.programm_id, p.titel
HAVING COUNT(a.antrag_id) >= 2
ORDER BY p.programm_id;
```

Ergebnis: `7 | Gründach | 2`. Je 1 P. Join, Zählung, Gruppierung, HAVING, Sortierung, Ergebnis.

### 4d – 4 Punkte

Schreiben Sie die SQL-Anweisung, die den Status von Antrag **53** auf `ABGELEHNT` setzt, aber **nur, wenn** sein bisheriger Status `IN_PRUEFUNG` ist.

**Lösung**

```sql
UPDATE antrag
SET status = 'ABGELEHNT'
WHERE antrag_id = 53 AND status = 'IN_PRUEFUNG';
```

2 P. `UPDATE SET`, 2 P. vollständige `WHERE`-Bedingung. Eine äquivalente Lösung zulässig.

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

**Lösung**

Die Abfrage liefert für die Beispieldaten:

| antrag_id | anzahl_zeilen | anzahl_zahlungen |
|---:|---:|---:|
| 51 | 2 | 2 |
| 52 | 1 | 1 |
| 53 | 1 | 0 |
| 54 | 1 | 0 |

`COUNT(*)` zählt sämtliche Ergebniszeilen einer Gruppe, auch Zeilen, die beim `LEFT JOIN` wegen einer fehlenden Zahlung mit `NULL`-Werten auf der rechten Tabellenseite ergänzt werden. Deshalb ergibt sich für die Anträge **53 und 54** jeweils `anzahl_zeilen = 1`.

`COUNT(z.zahlung_id)` zählt dagegen nur Zeilen, in denen `z.zahlung_id` nicht `NULL` ist. Für die Anträge **53 und 54** sind keine Zahlungen vorhanden, daher ergibt sich jeweils `anzahl_zahlungen = 0`.

**Zum Zählen tatsächlich erfasster Zahlungen ist `COUNT(z.zahlung_id)` geeignet**, weil der Primärschlüssel einer vorhandenen Zahlung niemals `NULL` ist und somit nur tatsächlich verknüpfte Zahlungen gezählt werden.

**Bewertung (4 Punkte)**

| Kriterium | Punkte |
|---|---:|
| Verhalten von `COUNT(*)` richtig erläutert | 1 |
| Verhalten von `COUNT(z.zahlung_id)` richtig erläutert | 1 |
| Unterschied anhand mindestens eines Antrags ohne Zahlung (53 oder 54) zutreffend erklärt | 1 |
| Geeigneten Ausdruck begründet ausgewählt | 1 |
| **Gesamt** | **4** |

Die vollständige Ergebnistabelle dient der Erläuterung in der Musterlösung; sie ist **nicht** ausdrücklich von den Prüflingen zu erstellen.

**Gesamt: 30 + 20 + 22 + 28 = 100 Punkte.**

**Ende des Prüfungsbereichs – 100 Punkte.**
