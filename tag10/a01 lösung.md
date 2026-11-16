# Aufgabe – Fehlerarten klassifizieren

Ordnen Sie jedem Fall eine der Fehlerarten Compilerfehler, Laufzeitfehler oder logischer Fehler zu. Begründen Sie Ihre Zuordnung anhand des Codes und der gegebenen Anforderungen.

## A1

```java
void myMethod() {
    int anzahl = 5;
    System.out.println(anzal);
}
```

**Lösung**  

Compilerfehler. Die Variable `anzal` ist nicht deklariert. Vermutlich ist `anzahl` gemeint. Der Java-Compiler kann den Bezeichner nicht auflösen.

## A2

```java
int[] werte = {4, 8, 2};
System.out.println(werte[3]);
```

**Lösung**  

Ein Array mit drei Elementen hat die gültigen Indizes 0, 1 und 2. Der Zugriff `werte[3]` führt bei Ausführung zu einem Laufzeitfehler (ArrayIndexOutOfBoundsException).

## A3

```text
FUNKTION istBestanden(punkte : Ganzzahl) : Boolean
    ZURÜCKGEBEN: punkte > 50
ENDE FUNKTION
```

Die fachliche Vorgabe lautet: Ab genau 50 Punkten gilt die Prüfung als bestanden.

**Lösung**  
Logischer Fehler. Die Bedingung `punkte > 50` liefert bei genau 50 Punkten FALSCH, obwohl die Prüfung laut Anforderung bestanden ist. Korrekt wäre `punkte >= 50`.

## A4

```java
static int berechneSumme(int[] werte) {
    int summe = 1;

    for (int i = 0; i<werte.length; i++) {
        summe = summe + werte[i];
    }

    return summe;
}
```

Die Funktion `berechneSumme` soll die Summe der Werte eines Arrays berechnen. Sie gibt aber beim Testen für das Array `{2, 3, 4}` den Wert 10 zurück.

**Lösung**  
Logischer Fehler. Es wird falscher Startwert für die Variable `summe` verwendet. Neutraler Startwert für die Addition ist 0.

