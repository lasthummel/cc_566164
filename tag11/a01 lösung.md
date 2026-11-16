# Algorithmische Problemlösung und Pseudocode

**Hinweise**
- Bearbeiten Sie die Aufgaben zunächst ohne IDE.
- Schreiben Sie bei jeder Aufgabe Eingaben, Ausgabe und relevante Randfälle auf.
- Pseudocode muss eindeutig und nachvollziehbar sein; eine bestimmte Syntax ist nicht vorgeschrieben.

## A1 – Textaufgabe analysieren

Ein Warenwirtschaftssystem erhält eine Liste von Bestellwerten. Gesucht ist die Anzahl der Bestellungen, deren Wert **mindestens 100,00 €** beträgt.

1. Nennen Sie Eingabe und Ausgabe.
2. Formulieren Sie die fachliche Bedingung eindeutig
3. Entwickeln Sie Pseudocode
4. Nennen Sie einen sinnvollen Randfall und das erwartete Ergebnis.

**Lösung**  

1. **Eingabe:** Liste von Bestellwerten. **Ausgabe:** Anzahl der Bestellungen mit Wert mindestens 100,00 €.
2. Bedingung: `bestellwert >= 100.00`; "mindestens" schließt 100,00 € ein.
3. 
```text
anzahl = 0
FÜR JEDEN bestellwert IN bestellwerte
    WENN bestellwert >= 100.00 DANN
        anzahl = anzahl + 1
    ENDE WENN
ENDE FÜR
AUSGABE anzahl
```
4. Leere Liste → Ausgabe 0. Alternativ: Liste mit genau 100,00 € → Ausgabe 1.

## A2 – Minimum und lineare Suche

Gegeben sind die Messwerte: `[-8, -3, -11, -3, -5]`.

1. Bestimmen das Minimum und begründen die Initialisierung Ihrer Minimum-Variablen.
2. Formulieren Sie Pseudocode, der den Index des ersten Vorkommens des Wertes `-3` liefert. Wenn der Wert fehlt, soll `-1` ausgegeben werden.
3. Nennen Sie einen Testfall, der einen Fehler bei der Initialisierung des Minimums mit 0 sichtbar macht.

**Lösung**  

1. Minimum ist `-11`. Bei nichtleerer Liste wird `minimum` mit dem ersten vorhandenen Wert initialisiert, hier -8. Danach werden die Werte verglichen. Eine Initialisierung mit 0 wäre fachlich ungeeignet, da 0 nicht in der Liste vorkommt und kleiner als alle tatsächlichen Werte ist.

2.
```text
gesuchterWert = -3
index = -1
FÜR i VON 0 BIS laenge(messwerte) - 1
    WENN messwerte[i] == gesuchterWert DANN
        index = i
        ABBRUCH DER SCHLEIFE
    ENDE WENN
ENDE FÜR
AUSGABE index
```
Ergebnis: Index 1. Der Abbruch ist korrekt, weil der erste Treffer gesucht wird. Bei einer leeren Liste wird die Schleife nicht ausgeführt und -1 ausgegeben.

3. Beispielsweise `[8, 3, 11]`: Wird das Minimum mit 0 initialisiert und nur bei kleineren Werten aktualisiert, bleibt 0 fälschlich Minimum.


## A3 – Filterung und Aggregation

Ein Gerät erfasst Messwerte. Berechnet werden soll der Durchschnitt aller positiven Werte; Werte kleiner oder gleich 0 werden ignoriert.

1. Formulieren Sie Pseudocode, der Summe und Anzahl der berücksichtigten Werte korrekt ermittelt.
2. Ergänzen Sie die Berechnung des Durchschnitts und eine Behandlung für den Fall, dass kein positiver Wert vorhanden ist.
3. Bestimmen Sie das Ergebnis für `[4, 0, -2, 6, 10]`.
4. Erkläre, warum ein Durchschnitt bei null berücksichtigten Werten nicht einfach berechnet werden kann.

**Lösung**  

1–2.
```text
summe = 0
anzahl = 0
FÜR JEDEN wert IN messwerte
    WENN wert > 0 DANN
        summe = summe + wert
        anzahl = anzahl + 1
    ENDE WENN
ENDE FÜR

WENN anzahl > 0 DANN
    durchschnitt = summe / anzahl
    AUSGABE durchschnitt
SONST
    AUSGABE "Kein positiver Messwert vorhanden"
ENDE WENN
```
3. Berücksichtigt werden 4, 6 und 10. Summe = 20, Anzahl = 3, Durchschnitt = **20 / 3 ≈ 6,67**.
4. Bei Anzahl 0 wäre der Nenner 0; außerdem ist der Durchschnitt einer leeren Auswahl mathematisch nicht definiert.

**Implementierungshinweis:** In Java muss für ein Dezimalergebnis Ganzzahldivision vermieden werden, z. B. durch `(double) summe / anzahl`, sofern `summe` und `anzahl` ganzzahlig sind.


## A4 – Fehlerdiagnose

Eine Methode soll zählen, wie viele Werte mindestens 100 sind:

```text
anzahl = 1
FÜR JEDEN wert IN werte
    WENN wert > 100 DANN
        anzahl = anzahl + 1
    ENDE WENN
ENDE FÜR
AUSGABE anzahl
```

Identifizieren Sie zwei voneinander unabhängige Fehler und beschreiben Sie jeweils die Korrektur. Begründen Sie, welche Testdaten die Fehler sichtbar machen.

**Lösung**  

Zwei unabhängige Fehler:
1. `anzahl = 1` zählt bei leerer Liste bereits ein Element. Korrektur: `anzahl = 0`.
2. `wert > 100` zählt Werte größer als 100, aber nicht genau 100. "Mindestens 100" bedeutet `wert >= 100`.

Tests:
- Leere Liste muss 0 liefern und macht den Initialisierungsfehler sichtbar.
- Liste mit einem Elemen `[100]` muss 1 liefern und macht den Grenzfehler sichtbar.
