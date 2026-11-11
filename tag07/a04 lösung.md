# Aufgabe - Verwaltung von Wartungsterminen

## Szenario

Ein Unternehmen entwickelt ein webbasiertes System zur Verwaltung von Wartungsterminen.

Anforderungen:
- Termine müssen korrekt gespeichert werden.
- Endzeit muss nach der Startzeit liegen.
- Nur berechtigte Mitarbeiter dürfen Termine löschen.
- Benutzer müssen nach dem Speichern eine eindeutige Rückmeldung erhalten.
- Änderungen am System sollen bestehende Funktionen möglichst nicht beeinträchtigen.
- Die Anwendung soll langfristig wartbar bleiben.

Probleme:
- saveMaintenance() enthält vier voneinander unabhängige if-Entscheidungen.
- Entwickler prüfen Änderungen bisher nur manuell.
- Code wird regelmäßig ohne Review in den Hauptbranch übernommen.

## A1
Nennen Sie vier relevante Qualitätsmerkmale für das Szenario.  

**Lösung.**  
- Funktionalität
- Sicherheit
- Usability
- Wartbarkeit
- Zuverlässigkeit
- Performance/Effizienz ??? (im Szenario nicht erkennbar)

## A2
Entwickeln Sie ein kleines QS-Konzept mit mindestens vier Maßnahmen. Ordnen Sie jeder Maßnahme ein Qualitätsziel oder Risiko zu.  

**Lösung.**  
| Maßnahme | Ziel/Risiko |
|---|---|
| Unit-/Integrationstests | korrekte Speicherung und Validierungslogik |
| Berechtigungstests | unberechtigtes Löschen verhindern |
| Code Review | fachliche und strukturelle Fehler früh erkennen |
| statische Analyse | problematische Konstruktionen erkennen |
| CI | Tests/Analyse bei Änderungen automatisiert ausführen |
| Pull-Requests einführen | kein Übernehmen in main ohne Review |
| Komplexitätsmetrik | auffällig komplexe Methoden identifizieren |

## A3
Begründen Sie, warum ein Code Review für dieses Projekt sinnvoll ist.  

**Lösung.**  
Viwe-Augen-Prinzip! Ein Code Review ist sinnvoll, weil eine zweite Person die Implementierung unabhängig prüfen kann. Dadurch können fachliche Fehler, unklare Konstruktionen und Wartbarkeitsprobleme früh erkannt werden. Außerdem werden Änderungen vor der Integration kontrolliert.

## A4
Bestimmen Sie die zyklomatische Komplexität von saveMaintenance(), wenn der Kontrollfluss zusammenhängend ist und die vier if-Entscheidungen jeweils einen zusätzlichen unabhängigen Pfad erzeugen.  

**Lösung.**  
Bei vier unabhängigen if-Entscheidungen in einem zusammenhängenden Kontrollfluss:

    M = Anzahl der Entscheidungen + 1

Damit ist M = 4 + 1 = 5

## A5
Beurteilen Sie, ob die zyklomatische Komplexität allein ausreicht, um die Qualität der Methode zu bewerten.  

**Lösung.**  
Nein.

Die Metrik beschreibt Kontrollflusskomplexität. Sie kann auf erhöhten Test- und Wartungsaufwand hinweisen, sagt allein aber nicht, ob die fachliche Funktion korrekt ist. Usability, Sicherheit, Performance und andere Qualitätsaspekte werden dadurch nicht vollständig bewertet.

## A6
Erläutern Sie, warum erfolgreiche automatisierte Tests nicht beweisen, dass keine Fehler vorhanden sind.  

**Lösung.**  

Erfolgreiche Tests zeigen, dass die getesteten Szenarien das erwartete Ergebnis geliefert haben. Nicht getestete Pfade, Eingaben, Zustandskombinationen oder Integrationsprobleme können weiterhin Fehler enthalten.
