# Aufgabe 'Algorithmen - Verschachtelte Schleifen und zweidimensionale Arrays'

Für alle Aufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert. Ein zweidimensionales Array wird mit `matrix[zeile][spalte]` angesprochen.
- `LÄNGE(array)` liefert die Anzahl der Elemente eines eindimensionalen Arrays. `ZEILEN(matrix)` liefert die Anzahl der Zeilen und `SPALTEN(matrix)` die Anzahl der Spalten einer rechteckigen Matrix.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Falls `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Gleichheitsvergleich, `!=` einen Ungleichheitsvergleich.
- `MOD` liefert bei den hier verwendeten nichtnegativen Ganzzahlen den Rest der Division.
- `ZURÜCKGEBEN: ...` beendet die Funktion unmittelbar. Alle verwendeten Arrays enthalten ausschließlich Ganzzahlen.
- Die Daten werden nicht verändert, sofern der Pseudocode keine Zuweisung an ein Arrayelement enthält.
- Bei fehlerhaftem Pseudocode sollen nur die für das beschriebene Verhalten erforderlichen Stellen korrigiert werden. Der korrigierte Ausschnitt genügt, sofern nichts anderes verlangt ist.

## 1. Verschachtelte Schleifen

### A1 - Nummerierte Sitzplätze

Ein Veranstaltungsraum hat `3` Reihen mit jeweils `4` Sitzplätzen. Der Algorithmus erzeugt fortlaufend Sitzplatzkennungen. Die äußere Schleife durchläuft die Reihen, die innere die Plätze jeder Reihe.

**Pseudocode:**

```text
FUNKTION nummeriereSitze(anzahlReihen, plaetzeJeReihe)
    kennungen = leere Liste

    FÜR reihe VON 1 BIS anzahlReihen
        FÜR platz VON 1 BIS plaetzeJeReihe
            ANHÄNGEN(kennungen, "R" + reihe + "-P" + platz)
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: kennungen
ENDE FUNKTION
```

Dabei bezeichnet `+` das Aneinanderhängen von Text; Zahlen werden dabei in Text umgewandelt.

**Eingabe:** `nummeriereSitze(3, 4)`

a) Geben Sie die ersten fünf und die letzten zwei Elemente der Ergebnisliste in ihrer tatsächlichen Reihenfolge an.

b) Ermitteln Sie, wie oft `ANHÄNGEN` ausgeführt wird. Erläutern Sie, wie sich die Anzahl bei `5` Reihen und `4` Plätzen je Reihe verändert.

c) Geben Sie an, wie viele Sitzplatzkennungen bei `nummeriereSitze(0, 4)` erzeugt werden, und begründen Sie dies anhand der Schleifengrenzen.

### A2 - Multiplikationstabelle mit Fehler

Eine Funktion soll eine rechteckige Multiplikationstabelle erzeugen. Für `zeilen = 2` und `spalten = 3` wird folgendes Ergebnis erwartet:

```text
[
    [1, 2, 3],
    [2, 4, 6]
]
```

**Fehlerhafter Pseudocode:**

```text
FUNKTION multiplikationstabelle(zeilen, spalten)
    tabelle = neues Ganzzahl-Array[zeilen][spalten]

    FÜR i VON 0 BIS zeilen - 1
        FÜR j VON 0 BIS zeilen - 1
            tabelle[i][j] = (i + 1) * (j + 1)
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: tabelle
ENDE FUNKTION
```

a) Ermitteln Sie die vollständige Rückgabe des fehlerhaften Algorithmus für `zeilen = 2` und `spalten = 3`. Neu angelegte Ganzzahl-Arrays enthalten anfangs ausschließlich `0`.

b) Identifizieren Sie den Fehler in der inneren Schleife und geben Sie die korrigierte Schleifenzeile an.

c) Erläutern Sie, welche Auswirkung derselbe Fehler für `zeilen = 3` und `spalten = 2` hat. Nennen Sie die erste ungültige Arrayposition, auf die zugegriffen wird.

### A3 - Anzahl ausgeführter Anweisungen

Ein Testprogramm protokolliert, wie viele Verarbeitungsschritte in zwei verschachtelten Schleifen ausgeführt werden. Die innere Schleife beginnt jeweils beim aktuellen Wert der äußeren Schleife.

**Pseudocode:**

```text
FUNKTION zaehleSchritte(n)
    schritte = 0

    FÜR i VON 1 BIS n
        FÜR j VON i BIS n
            schritte = schritte + 1
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: schritte
ENDE FUNKTION
```

a) Ermitteln Sie den Rückgabewert für `n = 4`. Geben Sie die Anzahl der Ausführungen der inneren Anweisung für jeden Wert von `i` an.

b) Erläutern Sie, weshalb der Rückgabewert für `n = 4` nicht `16` beträgt.

c) Geben Sie den Rückgabewert für `n = 5` an und bestimmen Sie die zeitliche Komplexität des Algorithmus in O-Notation bezogen auf `n`.

## 2. Verschachtelte Schleifen und paarweise Vergleiche

### A4 - Ähnliche Messwerte

Eine Qualitätskontrolle soll zählen, wie viele **verschiedene, ungeordnete** Paare von Messwerten höchstens `2` Einheiten auseinanderliegen. Jeder Messwert darf mit allen späteren Messwerten verglichen werden, nicht jedoch mit sich selbst. Der Betrag einer Zahl wird mit `ABS` ermittelt.

**Eingabedaten:**

```text
werte = [10, 12, 15, 11]
```

**Pseudocode:**

```text
FUNKTION zaehleAehnlichePaare(werte)
    anzahl = 0

    FÜR i VON 0 BIS LÄNGE(werte) - 2
        FÜR j VON i + 1 BIS LÄNGE(werte) - 1
            WENN ABS(werte[i] - werte[j]) <= 2 DANN
                anzahl = anzahl + 1
            ENDE WENN
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: anzahl
ENDE FUNKTION
```

a) Geben Sie alle Indexpaare `(i, j)` an, welche die Bedingung erfüllen, und ermitteln Sie den Rückgabewert.

b) Ermitteln Sie, wie viele Paarvergleiche unabhängig vom Ergebnis der Bedingung bei den vier Werten durchgeführt werden.

c) Erläutern Sie die Funktion der unteren Schleifengrenze `i + 1`. Geben Sie an, was sich bei einer Änderung auf `j VON 0 BIS LÄNGE(werte) - 1` hinsichtlich der verglichenen Paare ändern würde.

### A5 - Abstände zwischen Lieferterminen

Ein Projektteam vergleicht die geplanten Liefertage verschiedener Komponenten. Zwei Komponenten gelten als zeitlich nahe beieinander, wenn zwischen ihren Liefertagen **weniger als 3 Tage** liegen. Es sollen nur unterschiedliche, ungeordnete Komponentenpaare gezählt werden.

**Eingabedaten:** Die Tage sind als ganze Zahlen relativ zum Projektbeginn gespeichert.

```text
tage = [2, 4, 5, 10]
```

**Fehlerhafter Pseudocode:**

```text
FUNKTION zaehleNaheLiefertermine(tage)
    anzahl = 0

    FÜR i VON 0 BIS LÄNGE(tage) - 2
        FÜR j VON i + 1 BIS LÄNGE(tage) - 1
            WENN ABS(tage[i] - tage[j]) <= 3 DANN
                anzahl = anzahl + 1
            ENDE WENN
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: anzahl
ENDE FUNKTION
```

a) Geben Sie den Rückgabewert des fehlerhaften Algorithmus für die angegebenen Werte an.

b) Identifizieren Sie den logischen Fehler und formulieren Sie die korrekte Bedingung.

c) Geben Sie den korrigierten Rückgabewert an. Nennen Sie die betreffenden Wertepaare.

### A6 - Größere Werte rechts

Für eine Auswertung soll für jede Position eines Zahlenarrays ermittelt werden, wie viele **später stehende** Elemente einen größeren Wert haben. Das Ergebnis enthält je Eingabeposition einen Zähler.

**Eingabedaten:**

```text
werte = [4, 1, 3, 2]
```

**Pseudocode:**

```text
FUNKTION zaehleGroessereRechts(werte)
    ergebnis = neues Ganzzahl-Array[LÄNGE(werte)]

    FÜR i VON 0 BIS LÄNGE(werte) - 1
        FÜR j VON i + 1 BIS LÄNGE(werte) - 1
            WENN werte[j] > werte[i] DANN
                ergebnis[i] = ergebnis[i] + 1
            ENDE WENN
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: ergebnis
ENDE FUNKTION
```

Neu angelegte Ganzzahl-Arrays sind mit `0` initialisiert.

a) Ermitteln Sie die vollständige Ergebnisliste und erläutern Sie die Berechnung für `i = 1`.

b) Erläutern Sie, weshalb die innere Schleife beim letzten Wert der äußeren Schleife nicht ausgeführt wird.

c) Die Funktion soll künftig Werte rechts zählen, die **größer oder gleich** dem aktuellen Wert sind. Geben Sie die notwendige Änderung der Bedingung und das Ergebnis für `[3, 3, 2, 3]` an.

## 3. Verarbeitung zweidimensionaler Arrays

### A7 - Summen je Produktionslinie

Ein Betrieb erfasst für drei Produktionslinien jeweils die Stückzahlen aus vier Schichten. Jede Zeile steht für eine Produktionslinie, jede Spalte für eine Schicht. Der Algorithmus berechnet die Summe **je Zeile**, ohne gezielt nach bestimmten Elementwerten zu suchen.

**Eingabedaten:**

```text
produktion = [
    [3, 5, 2, 4],
    [6, 1, 0, 3],
    [2, 2, 2, 2]
]
```

**Pseudocode:**

```text
FUNKTION zeilensummen(matrix)
    summen = neues Ganzzahl-Array[ZEILEN(matrix)]

    FÜR zeile VON 0 BIS ZEILEN(matrix) - 1
        summe = 0

        FÜR spalte VON 0 BIS SPALTEN(matrix) - 1
            summe = summe + matrix[zeile][spalte]
        ENDE FÜR

        summen[zeile] = summe
    ENDE FÜR

    ZURÜCKGEBEN: summen
