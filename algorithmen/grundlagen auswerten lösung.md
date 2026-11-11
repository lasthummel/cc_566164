# Aufgabe 'Algorithmen - Grundlegende Verarbeitung'

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- `LÄNGE(liste)` liefert die Anzahl der Elemente.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- `ANHÄNGEN(liste, wert)` fügt den Wert am Ende der Liste hinzu.
- `ABBRUCH` beendet unmittelbar die innerste Schleife.
- Eine Map speichert Schlüssel-Wert-Paare. `ENTHÄLT_SCHLÜSSEL(map, schluessel)` prüft, ob ein Schlüssel bereits vorhanden ist.
- Sofern nicht ausdrücklich anders angegeben, bleibt die Reihenfolge der Eingabedaten unverändert.

## A1 - Zählen von Elementen unter einer Bedingung

Ein Ticketsystem speichert die Bearbeitungsdauer von Supportfällen in Minuten. Ermittelt werden soll, wie viele Supportfälle **länger als 30 Minuten** gedauert haben.

**Eingabedaten:**

```text
dauern = [15, 31, 30, 44, 8, 52]
```

**Pseudocode:**

```text
FUNKTION zaehleLangeFaelle(dauern)
    anzahl = 0

    FÜR i VON 0 BIS LÄNGE(dauern) - 1
        WENN dauern[i] > 30 DANN
            anzahl = anzahl + 1
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: anzahl
ENDE FUNKTION
```

### a)
Ermitteln Sie den Rückgabewert für die angegebenen Eingabedaten.

**Lösung.**  
Die Werte, die die Bedingung `dauern[i] > 30` erfüllen, sind `31`, `44` und `52`.

Rückgabewert: 3

### b)
Erläutern Sie, weshalb der Wert `30` nicht mitgezählt wird.

**Lösung.**  
Der Vergleichsoperator `>` bedeutet "größer als". Für den Wert `30` ist `30 > 30` falsch. Der Zähler wird deshalb für dieses Element nicht erhöht.

### c)
Ändern Sie die Bedingung im Pseudocode so, dass Fälle mit einer Bearbeitungsdauer von **mindestens 30 Minuten** gezählt werden. Geben Sie auch den neuen Rückgabewert an.

**Lösung.**  
Angepasste Bedingung:  

    WENN dauern[i] >= 30 DANN

Jetzt werden `31`, `30`, `44` und `52` gezählt.

Neuer Rückgabewert: `4`.


## A2 - Summenbildung und Durchschnittsberechnung

Für eine Auswertung sollen die Gesamtbearbeitungszeit und die durchschnittliche Bearbeitungszeit pro Supportfall berechnet werden.

**Eingabedaten:**

```text
dauern = [12, 18, 30, 20]
```

**Pseudocode:**

```text
FUNKTION berechneZeiten(dauern)
    summe = 0

    FÜR i VON 0 BIS LÄNGE(dauern) - 1
        summe = summe + dauern[i]
    ENDE FÜR

    WENN LÄNGE(dauern) == 0 DANN
        ZURÜCKGEBEN: (summe, "nicht berechenbar")
    ENDE WENN

    durchschnitt = summe / LÄNGE(dauern)
    ZURÜCKGEBEN: (summe, durchschnitt)
ENDE FUNKTION
```

### a)
Berechnen Sie die Summe aller Bearbeitungszeiten.

**Lösung.**  

    12 + 18 + 30 + 20 = 80 Minuten


### b)
Berechnen Sie den Durchschnitt und geben Sie den vollständigen Rückgabewert an.

**Lösung.**  

Die Liste enthält vier Werte.

    durchschnitt = 80 / 4 = 20

Vollständiger Rückgabewert:  

    (80, 20)

Die erste Komponente ist die Gesamtbearbeitungszeit, die zweite die durchschnittliche Bearbeitungszeit, jeweils in Minuten.

## c)
Erläutern Sie, welchen Rückgabewert der Algorithmus bei einer leeren Liste liefert und warum die Abfrage der Listenlänge notwendig ist.

**Lösung.**  

Bei einer leeren Liste wird die Schleife nicht ausgeführt. Die zuvor mit `0` initialisierte Summe bleibt daher `0`. Anschließend trifft die Bedingung `LÄNGE(dauern) == 0` zu.

Rückgabewert:

    (0, "nicht berechenbar")

Die Abfrage ist erforderlich, weil sonst bei der Durchschnittsberechnung durch die Anzahl `0` geteilt würde. Eine Division durch null ist nicht definiert.


## A3 - Minimum- und Maximumsuche

Eine Anwendung wertet Temperaturmessungen aus. Sie soll den kleinsten und den größten gemessenen Wert ermitteln.

