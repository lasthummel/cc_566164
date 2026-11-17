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

## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus führt auch dann weitere Vergleiche durch, wenn das Array bereits vollständig sortiert ist.

### a)

Erweitern Sie den Pseudocode aus der Ausgangssituation um eine boolesche Kontrollvariable, sodass die äußere Schleife vorzeitig beendet wird, wenn während eines vollständigen Durchlaufs der inneren Schleife keine Elemente vertauscht wurden.

`ABBRUCH` beendet unmittelbar die innerste noch aktive Schleife.

### b)

Erläutern Sie, warum in diesem Fall keine weiteren Durchläufe erforderlich sind.

### c)

Vergleichen Sie für ein bereits aufsteigend sortiertes Array `[1, 2, 3, 4, 5]` die Anzahl der Elementvergleiche beim ursprünglichen und beim optimierten Algorithmus. Zählen Sie ausschließlich die Vergleiche zwischen zwei Arrayelementen.
