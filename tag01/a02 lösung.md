## Aufgabe 2 – Toolchain und Werkzeugauswahl

Ein Team entwickelt eine Java-Anwendung. Der Quellcode soll automatisiert gebaut und getestet werden. Alle Teammitglieder sollen möglichst dieselben Abhängigkeitsversionen verwenden.

### 2.1 Toolchain darstellen

Stellen Sie in einem beschrifteten Ablaufmodell dar, welche Rolle Java-Quellcode, Compiler, Bytecode, JVM, IDE, Build-Tool und externe Bibliothek haben und wie sie zusammenwirken. Kennzeichnen Sie Entwicklungswerkzeuge und Komponenten der Programmausführung.

Java-Quellcode -> Compiler (übersetzt Quellcode) -> Bytecode -> JVM (führt den Bytecode aus)

IDE integriert Compiler, JVM, Build-Tool... 

### 2.2 Entscheidungsmatrix erstellen

Die Zahlen sind vorgegebene Bewertungen aus einem internen Workshop, keine allgemeingültigen Produktbewertungen.

| Kriterium | Gewichtung | IDE A | IDE B | IDE C |
|---|---:|---:|---:|---:|
| Java-Unterstützung | 30 % | 5 | 4 | 3 |
| Integration von Tests und Debugger | 25 % | 5 | 3 | 4 |
| Team-Onboarding | 20 % | 3 | 5 | 4 |
| Ressourcenverbrauch | 10 % | 3 | 4 | 5 |
| Kosten im geplanten Einsatz | 15 % | 2 | 5 | 5 |

Bewertungsskala: 1 = sehr schlecht bis 5 = sehr gut.

1. Berechnen Sie den gewichteten Nutzwert jeder IDE. Verwenden Sie Bewertung × Gewichtungsanteil und addieren Sie die Teilwerte.

- IDE A: 5 × 0,30 + 5 × 0,25 + 3 × 0,20 + 3 × 0,10 + 2 × 0,15 = 3,95
- IDE B: 4 × 0,30 + 3 × 0,25 + 5 × 0,20 + 4 × 0,10 + 5 × 0,15 = 4,1
- IDE C: 3 × 0,30 + 4 × 0,25 + 4 × 0,20 + 5 × 0,10 + 5 × 0,15 = 3,95

2. Nennen Sie die Alternative mit dem höchsten Nutzwert.

IDE B

3. Erläutern Sie zwei Grenzen dieser Berechnung.

Grenzen sind beispielsweise subjektive Bewertungen, Abhängigkeit von der Kriterienauswahl, unvollständig erfasste Aspekte oder die Tatsache, dass ein hoher Nutzwert eine zwingende Anforderung nicht kompensiert.

4. Nennen Sie ein mögliches KO-Kriterium und erklären Sie, wann es vor der Nutzwertberechnung anzuwenden wäre.

Mögliches KO-Kriterium: Unterstützung der verbindlich eingesetzten Java-Version. Wird diese nicht unterstützt, scheidet die Alternative vor der Nutzwertberechnung aus, sofern diese Unterstützung tatsächlich zwingend festgelegt wurde.

### 2.3 Entscheidung kritisch beurteilen

Ein Projektleiter erklärt: „IDE A hat die höchste Bewertung bei Java und beim Debugging. Deshalb müssen wir IDE A wählen. Die anderen Kriterien sind unwichtig.“

Beurteilen Sie diese Aussage anhand der Entscheidungsmatrix. Gehen Sie auf Gewichtungen und die Grenzen einer rein rechnerischen Entscheidung ein.

Die Aussage ist nicht tragfähig. Sie betrachtet nur zwei Einzelbewertungen und ignoriert die Gewichtungen und übrigen Kriterien. IDE B erzielt in der Gesamtberechnung 4,10, IDE A 3,95. Die Nutzwertanalyse bleibt eine Entscheidungshilfe: Kriterien, Bewertungen und zwingende Anforderungen müssen im Projektkontext geprüft werden.
