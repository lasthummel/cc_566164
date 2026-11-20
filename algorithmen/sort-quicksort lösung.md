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

**Lösung**

Quick Sort sortiert das Array aufsteigend durch wiederholte **Partitionierung** und rekursive Bearbeitung der beiden verbleibenden Teilbereiche.

`quickSortBereich` sortiert nur den inklusiven Bereich `tief` bis `hoch`. Wenn `tief >= hoch`, enthält der Bereich höchstens ein Element; dieser Aufruf endet ohne Partitionierung. Andernfalls wählt `partitioniere` das Element bei `hoch` als Pivot.

`j` durchläuft alle übrigen Positionen des Teilbereichs. `i` kennzeichnet die letzte Position, an der bereits ein Element **kleiner** als das Pivot abgelegt wurde; initial gilt `i = tief - 1`. Bei `werte[j] < pivot` wird `i` erhöht und `werte[i]` mit `werte[j]` vertauscht. Werte, die gleich oder größer als das Pivot sind, werden nicht in den linken Bereich übernommen.

Anschließend wird das Pivot mit dem Element bei `i + 1` vertauscht. Sein endgültiger Index ist `i + 1`: Links davon liegen kleinere Werte, rechts davon größere oder gleiche Werte. Danach werden zuerst die linke und dann die rechte Teilfolge rekursiv bearbeitet. Die Reihenfolge der weiteren Aufrufe beeinflusst den Ablauf, nicht die aufsteigende Sortierung.

**Wichtig:** Ein Selbsttausch ist im vorgegebenen Pseudocode eine tatsächlich ausgeführte Operation. Die Methode ist wegen möglicher Umordnungen gleicher Elemente im Allgemeinen **nicht stabil**.

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

**Lösung**

### a)

| Partitionierung | tief | hoch | pivot | pivotIndex | Zustand des gesamten Arrays | Elementvergleiche | Tauschblöcke |
|---|---:|---:|---:|---:|---|---:|---:|
| Ausgangszustand | - | - | - | - | [7, 3, 5, 2, 4] | - | - |
| 1 | 0 | 4 | 4 | 2 | [3, 2, 4, 7, 5] | 4 | 3 |
| 2 | 0 | 1 | 2 | 0 | [2, 3, 4, 7, 5] | 1 | 1 |
| 3 | 3 | 4 | 5 | 3 | [2, 3, 4, 5, 7] | 1 | 1 |

**Erste Partitionierung im Detail:**

- Pivot = `4`, anfangs `i = -1`.
- `j = 0`: `7 < 4` ist falsch; kein Tausch.
- `j = 1`: `3 < 4` ist wahr. `i = 0`; 7 und 3 werden vertauscht: `[3, 7, 5, 2, 4]`.
- `j = 2`: `5 < 4` ist falsch; kein Tausch.
- `j = 3`: `2 < 4` ist wahr. `i = 1`; 7 und 2 werden vertauscht: `[3, 2, 5, 7, 4]`.
- Abschließend werden die Werte an `i + 1 = 2` und `hoch = 4` vertauscht: `[3, 2, 4, 7, 5]`. Der Pivotindex ist `2`.

Die linke Partition `[3, 2]` wird **vor** der rechten Partition `[7, 5]` bearbeitet. Insgesamt werden **6 Elementvergleiche** und **5 Tauschblöcke** ausgeführt.

### b)

| Eingabe | Endzustand | Elementvergleiche | Tauschblöcke |
|---|---|---:|---:|
| [1, 2, 3, 4] | [1, 2, 3, 4] | 6 | 9 |
| [4, 3, 2, 1] | [1, 2, 3, 4] | 6 | 5 |
| [3, -2, 3, 0] | [-2, 0, 3, 3] | 4 | 3 |
| [5] | [5] | 0 | 0 |
| [] | [] | 0 | 0 |

Bei der bereits sortierten Eingabe werden mehrere **Selbsttausche** ausgeführt. Bei `[3, -2, 3, 0]` werden im zweiten Teilbereich die beiden gleichen 3er an unterschiedlichen Indizes getauscht; der Arrayzustand ändert sich dabei nicht. Beide Arten von Tauschblock zählen nach der Aufgabenfestlegung.

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

**Lösung**

```java
import java.util.ArrayList;
import java.util.List;

public class QuickSortDemo {

    public static void quickSort(List<Integer> werte) {
        quickSortBereich(werte, 0, werte.size() - 1);
    }

    private static void quickSortBereich(List<Integer> werte, int tief, int hoch) {
        if (tief >= hoch) {
            return;
        }

        int pivotIndex = partitioniere(werte, tief, hoch);
        quickSortBereich(werte, tief, pivotIndex - 1);
        quickSortBereich(werte, pivotIndex + 1, hoch);
    }

    private static int partitioniere(List<Integer> werte, int tief, int hoch) {
        int pivot = werte.get(hoch);
        int i = tief - 1;

        for (int j = tief; j <= hoch - 1; j++) {
            if (werte.get(j) < pivot) {
                i++;
                int temp = werte.get(i);
                werte.set(i, werte.get(j));
                werte.set(j, temp);
            }
        }

        int temp = werte.get(i + 1);
        werte.set(i + 1, werte.get(hoch));
        werte.set(hoch, temp);

        return i + 1;
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
            quickSort(werte);
            System.out.println(eingabe + " -> " + werte);
        }
    }
}
```