**Eingabedaten:**

```text
temperaturen = [-5, -1, -12, -3]
```

**Pseudocode:**

```text
FUNKTION findeMinMax(werte)
    WENN LÄNGE(werte) == 0 DANN
        ZURÜCKGEBEN: "keine Werte"
    ENDE WENN

    minimum = werte[0]
    maximum = werte[0]

    FÜR i VON 1 BIS LÄNGE(werte) - 1
        WENN werte[i] < minimum DANN
            minimum = werte[i]
        ENDE WENN

        WENN werte[i] > maximum DANN
            maximum = werte[i]
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: (minimum, maximum)
ENDE FUNKTION
```

### a)
Geben Sie Minimum, Maximum und den vollständigen Rückgabewert für die Eingabedaten an.  

**Lösung.**  

Aus der Liste `[-5, -1, -12, -3]` ergeben sich:

- Minimum: `-12`
- Maximum: `-1`

Vollständiger Rückgabewert:  

    (-12, -1)


### b)
Begründen Sie, weshalb Minimum und Maximum mit dem ersten Listenelement und nicht pauschal mit `0` initialisiert werden.  

**Lösung.**

Mit `werte[0]` werden Minimum und Maximum auf einen tatsächlich vorhandenen Messwert gesetzt. Dadurch funktionieren beide Vergleiche auch dann, wenn alle Zahlen negativ oder alle Zahlen positiv sind.

Eine pauschale Initialisierung mit `0` kann falsche Ergebnisse erzeugen: Bei ausschließlich negativen Werten würde `maximum` fälschlich `0` bleiben, obwohl dieser Wert nicht in der Liste vorkommt. Bei ausschließlich positiven Werten könnte entsprechend `minimum` fälschlich `0` bleiben.

### c)
Geben Sie die Rückgabewerte für die Eingaben `[7]` und `[]` an.  

**Lösung.**  

- Für `[7]`: `(7, 7)`. Der einzige Wert ist sowohl Minimum als auch Maximum. Die Schleife ab Index `1` wird nicht ausgeführt.
- Für `[]`: `"keine Werte"`. Die Funktion beendet sich bereits bei der Prüfung auf eine leere Liste; ein Zugriff auf `werte[0]` findet nicht statt.


## A4 - Filtern von Arrays und Listen

Ein Ticketsystem speichert die Priorität jedes Tickets als Ganzzahl von `1` (niedrig) bis `5` (hoch). Für eine Übersicht sollen alle Prioritätswerte **ab 4** in eine neue Liste übernommen werden.

**Eingabedaten:**

    prioritaeten = [2, 5, 4, 3, 5, 1]

**Pseudocode:**

```text
FUNKTION filtereHohePrioritaeten(prioritaeten)
    auswahl = leere Liste

    FÜR i VON 0 BIS LÄNGE(prioritaeten) - 1
        WENN prioritaeten[i] >= 4 DANN
            ANHÄNGEN(auswahl, prioritaeten[i])
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: auswahl
ENDE FUNKTION
```

### a)
Geben Sie die zurückgegebene Liste vollständig an.  

**Lösung.**  
Die Bedingung `prioritaeten[i] >= 4` erfüllen die Werte `5`, `4` und `5`.

Rückgabewert:

    [5, 4, 5]

### b)
Erläutern Sie, ob der Algorithmus die ursprüngliche Reihenfolge beibehält und ob mehrfach vorhandene Werte mehrfach übernommen werden.  

**Lösung.**  
Die Reihenfolge wird beibehalten, weil der Algorithmus die Eingabeliste von links nach rechts durchläuft und passende Werte jeweils am Ende von `auswahl` anhängt.

Mehrfach vorkommende Werte werden mehrfach übernommen. Beide Vorkommen von `5` erfüllen die Bedingung und werden deshalb unabhängig voneinander angehängt. Eine Duplikatprüfung findet nicht statt.

### c)
Ändern Sie die Bedingung so, dass nur Tickets mit Priorität `5` übernommen werden. Geben Sie die neue Ergebnisliste an.  

**Lösung.**  
Angepasste Bedingung:

    WENN prioritaeten[i] == 5 DANN

Neue Ergebnisliste:  

    [5, 5]

## A5 - Prüfen auf Duplikate

Bei einem Datenimport sollen doppelt vorkommende Ticket-IDs erkannt werden. Die IDs können an beliebigen Positionen der Liste stehen.

**Eingabedaten:**

    ticketIds = ["K-17", "K-22", "K-19", "K-17", "K-22"]

**Pseudocode:**

