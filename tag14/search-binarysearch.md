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

## A4 - Ersten Treffer ermitteln (optional)

Bei mehrfach vorkommenden Messwerten soll künftig **immer der kleinste passende Index** zurückgegeben werden.

### a)

Erweitern Sie den Pseudocode zu `binaereSucheErstesVorkommen`. Speichern Sie bei einem Treffer zunächst den gefundenen Index und suchen Sie danach **nur im linken Teilbereich weiter**. Bei keinem Treffer geben Sie `-1` zurück.

### b)

Erläutern Sie, warum diese Variante tatsächlich das erste Vorkommen findet und weshalb sich die asymptotische Laufzeit gegenüber A1–A3 nicht verschlechtert.

### c)

Vergleichen Sie für `werte = [1, 2, 2, 2, 4]` und `gesucht = 2` die Rückgabewerte und die Anzahl der Elementprüfungen der ursprünglichen und der erweiterten Variante. Zählen Sie ausschließlich eine Prüfung des mittleren Elements pro Schleifendurchlauf.
