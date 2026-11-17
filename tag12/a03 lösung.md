# Aufgabe - Teststufen und Testarten

## A1 – Teststufen zuordnen

Eine Anwendung verarbeitet Messwerte und stellt Ergebnisse über eine Schnittstelle bereit. Ordnen Sie jeder Situation die primär passende Teststufe zu und begründen Sie die Zuordnung.

1. Eine einzelne Methode zählt alle Werte, die mindestens 50 betragen. Sie wird mit verschiedenen Arrays auf den Rückgabewert geprüft.
2. Ein Service ruft ein Repository auf, erhält Messwerte und übergibt sie an die Auswertungslogik. Das Zusammenspiel wird geprüft.
3. Die vollständige Anwendung wird gestartet. Ein Schnittstellenaufruf wird bis zur Antwort verfolgt und gegen Systemanforderungen geprüft.
4. Fachanwender prüfen anhand vereinbarter Abnahmekriterien, ob der Bericht die benötigten Informationen korrekt ausweist.

**Lösung**  

1. **Unit-/Modultest:** Eine abgegrenzte Methode wird isoliert anhand definierter Eingaben und Rückgabewerte geprüft.
2. **Integrationstest:** Das Zusammenspiel von Service und Repository einschließlich Datenübergabe wird geprüft.
3. **Systemtest:** Die integrierte Anwendung wird als Gesamtsystem gegen Systemanforderungen geprüft.
4. **Abnahmetest:** Im Mittelpunkt steht die Erfüllung vereinbarter fachlicher Abnahmekriterien; Fachanwender können beteiligt sein.


## A2 – Testperspektive und Testzweck

Ordnen Sie jeder der nachfolgend beschriebenen Testsituationen die zutreffenden Testkategorien zu und begründen Sie Ihre Zuordnung kurz. Mehrfachzuordnungen sind möglich.

Kategorien: **Black-Box**, **White-Box**, **funktionaler Test**, **nichtfunktionaler Test**, **Regressionstest**. Kategorien dürfen mehrfach vorkommen.

1. Ohne den Quellcode zu betrachten, wird geprüft, ob eine Suchmethode bei fehlendem Wert `-1` zurückgibt.
2. Ausgehend von zwei Zweigen im Quellcode werden Eingaben gesucht, mit denen beide Zweige durchlaufen werden.
3. Nach einer Änderung an der Sortiermethode werden zuvor gespeicherte Testfälle erneut ausgeführt.
4. Es wird gemessen, ob ein Bericht bei 100.000 Messwerten innerhalb von zwei Sekunden erzeugt wird.
5. Es wird geprüft, ob eine Methode genau die Werte zählt, die laut Anforderung mindestens 50 betragen.

**Lösung**  

1. **Black-Box**, außerdem funktionaler Test: das erwartete Verhalten wird ohne interne Implementierungsdetails geprüft.
2. **White-Box:** Testfälle werden aus den internen Zweigen des Quellcodes abgeleitet.
3. **Regressionstest:** Vorhandene Tests werden nach einer Änderung erneut ausgeführt, um unbeabsichtigte Auswirkungen zu erkennen.
4. **Nichtfunktionaler Test**, genauer Performance-/Lasttest: Zeitkriterium bei definierter Datenmenge.
5. **Funktionaler Test**; Black-Box ist naheliegend, wenn Testfälle aus der Anforderung abgeleitet werden.

Teststufe, Perspektive und Zweck sind verschiedene Dimensionen. Ein Test kann gleichzeitig Unit-Test, Black-Box-Test und Regressionstest sein.



## A3 – Testfall und Testprotokoll

Eine Methode `indexOfFirst(int[] werte, int gesucht)` soll den Index des **ersten** Vorkommens zurückgeben. Ist der Wert nicht enthalten oder das Array leer, soll `-1` zurückgegeben werden.

1. Formulieren Sie einen Testfall für `werte = [4, 7, 4]`, `gesucht = 4`.
2. Ergänzen Sie für diesen Fall ein Beispiel-Testprotokoll mit Ist-Ergebnis und Status.
3. Formulieren Sie einen Testfall für einen fehlenden Wert.
4. Erklären Sie den Unterschied zwischen erwartetem Ergebnis und Ist-Ergebnis.


| Testfall-ID | Vorbedingung/Kontext | Eingabe/Aktion | Erwartetes Ergebnis | Ist-Ergebnis | Status |
|---|---|---|---|---|---|
| T-01 | | | | | |
| T-02 | | | | | |

**Lösung**  

| Testfall-ID | Vorbedingung/Kontext | Eingabe/Aktion | Erwartetes Ergebnis | Ist-Ergebnis | Status |
|---|---|---|---|---|---|
| T-01 | Methode verfügbar | `werte = [4, 7, 4]`, `gesucht = 4` | `0` | `0` (angenommen) | Bestanden |
| T-02 | Methode verfügbar | `werte = [4, 7, 4]`, `gesucht = 9` | `-1` | noch nicht ausgeführt | Offen |

Für T-01 ist Index 0 zu erwarten, da das erste Vorkommen an Position 0 liegt. Das zweite Vorkommen darf nicht das Ergebnis sein. Das Beispielprotokoll nimmt einen beobachteten Wert 0 an; bei einer realen Ausführung muss der tatsächliche Wert eingetragen und der Status daraus abgeleitet werden.

**Erwartetes Ergebnis:** vorab aus der Spezifikation abgeleiteter Sollwert. **Ist-Ergebnis:** bei der Ausführung beobachteter Wert. Der Vergleich ergibt den Status.


## A4 – Kategorien kombinieren

Eine Methode zur Berechnung der Messwertsumme wurde geändert.

1. Nennen Sie eine passende Teststufe für das isolierte Prüfen der Methode.
2. Erklären Sie, warum die erneute Ausführung vorhandener Tests hier ein Regressionstest ist.
3. Beschreiben Sie, wie ein Black-Box-Test abgeleitet werden kann.
4. Beschreiben Sie, wie ein White-Box-orientierter Test zusätzlich aussehen könnte.

**Lösung**  

1. **Unit-/Modultest**, sofern die Methode isoliert geprüft wird.
2. Nach einer Änderung werden bestehende Tests wiederholt, um unbeabsichtigte Beschädigungen vorher funktionierenden Verhaltens zu erkennen. Das ist der Zweck eines Regressionstests.
3. **Black-Box:** Aus der fachlichen Spezifikation Eingaben und erwartete Summen ableiten, ohne Implementierungsdetails zu verwenden.
4. **White-Box:** Kontrollflusszweige betrachten und Testdaten wählen, die beispielsweise sowohl den Additionszweig als auch den alternativen Zweig ausführen.
