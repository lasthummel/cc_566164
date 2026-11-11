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

**Lösung in c)**  

### b)
Begründen Sie bei mindestens vier Aussagen Ihre Zuordnung. Berücksichtigen Sie, dass eine Aussage mehrere Aspekte enthalten kann.

**Lösung in c)**  

### c)
Identifizieren Sie mindestens drei Aussagen, die noch zu ungenau, nicht überprüfbar oder fachlich klärungsbedürftig sind.

**Lösung.**  

1. Mitarbeitende sollen Geräte nach Bezeichnung und Geräteart suchen können.
Kategorie: Funktionale Anforderung  
Begründung: Beschreibt eine vom System bereitzustellende Funktion.  

2. Man soll sofort erkennen, ob ein Gerät verfügbar ist.  
Kategorie: Funktionale Anforderung  
Begründung: Beschreibt ein sichtbares Systemverhalten; "sofort" ist zusätzlich unpräzise.  

3. Eine Ausleihe muss mit Start- und Rückgabedatum angefragt werden können.  
Kategorie: Funktionale Anforderung  
Begründung: Beschreibt die Erfassung einer Anfrage. Validierungsregeln fehlen.

4. Die Geräteverwaltung muss Anfragen annehmen oder ablehnen können.  
Kategorie: Funktionale Anforderung  
Begründung: Beschreibt einen fachlichen Prozess. Berechtigung und Statusübergänge sind zu präzisieren.  

5. Bei einer Annahme muss das Gerät für den angefragten Zeitraum reserviert sein.  
Kategorie: Funktionale Anforderung (Geschäftsregel)  
Begründung: Definiert eine Zustandsänderung und eine fachliche Wirkung. Der genaue Zeitpunkt und die Atomarität der Reservierung müssen geklärt werden.  

6. Niemand darf dasselbe Gerät für überlappende Zeiträume verbindlich ausleihen.  
Kategorie: Funktionale Anforderung (Geschäftsregel)  
Begründung: Eine fachliche Integritätsregel; sie muss auch bei gleichzeitigen Anfragen eingehalten werden.  

7. Rückgaben müssen dokumentiert werden können.  
Kategorie: Funktionale Anforderung  
Begründung: Das System soll eine Rückgabe erfassen. Erforderliche Daten und mögliche Zustände sind offen.  

8. Die Anwendung soll einfach und schnell sein.
Kategorie: nichtfunktionale Ziel, noch nicht konkrett und messbar. Die Aussage ist schwammig.  
Begründung: Usability und Performance werden angesprochen, aber ohne überprüfbare Kriterien.  

9. Nur Mitarbeitende mit entsprechender Berechtigung dürfen Geräte verwalten oder Anfragen entscheiden.  
Kategorie: funktionale Anforderung und Sicherheitsanforderung zugleich  
Begründung: Rollen und Berechtigungen müssen festgelegt und prüfbar sein.  

10. Die Anwendung muss im vorhandenen Unternehmensnetz betrieben werden.  
Kategorie: Randbedingung  
Begründung: Legt die Betriebsumgebung fest.  

11. Die Geschäftsleitung möchte möglichst geringe Betriebskosten.  
Kategorie: nichtfunktionale Anforderung (wirtschaftliches Qualitätsziel, noch nicht konkrett)  
Begründung: Ohne Kostenrahmen oder Vergleichsmaßstab nicht ausreichend überprüfbar.  

12. Später könnten E-Mail-Erinnerungen vor dem Rückgabetermin ergänzt werden.  
Kategorie: keine, mögliche spätere Erweiterung  
Begründung: Noch keine verbindliche Anforderung für die erste Version.  

13. Für die erste Version wird keine Abrechnung und keine externe Kundenverwaltung benötigt.  
Kategorie: Randbedingung (Umfangsbegrenzung)  
Begründung: Definiert, was die erste Version nicht leisten muss.  

14. Die Lösung soll modern sein und mit möglichst wenig Aufwand programmiert werden.  
Kategorie: keine, unklare Wertung bzw. Lösungswunsch  
Begründung: "Modern" ist nicht operationalisiert; geringer Programmieraufwand ist kein ausreichendes fachliches Abnahmekriterium.  



## A2 – Unklare Anforderungen verbessern (ca. 15 Minuten)

Formulieren Sie drei unklare Anforderungen so um, dass sie eindeutiger und überprüfbarer werden.

- Mindestens eine Anforderung muss sich auf die Verfügbarkeit bzw. Überschneidung von Ausleihzeiträumen beziehen.
- Mindestens eine Anforderung muss sich auf Geschwindigkeit, Bedienbarkeit oder Betrieb beziehen.
- Mindestens eine Anforderung muss sich auf Berechtigungen oder die Verwaltung beziehen.

