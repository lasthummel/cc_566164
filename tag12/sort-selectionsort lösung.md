# Aufgabe - Algorithmen - Selection Sort

## Ausgangssituation

Eine Anwendung zur Auswertung von Temperaturmessungen soll ganzzahlige Messwerte aufsteigend sortieren. Dazu steht der folgende Algorithmus Selection Sort zur Verfügung.

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- `LÄNGE(werte)` liefert die Anzahl der Elemente des Arrays.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- Das übergebene Array wird direkt verändert. Die Funktion liefert keinen Rückgabewert.
- Das Array darf leer sein.

**Pseudocode:**

```text
FUNKTION selectionSort(werte : Ganzzahl[]) : void
    n = LÄNGE(werte)

    FÜR i VON 0 BIS n - 2
        minIndex = i

        FÜR j VON i + 1 BIS n - 1
            WENN werte[j] < werte[minIndex] DANN
                minIndex = j
            ENDE WENN
        ENDE FÜR

        temp = werte[i]
        werte[i] = werte[minIndex]
        werte[minIndex] = temp
    ENDE FÜR
ENDE FUNKTION
```

## A1 - Algorithmus erläutern

Erläutern Sie kurz die Funktionsweise des gegebenen Algorithmus. Gehen Sie dabei auf folgende Aspekte ein:

- Aufgabe der äußeren und der inneren Schleife
- Bedeutung und Aktualisierung von `minIndex`
- Zeitpunkt und Durchführung der Vertauschung mithilfe von `temp`
- Sortierreihenfolge
- Grund für die Verkürzung des Vergleichsbereichs in der inneren Schleife

**Lösung**  

Selection Sort sortiert das Array aufsteigend, indem in jedem Durchlauf das kleinste Element des noch nicht fest eingeordneten Bereichs gesucht und an dessen erste Position gebracht wird.

Die äußere Schleife legt mit `i` die nächste endgültige Position fest. Die innere Schleife durchsucht den verbleibenden Bereich ab `i + 1`. `minIndex` wird zu Beginn jedes Durchlaufs auf `i` gesetzt. Wird ein kleinerer Wert gefunden, wird dessen Index gespeichert. Bei gleichen Werten bleibt der bisherige Index erhalten.

Erst nach Abschluss der inneren Schleife werden die Elemente an den Positionen `i` und `minIndex` mithilfe von `temp` vertauscht. Die Hilfsvariable verhindert den Verlust eines Werts. Nach jedem Durchlauf ist ein weiteres Element links endgültig eingeordnet; der Suchbereich wird kleiner.


## A2 - Algorithmus nachvollziehen

### a)

Verfolgen Sie die Ausführung des gegebenen Algorithmus für das folgende Array.

**Eingabe:**

```text
werte = [7, 3, 5, 2, 4]
```

Geben Sie den Zustand des Arrays nach jedem vollständigen Durchlauf der äußeren Schleife sowie den ermittelten `minIndex` unmittelbar vor der Tauschoperation und die Anzahl der ausgeführten Tauschblöcke an. Ein Tauschblock zählt auch dann als eine Operation, wenn `minIndex == i` gilt.

| Durchlauf | i | minIndex | Zustand des Arrays | Anzahl der Tauschblöcke |
|---|---:|---:|---|---:|
| Ausgangszustand | - | - | [7, 3, 5, 2, 4] | - |
| 1 | 0 | | | |
| 2 | 1 | | | |
| 3 | 2 | | | |
| 4 | 3 | | | |

### b)

Ermitteln Sie für die folgenden Eingaben jeweils den Endzustand des Arrays und die Gesamtanzahl der Vertauschungen. Jede Eingabe wird unabhängig von den anderen verarbeitet.

| Eingabe | Endzustand | Anzahl der Tauschblöcke |
|---|---|---:|
| [1, 2, 3, 4] | | |
| [4, 3, 2, 1] | | |
| [3, -2, 3, 0] | | |
| [5] | | |
| [] | | |

**Lösung**  

### a)

| Durchlauf | i | minIndex | Zustand des Arrays | Anzahl der Tauschblöcke |
|---|---:|---:|---|---:|
| Ausgangszustand | - | - | [7, 3, 5, 2, 4] | - |
| 1 | 0 | 3 | [2, 3, 5, 7, 4] | 1 |
| 2 | 1 | 1 | [2, 3, 5, 7, 4] | 1 |
| 3 | 2 | 4 | [2, 3, 4, 7, 5] | 1 |
| 4 | 3 | 4 | [2, 3, 4, 5, 7] | 1 |

Insgesamt werden zehn Elementvergleiche und vier Tauschblöcke ausgeführt. Nur drei davon vertauschen unterschiedliche Positionen.

### b)

| Eingabe | Endzustand | Anzahl der Tauschblöcke |
|---|---|---:|
| [1, 2, 3, 4] | [1, 2, 3, 4] | 3 |
| [4, 3, 2, 1] | [1, 2, 3, 4] | 3 |
| [3, -2, 3, 0] | [-2, 0, 3, 3] | 3 |
| [5] | [5] | 0 |
| [] | [] | 0 |

