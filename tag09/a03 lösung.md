# Aufgabe – Integrierte Codeanalyse

## Szenario

Ein Lagerverwaltungssystem erfasst die Bestände verschiedener Artikel in einem Array.

Ein Algorithmus ermittelt, wie viele Artikel einen vorgegebenen Mindestbestand erreichen oder überschreiten.

Gegeben ist folgender Pseudocode:

```text
FUNKTION zaehleVerfuegbare(bestand: Array von Integer, mindestbestand: Integer) -> Integer
    anzahl = 0

    FÜR i VON 0 BIS LÄNGE(bestand) - 1
        WENN bestand[i] >= mindestbestand DANN
            anzahl = anzahl + 1
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: anzahl
ENDE FUNKTION


HAUPTPROGRAMM
    bestand = [0, 4, 7, 2, 5]
    mindestbestand = 4

    anzahl = zaehleVerfuegbare(bestand, mindestbestand)

    AUSGEBEN: anzahl
ENDE HAUPTPROGRAMM
```

**Hinweise:**
- Die Indizierung des Arrays beginnt bei 0.
- Die Obergrenze der FÜR-Schleife ist einschließlich.
- Ein Zugriff auf einen nicht vorhandenen Arrayindex führt zu einem Laufzeitfehler und beendet die Ausführung des Programms.
- Der Pseudocode wird der Reihe nach ausgeführt.

## A1 – Algorithmus beschreiben

Beschreiben Sie die Funktionsweise von `zaehleVerfuegbare`.

Gehen Sie dabei auf die Bedeutung der beiden Parameter, die Verarbeitung des Arrays, die Bedingung und den Rückgabewert ein.

**Lösung**  

Die Funktion `zaehleVerfuegbare` ermittelt, wie viele Elemente eines Arrays einen vorgegebenen Mindestbestand erreichen oder überschreiten.

- Der Parameter `bestand` enthält ein Array mit den Bestandsmengen der einzelnen Artikel.
- Der Parameter `mindestbestand` gibt den Mindestwert an, mit dem die Bestände verglichen werden.
- Die Variable `anzahl` wird mit `0` initialisiert und dient als Zähler.
- Die FÜR-Schleife durchläuft das Array vom Index `0` bis zum letzten gültigen Index.
- Für jedes Arrayelement wird geprüft, ob dessen Wert größer oder gleich `mindestbestand` ist.
- Ist die Bedingung erfüllt, wird `anzahl` um `1` erhöht.
- Nach dem letzten Schleifendurchlauf gibt die Funktion die Anzahl der Elemente zurück, welche die Bedingung erfüllen.

Ergebnis: Die Funktion zählt die Artikel, deren Bestand mindestens dem angegebenen Mindestbestand entspricht. Sie berechnet nicht die Summe der Bestandsmengen.

## A2 – Schreibtischtest durchführen

Führen Sie für den Aufruf `zaehleVerfuegbare([0, 4, 7, 2, 5], 4)` einen Schreibtischtest durch.

Vervollständigen Sie die Tabelle. Tragen Sie für jeden Schleifendurchlauf den geprüften Arraywert, das Ergebnis der Bedingung (`WAHR` oder `FALSCH`) und den Wert der Variablen `anzahl` nach der Prüfung ein.

| i | bestand[i] | bestand[i] >= mindestbestand | anzahl nach Prüfung |
|---:|---:|---|---:|
| 0 | | | |
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |

**Lösung**

Gegebener Funktionsaufruf: `zaehleVerfuegbare([0, 4, 7, 2, 5], 4)`  
Anfangswert: `anzahl = 0`  

| i | bestand[i] | bestand[i] >= mindestbestand | anzahl nach Prüfung |
|---:|---:|---|---:|
| 0 | 0 | FALSCH | 0 |
| 1 | 4 | WAHR | 1 |
| 2 | 7 | WAHR | 2 |
| 3 | 2 | FALSCH | 2 |
| 4 | 5 | WAHR | 3 |

