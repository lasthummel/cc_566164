# Aufgabe - Zyklomatische Komplexität (Cyclomatic Complexity)

## A1
Definieren Sie die zyklomatische Komplexität. Nennen Sie zwei Zwecke, für die sie verwendet wird.

**Lösung.**  
Die zyklomatische Komplexität ist eine Softwaremetrik, mit der man die Komplexität des Kontrollflusses einer Methode oder Funktion bewertet.

Sie wird hauptsächlich für zwei Zwecke verwendet:

1. Softwarequalität: Erkennen von Methoden mit komplexer Entscheidungslogik, die möglicherweise schwer zu verstehen, zu testen und zu warten sind.
2. Softwaretests: Ermitteln einer Grundlage für die Anzahl unabhängiger Ausführungspfade, die beim strukturellen Testen berücksichtigt werden sollten.

## A2
Gegeben ist der Code:

```java
public int berechneRabatt(int alter, boolean mitglied) {
    int rabatt = 0;

    if (alter < 18) {
        rabatt = 10;
    }

    if (mitglied) {
        rabatt += 5;
    }

    return rabatt;
}
```

Berechnen Sie die Zyklomatische Komplexität M nach der fereinfachten Formel:

    M = 1 + Anzahl der Entscheidungspunkte

**Lösung.**

M = 1 + 2 = 3

## A3
Beantworten Sie die folgenden Fragen zur Methode `berechneRabatt`aus der Teilaufgabe A2:

a) Ist die zyklomatische Komplexität gleich der Gesamtzahl aller möglichen Ausführungspfade? Begründen Sie Ihre Antwort anhand der gegebenen Methode.

b) Bestimmt die zyklomatische Komplexität, wie viele Testfälle für eine ausreichende Prüfung einer Methode erforderlich sind? Begründen Sie Ihre Antwort.

c) Erstellen Sie Testfälle, die alle möglichen Kombinationen der beiden if-Bedingungen abdecken. Geben Sie jeweils die Eingabewerte und das erwartete Ergebnis an.

**Lösung.** 

a) Nein. Die zyklomatische Komplexität beträgt 3. Die Methode besitzt jedoch 4 mögliche vollständige Ausführungspfade, da beide if-Bedingungen unabhängig voneinander wahr oder falsch sein können.
Die zyklomatische Komplexität zählt die Dimension des Raums linear unabhängiger Pfade des Kontrollflussgraphen und nicht sämtliche möglichen Pfade.

b) Nein. Die zyklomatische Komplexität gibt die Anzahl linear unabhängiger Kontrollflusspfade an. Die erforderliche Anzahl der Testfälle hängt auch von den Testzielen und fachlichen Anforderungen ab.

c) Tests

| Testfall | Alter | Mitglied | Rabatt |
|---|---|---|---|
| 1 | 16 | Ja | 15% |
| 2 | 16 | Nein | 10%
| 3 | 25 | Ja | 5% |
| 4 | 25 | Nein | 0% |

