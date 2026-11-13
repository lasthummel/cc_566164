# Aufgabe – Zuweisungen, Operatoren und Verzweigung

Gegeben ist der Code:

```java
int a = 5;
int b = 2;
a = a + b * 3;
if (a > 10 && b != 0) {
    b = a / b;
} else {
    b = b + 10;
}
System.out.println(a + ":" + b);
```

## A1

Verfolgen Sie den Code-Ablauf und füllen Sie dabei folgende Tabelle aus:

| Schritt | Anweisung/Prüfung | a | b | Ergebnis der Bedingung |
|---:|---|---:|---:|---|
| 1 | Initialisierung a | | | |
| 2 | Initialisierung b | | | |
| 3 | a = a + b * 3 | | | |
| 4 | a > 10 | | | |
| 5 | b != 0 und Gesamtbedingung | | | |
| 6 | Zuweisung im ausgeführten Zweig | | | |
| 7 | Ausgabe | | | |

Tragen Sie nach jeder Anweisung die aktuellen Werte der Variablen a und b ein. Zerlegen Sie die zusammengesetzte if-Bedingung zur Auswertung in ihre beiden Teilbedingungen. Verwenden Sie –, wenn eine Variable noch nicht initialisiert wurde oder kein Bedingungsergebnis vorliegt.

**Lösung**  

| Schritt | Anweisung/Prüfung | a | b | Ergebnis der Bedingung |
|---:|---|---:|---:|---|
| 1 | Initialisierung a | 5 | - | - |
| 2 | Initialisierung b | 5 | 2 | - |
| 3 | a = a + b * 3 | 11 | 2 | - |
| 4 | a > 10 | 11 | 2 | true |
| 5 | b != 0 und Gesamtbedingung | 11 | 2 | true, true |
| 6 | b = a / b | 11 | 5 | - |
| 7 | Ausgabe | 11 | 5 | - |

## A2
Welche Zeichenfolge wird ausgegeben?

**Lösung**  
Ausgabe: 

    11:5

## A3
Erläutern Sie die Auswertungsreihenfolge des Ausdrucks a + b * 3 und beschreiben Sie anschließend, wie die Zuweisung a = a + b * 3 den Wert der Variablen a verändert.

**Lösung**  

1. Multiplikation: b * 3 = 2 * 3 = 6
2. Addition: a + 6 = 5 + 6 = 11
3. Zuweisung: Der Wert 11 wird in a gespeichert und ersetzt den bisherigen Wert 5.
