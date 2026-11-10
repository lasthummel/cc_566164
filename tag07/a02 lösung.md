# Aufgabe – QS-Maßnahmen auswählen

### Szenario

Ein Zahlungssystem soll erweitert werden.

Anforderungen und Risiken:
- Berechtigungen dürfen nicht versehentlich ausgeweitet werden.
- Neue Änderungen sollen bestehende Funktionen möglichst nicht beschädigen.
- Quellcode soll langfristig wartbar bleiben.
- Fehler in komplexen Methoden sollen früh erkannt werden.
- Die Entwickler arbeiten zu dritt an demselben Repository.

Zur Auswahl stehen:
- Code Review
- statische Codeanalyse
- automatisierte Unit-Tests
- Integrationstests
- manuelle Abnahmetests
- kontinuierliche Integration (CI)
- zyklomatische Komplexität als Metrik

### Aufgaben

## A1
Ordnen Sie jedem Risiko mindestens eine geeignete Maßnahme zu.

**Lösung.**  

| Risiko | Geeignete Maßnahme |
|---|---|
| Berechtigungen nicht versehentlich ausweiten | Code Review + automatisierte Tests, manuelle Abnahmetests |
| Regressionen | Unit-/Integrationstests + CI |
| Wartbarkeit | Code Review + statische Analyse |
| komplexe Methoden | automatisierte Unit-Tests, statische Analyse + Komplexitätsmetrik |
| drei Entwickler am Repository | Code Review + CI |


## A2
Begründen Sie drei Zuordnungen.

**Lösung.**  

- Code Review: Ein zweiter Entwickler kann fachliche und strukturelle Probleme erkennen, bevor der Code übernommen wird.

- Statische Analyse: Sie kann bestimmte problematische Konstruktionen automatisiert erkennen, ohne alle Ausführungspfade auszuführen.

- CI: Nach einer Änderung können automatisierte Prüfungen reproduzierbar ausgeführt werden, bevor die Änderung in eine gemeinsame Version übernommen wird.

## A3
Erläutern Sie bei einer Maßnahme eine Grenze ihrer Aussagekraft.

**Lösung.**  

- Statische Analyse kann regelbasierte bzw. strukturelle Probleme finden, beweist aber nicht, dass die fachlichen Anforderungen erfüllt sind.

- Unit-Tests prüfen nur die implementierten Testfälle und ersetzen keine Integrationstests oder fachliche Abnahme.

## A4
Beurteilen Sie die Aussage:

"Wir benötigen nur automatisierte Tests; Reviews und statische Analyse sind überflüssig."

**Lösung.**  
Die Aussage ist nicht sinnvoll.

Tests prüfen beobachtbares Verhalten unter den gewählten Bedingungen. Reviews können fachliche und strukturelle Probleme erkennen; statische Analyse kann bestimmte Problemklassen automatisiert erkennen. Die Maßnahmen ergänzen sich.

