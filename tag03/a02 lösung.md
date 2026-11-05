## Aufgabe 'Support Tickets'

### Szenario

Ein Unternehmen möchte ein internes System zur Verwaltung von Support-Tickets entwickeln.

Der Kunde liefert folgende Aussagen:

1. Mitarbeiter müssen Tickets erstellen können.
2. Ein Ticket soll möglichst schnell gespeichert werden.
3. Nur der Ersteller eines Tickets darf dessen Beschreibung ändern.
4. Administratoren dürfen jedes Ticket bearbeiten.
5. Das System soll einfach zu bedienen sein.
6. Die Anwendung muss im vorhandenen Unternehmensnetz betrieben werden.
7. Die Anwendung wird mit Spring Boot entwickelt.
8. Tickets müssen nach Priorität sortiert werden können.
9. Das System muss 1.000 gleichzeitig angemeldete Benutzer unterstützen.
10. Eine Anmeldung darf höchstens fünf Sekunden dauern.

### Aufgaben

a) Klassifizieren Sie die Aussagen (funktionale Anforderung, nichtfunktionale Anforderung, Randbedingung, technische Festlegung).


1. Mitarbeiter müssen Tickets erstellen können.  
Funktionale Anforderung.  
Beschreibt eine konkrete Funktion des Systems.

2. Ein Ticket soll möglichst schnell gespeichert werden.  
Nichtfunktionale Anforderung.  
Beschreibt eine Performance-Anforderung, ist aber noch nicht ausreichend konkret.

3. Nur der Ersteller eines Tickets darf dessen Beschreibung ändern.  
Funktionale Anforderung mit Sicherheits-/Berechtigungsaspekt.  
Beschreibt das erlaubte Systemverhalten abhängig von der Rolle bzw. Identität des Benutzers.

4. Administratoren dürfen jedes Ticket bearbeiten.  
Funktionale Anforderung mit Berechtigungsaspekt.  
Beschreibt, welche Aktion Administratoren ausführen dürfen.

5. Das System soll einfach zu bedienen sein.  
Nichtfunktionale Anforderung.  
Betrifft die Usability, ist aber subjektiv und nicht ausreichend messbar.

6. Die Anwendung muss im vorhandenen Unternehmensnetz betrieben werden.  
Randbedingung.  
Gibt eine organisatorische bzw. technische Rahmenbedingung für den Betrieb vor.

7. Die Anwendung wird mit Spring Boot entwickelt.  
Technische Festlegung.  
Legt eine konkrete Technologie fest und beschreibt nicht, was das System fachlich leisten muss.

8. Tickets müssen nach Priorität sortiert werden können.  
Funktionale Anforderung.  
Beschreibt eine konkrete Funktion.

9. Das System muss 1.000 gleichzeitig angemeldete Benutzer unterstützen.  
Nichtfunktionale Anforderung.  
Beschreibt eine Kapazitäts-/Skalierbarkeitsanforderung.

10. Eine Anmeldung darf höchstens fünf Sekunden dauern.  
Nichtfunktionale Anforderung.  
Beschreibt eine messbare Performance-Anforderung.

Einzelne Aussagen können fachlich mehrere Aspekte enthalten. Entscheidend ist die Begründung der Zuordnung.

Beispielsweise sind die Aussagen 3 und 4 funktionale Anforderungen, obwohl sie gleichzeitig Anforderungen an die Zugriffskontrolle enthalten. Für eine Prüfung ist eine nachvollziehbare Zuordnung wichtiger als eine vermeintlich einzig mögliche Bezeichnung.

b) Identifizieren Sie mindestens drei schlecht spezifizierte Anforderungen.

**Anforderung 2.** "Ein Ticket soll möglichst schnell gespeichert werden."

Problem:

- "möglichst schnell" ist nicht eindeutig.
- Es fehlt ein messbarer Grenzwert.
- Die Anforderung ist deshalb nicht eindeutig überprüfbar.
- Außerdem ist unklar, unter welchen Betriebsbedingungen die Zeit gemessen werden soll.

**Anforderung 5.** "Das System soll einfach zu bedienen sein."

Problem:

