# Aufgabe - Algorithmen - Insertion Sort

## Ausgangssituation

Eine Anwendung zur Auswertung von Temperaturmessungen soll ganzzahlige Messwerte aufsteigend sortieren. Dazu steht der folgende Algorithmus Insertion Sort zur Verfügung.

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- `LÄNGE(werte)` liefert die Anzahl der Elemente des Arrays.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- Das übergebene Array wird direkt verändert. Die Funktion liefert keinen Rückgabewert.
- Das Array darf leer sein.
- `UND` wird von links nach rechts mit Kurzschlussauswertung ausgewertet: Ist die erste Bedingung falsch, wird die zweite nicht geprüft.

**Pseudocode:**

```text
FUNKTION insertionSort(werte : Ganzzahl[]) : void
    n = LÄNGE(werte)

    FÜR i VON 1 BIS n - 1
        temp = werte[i]
        j = i - 1

        SOLANGE j >= 0 UND werte[j] > temp
            werte[j + 1] = werte[j]
            j = j - 1
        ENDE SOLANGE

        werte[j + 1] = temp
    ENDE FÜR
ENDE FUNKTION
```

## A1 - Algorithmus erläutern

Erläutern Sie kurz die Funktionsweise des gegebenen Algorithmus. Gehen Sie dabei auf folgende Aspekte ein:

- Aufgabe der äußeren und der inneren Schleife
- Bedeutung der Variablen `temp` und `j`
- Verschieben und anschließendes Einfügen des zwischengespeicherten Elements
- Sortierreihenfolge und Bedeutung des bereits sortierten linken Bereichs
- Grund für die Reihenfolge der Teilbedingungen in der SOLANGE-Schleife

## A2 - Algorithmus nachvollziehen

### a)

Verfolgen Sie die Ausführung des gegebenen Algorithmus für das folgende Array.

**Eingabe:**

```text
werte = [7, 3, 5, 2, 4]
```

Geben Sie nach jedem vollständigen Durchlauf der äußeren Schleife den Wert von `temp`, den Einfügeindex `j + 1`, den Zustand des Arrays und die Anzahl der Verschiebungen an.

Als Verschiebung zählt ausschließlich die Ausführung von `werte[j + 1] = werte[j]`. Das abschließende Einfügen von `temp` zählt nicht als Verschiebung.

| Durchlauf | i | temp | Einfügeindex | Zustand des Arrays | Verschiebungen |
|---|---:|---:|---:|---|---:|
| Ausgangszustand | - | - | - | [7, 3, 5, 2, 4] | - |
| 1 | 1 | | | | |
| 2 | 2 | | | | |
| 3 | 3 | | | | |
| 4 | 4 | | | | |

### b)

Ermitteln Sie für die folgenden Eingaben jeweils den Endzustand des Arrays und die Gesamtanzahl der Verschiebungen. Jede Eingabe wird unabhängig von den anderen verarbeitet.

| Eingabe | Endzustand | Verschiebungen |
|---|---|---:|
| [1, 2, 3, 4] | | |
| [4, 3, 2, 1] | | |
| [3, -2, 3, 0] | | |
| [5] | | |
| [] | | |

## A3 - Algorithmus in Java implementieren

Die Anwendung verwendet nun eine Liste vom Typ `java.util.List<Integer>` anstelle eines Arrays.

Implementieren Sie die Java-Methode `insertionSort`, indem Sie den gegebenen Pseudocode auf die Verarbeitung einer Liste übertragen.

**Methodenkopf:**

```java
public static void insertionSort(List<Integer> werte)
```

**Vorgaben:**

- Die übergebene Liste soll aufsteigend sortiert und direkt verändert werden.
- Die Liste ist nicht `null`, enthält keine `null`-Elemente und unterstützt das Ersetzen von Elementen.
- Leere Listen und Listen mit nur einem Element sollen unverändert bleiben.
- Verwenden Sie die beiden verschachtelten Schleifen des gegebenen Algorithmus und verschieben Sie größere Elemente nach rechts. Fügen Sie anschließend den zwischengespeicherten Wert ein.
- Vorgefertigte Sortiermethoden wie `Collections.sort()` oder `List.sort()` sowie das Umwandeln der Liste in ein Array sind nicht zulässig.
- Erstellen Sie die Methode zunächst schriftlich und überprüfen Sie anschließend Ihre Implementierung in Java mit den Eingabedaten aus A2.

Für die Verarbeitung der Liste stehen unter anderem folgende Methoden zur Verfügung:

| Methode | Beschreibung |
|---|---|
| `size()` | Liefert die Anzahl der Elemente. |
| `get(index)` | Liefert das Element am angegebenen Index. |
| `set(index, element)` | Ersetzt das Element am angegebenen Index durch das übergebene Element. |

## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus schreibt `temp` auch dann in das Array zurück, wenn keine Verschiebung stattgefunden hat.

### a)

Erweitern Sie den Pseudocode so, dass dieser unnötige Schreibzugriff entfällt. Die übrigen Schritte bleiben unverändert.

### b)

Erläutern Sie, warum ein Durchlauf ohne Verschiebung keinen vorzeitigen Abbruch der äußeren Schleife rechtfertigt. Verwenden Sie das Array `[1, 3, 2]`.

### c)

Vergleichen Sie für das bereits aufsteigend sortierte Array `[1, 2, 3, 4, 5]` die Anzahl der Elementvergleiche und der Schreibzugriffe auf das Array beim ursprünglichen und beim optimierten Algorithmus.

Zählen Sie nur Auswertungen von `werte[j] > temp` und Zuweisungen an Arrayelemente, nicht Indexprüfungen oder Zuweisungen an Hilfsvariablen.
