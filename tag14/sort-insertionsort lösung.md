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

**Lösung**  

Insertion Sort sortiert aufsteigend, indem jedes weitere Element in den bereits sortierten linken Teilbereich eingefügt wird. Das erste Element bildet zunächst allein einen sortierten Bereich.

Die äußere Schleife beginnt bei Index 1. Sie speichert das aktuelle Element in `temp`, damit es beim Überschreiben seiner ursprünglichen Position nicht verloren geht. `j` beginnt links davon und wandert nach links. Solange das betrachtete Element größer als `temp` ist, wird es um eine Position nach rechts verschoben. Anschließend wird `temp` an Position `j + 1` eingefügt.

Nach jedem vollständigen Durchlauf ist der Bereich von Index 0 bis i sortiert. Seine Elemente befinden sich aber nicht zwingend an ihren endgültigen Positionen. Später können kleinere Elemente eingefügt werden.

Die Indexprüfung `j >= 0` muss vor dem Arrayzugriff stattfinden. Durch Kurzschlussauswertung wird bei j = -1 nicht auf das Array zugegriffen. Gleiche Werte werden wegen des strikten Vergleichs nicht aneinander vorbeigeschoben; das Verfahren ist stabil.

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

**Lösung**  

### a)

| Durchlauf | i | temp | Einfügeindex | Zustand des Arrays | Verschiebungen |
|---|---:|---:|---:|---|---:|
| Ausgangszustand | - | - | - | [7, 3, 5, 2, 4] | - |
| 1 | 1 | 3 | 0 | [3, 7, 5, 2, 4] | 1 |
| 2 | 2 | 5 | 1 | [3, 5, 7, 2, 4] | 1 |
| 3 | 3 | 2 | 0 | [2, 3, 5, 7, 4] | 3 |
| 4 | 4 | 4 | 2 | [2, 3, 4, 5, 7] | 2 |

Insgesamt werden 7 Verschiebungen, 9 Elementvergleiche und 11 Schreibzugriffe ausgeführt.

### b)

| Eingabe | Endzustand | Verschiebungen |
|---|---|---:|
| [1, 2, 3, 4] | [1, 2, 3, 4] | 0 |
| [4, 3, 2, 1] | [1, 2, 3, 4] | 6 |
| [3, -2, 3, 0] | [-2, 0, 3, 3] | 3 |
| [5] | [5] | 0 |
| [] | [] | 0 |

Leere Arrays und Arrays mit nur einem Element lösen keinen Schleifendurchlauf aus.

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

**Lösung**  

```java
import java.util.ArrayList;
import java.util.List;

public class InsertionSortDemo {

    public static void insertionSort(List<Integer> werte) {
        int n = werte.size();

        for (int i = 1; i < n; i++) {
            int temp = werte.get(i);
            int j = i - 1;

            while (j >= 0 && werte.get(j) > temp) {
                werte.set(j + 1, werte.get(j));
                j--;
            }

            werte.set(j + 1, temp);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> testdaten = List.of(
            List.of(7, 3, 5, 2, 4),
            List.of(1, 2, 3, 4),
            List.of(4, 3, 2, 1),
            List.of(3, -2, 3, 0),
            List.of(5),
            List.<Integer>of()
        );

        for (List<Integer> eingabe : testdaten) {
            List<Integer> werte = new ArrayList<>(eingabe);
            insertionSort(werte);
            System.out.println(eingabe + " -> " + werte);
        }
    }
}
```

Die Klasse kann als `InsertionSortDemo.java` in VS Code ausgeführt werden.

**Erwartete Ausgabe:**

```text
[7, 3, 5, 2, 4] -> [2, 3, 4, 5, 7]
[1, 2, 3, 4] -> [1, 2, 3, 4]
[4, 3, 2, 1] -> [1, 2, 3, 4]
[3, -2, 3, 0] -> [-2, 0, 3, 3]
[5] -> [5]
[] -> []
```

`get` liefert ein `Integer`, das beim numerischen Vergleich automatisch entpackt wird (Unboxing). `set` ersetzt ein Element; `temp` wird automatisch verpackt (Boxing). Die Testdaten werden in veränderbare `ArrayList`-Instanzen kopiert, da `List.of()` keine Ersetzung erlaubt.

## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus schreibt `temp` auch dann in das Array zurück, wenn keine Verschiebung stattgefunden hat.

### a)

Erweitern Sie den Pseudocode so, dass dieser unnötige Schreibzugriff entfällt. Die übrigen Schritte bleiben unverändert.

### b)

Erläutern Sie, warum ein Durchlauf ohne Verschiebung keinen vorzeitigen Abbruch der äußeren Schleife rechtfertigt. Verwenden Sie das Array `[1, 3, 2]`.

### c)

Vergleichen Sie für das bereits aufsteigend sortierte Array `[1, 2, 3, 4, 5]` die Anzahl der Elementvergleiche und der Schreibzugriffe auf das Array beim ursprünglichen und beim optimierten Algorithmus.

Zählen Sie nur Auswertungen von `werte[j] > temp` und Zuweisungen an Arrayelemente, nicht Indexprüfungen oder Zuweisungen an Hilfsvariablen.

**Lösung**  

### a)

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

        WENN j + 1 != i DANN
            werte[j + 1] = temp
        ENDE WENN
    ENDE FÜR
ENDE FUNKTION
```

### b)

Ohne Verschiebung ist lediglich das aktuelle Element bereits passend zum sortierten linken Bereich angeordnet. Über den restlichen Bereich ist nichts bekannt. Bei [1, 3, 2] bleibt im ersten Durchlauf die 3 stehen; erst im nächsten Durchlauf wird die 2 vor die 3 eingefügt.

### c)

| Variante | Elementvergleiche | Schreibzugriffe |
|---|---:|---:|
| Ursprünglich | 4 | 4 |
| Optimiert | 4 | 0 |

**Vertiefung - Laufzeitkomplexität:**

| Fall | Ursprünglich | Optimiert |
|---|---|---|
| Best Case | Θ(n) | Θ(n) |
| Average Case (gleichverteilte Permutationen verschiedener Werte) | Θ(n²) | Θ(n²) |
| Worst Case | Θ(n²) | Θ(n²) |

Insertion Sort ist adaptiv: Je weniger Elemente verschoben werden müssen, desto geringer ist im Allgemeinen der Aufwand. Beide Varianten benötigen Θ(1) zusätzlichen Speicher bei Arrays. Die Laufzeitangaben gelten auch für Listen mit konstantem Zeitaufwand für indexbasierte Zugriffe (z. B. ArrayList), nicht grundsätzlich für LinkedList. Die Optimierung spart Schreibzugriffe, ändert aber die asymptotische Laufzeit nicht und fügt eine Indexprüfung pro Durchlauf hinzu.
