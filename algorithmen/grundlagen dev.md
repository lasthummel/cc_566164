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

## A2 - Min und Max

Entwickeln Sie mit Pseudocode die Funktion `findeMinMax`, die als Parameter ein Array mit Ganzzahlen bekommt, den kleinesten und den größten Wert ermittelt und die ermittelten Werte zurück liefert.


## A3 - Filtern von Arrays und Listen

Entwickeln Sie mit Pseudocode eine Funktion `filtereWerte`, die als Parameter eine Liste mit Ganzzahlen und einen Grenzwert bekommt. Die Funktion findet in der Liste alle Werte, die größer oder gleich dem Grenzwert sind, und liefert sie in einer neuen Liste zurück.  

## A4 - Prüfen auf Duplikate

Entwickeln Sie mit Pseudocode eine Funktion `enthaeltDuplikate(werte : Ganzzahl[]) : Boolean`, die überprüft, ob das übergebe Array Duplikate beinhaltet. Wenn es im Array Duplikate gibt, liefert die Funktion WAHR zurück, ansonsten FALSCH.

## A5 - Gruppieren und Häufigkeiten ermitteln

Entwickeln Sie mit Pseudocode eine Funktion `ermittleHaeufigkeiten`, die ein Array mit Strings als Parameter bekommt. Die Funktion ermittelt zu jedem String, wie oft der String in dem Array vorkommt, und liefert die ermittelten Informationen zu Häufigkeiten aller Strings aus dem Array in einer Map zurück. Die Map hat als Schlüßel die Strings und als Werte die Häufigkeiten.

## A6 - Lineare Suche (Linear Search)

In einer unsortierten Liste von Ganzzahlen soll die Position des ersten Vorkommens einer gesuchten Zahl ermittelt werden. Ist die gesuchte Zahl nicht vorhanden, soll -1 zurückgegeben werden. Entwickeln Sie dafür mit Pseudocode die Funktion `lineareSuche`, die die Liste von Anfang durchsucht, bis sie die gesuchte Zahl gefunden hat oder bis das Ende der Liste erreicht wurde.