Wenn der Auftraggeber noch keine konkreten Werte oder Regeln festgelegt hat, formulieren Sie zunächst einen Vorschlag und kennzeichnen Sie ihn als **Annahme zur Abstimmung**. Erfinden Sie keine verbindlichen Geschäftsregeln.

**Lösung.**  
Mögliche Formulierungen. Messwerte und Regeln, die im Szenario nicht festgelegt sind, sind ausdrücklich Vorschläge.

1. Verfügbarkeit / Überschneidung:  
   "Das System darf für dasselbe Gerät keine zwei verbindlichen Reservierungen mit sich überschneidenden Zeiträumen speichern. Eine Reservierung ist erst nach erfolgreicher Annahme durch eine berechtigte Person verbindlich."  
   *Annahme zur Abstimmung:* Reservierungen entstehen erst bei Annahme. Das Szenario legt den genauen technischen Zeitpunkt nicht fest.

2. Performance:  
   "Bei einer vereinbarten Last von 50 gleichzeitig aktiven Benutzern werden 95 % der Suchanfragen innerhalb von zwei Sekunden beantwortet."  
   *Annahme zur Abstimmung:* Lastprofil und Grenzwert sind Vorschläge und müssen mit dem Auftraggeber festgelegt werden.

3. Berechtigung:  
   "Nur Benutzer mit der Rolle "Geräteverwaltung" dürfen Ausleihanfragen annehmen oder ablehnen. Benutzer ohne diese Berechtigung erhalten keine Statusänderung; der Versuch wird protokolliert."  
   *Annahme zur Abstimmung:* Rollenname und Protokollierungsumfang müssen mit dem Betriebskonzept abgestimmt werden.


## A3 – Offene Punkte und Annahmen (ca. 10 Minuten)

Nennen und priorisieren Sie vier offene Fragen, die vor einer belastbaren Spezifikation geklärt werden sollten.

Mindestens zwei Fragen müssen fachliche Regeln betreffen, zum Beispiel:
- Wann gilt ein Gerät als verbindlich reserviert?
- Wie werden verspätete Rückgaben behandelt?
- Darf eine Anfrage geändert oder zurückgezogen werden?
- Was geschieht, wenn zwei Anfragen für denselben Zeitraum gleichzeitig eingehen?

Erklären Sie jeweils kurz, warum die Antwort für die Umsetzung oder die Testbarkeit relevant ist.

**Lösung.**  
Beispielhafte Priorisierung:

1. Wann wird ein Gerät verbindlich reserviert?  
Ohne diese Regel sind Statusmodell, Verfügbarkeitsanzeige und Akzeptanztests nicht eindeutig.

2. Wie werden zeitgleiche Anfragen behandelt?  
Zwei Personen könnten fast gleichzeitig eine Anfrage stellen. Die fachliche Regel muss mit einer technisch konsistenten Speicherung umgesetzt werden; bloße Anzeige der Verfügbarkeit verhindert keine Doppelreservierung.

3. Welche Datums- und Zeitregeln gelten?  
Sind Start- und Rückgabedatum inklusive? Sind Ausleihen über Nacht oder an Wochenenden zulässig? Wie werden ungültige bzw. vergangene Zeiträume behandelt?

4. Welche Rollen und Rechte existieren?  
Es muss geklärt werden, ob Mitarbeitende nur eigene Anfragen sehen dürfen und wer Geräte anlegen, sperren oder Rückgaben korrigieren darf.

Weitere gute Fragen: Welche Angaben sind Pflicht? Darf eine Anfrage geändert/zurückgezogen werden? Wie werden verspätete Rückgaben markiert? Welche Sicherungs- und Aufbewahrungsregeln gelten? Welche Antwortzeit wird erwartet?


## A4 – Akzeptanzkriterien entwickeln (ca. 15 Minuten)

Entwickeln Sie für die folgenden Anforderungen jeweils mindestens ein überprüfbares Akzeptanzkriterium:

1. Mitarbeitende können eine Ausleihe anfragen.
2. Die Geräteverwaltung kann eine Anfrage annehmen oder ablehnen.
3. Überlappende verbindliche Ausleihen desselben Geräts sind nicht zulässig.

Formulieren Sie so, dass ein Tester das erwartete Verhalten anhand konkreter Vorbedingungen, einer Aktion und eines Ergebnisses prüfen kann. Verwenden Sie bei nicht vorgegebenen Regeln ausdrücklich eine gekennzeichnete Annahme.

**Lösung.**  

### Anforderung 1: Ausleihe anfragen

