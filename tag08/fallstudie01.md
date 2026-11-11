# Fallstudie 1 - Anforderungsanalyse und Programmspezifikation

**Zeit:** 90 Minuten  
**Kompetenzschwerpunkte:** Anforderungen analysieren; funktionale und nichtfunktionale Anforderungen unterscheiden; Unklarheiten und Annahmen erkennen; Akzeptanzkriterien formulieren; eine Prozessspezifikation entwickeln.

**Hinweis:** Fehlende Messwerte oder fachliche Regeln dürfen nicht stillschweigend erfunden werden. Kennzeichnen Sie eigene Vorschläge als Annahmen bzw. Klärungsbedarf.

## Ausgangssituation

Die Firma **Nordlicht Bildungstechnik GmbH** verleiht Mitarbeitenden für Schulungen und Kundentermine mobile Präsentationstechnik. Derzeit werden Ausleihen über eine gemeinsam genutzte Tabellenkalkulation verwaltet. Dadurch entstehen Doppelbuchungen, unklare Rückgabetermine und Rückfragen zum Verbleib von Geräten.

Die Geschäftsleitung möchte eine interne Webanwendung einführen. Die erste Version soll in etwa zwölf Wochen nutzbar sein. Es gibt rund 80 Mitarbeitende und etwa 150 ausleihbare Geräte. Die Anwendung wird zunächst nur im Unternehmensnetz eingesetzt.

## Beteiligte Personen

- Mitarbeitende: suchen verfügbare Geräte und stellen Ausleihanfragen.
- Geräteverwaltung: pflegt Geräte, entscheidet über Anfragen und dokumentiert Rückgaben.
- IT-Abteilung: betreibt die Anwendung und verwaltet Benutzerkonten.
- Geschäftsleitung: erwartet weniger Verwaltungsaufwand und weniger Doppelbuchungen.

## Aussagen aus dem Gespräch mit dem Auftraggeber

1. Mitarbeitende sollen Geräte nach Bezeichnung und Geräteart suchen können.
2. Man soll sofort erkennen, ob ein Gerät verfügbar ist.
3. Eine Ausleihe muss mit Start- und Rückgabedatum angefragt werden können.
4. Die Geräteverwaltung muss Anfragen annehmen oder ablehnen können.
5. Bei einer Annahme muss das Gerät für den angefragten Zeitraum reserviert sein.
6. Niemand darf dasselbe Gerät für überlappende Zeiträume verbindlich ausleihen.
7. Rückgaben müssen dokumentiert werden können.
8. Die Anwendung soll einfach und schnell sein.
9. Nur Mitarbeitende mit entsprechender Berechtigung dürfen Geräte verwalten oder Anfragen entscheiden.
10. Die Anwendung muss im vorhandenen Unternehmensnetz betrieben werden.
11. Die Geschäftsleitung möchte möglichst geringe Betriebskosten.
12. Später könnten E-Mail-Erinnerungen vor dem Rückgabetermin ergänzt werden.
13. Für die erste Version wird keine Abrechnung und keine externe Kundenverwaltung benötigt.
14. Die Lösung soll modern sein und mit möglichst wenig Aufwand programmiert werden.

## Vorläufige technische Informationen

- Im Unternehmen existiert bereits ein zentraler Verzeichnisdienst für Mitarbeitendenkonten.
- Eine relationale Datenbank wird von anderen internen Anwendungen genutzt.
- Für die Entwicklung stehen zwei Personen zur Verfügung. Eine Person hat Erfahrung mit Java und Spring Boot, die andere vor allem mit JavaScript.
- Es ist noch nicht entschieden, ob eine bestehende interne Webplattform erweitert oder eine eigenständige Anwendung erstellt wird.
- Konkrete Anforderungen an Antwortzeiten, Verfügbarkeit, Datensicherung und Aufbewahrungsfristen liegen noch nicht vor.


## A1 – Anforderungen extrahieren und klassifizieren (ca. 20 Minuten)

### a)
Klassifizieren Sie mindestens acht Aussagen aus dem Auftraggebergespräch als funktionale Anforderung, nichtfunktionale Anforderung, Randbedingung oder technische Vorentscheidung.

### b)
Begründen Sie bei mindestens vier Aussagen Ihre Zuordnung. Berücksichtigen Sie, dass eine Aussage mehrere Aspekte enthalten kann.

### c)
Identifizieren Sie mindestens drei Aussagen, die noch zu ungenau, nicht überprüfbar oder fachlich klärungsbedürftig sind.

## A2 – Unklare Anforderungen verbessern (ca. 15 Minuten)

Formulieren Sie drei unklare Anforderungen so um, dass sie eindeutiger und überprüfbarer werden.

- Mindestens eine Anforderung muss sich auf die Verfügbarkeit bzw. Überschneidung von Ausleihzeiträumen beziehen.
- Mindestens eine Anforderung muss sich auf Geschwindigkeit, Bedienbarkeit oder Betrieb beziehen.
- Mindestens eine Anforderung muss sich auf Berechtigungen oder die Verwaltung beziehen.

Wenn der Auftraggeber noch keine konkreten Werte oder Regeln festgelegt hat, formulieren Sie zunächst einen Vorschlag und kennzeichnen Sie ihn als **Annahme zur Abstimmung**. Erfinden Sie keine verbindlichen Geschäftsregeln.

## A3 – Offene Punkte und Annahmen (ca. 10 Minuten)

Nennen und priorisieren Sie vier offene Fragen, die vor einer belastbaren Spezifikation geklärt werden sollten.

Mindestens zwei Fragen müssen fachliche Regeln betreffen, zum Beispiel:
- Wann gilt ein Gerät als verbindlich reserviert?
- Wie werden verspätete Rückgaben behandelt?
- Darf eine Anfrage geändert oder zurückgezogen werden?
- Was geschieht, wenn zwei Anfragen für denselben Zeitraum gleichzeitig eingehen?

Erklären Sie jeweils kurz, warum die Antwort für die Umsetzung oder die Testbarkeit relevant ist.

## A4 – Akzeptanzkriterien entwickeln (ca. 15 Minuten)

Entwickeln Sie für die folgenden Anforderungen jeweils mindestens ein überprüfbares Akzeptanzkriterium:

1. Mitarbeitende können eine Ausleihe anfragen.
2. Die Geräteverwaltung kann eine Anfrage annehmen oder ablehnen.
3. Überlappende verbindliche Ausleihen desselben Geräts sind nicht zulässig.

Formulieren Sie so, dass ein Tester das erwartete Verhalten anhand konkreter Vorbedingungen, einer Aktion und eines Ergebnisses prüfen kann. Verwenden Sie bei nicht vorgegebenen Regeln ausdrücklich eine gekennzeichnete Annahme.

## A5 – Prozessspezifikation für eine Ausleihanfrage (ca. 15 Minuten)

Entwickeln Sie eine kurze, technologieunabhängige Programmspezifikation für das Anlegen einer Ausleihanfrage.

Ihre Spezifikation muss enthalten:
- Vorbedingungen,
- Eingabedaten,
- wesentliche Verarbeitungsschritte,
- Erfolgsfall,
- mindestens zwei Fehlerfälle mit erwarteter Reaktion.

Achten Sie insbesondere darauf, zwischen einer Anfrage und einer verbindlichen Reservierung zu unterscheiden. Legen Sie nicht ohne Kennzeichnung fest, ob ein Gerät bereits beim Anlegen einer Anfrage blockiert wird.
