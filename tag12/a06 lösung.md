# Aufgabe – Integrierte Fallstudie

Ein Webshop berechnet Versandkosten nach folgender Spezifikation:

- Für Bestellwerte unter 50,00 € betragen die Versandkosten 5,90 €.
- Ab einem Bestellwert von 50,00 € ist der Versand kostenlos.
- Bestellwerte müssen numerisch und mindestens 0,00 € sein.
- Es wird auf Cent genau gerechnet.

## A1
Bestimmen Sie gültige und ungültige Äquivalenzklassen.

**Lösung**  

| Klasse | Beschreibung | Erwartung |
|---|---|---|
| K1 | Numerischer Bestellwert kleiner als 0,00 € | Zurückweisung |
| K2 | Numerischer Bestellwert von 0,00 € bis unter 50,00 € | Versandkosten 5,90 € |
| K3 | Numerischer Bestellwert ab 50,00 € | Versandkosten 0,00 € |
| K4 | Nichtnumerische Eingabe, sofern die Schnittstelle Rohtexte entgegennehmen kann | Zurückweisung |

## A2
Leiten Sie eine sinnvolle Grenzwert-Testmenge um 0,00 € und 50,00 € ab.

**Lösung**  

Bei Cent-genauer Eingabe:
- um 0,00 €: -0,01 €, 0,00 €, 0,01 €;
- um 50,00 €: 49,99 €, 50,00 €, 50,01 €.

Die Eingabe -0,01 € ist ungültig; 0,00 € und 0,01 € sind gültig und führen zu 5,90 € Versandkosten. 49,99 € führt zu 5,90 €; 50,00 € und 50,01 € führen zu 0,00 € Versandkosten.

## A3
Erstellen Sie eine Testfalltabelle mit mindestens fünf Testfällen und erwarteten Versandkosten bzw. erwarteter Zurückweisung.

| ID | Bestellwert | Klasse/Grenze | Erwartetes Ergebnis |
|---|---:|---|---|
| T1 | | | |
| T2 | | | |
| T3 | | | |
| T4 | | | |
| T5 | | | |

**Lösung**  

| ID | Bestellwert | Klasse/Grenze | Erwartetes Ergebnis |
|---|---:|---|---|
| T1 | -0,01 € | untere ungültige Klasse | Zurückweisung |
| T2 | 0,00 € | untere gültige Grenze | 5,90 € Versandkosten |
| T3 | 0,01 € | direkt über unterer Grenze | 5,90 € Versandkosten |
| T4 | 49,99 € | direkt unter Versandfreigrenze | 5,90 € Versandkosten |
| T5 | 50,00 € | Versandfreigrenze | 0,00 € Versandkosten |
| T6 | 50,01 € | direkt über Versandfreigrenze | 0,00 € Versandkosten |

Mindestens fünf Testfälle sind gefordert; sechs sind hier fachlich sinnvoll, weil zwei Grenzen geprüft werden.


## A4
Nennen Sie eine offene Spezifikationsfrage, die vor einer Implementierung geklärt werden müsste, sofern sie für die konkrete Schnittstelle relevant ist.

**Lösung**  

Mögliche Antworten:
- Wie werden nichtnumerische Eingaben, leere Eingaben oder fehlende Werte behandelt?
- Wird der Bestellwert vor oder nach Rabatten und vor oder nach Steuern betrachtet?
- Wie werden Rundungsdifferenzen bei intern höherer Präzision behandelt?

Eine offene Frage ist nur dann erforderlich, wenn sie für die konkrete Schnittstelle bzw. Fachlogik relevant ist. Aus der Spezifikation darf nicht einfach eine nicht genannte Regel abgeleitet werden.
