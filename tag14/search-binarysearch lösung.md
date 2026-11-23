# Aufgabe - Algorithmen - Binäre Suche (iterativ)

## Ausgangssituation

Eine Anwendung zur Auswertung von Temperaturmessungen verwaltet ganzzahlige Messwerte in **aufsteigend sortierter Reihenfolge**. Es soll überprüft werden, ob ein bestimmter Messwert vorhanden ist. Dazu wird die **iterative binäre Suche** verwendet. Anders als die bisherigen Sortieralgorithmen verändert dieser Suchalgorithmus die Eingabedaten **nicht**, sondern gibt einen Index zurück.

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays und Listen sind ab Index `0` indiziert.
- Die Eingabe ist aufsteigend sortiert; gleiche Werte dürfen mehrfach vorkommen.
- `LÄNGE(werte)` liefert die Anzahl der Arrayelemente.
- `DIV` bezeichnet die ganzzahlige Division mit Abrundung.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit.
- `RÜCKGABE` beendet die Funktion sofort.
- Die Intervallgrenzen `links` und `rechts` sind **einschließlich** zu verstehen.
- Der Suchbereich ist leer, sobald `links > rechts` gilt.
- `-1` bedeutet, dass der Suchwert nicht gefunden wurde.
- Bei mehreren gleichen Werten darf die **ursprüngliche Variante** einen beliebigen passenden Index zurückgeben; hier entscheidet die gewählte Mitte.
- Als **Elementprüfung** zählt in A2 und A4 jede Auswertung eines mittleren Listenelements `werte[mitte]` im Schleifendurchlauf **einmal**, nicht jede einzelne Teilbedingung. Vergleiche von Indizes zählen nicht.
- Auch ein leeres Array ist zulässig. `null`-Eingaben werden nicht betrachtet.

**Pseudocode:**

```text
FUNKTION binaereSuche(werte : Ganzzahl[], gesucht : Ganzzahl) : Ganzzahl
    links = 0
    rechts = LÄNGE(werte) - 1

    SOLANGE links <= rechts
        mitte = links + (rechts - links) DIV 2

        WENN werte[mitte] == gesucht DANN
            RÜCKGABE mitte
        SONST WENN werte[mitte] < gesucht DANN
            links = mitte + 1
        SONST
            rechts = mitte - 1
        ENDE WENN
    ENDE SOLANGE

    RÜCKGABE -1
ENDE FUNKTION
```


## A1 - Algorithmus erläutern

Erläutern Sie kurz die Funktionsweise des gegebenen Algorithmus. Gehen Sie dabei auf folgende Aspekte ein:

- Bedeutung der aufsteigenden Sortierung und der Grenzen `links` und `rechts`
- Auswahl und Prüfung des mittleren Elements
- Entscheidung, welche Hälfte des Suchbereichs ausgeschlossen wird
- Abbruch bei Treffer oder leerem Suchbereich sowie Bedeutung des Rückgabewerts
- Warum bei der iterativen Variante keine Rekursion erforderlich ist


**Lösung**

Die binäre Suche setzt die **aufsteigende Sortierung** voraus. `links` und `rechts` begrenzen das aktuell noch mögliche Suchintervall. `mitte` bezeichnet dessen mittleren Index (bei gerader Anzahl das linke der beiden mittleren Elemente). Bei Gleichheit wird `mitte` zurückgegeben. Ist das mittlere Element kleiner als der Suchwert, kann die linke Hälfte einschließlich `mitte` ausgeschlossen werden (`links = mitte + 1`); andernfalls wird die rechte Hälfte ausgeschlossen (`rechts = mitte - 1`). Die Suche endet bei einem Treffer oder wenn `links > rechts`; in letzterem Fall wird `-1` geliefert. Die Wiederholung erfolgt durch eine `SOLANGE`-Schleife ohne rekursive Aufrufe. Das Array bleibt unverändert.

## A2 - Algorithmus nachvollziehen

### a)

Verfolgen Sie den Pseudocode für die folgende Eingabe:

```text
werte = [2, 3, 4, 5, 7]
gesucht = 7
```

Geben Sie **für jeden ausgeführten Schleifendurchlauf** die Werte von `links`, `rechts`, `mitte`, `werte[mitte]`, die getroffene Entscheidung und die neuen Grenzen beziehungsweise den Rückgabewert an. Verwenden Sie die Grenzen **vor** der Ausführung des jeweiligen Vergleichs.

| Durchlauf | links | rechts | mitte | werte[mitte] | Entscheidung | Neue Grenzen / Rückgabe |
|---|---:|---:|---:|---:|---|---|
| 1 | | | | | | |
| 2 | | | | | | |
| 3 | | | | | | |

Wie viele Elementprüfungen werden ausgeführt?

### b)

