# Aufgabe UI

### Szenario

Ein Unternehmen entwickelt ein webbasiertes Ticketsystem.

Ein Supportmitarbeiter soll:

- offene Tickets anzeigen,
- ein Ticket auswählen,
- die Priorität ändern,
- einen Kommentar erfassen,
- Änderungen speichern.

Randbedingungen:

- Die Benutzer arbeiten täglich mehrere Stunden mit dem System.
- Einige Benutzer bedienen Anwendungen bevorzugt per Tastatur.
- Fehlbedienungen sollen möglichst vermieden werden.
- Kritische Aktionen sollen bestätigt werden.

## A1
Nennen Sie fünf Anforderungen an die ergonomische Gestaltung der Oberfläche.

**Lösung.**  

- konsistente Bedienung
- eindeutige Beschriftungen
- verständliche Benutzerführung
- sichtbare Rückmeldungen
- Fehlertoleranz
- Tastaturbedienbarkeit
- übersichtliche Informationsdarstellung
- verständliche Fehlermeldungen
- Bestätigung kritischer Aktionen
- ausreichende Unterscheidbarkeit von Bedienelementen

## A2
Beschreiben Sie zwei geeignete Maßnahmen zur Unterstützung der Tastaturbedienung.

**Lösung.**  
- alle interaktiven Elemente müssen per Tab-Taste erreichbar sein und eine sinnvolle Fokusreihenfolge besitzen.
- Fokuszustand muss sichtbar sein
- Aktionen müssen ohne zwingende Mausinteraktion ausführbar sein.

Andere Antworten sind möglich.

## A3
Beurteilen Sie folgende Gestaltung: Die Priorität wird ausschließlich durch die Farben Rot, Gelb und Grün dargestellt. Zusätzlich gibt es ein Icon ohne Beschriftung.

**Lösung.**  
Die Gestaltung ist kritisch zu beurteilen:

- Farbe allein ist keine ausreichende Informationsquelle.
- Nutzer mit Farbsehschwächen können die Unterscheidung nicht zuverlässig wahrnehmen.
- Ein unbeschriftetes Icon kann zusätzlich mehrdeutig sein.
- Ergänzend sollten Text, eindeutige Bezeichnungen oder andere unterscheidbare Merkmale verwendet werden.

## A4
Entwickeln Sie eine geeignete Fehlermeldung für den Fall, dass ein Kommentar leer gespeichert werden soll.  

**Lösung.**  

"Der Kommentar darf nicht leer sein. Bitte geben Sie einen Kommentar ein."

## A5
Begründen Sie, warum ein Dialog zur Bestätigung einer kritischen Aktion sinnvoll sein kann.

**Lösung.**  

Mögliche Begründung:

- verhindert versehentliche Ausführung
- macht die Tragweite (potenziell kritische Auswirkung) der Aktion transparent
- gibt dem Benutzer Gelegenheit, die Aktion abzubrechen

Eine Bestätigung sollte nicht für jede beliebige Aktion verwendet werden, weil unnötige Dialoge den Bedienfluss stören.

## A6
Entwickeln Sie eine einfache Struktur für die Ticketdetailansicht. Nennen Sie mindestens sechs UI-Elemente und ordnen Sie diese sinnvoll an.

**Lösung in drawio-Datei.**  

Auswahl der UI-Elemente:  
1. Ticketnummer – schreibgeschützte Textanzeige  (Label oder read-only Textfield)  

2. Betreff – schreibgeschützte Textanzeige (Label oder read-only Textfield)  
> Die Aufgabe sagt nicht, dass Betreff bearbeitet werden darf

3. Priorität – Dropdown-Liste  

4. Beschreibung – mehrzeiliges, schreibgeschütztes Textfeld (read-only Textarea)  

5. Neuer Kommentar – mehrzeiliges Eingabefeld (Textarea)  

6. Änderungen speichern – Schaltfläche (Button)  