```text
FUNKTION enthaeltDuplikate(ids)
    FÜR i VON 0 BIS LÄNGE(ids) - 2
        FÜR j VON i + 1 BIS LÄNGE(ids) - 1
            WENN ids[i] == ids[j] DANN
                ZURÜCKGEBEN: WAHR
            ENDE WENN
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: FALSCH
ENDE FUNKTION
```

### a)
Geben Sie den Rückgabewert des Algorithmus an. Nennen Sie das erste Indexpaar `(i, j)`, bei dem eine Übereinstimmung erkannt wird.  

**Lösung.**  

Der Algorithmus beginnt mit `i = 0`. Dabei wird die Ticket-ID "K-17" nacheinander mit den Elementen an den Indizes `1`, `2` und `3` verglichen.

Bei `i = 0` und `j = 3` gilt:

    ids[0] == ids[3]
    "K-17" == "K-17"

Erstes übereinstimmendes Indexpaar: `(0, 3)`.

Rückgabewert: `WAHR`. Durch `ZURÜCKGEBEN: WAHR` endet die Funktion sofort.


### b)
Nennen Sie alle Ticket-IDs, die in den Eingabedaten mehrfach vorkommen.  

**Lösung.**  

Die Eingabedaten enthalten zwei verschiedene IDs, die jeweils zweimal vorkommen:

- "K-17": Indizes `0` und `3`.
- "K-22": Indizes `1` und `4`.

Dies ist eine Auswertung der gesamten Eingabeliste. Der vorgegebene Algorithmus selbst ermittelt nicht alle Duplikate, sondern beendet sich bereits bei der ersten Übereinstimmung und gibt nur einen booleschen Wert zurück.

### c)
Begründen Sie, weshalb ein Vergleich ausschließlich benachbarter Listenelemente für diese Aufgabe nicht ausreicht.

**Lösung.**  

In einer unsortierten Liste können gleiche Werte an beliebigen, nicht benachbarten Positionen vorkommen. Ein ausschließlich benachbarter Vergleich würde etwa "K-17" an den Indizes `0` und `3` nicht erkennen.

Die verschachtelten Schleifen vergleichen daher jedes Element `ids[i]` mit den nachfolgenden Elementen `ids[j]`. So kann auch ein Duplikat an weiter entfernten Positionen erkannt werden.

## A6 - Gruppieren und Häufigkeiten ermitteln

Ein Servicedesk möchte ermitteln, wie oft Tickets aus den verschiedenen Kategorien auftreten. Die Anzahl soll jeweils unter dem Kategorienamen gespeichert werden.

**Eingabedaten:**

    kategorien = ["Netzwerk", "Software", "Netzwerk",
                "Hardware", "Software", "Netzwerk"]

**Pseudocode:**

```text
FUNKTION ermittleHaeufigkeiten(kategorien)
    haeufigkeiten = leere Map

    FÜR i VON 0 BIS LÄNGE(kategorien) - 1
        kategorie = kategorien[i]

        WENN ENTHÄLT_SCHLÜSSEL(haeufigkeiten, kategorie) DANN
            haeufigkeiten[kategorie] = haeufigkeiten[kategorie] + 1
        SONST
            haeufigkeiten[kategorie] = 1
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: haeufigkeiten
ENDE FUNKTION
```

### a)
Geben Sie für jede vorkommende Kategorie die ermittelte Häufigkeit an. Die Reihenfolge der Map-Einträge ist unerheblich.  

**Lösung.**  

Rückgabewert (Reihenfolge der Map-Einträge beliebig):

    {
        "Netzwerk": 3,
        "Software": 2,
        "Hardware": 1
    }

### b)
Geben Sie an, welcher zusätzliche Map-Eintrag entsteht, wenn am Ende der Eingabeliste die bislang nicht vorkommende Kategorie "Datenbank" ergänzt wird.  

**Lösung.**  

Wird "Datenbank" am Ende der Eingabeliste ergänzt, entsteht der zusätzliche Eintrag:

    "Datenbank": 1

Die bisherigen Häufigkeiten bleiben unverändert.

### c)
Erläutern Sie, warum für eine bisher unbekannte Kategorie die Häufigkeit mit `1` initialisiert wird.  

**Lösung.**  

Wenn eine Kategorie noch nicht als Schlüssel in der Map vorhanden ist, wurde sie zuvor noch nicht gezählt. Ihr aktuelles Auftreten ist ihr erstes Vorkommen. Deshalb wird direkt `1` gespeichert.

Beim nächsten Auftreten derselben Kategorie wird der vorhandene Zähler um `1` erhöht. Würde man beim ersten Auftreten `0` speichern, lägen die Häufigkeiten jeweils um eins zu niedrig.


## A7 - Lineare Suche (Linear Search)