Ermitteln Sie für die folgenden **unabhängigen** Suchvorgänge den Rückgabewert sowie die Anzahl der Elementprüfungen. Verwenden Sie den unveränderten Pseudocode.

| Sortierte Eingabe | gesucht | Rückgabewert | Elementprüfungen |
|---|---:|---:|---:|
| [1, 2, 3, 4] | 3 | | |
| [1, 2, 3, 4] | 5 | | |
| [-2, 0, 3, 3] | 3 | | |
| [2, 3, 4, 5, 7] | 6 | | |
| [5] | 5 | | |
| [] | 5 | | |

Erläutern Sie außerdem kurz, warum eine unsortierte Eingabe wie `[7, 3, 5, 2, 4]` **nicht** ohne vorheriges Sortieren mit diesem Algorithmus verarbeitet werden darf.


**Lösung**

### a)

| Durchlauf | links | rechts | mitte | werte[mitte] | Entscheidung | Neue Grenzen / Rückgabe |
|---|---:|---:|---:|---:|---|---|
| 1 | 0 | 4 | 2 | 4 | 4 < 7 | links = 3, rechts = 4 |
| 2 | 3 | 4 | 3 | 5 | 5 < 7 | links = 4, rechts = 4 |
| 3 | 4 | 4 | 4 | 7 | 7 == 7 | RÜCKGABE 4 |

Es erfolgen **3 Elementprüfungen**.

### b)

| Sortierte Eingabe | gesucht | Rückgabewert | Elementprüfungen |
|---|---:|---:|---:|
| [1, 2, 3, 4] | 3 | 2 | 2 |
| [1, 2, 3, 4] | 5 | -1 | 3 |
| [-2, 0, 3, 3] | 3 | 2 | 2 |
| [2, 3, 4, 5, 7] | 6 | -1 | 3 |
| [5] | 5 | 0 | 1 |
| [] | 5 | -1 | 0 |

Bei `[7, 3, 5, 2, 4]` lässt sich aus `werte[mitte] < gesucht` nicht zuverlässig ableiten, in welcher Hälfte der Suchwert liegen kann. Die Verwerfung einer Hälfte kann deshalb vorhandene Werte überspringen. Die frühere Sortier-Aufgabe lieferte nach aufsteigender Sortierung beispielsweise `[2, 3, 4, 5, 7]`.

## A3 - Algorithmus in Java implementieren

Die Anwendung verwendet eine Liste vom Typ `java.util.List<Integer>` anstelle eines Arrays.

Implementieren Sie die iterative Java-Methode `binaereSuche` entsprechend dem Pseudocode.

**Methodenkopf:**

```java
public static int binaereSuche(List<Integer> werte, int gesucht)
```

**Vorgaben:**

- Die Liste ist aufsteigend sortiert, nicht `null` und enthält keine `null`-Elemente.
- Die Liste darf leer sein und wird nicht verändert.
- Geben Sie bei einem Treffer den Index, ansonsten `-1` zurück.
- Verwenden Sie eine `while`-Schleife und die inclusive Grenzen `links` und `rechts`; keine Rekursion.
- Verwenden Sie keine vorgefertigte Suche wie `Collections.binarySearch` und wandeln Sie die Liste nicht in ein Array um.
- Für die Laufzeitbetrachtung wird eine `ArrayList` mit konstantem Aufwand für `get(index)` vorausgesetzt.
- Erstellen Sie den Code zunächst schriftlich und testen Sie ihn danach mit den Fällen aus A2.

| Listenmethode | Beschreibung |
|---|---|
| `size()` | Anzahl der Elemente |
| `get(index)` | Element am angegebenen Index |


**Lösung**

Die folgende Klasse ist als `BinaereSucheDemo.java` lauffähig:

```java
import java.util.List;

public class BinaereSucheDemo {

    public static int binaereSuche(List<Integer> werte, int gesucht) {
        int links = 0;
        int rechts = werte.size() - 1;

        while (links <= rechts) {
            int mitte = links + (rechts - links) / 2;
            int wert = werte.get(mitte);

            if (wert == gesucht) {
                return mitte;
            } else if (wert < gesucht) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }
        return -1;
    }

    public static int binaereSucheErstesVorkommen(List<Integer> werte, int gesucht) {
        int links = 0;
        int rechts = werte.size() - 1;
        int ergebnis = -1;

        while (links <= rechts) {
            int mitte = links + (rechts - links) / 2;
            int wert = werte.get(mitte);

            if (wert == gesucht) {
                ergebnis = mitte;
                rechts = mitte - 1;
            } else if (wert < gesucht) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }
        return ergebnis;
    }

    public static void main(String[] args) {
        List<List<Integer>> testdaten = List.of(
            List.of(1, 2, 3, 4),
            List.of(1, 2, 3, 4),
            List.of(-2, 0, 3, 3),
            List.of(2, 3, 4, 5, 7),
            List.of(5),
            List.<Integer>of()
        );
        int[] suchwerte = {3, 5, 3, 6, 5, 5};

        for (int i = 0; i < testdaten.size(); i++) {
            List<Integer> werte = testdaten.get(i);
            int gesucht = suchwerte[i];
            System.out.printf("%s, gesucht=%d -> Index %d%n",
                    werte, gesucht, binaereSuche(werte, gesucht));
        }
        List<Integer> mehrfach = List.of(1, 2, 2, 2, 4);
        System.out.println("Erstes Vorkommen: " +
                binaereSucheErstesVorkommen(mehrfach, 2));
    }
}
```

