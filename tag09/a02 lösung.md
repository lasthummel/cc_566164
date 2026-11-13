# Aufgabe – Schleifen, Funktionen und Arrays

## Ausgangssituation

Ein Unternehmen entwickelt eine Anwendung zur Auswertung von Messdaten.

Die Messwerte werden als Ganzzahlen in einem Array gespeichert. Vor der Auswertung wird jeder Messwert durch die Funktion `bewerten` nach folgenden Regeln verarbeitet:

- Ist der Messwert gerade, wird er halbiert.
- Ist der Messwert ungerade, wird er um 1 erhöht.

Anschließend werden ausschließlich die berechneten Ergebnisse addiert, die mindestens den Wert 4 erreichen.

Die Anwendung verwendet dazu den folgenden Pseudocode.

## Pseudocode

```text
FUNKTION bewerten(wert: Ganzzahl) : Ganzzahl
    WENN wert MOD 2 == 0 DANN
        ZURÜCKGEBEN: wert / 2
    ENDE WENN

    ZURÜCKGEBEN: wert + 1
ENDE FUNKTION


HAUPTPROGRAMM
    daten = [3, 8, 5, 2]
    summe = 0

    FÜR i VON 0 BIS LÄNGE(daten) - 1
        ergebnis = bewerten(daten[i])

        WENN ergebnis >= 4 DANN
            summe = summe + ergebnis
        ENDE WENN
    ENDE FÜR

    AUSGEBEN summe
ENDE HAUPTPROGRAMM
```

**Hinweise:**

- Die Indizierung des Arrays beginnt bei 0.
- `MOD` liefert den Rest einer ganzzahligen Division.
- Die Grenzen der `FÜR`-Schleife sind einschließlich.
- Bei der Division zweier Ganzzahlen wird der Nachkommateil verworfen.

## A1 – Funktionsaufrufe analysieren

Analysieren Sie die Funktion `bewerten`.

Ergänzen Sie für jeden Funktionsaufruf die folgende Tabelle. Geben Sie jeweils den Parameterwert, das Ergebnis der Bedingungsprüfung und den Rückgabewert an.

| Funktionsaufruf | Parameter `wert` | `wert MOD 2 == 0` | Rückgabewert |
|---|---:|---|---:|
| `bewerten(3)` | | | |
| `bewerten(8)` | | | |
| `bewerten(5)` | | | |
| `bewerten(2)` | | | |

**Lösung**  

Die Funktion `bewerten` prüft, ob der übergebene Wert gerade oder ungerade ist.

- Bei geraden Zahlen wird der Wert durch 2 geteilt.
- Bei ungeraden Zahlen wird der Wert um 1 erhöht.

| Funktionsaufruf | Parameter `wert` | `wert MOD 2 == 0` | Rückgabewert |
|---|---:|---|---:|
| `bewerten(3)` | 3 | FALSCH | 4 |
| `bewerten(8)` | 8 | WAHR | 4 |
| `bewerten(5)` | 5 | FALSCH | 6 |
| `bewerten(2)` | 2 | WAHR | 1 |

## A2 – Schleifendurchläufe analysieren

Verfolgen Sie die Ausführung der `FÜR`-Schleife im Hauptprogramm.

Vervollständigen Sie die folgende Tabelle für alle Schleifendurchläufe.

Geben Sie dabei den aktuellen Index, den verarbeiteten Arraywert, den Rückgabewert der Funktion `bewerten`, das Ergebnis der Bedingungsprüfung und den Wert der Variablen `summe` am Ende des jeweiligen Schleifendurchlaufs an.

| Durchlauf | Index `i` | `daten[i]` | `ergebnis` | `ergebnis >= 4` | `summe` danach |
|---:|---:|---:|---:|---|---:|
| 1 | | | | | |
| 2 | | | | | |
| 3 | | | | | |
| 4 | | | | | |

**Lösung**  

Das Array `daten` enthält vier Elemente:

```text
Index:   0  1  2  3
Wert:    3  8  5  2
```

Die Variable `summe` besitzt vor dem ersten Schleifendurchlauf den Wert 0.

| Durchlauf | Index `i` | `daten[i]` | `ergebnis` | `ergebnis >= 4` | `summe` danach |
|---:|---:|---:|---:|---|---:|
| 1 | 0 | 3 | 4 | WAHR | 4 |
| 2 | 1 | 8 | 4 | WAHR | 8 |
| 3 | 2 | 5 | 6 | WAHR | 14 |
| 4 | 3 | 2 | 1 | FALSCH | 14 |

## A3 – Ausgabe und Arraygrenzen

### a)
Ermitteln Sie, welcher Wert am Ende des Hauptprogramms ausgegeben wird.

**Lösung**  
Ausgabe: 14

Nur die Rückgabewerte, die mindestens 4 betragen, werden addiert: 4 + 4 + 6 = 14

### b)
Geben Sie den kleinsten und den größten gültigen Index des Arrays `daten` an.

**Lösung**  
Das Array `daten` besitzt vier Elemente.

- Kleinster gültiger Index: `0`
- Größter gültiger Index: `3`

Allgemein gilt bei nullbasierter Indizierung:

```text
0 bis LÄNGE(daten) - 1
```

Für das gegebene Array ergibt sich:

```text
0 bis 4 - 1 = 3
```

### c)
Die Schleifenbedingung soll so verändert werden, dass die Schleife auch für `i = LÄNGE(daten)` ausgeführt wird. Erläutern Sie, welcher Fehler dadurch beim Zugriff auf das Array entsteht.

**Lösung**  
Wird die Schleife auch für `i = LÄNGE(daten)` ausgeführt, entsteht ein ungültiger Arrayzugriff.

Da `LÄNGE(daten) = 4` ist, versucht der Algorithmus im zusätzlichen Schleifendurchlauf, auf das Element `daten[4]` zuzugreifen.

Dieses Element existiert nicht, da die gültigen Indizes nur von 0 bis 3 reichen.

Folge: Es entsteht ein Indexfehler (*Array-Index-out-of-Bounds*).


## A4 – Variablen und Initialisierung

Begründen Sie die folgenden Entscheidungen im Algorithmus:

1. Die Variable `summe` wird vor der Schleife mit dem Wert 0 initialisiert und nicht innerhalb der Schleife.

**Lösung**  

Die Variable `summe` dient als Akkumulator. Sie speichert die Summe der bisher berücksichtigten Ergebnisse.

Die Initialisierung mit 0 ist erforderlich, weil 0 das neutrale Element der Addition ist. Dadurch kann der erste gültige Rückgabewert korrekt addiert werden.

Die Initialisierung erfolgt vor der Schleife, damit die Summe über alle Schleifendurchläufe hinweg erhalten bleibt.

Würde `summe` innerhalb der Schleife bei jedem Durchlauf erneut auf 0 gesetzt, gingen die zuvor addierten Werte verloren.


2. Die Variable `ergebnis` erhält in jedem Schleifendurchlauf durch den Aufruf der Funktion `bewerten` einen neuen Wert.

**Lösung**  
Die Variable `ergebnis` speichert den Rückgabewert der Funktion `bewerten` für das aktuell verarbeitete Arrayelement.

Da in jedem Schleifendurchlauf ein anderes Arrayelement verarbeitet wird, muss die Funktion für jedes Element erneut aufgerufen werden.

Dadurch wird sichergestellt, dass die anschließende Bedingung `ergebnis >= 4` immer den Rückgabewert des aktuellen Funktionsaufrufs überprüft.

Die Variable `ergebnis` dient somit als temporäre Ergebnisvariable.
