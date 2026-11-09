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