Die Klasse kann als `QuickSortDemo.java` gespeichert und in VS Code ausgeführt werden. Die immutable Testlisten aus `List.of` werden für die Sortierung jeweils in eine veränderbare `ArrayList` kopiert.

**Erwartete Ausgabe:**

```text
[7, 3, 5, 2, 4] -> [2, 3, 4, 5, 7]
[1, 2, 3, 4] -> [1, 2, 3, 4]
[4, 3, 2, 1] -> [1, 2, 3, 4]
[3, -2, 3, 0] -> [-2, 0, 3, 3]
[5] -> [5]
[] -> []
```

Die Listenoperationen `get` und `set` übertragen die Array-Zugriffe des Pseudocodes. Für die durchschnittliche Laufzeit ist die Wahl einer `ArrayList` relevant, da ihre indexbasierten Zugriffe konstanten Zeitaufwand haben. Die Methode ist rekursiv; zusätzlich zum Array bzw. zur Liste wird Speicher für die Aufrufkette benötigt.

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

**Lösung**

### a)

Nur `partitioniere` wird geändert; `quickSort` und `quickSortBereich` bleiben unverändert.

```text
FUNKTION partitioniere(werte : Ganzzahl[], tief : Ganzzahl, hoch : Ganzzahl) : Ganzzahl
    pivot = werte[hoch]
    i = tief - 1

    FÜR j VON tief BIS hoch - 1
        WENN werte[j] < pivot DANN
            i = i + 1
            WENN i != j DANN
                temp = werte[i]
                werte[i] = werte[j]
                werte[j] = temp
            ENDE WENN
        ENDE WENN
    ENDE FÜR

    WENN i + 1 != hoch DANN
        temp = werte[i + 1]
        werte[i + 1] = werte[hoch]
        werte[hoch] = temp
    ENDE WENN

    RÜCKGABE i + 1
ENDE FUNKTION
```

### b)

Wenn beide Tauschindizes gleich sind, ändert der Tauschblock keinen Arraywert. Das Weglassen verändert weder `i` noch `j`, die Bedingung `werte[j] < pivot` oder den Rückgabewert `i + 1`. Alle nichttrivialen Vertauschungen werden unverändert durchgeführt. Somit bleiben Pivotpositionen, Partitionen und das Endergebnis gleich.

Der Vergleich unterschiedlicher Indizes schließt allerdings nicht aus, dass zufällig **gleiche Werte** vertauscht werden. Solche Operationen bleiben hier erhalten.

### c)

| Variante | Elementvergleiche | Tauschblöcke |
|---|---:|---:|
| Ursprünglicher Algorithmus | 4 + 3 + 2 + 1 = 10 | 5 + 4 + 3 + 2 = 14 |
| Optimiert ohne Selbsttausch | 10 | 0 |

Beim aufsteigend sortierten Array ist jedes betrachtete Element kleiner als das letzte Element des jeweiligen Teilbereichs. Deshalb gilt bei allen Vergleichen `i == j`; auch die abschließende Pivotplatzierung wäre jeweils ein Selbsttausch.

### d)

Das Weglassen unnötiger Schreibzugriffe beseitigt **keine** der Elementvergleiche und verändert die Größe der rekursiven Teilbereiche nicht. Mit dem letzten Element als Pivot entstehen bei sortierten Eingaben Partitionen der Größen `n - 1` und `0`. Deshalb bleibt der Worst Case `Θ(n²)`.

Eine andere Pivotwahl, etwa das mittlere Element nach Index oder ein Median-aus-drei-Verfahren, kann bei bereits sortierten Eingaben gleichmäßigere Partitionen erzeugen. Für eine Anwendung dieser Pivotwahl mit Lomuto-Partitionierung muss der ausgewählte Pivotwert zunächst an das Ende des Teilbereichs gebracht werden.

**Vertiefung - Laufzeitkomplexität:**

| Eigenschaft | Diese Quick-Sort-Variante |
|---|---|
| Best Case (annähernd ausgeglichene Partitionen) | `Θ(n log n)` |
| Average Case (gleichverteilte Permutationen verschiedener Werte) | `Θ(n log n)` |
| Worst Case (stark unausgeglichene Partitionen) | `Θ(n²)` |
| Zusätzlicher Rekursionsspeicher bei annähernd ausgeglichener Partitionierung | `Θ(log n)` |
| Zusätzlicher Rekursionsspeicher im Worst Case | `Θ(n)` |

Die Laufzeitangaben beziehen sich auf Arrays oder Listen mit konstanten indexbasierten Zugriffen, beispielsweise `ArrayList`, **nicht** allgemein auf `LinkedList`. Die hier betrachtete Lomuto-Variante ist im Allgemeinen nicht stabil. Die Aussagen sind fachliche Vertiefung und keine Behauptung über das Auftreten eines bestimmten Algorithmus in zukünftigen IHK-Prüfungen.