ENDE FUNKTION
```

a) Ermitteln Sie das vollständig zurückgegebene Array.

b) Erläutern Sie, weshalb `summe = 0` innerhalb der äußeren Schleife steht. Geben Sie die Rückgabe an, wenn `summe` stattdessen einmalig vor der äußeren Schleife mit `0` initialisiert wird.

c) Ermitteln Sie die Anzahl der Additionen für die angegebenen Eingabedaten und geben Sie die zeitliche Komplexität in Abhängigkeit von der Zeilenanzahl `z` und Spaltenanzahl `s` in O-Notation an.

### A8 - Spaltensummen mit Fehler

Ein Lager dokumentiert Artikelzugänge für drei Standorte an zwei Tagen. Die Zeilen stehen für Standorte, die Spalten für Tage. Gesucht sind die **Gesamtzugänge pro Tag**, also die Spaltensummen.

**Eingabedaten:**

```text
zugaenge = [
    [2, 5],
    [3, 1],
    [4, 6]
]
```

**Fehlerhafter Pseudocode:**

```text
FUNKTION spaltensummen(matrix)
    summen = neues Ganzzahl-Array[SPALTEN(matrix)]

    FÜR spalte VON 0 BIS SPALTEN(matrix) - 1
        summe = 0

        FÜR zeile VON 0 BIS ZEILEN(matrix) - 2
            summe = summe + matrix[zeile][spalte]
        ENDE FÜR

        summen[spalte] = summe
    ENDE FÜR

    ZURÜCKGEBEN: summen
