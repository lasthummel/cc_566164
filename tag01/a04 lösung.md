## Aufgabe 4 – Merge-Konflikt analysieren

Ausgangsstand auf `main`:

```java
int maxRetries = 3;
```

Branch `feature-retry-policy` ändert die Zeile zu `int maxRetries = 5;`, Branch `feature-network-policy` zu `int maxRetries = 2;`.

### 4.1 Ursache erläutern

Erläutern Sie, weshalb Git diese Änderungen nicht automatisch fachlich korrekt zusammenführen kann.

Beide Branches ändern dieselbe Zeile unterschiedlich. Git kennt die fachliche Regel nicht, welcher Wert in welcher Situation gelten soll.

### 4.2 Konfliktlösung entwickeln

Die Anforderung lautet: Bei normalen Anfragen sind höchstens fünf Versuche erlaubt; bei kostenpflichtigen externen Schnittstellen höchstens zwei. Beschreiben Sie eine passende Lösung. Die bloße Auswahl einer der beiden Zeilen genügt nicht.

    int maxRetries(boolean isPaidService) {
        if (isPaidService) 
            return 2;
        else
            return 5;
    }

### 4.3 Qualität sichern

Nennen Sie zwei Prüfungen, die vor dem Merge bzw. nach der Konfliktauflösung sinnvoll sind, und begründen Sie diese.

- Tests für beide Fälle: normale Anfrage ergibt 5, kostenpflichtige externe Schnittstelle ergibt 2.
- Build und relevante Tests nach der Konfliktauflösung ausführen, um Syntax-, Integrations- oder Verhaltensfehler zu erkennen.
- Zusätzlich ist ein Code Review zur Prüfung der fachlichen Anforderung sinnvoll.
