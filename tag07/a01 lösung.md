# Aufgabe – Qualitätsanalyse

## Szenario

Ein Unternehmen setzt ein internes Ticketsystem ein.

Bei einer ersten Erprobung wurden folgende Beobachtungen gemacht:

1. Eine Suche nach Tickets dauert bei großen Datenbeständen teilweise 8 Sekunden.
2. Mitarbeiter können Tickets ohne klare Rückmeldung speichern.
3. Ein Benutzer kann durch einen manipulierten Request fremde Tickets löschen.
4. Eine Methode processTicket() enthält viele verschachtelte Verzweigungen und ist schwer verständlich.
5. Ein Teil der Geschäftslogik wird nur manuell getestet.
6. Änderungen am Code werden direkt in den Hauptbranch übernommen.

## A1
Analysieren Sie die sechs Beobachtungen und ordnen Sie jeweils mindestens ein betroffenes Qualitätsmerkmal zu.

**Lösung.**  

1. Eine Suche nach Tickets dauert bei großen Datenbeständen teilweise 8 Sekunden.
Qualitätsmerkmale: Effizienz/Performance  

2. Mitarbeiter können Tickets ohne klare Rückmeldung speichern.
Qualitätsmerkmale: Usability/Benutzerführung, Fehlervermeidung  

3. Ein Benutzer kann durch einen manipulierten Request fremde Tickets löschen.
Qualitätsmerkmale: Sicherheit/Integrität  

4. Eine Methode processTicket() enthält viele verschachtelte Verzweigungen und ist schwer verständlich.
Qualitätsmerkmal: Wartbarkeit (Analysierbarkeit / Modifizierbarkeit)

5. Ein Teil der Geschäftslogik wird nur manuell getestet.
Qualitätsmerkmale: Qualitätssicherung/Testbarkeit  

6. Änderungen am Code werden direkt in den Hauptbranch übernommen.
Qualitätsmerkmale: Prozessqualität / Stabilität, Integrations-/Änderungsrisiko  


Andere fachlich begründete Zuordnungen sind möglich.

## A2
Entwickeln Sie für vier Beobachtungen jeweils eine geeignete Qualitätssicherungsmaßnahme.

**Lösung.**  
1. Eine Suche nach Tickets dauert bei großen Datenbeständen teilweise 8 Sekunden.
Qualitätsmerkmale: Effizienz/Performance  
Qualitätssicherungsmaßnahme: Performance-Test und Analyse der Datenbankabfrage.  

2. Mitarbeiter können Tickets ohne klare Rückmeldung speichern.
Qualitätsmerkmale: Usability/Benutzerführung  
Qualitätssicherungsmaßnahme: Eine klare Rückmeldung integrieren, Review der Benutzer-Rückmeldungen bzw. gezielter Usability-Test.  

3. Ein Benutzer kann durch einen manipulierten Request fremde Tickets löschen.
Qualitätsmerkmale: Sicherheit/Integrität  
Qualitätssicherungsmaßnahme: Implementierung einer serverseitigen Berechtigungsprüfung, automatisierte Berechtigungstests und Code Review der Autorisierungslogik.  

4. Eine Methode processTicket() enthält viele verschachtelte Verzweigungen und ist schwer verständlich.
Qualitätsmerkmal: Wartbarkeit  
Qualitätssicherungsmaßnahme: Code Review und statische Analyse, komplexe Methode ggf. refaktorieren: Verschachtlung aufbrechen in kleinere Logikblöke...  

5. Ein Teil der Geschäftslogik wird nur manuell getestet.
Qualitätsmerkmale: Qualitätssicherung/Testbarkeit  
Qualitätssicherungsmaßnahme: automatisierte Unit-/Integrationstests ergänzen.  

6. Änderungen am Code werden direkt in den Hauptbranch übernommen.
Qualitätsmerkmale: Integrations-/Änderungsrisiko  
Qualitätssicherungsmaßnahme: Review und automatisierte Prüfungen vor Integration.

## A3
Begründen Sie zwei Ihrer Maßnahmen mit Bezug auf das jeweilige Risiko.

**Lösung.**  
- Ein automatisierter Berechtigungstest ist geeignet, weil damit überprüft werden kann, dass ein Benutzer ohne erforderliche Berechtigung den Löschvorgang nicht erfolgreich ausführen kann.

- Ein Code Review ist geeignet, weil ein zweiter Entwickler die Autorisierungslogik prüfen kann und Fehler frühzeitig vor der Integration erkannt werden können.

## A4
Beurteilen Sie die Aussage:

"Wenn alle vorhandenen Tests erfolgreich sind, kann die Anwendung als fehlerfrei betrachtet werden."

**Lösung.**  
Die Aussage ist falsch.

Tests prüfen konkrete Testfälle und Eingaben. Nicht getestete Zustände, Datenkombinationen oder Ausführungspfade können weiterhin Fehler enthalten. Auch Fehler in Tests selbst sind möglich.