**Erwartete Ausgabe:**

```text
[1, 2, 3, 4], gesucht=3 -> Index 2
[1, 2, 3, 4], gesucht=5 -> Index -1
[-2, 0, 3, 3], gesucht=3 -> Index 2
[2, 3, 4, 5, 7], gesucht=6 -> Index -1
[5], gesucht=5 -> Index 0
[], gesucht=5 -> Index -1
Erstes Vorkommen: 1
```

`links + (rechts - links) / 2` vermeidet die potenzielle Überlaufgefahr der Berechnung `(links + rechts) / 2` bei großen Indizes. Bei `ArrayList` ist der indexbasierte Zugriff im üblichen Kostenmodell konstant. Bei `LinkedList` gilt das nicht, sodass eine indexbasierte binäre Suche dort ihre logarithmische Laufzeit einbüßen kann.

## A4 - Ersten Treffer ermitteln (optional)

Bei mehrfach vorkommenden Messwerten soll künftig **immer der kleinste passende Index** zurückgegeben werden.

### a)

Erweitern Sie den Pseudocode zu `binaereSucheErstesVorkommen`. Speichern Sie bei einem Treffer zunächst den gefundenen Index und suchen Sie danach **nur im linken Teilbereich weiter**. Bei keinem Treffer geben Sie `-1` zurück.

### b)

Erläutern Sie, warum diese Variante tatsächlich das erste Vorkommen findet und weshalb sich die asymptotische Laufzeit gegenüber A1–A3 nicht verschlechtert.

### c)

Vergleichen Sie für `werte = [1, 2, 2, 2, 4]` und `gesucht = 2` die Rückgabewerte und die Anzahl der Elementprüfungen der ursprünglichen und der erweiterten Variante. Zählen Sie ausschließlich eine Prüfung des mittleren Elements pro Schleifendurchlauf.

**Lösung**

### a)

```text
FUNKTION binaereSucheErstesVorkommen(werte : Ganzzahl[], gesucht : Ganzzahl) : Ganzzahl
    links = 0
    rechts = LÄNGE(werte) - 1
    ergebnis = -1

    SOLANGE links <= rechts
        mitte = links + (rechts - links) DIV 2

        WENN werte[mitte] == gesucht DANN
            ergebnis = mitte
            rechts = mitte - 1
        SONST WENN werte[mitte] < gesucht DANN
            links = mitte + 1
        SONST
            rechts = mitte - 1
        ENDE WENN
    ENDE SOLANGE

    RÜCKGABE ergebnis
ENDE FUNKTION
```

### b)

Nach einem Treffer bei `mitte` sind alle früheren möglichen Treffer ausschließlich im linken Teilbereich. Durch `rechts = mitte - 1` wird dieser weiter untersucht. Jeder erneute Treffer ersetzt den bisherigen Kandidaten durch einen kleineren Index. Ein noch früherer Treffer wird daher nicht übersehen. Pro Schleifendurchlauf wird der verbleibende Suchbereich in etwa halbiert; Zeitaufwand ist bei indexkonstantem Zugriff weiterhin **O(log n)** für nichtleere Eingaben, Zusatzspeicher **O(1)**. Beide Varianten haben auch im ungünstigsten Fall logarithmische Laufzeit; die Erweiterung löst eine andere Anforderung, sie verbessert nicht die asymptotische Laufzeit.

### c)

| Variante | Geprüfte mittlere Indizes | Rückgabewert | Elementprüfungen |
|---|---|---:|---:|
| Ursprüngliche Suche | 2 | 2 | 1 |
| Erstes Vorkommen | 2, 0, 1 | 1 | 3 |

**Didaktischer Hinweis:** Die im Suchalgorithmus obligatorische Sortierung der Eingabe ist eine **Vorbedingung** und keine Arbeit, die die Suchmethode selbst durchführt. Muss eine zunächst unsortierte Liste nur einmal durchsucht werden, kann eine lineare Suche wirtschaftlicher sein als Sortieren plus binäres Suchen. Bei vielen Suchanfragen über unveränderten Daten kann sich die vorherige Sortierung amortisieren. `O(log n)` gilt für die eigentliche binäre Suche nach Herstellung der Vorbedingung; der Aufwand der Sortierung ist gesondert zu berücksichtigen.