ENDE FUNKTION
```

a) Geben Sie das vom fehlerhaften Algorithmus zurückgegebene Array an.

b) Identifizieren Sie den Fehler in der inneren Schleife und geben Sie die korrigierte Schleifenzeile an.

c) Ermitteln Sie das korrekte Ergebnis. Erläutern Sie, warum die äußere Schleife über die Spalten und die innere über die Zeilen läuft.

### A9 - Matrix transponieren

Bei einem Datenexport müssen Zeilen und Spalten einer rechteckigen Tabelle vertauscht werden. Der Algorithmus soll die **transponierte Matrix** erzeugen.

**Eingabedaten:**

```text
matrix = [
    [7, 8, 9],
    [1, 2, 3]
]
```

**Pseudocode:**

```text
FUNKTION transponiere(matrix)
    ergebnis = neues Ganzzahl-Array[SPALTEN(matrix)][ZEILEN(matrix)]

    FÜR zeile VON 0 BIS ZEILEN(matrix) - 1
        FÜR spalte VON 0 BIS SPALTEN(matrix) - 1
            ergebnis[spalte][zeile] = matrix[zeile][spalte]
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: ergebnis
ENDE FUNKTION
```

a) Geben Sie die transponierte Matrix vollständig an.

b) Erläutern Sie anhand des Werts `9` aus der Matrix, wie sich die Indizes bei der Übertragung verändern.

c) Eine Entwicklerin legt `ergebnis` versehentlich mit `ZEILEN(matrix)` Zeilen und `SPALTEN(matrix)` Spalten an, lässt aber die Zuweisung unverändert. Ermitteln Sie die erste ungültige Zielposition beim Durchlaufen der Schleifen und begründen Sie Ihre Antwort.
