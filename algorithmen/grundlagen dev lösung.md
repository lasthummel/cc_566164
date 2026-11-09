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

## A3 - Min und Max

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


---------------  
weitere Lösungen folgen...