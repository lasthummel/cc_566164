# Aufgabe – Schleifenfehler lokalisieren

Eine Methode soll alle ganzen Zahlen von 1 bis einschließlich `grenze` addieren.

Gültige Annahme für diese Aufgabe: `grenze` ist nicht negativ. 

```java
static int summeBis(int grenze) {
    int summe = 1;
    for (int i = 1; i < grenze; i++) {
        summe += i;
    }
    return summe;
}
```

## A1 - Analysieren
Nennen Sie zwei voneinander unabhängige Fehler und erklären Sie ihre Auswirkung.

**Lösung**  

1. `summe = 1` ist als Initialisierung falsch, wenn alle Zahlen von 1 bis zur Grenze addiert werden sollen: Der Wert 1 wird zusätzlich vor der Schleife gezählt.
2. `i < grenze` schließt die Grenze aus. Für die inklusive Vorgabe muss die Schleife bis `i <= grenze` laufen.

## A2 - Korrigieren
Schreiben Sie eine korrigierte Methode.

**Lösung**  

```java
static int summeBis(int grenze) {
    int summe = 0;
    for (int i = 1; i <= grenze; i++) {
        summe += i;
    }
    return summe;
}
```

## A3 - Testfälle
Geben Sie drei Testfälle mit Eingabe und erwartetem Ergebnis an. Berücksichtigen Sie mindestens einen Grenzfall.

**Lösung**  

| Eingabe `grenze` | Erwartetes Ergebnis | Begründung |
|---:|---:|---|
| 1 | 1 | kleinster positiver Grenzfall |
| 5 | 15 | 1 + 2 + 3 + 4 + 5 |
| 0 | 0 | keine positiven Zahlen im Bereich 1 bis 0 |


## A4 - Begründen
Warum ist ein einzener Test kein ausreichender Nachweis der Korrektheit?

**Lösung**  

Ein einzelner Test prüft nur eine Eingabe. Er kann Fehler bei der kleinsten Grenze, bei leeren Bereichen oder bei anderen Schleifenenden übersehen. Testfälle sollten unterschiedliche relevante Fälle abdecken.
