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

**Lösung**  

Die ersten fünf Listenelemente sind:

```text
["R1-P1", "R1-P2", "R1-P3", "R1-P4", "R2-P1"]
```

Die letzten zwei Listenelemente sind:

```text
["R3-P3", "R3-P4"]
```

Zuerst wird die innere Schleife für alle vier Plätze der ersten Reihe durchlaufen. Erst danach wird die nächste Reihe verarbeitet.

b) Ermitteln Sie, wie oft `ANHÄNGEN` ausgeführt wird. Erläutern Sie, wie sich die Anzahl bei `5` Reihen und `4` Plätzen je Reihe verändert.

**Lösung**  
Bei `3` Reihen mit je `4` Plätzen:

```text
3 * 4 = 12 Aufrufe
```

Bei `5` Reihen mit je `4` Plätzen:

```text
5 * 4 = 20 Aufrufe
```

Die Anzahl der Aufrufe entspricht dem Produkt aus Reihenanzahl und Plätzen je Reihe.

c) Geben Sie an, wie viele Sitzplatzkennungen bei `nummeriereSitze(0, 4)` erzeugt werden, und begründen Sie dies anhand der Schleifengrenzen.

**Lösung**  
Bei `nummeriereSitze(0, 4)` werden **0 Kennungen** erzeugt. Die äußere Schleife reicht von `1` bis `0`; da die untere Grenze größer als die obere ist, wird ihr Rumpf nicht ausgeführt. Die Funktion liefert die leere Liste `[]` zurück.

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

**Lösung**  
Für `zeilen = 2` und `spalten = 3` ist die innere Schleife fälschlich auf `j = 0` bis `1` begrenzt. Spalte `2` bleibt daher auf dem Initialwert `0`.

```text
[
    [1, 2, 0],
    [2, 4, 0]
]
```

b) Identifizieren Sie den Fehler in der inneren Schleife und geben Sie die korrigierte Schleifenzeile an.

**Lösung**  
Die innere Schleife verwendet `zeilen` statt `spalten` für die obere Grenze. Korrekt lautet sie:

```text
FÜR j VON 0 BIS spalten - 1
```

Mit dieser Änderung entsteht für `(2, 3)` das erwartete Ergebnis `[[1, 2, 3], [2, 4, 6]]`.

c) Erläutern Sie, welche Auswirkung derselbe Fehler für `zeilen = 3` und `spalten = 2` hat. Nennen Sie die erste ungültige Arrayposition, auf die zugegriffen wird.

**Lösung**  
Das Array hat die gültigen Zeilenindizes `0` bis `2` und die gültigen Spaltenindizes `0` bis `1`. Die innere Schleife läuft jedoch bis `j = 2`.

Bereits bei `i = 0` und `j = 2` versucht die Funktion, auf `tabelle[0][2]` zuzugreifen. Dieser Spaltenindex liegt außerhalb des Arrays. In einer Programmiersprache mit geprüften Arraygrenzen führt das zu einem Laufzeitfehler; die Funktion erreicht keine reguläre Rückgabe.

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

**Lösung**  
| `i` | Durchlaufene Werte von `j` | Ausführungen |
| --- | --- | ---: |
| 1 | 1, 2, 3, 4 | 4 |
| 2 | 2, 3, 4 | 3 |
| 3 | 3, 4 | 2 |
| 4 | 4 | 1 |

Die Anweisung `schritte = schritte + 1` wird `4 + 3 + 2 + 1 = 10` Mal ausgeführt.

b) Erläutern Sie, weshalb der Rückgabewert für `n = 4` nicht `16` beträgt.

**Lösung**  
`16` ergäbe sich, wenn die innere Schleife in jedem der vier äußeren Durchläufe jeweils viermal ausgeführt würde. Tatsächlich beginnt sie bei `j = i`. Mit wachsendem `i` werden die inneren Durchläufe weniger.

c) Geben Sie den Rückgabewert für `n = 5` an und bestimmen Sie die zeitliche Komplexität des Algorithmus in O-Notation bezogen auf `n`.

**Lösung**  
Für `n = 5` ergibt sich:

```text
5 + 4 + 3 + 2 + 1 = 15
```

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

**Lösung**  
| Indexpaar | Werte | Abstand |
| --- | --- | ---: |
| `(0, 1)` | 10 und 12 | 2 |
| `(0, 3)` | 10 und 11 | 1 |
| `(1, 3)` | 12 und 11 | 1 |

Rückgabewert: `3`

b) Ermitteln Sie, wie viele Paarvergleiche unabhängig vom Ergebnis der Bedingung bei den vier Werten durchgeführt werden.

