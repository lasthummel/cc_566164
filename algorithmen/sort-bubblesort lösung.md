# Aufgabe - Algorithmen - Bubble Sort

## Ausgangssituation

Eine Anwendung zur Auswertung von Temperaturmessungen soll ganzzahlige Messwerte aufsteigend sortieren. Dazu steht der folgende Algorithmus Bubble Sort zur Verfügung.

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- `LÄNGE(werte)` liefert die Anzahl der Elemente des Arrays.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- Das übergebene Array wird direkt verändert. Die Funktion liefert keinen Rückgabewert.
- Das Array darf leer sein.

**Pseudocode:**

```text
FUNKTION bubbleSort(werte : Ganzzahl[]) : void
    n = LÄNGE(werte)

    FÜR i VON 0 BIS n - 2
        FÜR j VON 0 BIS n - 2 - i
            WENN werte[j] > werte[j + 1] DANN
                temp = werte[j]
                werte[j] = werte[j + 1]
                werte[j + 1] = temp
            ENDE WENN
        ENDE FÜR
    ENDE FÜR
ENDE FUNKTION
```

## A1 - Algorithmus erläutern

Erläutern Sie kurz die Funktionsweise des gegebenen Algorithmus. Gehen Sie dabei auf folgende Aspekte ein:

- Aufgabe der äußeren und der inneren Schleife
- Vergleichsbedingung und Vertauschen der Elemente mithilfe von `temp`
- Sortierreihenfolge
- Grund für die Verkürzung des Vergleichsbereichs in der inneren Schleife

**Lösung**  

Bubble Sort sortiert das Array aufsteigend, indem benachbarte Elemente wiederholt miteinander verglichen und bei falscher Reihenfolge vertauscht werden.

Die äußere Schleife steuert die Sortierdurchläufe. Die innere Schleife vergleicht jeweils die Elemente an den Positionen `j` und `j + 1`. Ist das linke Element größer als das rechte, werden beide mithilfe der Hilfsvariablen `temp` vertauscht, damit kein Wert überschrieben und verloren geht.

Nach jedem vollständigen Durchlauf befindet sich das größte Element des noch nicht fest eingeordneten Bereichs an seiner endgültigen Position. Deshalb kann der Vergleichsbereich im nächsten Durchlauf um ein Element verkleinert werden.


## A2 - Algorithmus nachvollziehen

### a)

Verfolgen Sie die Ausführung des gegebenen Algorithmus für das folgende Array.

**Eingabe:**

```text
werte = [7, 3, 5, 2, 4]
```

Geben Sie den Zustand des Arrays nach jedem vollständigen Durchlauf der äußeren Schleife sowie die Anzahl der in diesem Durchlauf ausgeführten Vertauschungen an.

| Durchlauf | i | Zustand des Arrays | Anzahl der Vertauschungen |
|---|---:|---|---:|
| Ausgangszustand | - | [7, 3, 5, 2, 4] | - |
| 1 | 0 | | |
| 2 | 1 | | |
| 3 | 2 | | |
| 4 | 3 | | |

### b)

Ermitteln Sie für die folgenden Eingaben jeweils den Endzustand des Arrays und die Gesamtanzahl der Vertauschungen. Jede Eingabe wird unabhängig von den anderen verarbeitet.

| Eingabe | Endzustand | Anzahl der Vertauschungen |
|---|---|---:|
| [1, 2, 3, 4] | | |
| [4, 3, 2, 1] | | |
| [3, -2, 3, 0] | | |
| [5] | | |
| [] | | |

**Lösung**  

### a)

| Durchlauf | i | Zustand des Arrays | Anzahl der Vertauschungen |
|---|---:|---|---:|
| Ausgangszustand | - | [7, 3, 5, 2, 4] | - |
| 1 | 0 | [3, 5, 2, 4, 7] | 4 |
| 2 | 1 | [3, 2, 4, 5, 7] | 2 |
| 3 | 2 | [2, 3, 4, 5, 7] | 1 |
| 4 | 3 | [2, 3, 4, 5, 7] | 0 |

Insgesamt werden sieben Vertauschungen durchgeführt. Der vierte Durchlauf findet auch ohne Vertauschung statt, da der gegebene Algorithmus keinen vorzeitigen Abbruch vorsieht.

### b)

| Eingabe | Endzustand | Anzahl der Vertauschungen |
|---|---|---:|
| [1, 2, 3, 4] | [1, 2, 3, 4] | 0 |
| [4, 3, 2, 1] | [1, 2, 3, 4] | 6 |
| [3, -2, 3, 0] | [-2, 0, 3, 3] | 3 |
| [5] | [5] | 0 |
| [] | [] | 0 |

