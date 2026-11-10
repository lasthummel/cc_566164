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

Entwickeln Sie mit Pseudocode die Funktion `zaehleLangeFaelle(dauer: Ganzzahl[]): Ganzzahl`. Die Funktion soll die Anzahl der Elemente des Array ermitteln, die den Wert > 30 haben. Die Funktion liefert die Anzahl zurück.

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

## A2 - Min und Max

Entwickeln Sie mit Pseudocode die Funktion `findeMinMax`, die als Parameter ein Array mit Ganzzahlen bekommt, den kleinesten und den größten Wert ermittelt und die ermittelten Werte zurück liefert.

**Lösung.**  

```text
FUNKTION findeMinMax(werte)

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


## A3 - Filtern von Arrays und Listen

Entwickeln Sie mit Pseudocode eine Funktion `filtereWerte`, die als Parameter eine Liste mit Ganzzahlen und einen Grenzwert bekommt. Die Funktion findet in der Liste alle Werte, die größer oder gleich dem Grenzwert sind, und liefert sie in einer neuen Liste zurück.  

**Lösung.**

```text
FUNKTION filtereWerte(werte, grenze)
    auswahl = leere Liste

    FÜR i VON 0 BIS LÄNGE(werte) - 1
        WENN werte[i] >= grenze DANN
            ANHÄNGEN(auswahl, werte[i])
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: auswahl
ENDE FUNKTION
```


## A4 - Prüfen auf Duplikate

Entwickeln Sie mit Pseudocode eine Funktion `enthaeltDuplikate(werte : Ganzzahl[]) : Boolean`, die überprüft, ob das übergebe Array Duplikate beinhaltet. Wenn es im Array Duplikate gibt, liefert die Funktion WAHR zurück, ansonsten FALSCH.

**Lösung.**  

```text
FUNKTION enthaeltDuplikate(werte : Ganzzahl[])
    FÜR i VON 0 BIS LÄNGE(werte) - 2
        FÜR j VON i + 1 BIS LÄNGE(werte) - 1
            WENN werte[i] == werte[j] DANN
                ZURÜCKGEBEN: WAHR
            ENDE WENN
        ENDE FÜR
    ENDE FÜR

    ZURÜCKGEBEN: FALSCH
ENDE FUNKTION
```

## A5 - Gruppieren und Häufigkeiten ermitteln

Entwickeln Sie mit Pseudocode eine Funktion `ermittleHaeufigkeiten`, die ein Array mit Strings als Parameter bekommt. Die Funktion ermittelt zu jedem String, wie oft der String in dem Array vorkommt, und liefert die ermittelten Informationen zu Häufigkeiten aller Strings aus dem Array in einer Map zurück. Die Map hat als Schlüßel die Strings und als Werte die Häufigkeiten.

**Lösung.**

```text
FUNKTION ermittleHaeufigkeiten(strings)
    haeufigkeiten = leere Map

    FÜR i VON 0 BIS LÄNGE(strings) - 1
        str = strings[i]

        WENN ENTHÄLT_SCHLÜSSEL(haeufigkeiten, str) DANN
            haeufigkeiten[str] = haeufigkeiten[str] + 1
        SONST
            haeufigkeiten[str] = 1
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: haeufigkeiten
ENDE FUNKTION
```

## A6 - Lineare Suche (Linear Search)

In einer unsortierten Liste von Ganzzahlen soll die Position des ersten Vorkommens einer gesuchten Zahl ermittelt werden. Ist die gesuchte Zahl nicht vorhanden, soll -1 zurückgegeben werden. Entwickeln Sie dafür mit Pseudocode die Funktion `lineareSuche`, die die Liste von Anfang durchsucht, bis sie die gesuchte Zahl gefunden hat oder bis das Ende der Liste erreicht wurde.

**Lösung.**


```text
FUNKTION lineareSuche(zahlen, gesucht)
    position = -1

    FÜR i VON 0 BIS LÄNGE(zahlen) - 1
        WENN zahlen[i] == gesucht DANN
            position = i
            ABBRUCH
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: position
ENDE FUNKTION
```
