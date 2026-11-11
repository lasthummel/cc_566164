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

### b)
Erläutern Sie, weshalb der Wert `30` nicht mitgezählt wird.

### c)
Ändern Sie die Bedingung im Pseudocode so, dass Fälle mit einer Bearbeitungsdauer von **mindestens 30 Minuten** gezählt werden. Geben Sie auch den neuen Rückgabewert an.

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

### b)
Berechnen Sie den Durchschnitt und geben Sie den vollständigen Rückgabewert an.

### c)
Erläutern Sie, welchen Rückgabewert der Algorithmus bei einer leeren Liste liefert und warum die Abfrage der Listenlänge notwendig ist.

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

### b)
Begründen Sie, weshalb Minimum und Maximum mit dem ersten Listenelement und nicht pauschal mit `0` initialisiert werden.  

### c)
Geben Sie die Rückgabewerte für die Eingaben `[7]` und `[]` an.  


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

    GIB auswahl ZURÜCK
ENDE FUNKTION
```

### a)
Geben Sie die zurückgegebene Liste vollständig an.  

### b)
Erläutern Sie, ob der Algorithmus die ursprüngliche Reihenfolge beibehält und ob mehrfach vorhandene Werte mehrfach übernommen werden.  

### c)
Ändern Sie die Bedingung so, dass nur Tickets mit Priorität `5` übernommen werden. Geben Sie die neue Ergebnisliste an.  


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

### b)
Nennen Sie alle Ticket-IDs, die in den Eingabedaten mehrfach vorkommen.  

### c)
Begründen Sie, weshalb ein Vergleich ausschließlich benachbarter Listenelemente für diese Aufgabe nicht ausreicht.


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

### b)
Geben Sie an, welcher zusätzliche Map-Eintrag entsteht, wenn am Ende der Eingabeliste die bislang nicht vorkommende Kategorie "Datenbank" ergänzt wird.  

### c)
Erläutern Sie, warum für eine bisher unbekannte Kategorie die Häufigkeit mit `1` initialisiert wird.  


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

### b)
Geben Sie den Rückgabewert für `gesucht = 510` an und erläutern Sie seine Bedeutung.

### c)
Erläutern Sie die Wirkung von `ABBRUCH`, insbesondere wenn eine Artikelnummer mehrfach vorkommt.


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

### b)
Nennen Sie die beiden aufeinanderfolgenden Wertepaare, bei denen ein Rückgang erkannt wird.  

### c)
Ändern Sie die Bedingung so, dass **jede Veränderung** gegenüber dem unmittelbar vorherigen Wert gezählt wird, also sowohl Anstiege als auch Rückgänge, jedoch keine gleichen Werte. Geben Sie den neuen Rückgabewert an.  
