## Aufgabe 'Werkstatt-Terminportal'

### Szenario: Werkstatt-Terminportal

Eine Autowerkstatt möchte ein Online-Terminportal einführen.

Kunden sollen freie Termine sehen und einen Termin buchen können. Für die Buchung müssen Name, E-Mail-Adresse und Kennzeichen angegeben werden.

Nach erfolgreicher Buchung soll der Kunde eine Bestätigung erhalten.

Die Mitarbeiter der Werkstatt sollen Termine anzeigen und bearbeiten können.

Termine dürfen nur von Mitarbeitern gelöscht werden.

Das System soll auch bei hoher Auslastung gut reagieren.

Die Werkstatt möchte zunächst nur deutschsprachige Benutzer unterstützen.

Die Anwendung soll auf den vorhandenen Unternehmensrechnern betrieben werden können.

**1**. 
Leiten Sie aus dem Text mindestens acht Anforderungen ab.

| Nr. | Anforderung |
|---:|---|
| R01 | Kunden müssen freie Termine anzeigen können. |
| R02 | Kunden müssen einen freien Termin buchen können. |
| R03 | Für eine Buchung müssen Name, E-Mail-Adresse und Kennzeichen erfasst werden. |
| R04 | Nach erfolgreicher Buchung soll dem Kunden eine Buchungsbestätigung übermittelt werden. |
| R05 | Mitarbeiter müssen vorhandene Termine anzeigen können. |
| R06 | Mitarbeiter müssen vorhandene Termine bearbeiten können. |
| R07 | Termine dürfen nur von Mitarbeitern gelöscht werden. |
| R08 | Das System soll auch bei hoher Auslastung ausreichend performant reagieren. |
| R09 | Das System soll zunächst nur deutschsprachige Benutzer unterstützen. |
| R10 | Die Anwendung muss auf den vorhandenen Unternehmensrechnern betrieben werden können. |


**2.**
Kennzeichnen Sie:

- funktionale Anforderungen,
- nichtfunktionale Anforderungen,
- Randbedingungen.

R01: Funktional - Konkrete Funktion: freie Termine anzeigen.

R02: Funktional - Konkrete Funktion: Termin buchen.

R03: Funktional - Beschreibt die erforderlichen Eingaben für eine Buchung.

R04: Funktional - Beschreibt das Systemverhalten nach erfolgreicher Buchung.

R05: Funktional - Mitarbeiter können Termine anzeigen.

R06: Funktional - Mitarbeiter können Termine bearbeiten.

R07: Funktional (mit Berechtigungsaspekt) - Beschreibt, unter welcher Berechtigung das Löschen zulässig ist.

R08: Nichtfunktional - Beschreibt eine Performance-Anforderung, ist aber noch nicht ausreichend konkret.

R09: Randbedingung - Schränkt den zunächst unterstützten Benutzerkreis bzw. die Sprache ein.

R10: Randbedingung - Gibt die vorhandene Betriebsumgebung als Vorgabe vor.


**3.**
Priorisieren Sie Ihre Anforderungen mit:

- Must
- Should
- Could

R01: Must - Ohne Anzeige freier Termine kann kein geeigneter Termin ausgewählt werden.

R02: Must - Die Buchung ist eine Kernfunktion des Portals.

R03: Must - Die Angaben sind laut Szenario für eine Buchung erforderlich.

R04: Should - Für einen vollständigen Buchungsprozess wichtig, aber nicht die eigentliche Kernfunktion.

R05: Must - Mitarbeiter benötigen Zugriff auf die Terminverwaltung.

R06: Must - Die Bearbeitung von Terminen ist ausdrücklich vorgesehen.

R07: Must  - Die Einschränkung ist eine explizite fachliche Vorgabe und betrifft die Zugriffssicherheit.

R08: Should - Performance ist wichtig, die Anforderung muss allerdings noch konkretisiert werden.

R09: Must - Die Werkstatt möchte zunächst ausschließlich deutschsprachige Benutzer unterstützen.

R10: Must - Der Betrieb auf vorhandenen Unternehmensrechnern ist eine vorgegebene Randbedingung.