**Lösung**  
Bei vier Werten werden `3 + 2 + 1 = 6` unterschiedliche, ungeordnete Paare geprüft, unabhängig davon, ob die Bedingung erfüllt ist.

Die geprüften Indexpaare sind `(0,1)`, `(0,2)`, `(0,3)`, `(1,2)`, `(1,3)` und `(2,3)`.

c) Erläutern Sie die Funktion der unteren Schleifengrenze `i + 1`. Geben Sie an, was sich bei einer Änderung auf `j VON 0 BIS LÄNGE(werte) - 1` hinsichtlich der verglichenen Paare ändern würde.

**Lösung**  
Die untere Grenze `i + 1` sorgt dafür, dass kein Element mit sich selbst und jedes ungeordnete Paar nur einmal verglichen wird. Bei einem Start der inneren Schleife mit `j = 0` würden auch Selbstvergleiche `(i,i)` und Gegenrichtungen wie `(0,1)` und `(1,0)` berücksichtigt.

Da die äußere Schleife weiterhin nur `i = 0` bis `2` durchläuft, würden dabei allerdings **nicht sämtliche Gegenrichtungen** verglichen: Beispielsweise fehlen alle Paare mit erstem Index `3`. Es würden `3 * 4 = 12` Vergleiche stattfinden, statt `6`.

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

**Lösung**  
Die fehlerhafte Bedingung akzeptiert Abstände bis einschließlich `3` Tage. Für `tage = [2, 4, 5, 10]` werden gezählt:

| Werte | Abstand | Fehlerhaft berücksichtigt? |
| --- | ---: | --- |
| 2 und 4 | 2 | Ja |
| 2 und 5 | 3 | Ja |
| 4 und 5 | 1 | Ja |
| 2 und 10 | 8 | Nein |
| 4 und 10 | 6 | Nein |
| 5 und 10 | 5 | Nein |

Fehlerhafter Rückgabewert: `3`

b) Identifizieren Sie den logischen Fehler und formulieren Sie die korrekte Bedingung.

**Lösung**  
Die Anforderung lautet "weniger als 3 Tage". Die ursprüngliche Bedingung `<= 3` berücksichtigt fälschlich auch Abstände von genau drei Tagen.

Korrektur:

```text
WENN ABS(tage[i] - tage[j]) < 3 DANN
```

c) Geben Sie den korrigierten Rückgabewert an. Nennen Sie die betreffenden Wertepaare.

**Lösung**  
Es bleiben die Wertepaare `(2, 4)` und `(4, 5)`.

Korrigierter Rückgabewert: `2`

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

**Lösung**  
Für `werte = [4, 1, 3, 2]` gilt:

| `i` | Aktueller Wert | Spätere Werte | Größere spätere Werte | Zähler |
| --- | ---: | --- | --- | ---: |
| 0 | 4 | 1, 3, 2 | keine | 0 |
| 1 | 1 | 3, 2 | 3, 2 | 2 |
| 2 | 3 | 2 | keine | 0 |
| 3 | 2 | keine | keine | 0 |

**Rückgabewert:**

```text
[0, 2, 0, 0]
```

Für `i = 1` werden die Werte an den Indizes `2` und `3` mit `werte[1] = 1` verglichen. Beide sind größer, daher erhält `ergebnis[1]` den Wert `2`.

b) Erläutern Sie, weshalb die innere Schleife beim letzten Wert der äußeren Schleife nicht ausgeführt wird.

**Lösung**  
Beim letzten Arrayindex `i = 3` beginnt die innere Schleife mit `j = 4`, ihre obere Grenze beträgt aber `3`. Sie wird deshalb nicht durchlaufen. Rechts vom letzten Element existieren keine weiteren Elemente.

c) Die Funktion soll künftig Werte rechts zählen, die **größer oder gleich** dem aktuellen Wert sind. Geben Sie die notwendige Änderung der Bedingung und das Ergebnis für `[3, 3, 2, 3]` an.

**Lösung**  
Die korrigierte Bedingung lautet:

```text
WENN werte[j] >= werte[i] DANN
```

Für `[3, 3, 2, 3]` gilt:

- Index `0`: Die späteren Werte an den Indizes `1` und `3` sind größer oder gleich `3`.
- Index `1`: Der spätere Wert an Index `3` ist größer oder gleich `3`.
- Index `2`: Der spätere Wert an Index `3` ist größer oder gleich `2`.
- Index `3`: Keine späteren Elemente.

**Rückgabewert:**

```text
[2, 1, 1, 0]
```

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

**Lösung**  
Die Zeilensummen sind:

```text
Zeile 0: 3 + 5 + 2 + 4 = 14
Zeile 1: 6 + 1 + 0 + 3 = 10
Zeile 2: 2 + 2 + 2 + 2 = 8

Rückgabe: [14, 10, 8]
```

b) Erläutern Sie, weshalb `summe = 0` innerhalb der äußeren Schleife steht. Geben Sie die Rückgabe an, wenn `summe` stattdessen einmalig vor der äußeren Schleife mit `0` initialisiert wird.

**Lösung**  
Jede Produktionslinie muss unabhängig von den anderen summiert werden. Deshalb wird `summe` am Anfang jeder Zeilenverarbeitung wieder auf `0` gesetzt.

Wird `summe = 0` einmalig vor die äußere Schleife verschoben, ergibt sich eine fortlaufende Gesamtsumme:

```text
Zeile 0: 14
Zeile 1: 14 + 10 = 24
Zeile 2: 24 + 8 = 32

Fehlerhafte Rückgabe: [14, 24, 32]
```

c) Ermitteln Sie die Anzahl der Additionen für die angegebenen Eingabedaten und geben Sie die zeitliche Komplexität in Abhängigkeit von der Zeilenanzahl `z` und Spaltenanzahl `s` in O-Notation an.

**Lösung**  
Bei `3` Zeilen und `4` Spalten wird die Summenanweisung `3 * 4 = 12` Mal ausgeführt. Es finden also **12 Additionen** statt.

Für `z` Zeilen und `s` Spalten gilt eine zeitliche Komplexität von `O(z * s)` bzw. `O(n²)`.

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

**Lösung**  
Die innere Schleife läuft von `zeile = 0` bis `ZEILEN(matrix) - 2 = 1`. Die dritte Zeile mit Index `2` bleibt unberücksichtigt.

```text
Spalte 0: 2 + 3 = 5
Spalte 1: 5 + 1 = 6

Fehlerhafte Rückgabe: [5, 6]
```

b) Identifizieren Sie den Fehler in der inneren Schleife und geben Sie die korrigierte Schleifenzeile an.

**Lösung**  
Die Schleife endet eine Zeile zu früh. Korrekt lautet sie:

```text
FÜR zeile VON 0 BIS ZEILEN(matrix) - 1
```

c) Ermitteln Sie das korrekte Ergebnis. Erläutern Sie, warum die äußere Schleife über die Spalten und die innere über die Zeilen läuft.

**Lösung**  
```text
Spalte 0: 2 + 3 + 4 = 9
Spalte 1: 5 + 1 + 6 = 12

Korrekte Rückgabe: [9, 12]
```

Die äußere Schleife legt fest, **für welche Spalte** die Summe berechnet wird. Die innere Schleife durchläuft dafür sämtliche Zeilen und addiert deren Einträge in der festgelegten Spalte. Anschließend wird die berechnete Summe in `summen[spalte]` gespeichert.

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

**Lösung**  
Die Eingabematrix hat `2` Zeilen und `3` Spalten. Nach der Transposition hat die Ergebnismatrix `3` Zeilen und `2` Spalten.

```text
[
    [7, 1],
    [8, 2],
    [9, 3]
]
```

b) Erläutern Sie anhand des Werts `9` aus der Matrix, wie sich die Indizes bei der Übertragung verändern.

**Lösung**  
Der Wert `9` steht in der Eingabematrix an Position `matrix[0][2]`. Durch Vertauschen der Indizes wird er in `ergebnis[2][0]` geschrieben.

```text
ergebnis[2][0] = matrix[0][2]
```

c) Eine Entwicklerin legt `ergebnis` versehentlich mit `ZEILEN(matrix)` Zeilen und `SPALTEN(matrix)` Spalten an, lässt aber die Zuweisung unverändert. Ermitteln Sie die erste ungültige Zielposition beim Durchlaufen der Schleifen und begründen Sie Ihre Antwort.

**Lösung**  
Wird das Ergebnisarray irrtümlich mit `ZEILEN(matrix) = 2` Zeilen und `SPALTEN(matrix) = 3` Spalten angelegt, besitzt es nur die gültigen Zeilenindizes `0` und `1`.

Die Schleifen besuchen zunächst:

```text
zeile = 0, spalte = 0  -> ergebnis[0][0]  gültig
zeile = 0, spalte = 1  -> ergebnis[1][0]  gültig
zeile = 0, spalte = 2  -> ergebnis[2][0]  ungültig
```

Die erste ungültige Zielposition ist `ergebnis[2][0]`. Obwohl der Spaltenindex `0` gültig ist, existiert im falsch dimensionierten Ergebnisarray keine Zeile `2`. Bei geprüften Arraygrenzen tritt deshalb ein Laufzeitfehler auf.