Bei einem leeren Array oder einem Array mit nur einem Element wird bereits die äußere Schleife nicht ausgeführt.


## A3 - Algorithmus in Java implementieren

Die Anwendung verwendet nun eine Liste vom Typ `java.util.List<Integer>` anstelle eines Arrays.

Implementieren Sie die Java-Methode `bubbleSort`, indem Sie den gegebenen Pseudocode auf die Verarbeitung einer Liste übertragen.

**Methodenkopf:**

```java
public static void bubbleSort(List<Integer> werte)
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

public class BubbleSortDemo {

    public static void bubbleSort(List<Integer> werte) {
        int n = werte.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (werte.get(j) > werte.get(j + 1)) {
                    int temp = werte.get(j);
                    werte.set(j, werte.get(j + 1));
                    werte.set(j + 1, temp);
                }
            }
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
            bubbleSort(werte);
            System.out.println(eingabe + " -> " + werte);
        }
    }
}
```

Die Klasse kann in der Datei `BubbleSortDemo.java` gespeichert und in VS Code ausgeführt werden.

**Erwartete Ausgabe:**

```text
[7, 3, 5, 2, 4] -> [2, 3, 4, 5, 7]
[1, 2, 3, 4] -> [1, 2, 3, 4]
[4, 3, 2, 1] -> [1, 2, 3, 4]
[3, -2, 3, 0] -> [-2, 0, 3, 3]
[5] -> [5]
[] -> []
```


## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus führt auch dann weitere Vergleiche durch, wenn das Array bereits vollständig sortiert ist.

### a)

Erweitern Sie den Pseudocode aus der Ausgangssituation um eine boolesche Kontrollvariable, sodass die äußere Schleife vorzeitig beendet wird, wenn während eines vollständigen Durchlaufs der inneren Schleife keine Elemente vertauscht wurden.

`ABBRUCH` beendet unmittelbar die innerste noch aktive Schleife.

### b)

Erläutern Sie, warum in diesem Fall keine weiteren Durchläufe erforderlich sind.

### c)

Vergleichen Sie für ein bereits aufsteigend sortiertes Array `[1, 2, 3, 4, 5]` die Anzahl der Elementvergleiche beim ursprünglichen und beim optimierten Algorithmus. Zählen Sie ausschließlich die Vergleiche zwischen zwei Arrayelementen.


**Lösung**  

### a)

```text
FUNKTION bubbleSort(werte : Ganzzahl[]) : void
    n = LÄNGE(werte)

    FÜR i VON 0 BIS n - 2
        vertauscht = FALSCH

        FÜR j VON 0 BIS n - 2 - i
            WENN werte[j] > werte[j + 1] DANN
                temp = werte[j]
                werte[j] = werte[j + 1]
                werte[j + 1] = temp
                vertauscht = WAHR
            ENDE WENN
        ENDE FÜR

        WENN vertauscht == FALSCH DANN
            ABBRUCH
        ENDE WENN
    ENDE FÜR
ENDE FUNKTION
```

### b)

Wenn während eines vollständigen Durchlaufs keine Vertauschung stattfindet, sind alle benachbarten Elemente im verbleibenden Vergleichsbereich bereits aufsteigend angeordnet. Der Bereich rechts davon befindet sich bereits an seiner endgültigen Position. Folglich ist das gesamte Array sortiert.

Die Kontrollvariable muss vor jedem Durchlauf der inneren Schleife erneut auf `FALSCH` gesetzt werden. Die Abbruchprüfung erfolgt erst nach Abschluss der inneren Schleife.

### c)

| Variante | Elementvergleiche |
|---|---:|
| Ursprünglicher Algorithmus | 4 + 3 + 2 + 1 = 10 |
| Optimierter Algorithmus | 4 |

Bei einem bereits sortierten Array genügt bei der optimierten Variante ein vollständiger Durchlauf ohne Vertauschungen.

**Vertiefung - Laufzeitkomplexität:**

| Fall | Ursprünglicher Algorithmus | Optimierter Algorithmus |
|---|---|---|
| Best Case | Θ(n²) | Θ(n) |
| Average Case (gleichverteilte Permutationen verschiedener Werte) | Θ(n²) | Θ(n²) |
| Worst Case | Θ(n²) | Θ(n²) |

Die Angaben gelten für Arrays und für Listen mit konstantem Zeitaufwand für indexbasierte Lese- und Schreibzugriffe, beispielsweise `ArrayList`. Bei einer `LinkedList` sind indexbasierte Zugriffe im Allgemeinen nicht konstant.

