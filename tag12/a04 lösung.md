# Aufgabe – Fallstudie

Anforderung: Für eine übergebene Liste ganzzahliger Messwerte ist die Anzahl aller Werte auszugeben, die größer oder gleich 50 sind. Zusätzlich ist die Summe dieser Werte auszugeben. Wenn kein Wert die Bedingung erfüllt, müssen Anzahl und Summe jeweils 0 sein.

## A1. 
Nennen Sie zwei geeignete Testfälle mit Eingabe und erwarteten Ergebnissen. Einer muss ein Randfall sein.

**Lösung**  

Beispielsweise:

- Eingabe `[12, 50, 61]` -> Anzahl `2`, Summe `111`.
- Randfall leere Liste `[]` -> Anzahl `0`, Summe `0`.

Ebenfalls passend: `[10, 20]` -> Anzahl `0`, Summe `0`; oder ein einzelner Wert 50, der berücksichtigt werden muss.

## A2. 
Ordnen Sie den isolierten Test der Auswertungsmethode einer Teststufe zu und begründen Sie die Zuordnung.

**Lösung**  
Unit-/Modultest, wenn die Auswertungsmethode isoliert geprüft wird.

## A3. 
Beschreiben Sie einen Black-Box-Test und einen White-Box-orientierten Test.

**Lösung**  
**Black-Box:** Fälle aus der Anforderung ableiten, etwa Werte unter 50, genau 50 und über 50.  
**White-Box:** Eingaben so wählen, dass der Zweig `wert >= 50` und der alternative Zweig ausgeführt werden.

## A4. 
Nach einer Korrektur werden bisherige Testfälle erneut ausgeführt. Benennen und erklären Sie den Zweck.

**Lösung**  
Regressionstest: Vorhandene Testfälle nach der Korrektur erneut ausführen, um zu prüfen, ob bisher korrektes Verhalten erhalten blieb.

## A5. 
Nennen Sie eine sinnvolle nichtfunktionale Anforderung und formulieren Sie sie möglichst überprüfbar.

**Lösung**  
Beispiel: "Die Auswertung von 100.000 Messwerten muss unter einer festgelegten Testumgebung in höchstens zwei Sekunden abgeschlossen sein." Die konkrete Schwelle ist eine Beispielanforderung und muss im Projekt vereinbart werden.