Gegeben: Ein angemeldeter Mitarbeitender wählt ein Gerät und einen gültigen Zeitraum aus.  
Wenn: Er die Anfrage absendet.  
Dann: Das System speichert eine Anfrage mit eindeutiger Kennung und Status "Offen" und zeigt eine Bestätigung an.

*Annahme zur Abstimmung:* Statusbezeichnung "Offen" und Kennung werden als Vorschlag verwendet. Die Anfrage ist noch keine verbindliche Reservierung.

Negativkriterium: Fehlt ein Pflichtfeld oder liegt das Rückgabedatum vor dem Startdatum, wird die Anfrage nicht angelegt und der Fehler verständlich angezeigt.

### Anforderung 2: Anfrage entscheiden

Gegeben: Eine offene Anfrage und ein Benutzer mit Berechtigung zur Geräteverwaltung liegen vor.  
Wenn: Der Benutzer die Anfrage annimmt.  
Dann: Das System prüft die Verfügbarkeit erneut und legt bei erfolgreicher Prüfung eine verbindliche Reservierung an; der Status wird nachvollziehbar aktualisiert.

Wenn eine Überschneidung vorliegt, darf die Annahme nicht erfolgreich abgeschlossen werden. Ein Benutzer ohne Berechtigung darf keine Statusänderung bewirken.

### Anforderung 3: Keine überlappenden Reservierungen

Gegeben: Für Gerät X besteht bereits eine verbindliche Reservierung vom 10. bis einschließlich 12. Mai.  
Wenn: eine weitere verbindliche Reservierung für Gerät X vom 12. bis 14. Mai angelegt werden soll.  
Dann: Die zweite Reservierung wird abgelehnt, sofern die Zeiträume gemäß der noch zu bestätigenden Regel inklusive beider Tage gelten.

Wichtig: Ob direkt aufeinanderfolgende Zeiträume als überlappend gelten, hängt von der fachlichen Definition der Zeitintervalle ab. Diese Regel muss ausdrücklich festgelegt werden.


## A5 – Prozessspezifikation für eine Ausleihanfrage (ca. 15 Minuten)

Entwickeln Sie eine kurze, technologieunabhängige Programmspezifikation für das Anlegen einer Ausleihanfrage.

Ihre Spezifikation muss enthalten:
- Vorbedingungen,
- Eingabedaten,
- wesentliche Verarbeitungsschritte,
- Erfolgsfall,
- mindestens zwei Fehlerfälle mit erwarteter Reaktion.

Achten Sie insbesondere darauf, zwischen einer Anfrage und einer verbindlichen Reservierung zu unterscheiden. Legen Sie nicht ohne Kennzeichnung fest, ob ein Gerät bereits beim Anlegen einer Anfrage blockiert wird.

**Lösung.**  

### Vorbedingungen
- Benutzer ist angemeldet.
- Das ausgewählte Gerät existiert und ist grundsätzlich ausleihbar.
- Start- und Rückgabedatum wurden eingegeben.

### Eingabedaten
- Gerätekennung
- gewünschter Beginn
- gewünschtes Ende
- anfragende Person

### Verarbeitung
1. Pflichtfelder und Datumswerte prüfen.
2. Prüfen, ob der Zeitraum fachlich zulässig ist, z. B. Ende nicht vor Beginn.
3. Gerät und Benutzer auf Existenz bzw. Berechtigung prüfen.
4. Anfrage mit eindeutiger Kennung und einem definierten Anfangsstatus speichern.
5. Bestätigung mit Kennung und aktuellem Status ausgeben.

**Annahme zur Abstimmung:** Die Anfrage wird zunächst als "Offen" gespeichert und blockiert den Zeitraum noch nicht verbindlich. Die endgültige Reservierung erfolgt erst bei Annahme durch die Geräteverwaltung. Bei Annahme muss die Verfügbarkeit erneut geprüft werden.

### Erfolgsfall
Die Anfrage wird genau einmal gespeichert. Der Benutzer erhält eine Bestätigung mit Kennung und Status. Die Anzeige behauptet nicht, dass bereits eine verbindliche Reservierung besteht.

### Fehlerfälle
- Pflichtfeld fehlt oder Zeitraum ungültig: Anfrage wird nicht gespeichert; das fehlerhafte Feld und die Korrekturmöglichkeit werden verständlich angezeigt.
- Gerät nicht vorhanden oder gesperrt: Anfrage wird nicht angelegt; es erscheint eine passende Rückmeldung.
- Speicherfehler: Es wird keine Erfolgsmeldung angezeigt. Der Fehler wird nachvollziehbar protokolliert.
- Zeitgleiche konkurrierende Anfragen: Bei der späteren verbindlichen Reservierung wird die Verfügbarkeit erneut und transaktionssicher geprüft; eine Überschneidung darf nicht zu zwei verbindlichen Reservierungen führen.