In einer unsortierten Liste von Artikelnummern soll die Position des **ersten Vorkommens** einer gesuchten Artikelnummer ermittelt werden. Ist die Nummer nicht vorhanden, soll `-1` zurückgegeben werden.

**Eingabedaten:**

    artikelnummern = [501, 504, 502, 504, 509]
    gesucht = 504

**Pseudocode:**

```text
FUNKTION lineareSuche(nummern, gesucht)
    position = -1

    FÜR i VON 0 BIS LÄNGE(nummern) - 1
        WENN nummern[i] == gesucht DANN
            position = i
            ABBRUCH
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: position
ENDE FUNKTION
```

### a)
Geben Sie den Rückgabewert für `gesucht = 504` an.

***Lösung.**  

Die Liste lautet `[501, 504, 502, 504, 509]`.

- Index `0`: `501` ist nicht `504`.
- Index `1`: `504` stimmt mit dem Suchwert überein.

Rückgabewert: `1` (Index des ersten Vorkommens).

### b)
Geben Sie den Rückgabewert für `gesucht = 510` an und erläutern Sie seine Bedeutung.

***Lösung.**  

Der Wert `510` ist in der Liste nicht enthalten. Die Schleife durchläuft daher alle Elemente, ohne `position` zu verändern.

Rückgabewert: `-1`.

`-1` ist der im Algorithmus vereinbarte Kennwert für "nicht gefunden" und kein gültiger Listenindex.

### c)
Erläutern Sie die Wirkung von `ABBRUCH`, insbesondere wenn eine Artikelnummer mehrfach vorkommt.

***Lösung.**  

`ABBRUCH` beendet die `FÜR`-Schleife, sobald ein passender Wert gefunden wurde. Weil die Liste mit aufsteigenden Indizes durchlaufen wird, bleibt damit die Position des **ersten Vorkommens** gespeichert.

Der zweite Wert `504` an Index `3` wird nicht mehr untersucht. Ohne `ABBRUCH` würde der vorliegende Algorithmus `position` beim späteren Vorkommen erneut überschreiben und hier `3` zurückgeben.

## A8 - Vergleichen aufeinanderfolgender Elemente

Ein Sensor übermittelt stündlich Messwerte in zeitlicher Reihenfolge. Ein Algorithmus soll zählen, wie oft ein Messwert gegenüber dem **unmittelbar vorherigen** Messwert gesunken ist. Gleiche Werte zählen nicht als Rückgang.

**Eingabedaten:**

    messwerte = [12, 16, 15, 15, 19, 14]

**Pseudocode:**

```text
FUNKTION zaehleRueckgaenge(messwerte)
    rueckgaenge = 0

    FÜR i VON 1 BIS LÄNGE(messwerte) - 1
        WENN messwerte[i] < messwerte[i - 1] DANN
            rueckgaenge = rueckgaenge + 1
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: rueckgaenge
ENDE FUNKTION
```

### a)
Ermitteln Sie den Rückgabewert für die angegebenen Messwerte.  

**Lösung.**  

Die Messwerte sind `[12, 16, 15, 15, 19, 14]`.

| Index `i` | Vorheriger Wert `messwerte[i - 1]` | Aktueller Wert `messwerte[i]` | Rückgang? |
| --- | --- | --- | --- |
| 1 | 12 | 16 | Nein |
| 2 | 16 | 15 | Ja |
| 3 | 15 | 15 | Nein |
| 4 | 15 | 19 | Nein |
| 5 | 19 | 14 | Ja |

Rückgabewert: `2`.

### b)
Nennen Sie die beiden aufeinanderfolgenden Wertepaare, bei denen ein Rückgang erkannt wird.  

**Lösung.**  

- `16 -> 15` (Indizes `1` und `2`)
- `19 -> 14` (Indizes `4` und `5`)

### c)
Ändern Sie die Bedingung so, dass **jede Veränderung** gegenüber dem unmittelbar vorherigen Wert gezählt wird, also sowohl Anstiege als auch Rückgänge, jedoch keine gleichen Werte. Geben Sie den neuen Rückgabewert an.  

**Lösung.**  

Die Bedingung muss sowohl Anstiege als auch Rückgänge erfassen und gleiche Werte ausschließen.

Angepasste Bedingung:  

```text
WENN messwerte[i] != messwerte[i - 1] DANN
```

Eine gleichwertige Alternative ist:

```text
WENN messwerte[i] < messwerte[i - 1] ODER
     messwerte[i] > messwerte[i - 1] DANN
```

Die Änderungen liegen bei `12 -> 16`, `16 -> 15`, `15 -> 19` und `19 -> 14` vor. `15 -> 15` ist keine Veränderung.

Neuer Rückgabewert: `4`.