Nach Abschluss der Schleife gibt die Funktion den Wert `3` zurück.

## A3 – Programmausgabe ermitteln

Ermitteln Sie die Ausgabe des Hauptprogramms.

Begründen Sie Ihr Ergebnis, indem Sie die Arraywerte nennen, die zur Erhöhung der Variablen `anzahl` führen.

**Lösung**  
Ausgabe: 3

Der Mindestbestand beträgt `4`.

Die Arraywerte `4`, `7` und `5` erfüllen die Bedingung `bestand[i] >= mindestbestand`.

Die Werte `0` und `2` erfüllen die Bedingung nicht.

Damit wird die Variable `anzahl` insgesamt dreimal erhöht. Die Funktion gibt den Wert `3` zurück, der anschließend vom Hauptprogramm ausgegeben wird.

## A4 – Fehler analysieren

Ein Entwickler verändert die Schleife in der Funktion `zaehleVerfuegbare` wie folgt:

```text
FÜR i VON 0 BIS LÄNGE(bestand)
```

Analysieren Sie die Auswirkungen dieser Änderung.

1. Geben Sie den Wert von `i` an, bei dem der Fehler auftritt.
2. Erläutern Sie die Ursache des Fehlers.
3. Geben Sie an, ob das Hauptprogramm mit dieser Änderung eine reguläre Ausgabe erzeugt. Begründen Sie Ihre Antwort.

**Lösung**  

### 1. Fehlerzeitpunkt

Der Fehler tritt bei `i = 5` auf.

### 2. Fehlerursache

Das Array `bestand` enthält fünf Elemente. Da die Indizierung bei `0` beginnt, sind nur die Indizes `0` bis `4` gültig.

Durch die geänderte Schleifenbedingung wird zusätzlich ein Schleifendurchlauf mit `i = 5` ausgeführt.

Dabei versucht der Algorithmus, auf `bestand[5]` zuzugreifen. Dieser Index liegt außerhalb der Arraygrenzen.

Der Zugriff führt zu einem Laufzeitfehler.

### 3. Auswirkung auf die Programmausgabe

Das Hauptprogramm erzeugt **keine reguläre Ausgabe**.

Beim Zugriff auf `bestand[5]` tritt ein Laufzeitfehler auf. Die Funktion wird dadurch vorzeitig abgebrochen und erreicht die Anweisung `ZURÜCKGEBEN: anzahl` nicht.

Folglich wird auch die anschließende Ausgabeanweisung im Hauptprogramm nicht ausgeführt.

## A5 – Algorithmus anpassen

Die Funktion soll künftig nur Artikel zählen, deren Bestand echt größer als der angegebene Mindestbestand ist.

1. Geben Sie die dafür erforderliche Änderung der Bedingung im Pseudocode an.
2. Ermitteln Sie die Ausgabe des Hauptprogramms nach dieser Änderung. Gehen Sie dabei vom ursprünglichen, fehlerfreien Algorithmus aus.

**Lösung**

### 1. Änderung der Bedingung

Ursprüngliche Bedingung:

```text
WENN bestand[i] >= mindestbestand DANN
```

Geänderte Bedingung:

```text
WENN bestand[i] > mindestbestand DANN
```

Durch die Verwendung des Operators `>` werden nur Arraywerte berücksichtigt, die echt größer als der Mindestbestand sind.

Ein Arraywert, der genau dem Mindestbestand entspricht, wird nicht mehr gezählt.

### 2. Neue Programmausgabe

Gegebene Werte:

```text
bestand = [0, 4, 7, 2, 5]
mindestbestand = 4
```

Die Werte `7` und `5` sind größer als `4`.

Der Wert `4` wird nicht mehr gezählt, da er nicht echt größer als der Mindestbestand ist.

Ausgabe: 2  
Die Funktion gibt somit den Wert `2` zurück.
