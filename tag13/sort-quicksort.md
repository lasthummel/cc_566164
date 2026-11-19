# Aufgabe - Algorithmen - Quick Sort

## Ausgangssituation

Eine Anwendung zur Auswertung von Temperaturmessungen soll ganzzahlige Messwerte aufsteigend sortieren. Dazu steht der folgende Algorithmus Quick Sort zur Verfügung.

Für diese Aufgabe wird die **Lomuto-Partitionierung** verwendet: Das letzte Element des jeweils betrachteten Teilbereichs dient als Pivot. Es handelt sich um eine konkret festgelegte Variante von Quick Sort; andere Partitionierungsverfahren können abweichende Zwischenzustände liefern.

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- `LÄNGE(werte)` liefert die Anzahl der Elemente des Arrays.
- Bei `FÜR j VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- Das übergebene Array wird direkt verändert. Die Funktion liefert keinen Rückgabewert.
- Die Funktionen `quickSortBereich` und `partitioniere` arbeiten ausschließlich innerhalb des Teilbereichs von Index `tief` bis `hoch` (einschließlich).
- Ein Tauschblock besteht aus den drei Zuweisungen mit `temp`. Er wird **auch bei identischen Tauschindizes oder gleichen Werten** als eine ausgeführte Operation gezählt.
- `RÜCKGABE` ohne Wert beendet die aktuelle Funktion sofort; bei `partitioniere` gibt `RÜCKGABE` den Pivotindex zurück.
- Das Array darf leer sein. Für leere oder ein-elementige Teilbereiche erfolgt keine Partitionierung.
- Bei der Rekursion wird stets **zuerst der linke, danach der rechte Teilbereich** bearbeitet.

**Pseudocode:**

```text
FUNKTION quickSort(werte : Ganzzahl[]) : void
    quickSortBereich(werte, 0, LÄNGE(werte) - 1)
ENDE FUNKTION

FUNKTION quickSortBereich(werte : Ganzzahl[], tief : Ganzzahl, hoch : Ganzzahl) : void
    WENN tief >= hoch DANN
        RÜCKGABE
    ENDE WENN

    pivotIndex = partitioniere(werte, tief, hoch)
    quickSortBereich(werte, tief, pivotIndex - 1)
    quickSortBereich(werte, pivotIndex + 1, hoch)
ENDE FUNKTION

FUNKTION partitioniere(werte : Ganzzahl[], tief : Ganzzahl, hoch : Ganzzahl) : Ganzzahl
    pivot = werte[hoch]
    i = tief - 1

    FÜR j VON tief BIS hoch - 1
        WENN werte[j] < pivot DANN
            i = i + 1
            temp = werte[i]
            werte[i] = werte[j]
            werte[j] = temp
        ENDE WENN
    ENDE FÜR

    temp = werte[i + 1]
    werte[i + 1] = werte[hoch]
    werte[hoch] = temp

    RÜCKGABE i + 1