- "einfach" ist subjektiv.
- Es ist nicht definiert, woran die einfache Bedienbarkeit erkannt werden soll.
- Die Anforderung ist ohne weitere Konkretisierung nur schwer testbar.

**Anforderung 9.** "Das System muss 1.000 gleichzeitig angemeldete Benutzer unterstützen."

Die Aussage ist grundsätzlich deutlich besser spezifiziert als 2 und 5, weil sie eine konkrete Zahl enthält. Für eine belastbare technische Anforderung fehlen jedoch noch Randbedingungen, beispielsweise:

- Was bedeutet "unterstützen" konkret?
- Welche Aktionen führen diese Benutzer gleichzeitig aus?
- Welche Antwortzeiten müssen dabei eingehalten werden?
- Unter welchen System- und Netzbedingungen wird geprüft?

Die Zahl 1.000 allein beschreibt daher noch keine vollständige Lastanforderung.

c) Überarbeiten Sie diese Anforderungen.

Es gibt nicht nur eine einzig richtige Formulierung. Entscheidend ist, dass die überarbeitete Anforderung eindeutig, nachvollziehbar und überprüfbar wird.

**Anforderung 2** – Performance

Ausgang:
"Ein Ticket soll möglichst schnell gespeichert werden."

Mögliche Überarbeitung:

"Das Speichern eines Tickets muss innerhalb einer festgelegten maximalen Zeit abgeschlossen sein. Die maximale Zeit sowie die für die Messung geltenden Betriebsbedingungen sind im Rahmen der Spezifikation festzulegen."

Für eine bereits vollständig spezifizierte Anforderung müsste ein konkreter Grenzwert vorgegeben sein, beispielsweise:

"Das Speichern eines Tickets muss unter den definierten Betriebsbedingungen innerhalb von 2 Sekunden abgeschlossen sein."

Der Wert "2 Sekunden" ist hier nur ein Beispiel und nicht aus der Aufgabenstellung abgeleitet.

**Anforderung 5** – Usability

Ausgang:
"Das System soll einfach zu bedienen sein."

Mögliche Überarbeitung:

"Die Benutzeroberfläche muss die für die jeweiligen Benutzer vorgesehenen Funktionen verständlich darstellen und eine nachvollziehbare Benutzerführung ermöglichen. Die konkreten Usability-Kriterien sind vor der Abnahme festzulegen und zu prüfen."

Noch besser ist eine Konkretisierung über überprüfbare Akzeptanzkriterien, sofern diese im Projekt bekannt sind.

Beispiel:

"Ein Mitarbeiter muss ein neues Ticket über die dafür vorgesehene Eingabemaske erstellen können, ohne dass hierfür eine zusätzliche Anleitung erforderlich ist."

**Anforderung 9** – Kapazität

Ausgang:
"Das System muss 1.000 gleichzeitig angemeldete Benutzer unterstützen."

Mögliche Überarbeitung:

"Das System muss den gleichzeitigen Betrieb mit mindestens 1.000 angemeldeten Benutzern ermöglichen. Für die Abnahme sind die dabei auszuführenden Benutzeraktionen, die zulässigen Antwortzeiten und die übrigen Testbedingungen festzulegen."

Damit wird deutlich, dass "1.000 Benutzer" allein noch keine vollständige Lastspezifikation darstellt.

d) Identifizieren Sie technische Festlegungen.

Die eindeutigste technische Festlegung ist:

Anforderung 7. "Die Anwendung wird mit Spring Boot entwickelt."

Spring Boot ist eine konkrete Technologie bzw. ein Framework für die technische Umsetzung. Die Aussage beschreibt daher nicht die fachliche Anforderung, sondern eine Festlegung zur Implementierung.

Auch Aussage 6 enthält einen technischen bzw. infrastrukturellen Rahmen:

Anforderung 6. "Die Anwendung muss im vorhandenen Unternehmensnetz betrieben werden."

Diese Aussage ist jedoch eher als Randbedingung zu klassifizieren. Sie legt nicht unmittelbar eine konkrete Technologie fest, sondern schränkt die möglichen technischen Lösungen ein.