Bei einem leeren Array oder einem Array mit nur einem Element wird die äußere Schleife nicht ausgeführt.


## A3 - Algorithmus in Java implementieren

Die Anwendung verwendet nun eine Liste vom Typ `java.util.List<Integer>` anstelle eines Arrays.

Implementieren Sie die Java-Methode `selectionSort`, indem Sie den gegebenen Pseudocode auf die Verarbeitung einer Liste übertragen.

**Methodenkopf:**

```java
public static void selectionSort(List<Integer> werte)
```

**Vorgaben:**

- Die übergebene Liste soll aufsteigend sortiert und direkt verändert werden.
- Die Liste ist nicht `null`, enthält keine `null`-Elemente und unterstützt das Ersetzen von Elementen.
- Leere Listen und Listen mit nur einem Element sollen unverändert bleiben.
- Verwenden Sie die beiden verschachtelten Schleifen des gegebenen Algorithmus und vertauschen Sie die Elemente selbst mithilfe einer Hilfsvariablen.
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

public class SelectionSortDemo {

    public static void selectionSort(List<Integer> werte) {
        int n = werte.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (werte.get(j) < werte.get(minIndex)) {
                    minIndex = j;
                }
            }
            int temp = werte.get(i);
            werte.set(i, werte.get(minIndex));
            werte.set(minIndex, temp);
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
            selectionSort(werte);
            System.out.println(eingabe + " -> " + werte);
        }
    }
}
```

Die Klasse kann als `SelectionSortDemo.java` in VS Code ausgeführt werden.

**Erwartete Ausgabe:**

```text
[7, 3, 5, 2, 4] -> [2, 3, 4, 5, 7]
[1, 2, 3, 4] -> [1, 2, 3, 4]
[4, 3, 2, 1] -> [1, 2, 3, 4]
[3, -2, 3, 0] -> [-2, 0, 3, 3]
[5] -> [5]
[] -> []
```

`get` liefert ein `Integer`, das beim numerischen Vergleich und bei der Zuweisung an `int temp` automatisch entpackt wird (Unboxing). `set` ersetzt einen Wert; `temp` wird automatisch verpackt (Boxing). Die Testdaten werden in veränderbare `ArrayList`-Instanzen kopiert, da `List.of()` keine Ersetzung erlaubt.


## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus führt auch dann einen Tauschblock aus, wenn das kleinste Element bereits an der richtigen Position steht.

### a)

Erweitern Sie den Pseudocode so, dass nur Elemente an unterschiedlichen Positionen vertauscht werden.

### b)

Erläutern Sie, warum ein Durchlauf ohne Vertauschung bei Selection Sort keinen vorzeitigen Abbruch rechtfertigt. Verwenden Sie als Beispiel das Array `[1, 3, 2]`.

### c)

Vergleichen Sie für das bereits aufsteigend sortierte Array `[1, 2, 3, 4, 5]` die Anzahl der Elementvergleiche und der ausgeführten Tauschblöcke beim ursprünglichen und beim optimierten Algorithmus. Zählen Sie ausschließlich Vergleiche zwischen zwei Arrayelementen.

**Lösung**  

### a)

```text
FUNKTION selectionSort(werte : Ganzzahl[]) : void
    n = LÄNGE(werte)

    FÜR i VON 0 BIS n - 2
        minIndex = i

        FÜR j VON i + 1 BIS n - 1
            WENN werte[j] < werte[minIndex] DANN
                minIndex = j
            ENDE WENN
        ENDE FÜR

        WENN minIndex != i DANN
            temp = werte[i]
            werte[i] = werte[minIndex]
            werte[minIndex] = temp
        ENDE WENN
    ENDE FÜR
ENDE FUNKTION
```

### b)

Ein Durchlauf ohne tatsächliche Vertauschung bedeutet nur, dass das Minimum des verbleibenden Bereichs bereits an Position `i` steht. Über die Reihenfolge der anderen Elemente ist damit nichts ausgesagt.

Bei `[1, 3, 2]` ist im ersten Durchlauf `minIndex == 0`. Ein Abbruch wäre falsch, da 3 und 2 noch vertauscht werden müssen.

### c)

| Variante | Elementvergleiche | Tauschblöcke |
|---|---:|---:|
| Ursprünglich | 10 | 4 |
| Optimiert | 10 | 0 |

**Vertiefung - Laufzeitkomplexität:**

Beide Varianten führen für n Elemente genau n * (n - 1) / 2 Elementvergleiche aus. Best, Average und Worst Case sind jeweils Θ(n²); der zusätzliche Speicherbedarf beträgt Θ(1) bei Arrays. Die Optimierung reduziert lediglich unnötige Schreibzugriffe; sie fügt eine Indexprüfung pro Durchlauf hinzu. Das gilt für die Laufzeit auch bei Listen mit konstanten indexbasierten Zugriffen (z. B. ArrayList), nicht grundsätzlich bei LinkedList.

Der klassische Selection Sort mit Vertauschungen ist im Allgemeinen nicht stabil.
