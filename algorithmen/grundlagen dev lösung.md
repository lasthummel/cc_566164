# Aufgabe 'Algorithmen - Grundlegende Verarbeitung'

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