# Aufgabe 'Aktivitätsdiagramm'

Ein Unternehmen betreibt ein internes Schulungsportal.
Modellieren Sie den folgenden Ablauf als UML-Aktivitätsdiagramm:

1. Ein Mitarbeiter wählt eine Schulung aus.
2. Das System prüft, ob noch Plätze verfügbar sind.
3. Wenn kein Platz verfügbar ist, zeigt das System eine Fehlermeldung an und der Vorgang endet.
4. Wenn ein Platz verfügbar ist, gibt der Mitarbeiter seine Buchungsdaten ein.
5. Das System validiert die Daten.
6. Bei ungültigen Daten wird eine Fehlermeldung angezeigt.
7. Bei gültigen Daten wird die Anmeldung gespeichert.
8. Anschließend wird eine Bestätigung angezeigt.
9. Der Vorgang endet.

Kennzeichnen Sie die Bedingungen an den Verzweigungen.


**Lösung:**

Das Diagramm befindet sich in der uxf-Datei.

Bemerkung:  
Aufgabenbeschreibung bei Schritt 6 lediglich: "Bei ungültigen Daten wird eine Fehlermeldung angezeigt". Es wird nicht ausdrücklich festgelegt, was anschließend geschieht. Der Rücksprung zur Aktivität `Buchungsdaten eingeben` ist aus Anwendungssicht sinnvoll, noch besser wäre eine weitere Aktivität `Buchungsdaten korrigieren`. Die Situation sollte mit einem zuständigen fachlichen Stakeholder geklärt werden.

Bemerkung:  
Aufgabenbeschreibung unterscheidet ausdrücklich zwischen Mitarbeiter und System. Diese Trennung kann im Diagramm durch Aktivitätspartitionen (Swimlanes) dargestellt werden. Wenn ausdrücklich Aktivitätspartitionen oder die Zuordnung von Aktivitäten zu Verantwortlichen gefordert werden, müssen diese modelliert werden. Auf Aktivitätspartitionen kann man in der Praxis gut verzichten, wenn die Verantwortlichkeiten für das Verständnis des Prozesses nicht wesentlich sind.