ENDE FUNKTION
```

## A1 - Algorithmus erläutern

Erläutern Sie kurz die Funktionsweise des gegebenen Algorithmus. Gehen Sie dabei auf folgende Aspekte ein:

- Bedeutung der Rekursion und der Abbruchbedingung `tief >= hoch`
- Auswahl des Pivot-Elements und Bedeutung von `tief`, `hoch`, `i` und `j`
- Wirkung der Bedingung `werte[j] < pivot` und Zweck der Tauschblöcke
- Position des Pivot-Elements nach der Partitionierung und weitere Bearbeitung der Teilbereiche
- Sortierreihenfolge

## A2 - Algorithmus nachvollziehen

### a)

Verfolgen Sie die Ausführung des gegebenen Algorithmus für das folgende Array.

**Eingabe:**

```text
werte = [7, 3, 5, 2, 4]
```

Geben Sie **nach jeder vollständig abgeschlossenen Partitionierung** die Bereichsgrenzen `tief` und `hoch`, den gewählten Pivot-Wert, den zurückgegebenen `pivotIndex`, den Zustand des **gesamten** Arrays sowie die in dieser Partitionierung ausgeführten Elementvergleiche und Tauschblöcke an. Tragen Sie die Partitionierungen in tatsächlicher Ausführungsreihenfolge ein (linker Teilbereich vor rechtem Teilbereich).

Als Elementvergleich zählt ausschließlich die Auswertung `werte[j] < pivot`. Der Tauschblock zur abschließenden Pivotplatzierung zählt mit. Die Tabelle enthält keine eigenen Zeilen für Rekursionsaufrufe, die wegen `tief >= hoch` sofort enden.

| Partitionierung | tief | hoch | pivot | pivotIndex | Zustand des gesamten Arrays | Elementvergleiche | Tauschblöcke |
|---|---:|---:|---:|---:|---|---:|---:|
| Ausgangszustand | - | - | - | - | [7, 3, 5, 2, 4] | - | - |
| 1 | | | | | | | |
| 2 | | | | | | | |
| 3 | | | | | | | |

### b)

Ermitteln Sie für die folgenden Eingaben jeweils den Endzustand des Arrays sowie die **Gesamtanzahl** der ausgeführten Elementvergleiche und Tauschblöcke. Jede Eingabe wird unabhängig von den anderen verarbeitet. Verwenden Sie den **unveränderten** Pseudocode aus der Ausgangssituation.

| Eingabe | Endzustand | Elementvergleiche | Tauschblöcke |
|---|---|---:|---:|
| [1, 2, 3, 4] | | | |
| [4, 3, 2, 1] | | | |
| [3, -2, 3, 0] | | | |
| [5] | | | |
| [] | | | |

## A3 - Algorithmus in Java implementieren

Die Anwendung verwendet nun eine Liste vom Typ `java.util.List<Integer>` anstelle eines Arrays.

Implementieren Sie die Java-Methode `quickSort` sowie die beiden benötigten Hilfsmethoden entsprechend dem gegebenen Pseudocode.

**Methodenköpfe:**

```java
public static void quickSort(List<Integer> werte)
private static void quickSortBereich(List<Integer> werte, int tief, int hoch)
private static int partitioniere(List<Integer> werte, int tief, int hoch)
```

**Vorgaben:**

- Die übergebene Liste soll aufsteigend sortiert und direkt verändert werden.
- Die Liste ist nicht `null`, enthält keine `null`-Elemente und unterstützt das Ersetzen von Elementen. Für die Laufzeitbetrachtung wird eine `ArrayList` mit konstantem Aufwand für `get` und `set` angenommen.
- Leere Listen und Listen mit nur einem Element sollen unverändert bleiben.
- Verwenden Sie die rekursive Aufteilung und die **Lomuto-Partitionierung mit dem letzten Element als Pivot** wie im Pseudocode. Die Tauschblöcke werden auch bei identischen Indizes ausgeführt.
- Vorgefertigte Sortiermethoden wie `Collections.sort()` oder `List.sort()` sowie das Umwandeln der Liste in ein Array sind nicht zulässig.
- Erstellen Sie die Methoden zunächst schriftlich und überprüfen Sie anschließend die Implementierung in Java mit den Eingabedaten aus A2.

Für die Verarbeitung der Liste stehen unter anderem folgende Methoden zur Verfügung:

| Methode | Beschreibung |
|---|---|
| `size()` | Liefert die Anzahl der Elemente. |
| `get(index)` | Liefert das Element am angegebenen Index. |
| `set(index, element)` | Ersetzt das Element am angegebenen Index durch das übergebene Element. |

## A4 - Algorithmus optimieren (optional)

Der gegebene Algorithmus führt auch dann einen Tauschblock aus, wenn Quelle und Ziel **derselbe Index** sind. Das verändert das Array nicht, verursacht aber unnötige Schreibzugriffe.

### a)

Erweitern Sie den Pseudocode der Funktion `partitioniere` so, dass **beide Arten von Selbsttausch** vermieden werden: beim Verschieben kleinerer Werte und beim abschließenden Platzieren des Pivots. Die Pivotwahl, die Vergleichsbedingung und die Reihenfolge der Rekursionsaufrufe sollen unverändert bleiben.

### b)

Erläutern Sie, warum die Optimierung weder das Sortierergebnis noch die zurückgegebenen Pivotindizes verändert.

### c)

Vergleichen Sie für das bereits aufsteigend sortierte Array `[1, 2, 3, 4, 5]` die **Gesamtanzahl der Elementvergleiche und Tauschblöcke** beim ursprünglichen und beim optimierten Algorithmus. Zählen Sie nur `werte[j] < pivot` sowie tatsächlich ausgeführte Tauschblöcke, nicht Index- oder Abbruchvergleiche.

### d)

Erläutern Sie, warum diese Optimierung den ungünstigsten asymptotischen Zeitaufwand nicht verbessert. Nennen Sie eine mögliche Änderung der Pivotwahl, die bei bereits sortierten Eingaben günstigere Partitionen erzeugen kann.

