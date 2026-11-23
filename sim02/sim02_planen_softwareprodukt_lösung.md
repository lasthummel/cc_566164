# Prüfungssimulation sim02 – Planen eines Softwareproduktes

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100 Punkte  
**Aufgaben:** 4 Hauptaufgaben.  

## Ausgangssituation

Die kommunale **BauRaum Service GmbH** betreut zwölf Schul- und Verwaltungsgebäude. Defekte Beleuchtungen, Heizungsstörungen und beschädigte Türen werden bislang per E-Mail gemeldet. Die Disposition überträgt Meldungen manuell in Listen. Dadurch fehlen Statusinformationen, Zuständigkeiten und nachvollziehbare Bearbeitungszeiten.

Das Projekt **FixCampus** soll eine Webanwendung zur Bearbeitung von Störungstickets bereitstellen. **Meldende** erfassen Standort, Kategorie, Beschreibung und eine mögliche Sicherheitsgefahr. Die **Disposition** bewertet und ordnet Tickets zu. **Technikerinnen und Techniker** bearbeiten zugewiesene Tickets und dokumentieren die Erledigung. Die **Administration** verwaltet Rollen und Stammdaten. Die Benutzer melden sich mit vorhandenen Unternehmenskonten an; Rechte hängen von ihrer Rolle ab.

Pro Werktag werden voraussichtlich 250 Tickets gemeldet. Es gibt 40 Technikerinnen und Techniker. Die ersten sechs Wochen dienen der Entwicklung eines einsatzfähigen Grundsystems. Später sollen weitere Gebäudetypen integriert werden. Das Unternehmen betreibt derzeit einen Java-21-Server und eine relationale Datenbank.

Für die **automatische Ersteinstufung** gilt verbindlich:

- Liegt eine **Sicherheitsgefahr** vor, erhält das Ticket immer **Priorität 1**.
- Sonst erhält ein Ticket der Kategorie **HEIZUNG** bei einer gemessenen Außentemperatur von **weniger als 5 °C** Priorität 1.
- Sonst erhält ein Ticket mit **Betriebseinschränkung** Priorität 2.
- Alle übrigen Tickets erhalten Priorität 3.
- Die Prioritätszahl 1 kennzeichnet die höchste Dringlichkeit.
- Die Außentemperatur ist bei Heizungsstörungen zum Einstufungszeitpunkt zuverlässig verfügbar.

## Aufgabe 1: Spezifikation und Entscheidungslogik (25 Punkte)

### 1a – 8 Punkte

Leiten Sie aus dem Szenario **vier unterschiedliche funktionale Anforderungen** ab. Formulieren Sie jede Anforderung so, dass ihre Erfüllung durch einen konkreten Test überprüft werden kann. (Je 2 Punkte.)

**Lösung**

Vier Beispiele: (1) Nach Speichern eines Tickets sind Standort, Kategorie, Beschreibung und Gefahrenkennzeichen wieder abrufbar. (2) Die Disposition kann ein offenes Ticket einer Fachkraft zuweisen; die zugewiesene Person wird gespeichert. (3) Angemeldete Fachkräfte können nur zugewiesene Tickets mit einem Bericht abschließen; danach steht der Status auf ERLEDIGT. (4) Ein Benutzer sieht eine aktuelle Liste seiner berechtigten Tickets einschließlich Status.

**Bewertung**

 je 1 P. konkrete Funktion, 1 P. überprüfbares beobachtbares Ergebnis. Andere Szenariofunktionen möglich.

### 1b – 9 Punkte

Das Projektteam hat drei Aussagen notiert: "Tickets sind sofort sichtbar", "Das System ist zuverlässig" und "Die Suche ist intuitiv". Formulieren Sie jede Aussage in **ein eindeutiges, quantitativ oder anhand eines definierten Verfahrens überprüfbares Akzeptanzkriterium** um. Nennen Sie jeweils eine Messbedingung oder ein Prüfverfahren. Plausible Zielwerte dürfen Sie selbst als **Projektfestlegung** definieren. (Je 3 Punkte.)

**Lösung**

Plausible Projektfestlegungen, keine amtlichen Zielwerte: (1) 95 % der neu gespeicherten Tickets erscheinen bei 50 parallelen Nutzern binnen 2 s nach erfolgreicher Speicherung in der berechtigten Übersicht, gemessen in 1000 Testfällen. (2) Das System erreicht im definierten Betriebsmonat eine Verfügbarkeit von 99,5 %, gemessen durch eine externe synthetische Anwendungsprüfung im 1-Minuten-Takt; geplante Wartung ist vorab explizit festzulegen, nicht stillschweigend auszunehmen. (3) Mindestens 9 von 10 bislang ungeschulten Probanden finden anhand der Ticketnummer binnen 30 s ein Ticket ohne Hilfe; standardisiertes Usability-Testprotokoll.

**Bewertung**

 je 1 P. konkretes Ziel/Ergebnis, 1 P. Messgröße/Zielwert bzw. reproduzierbare Erfolgsschwelle, 1 P. Prüfbedingungen.

### 1c – 8 Punkte

Ermitteln Sie für die folgenden vier neu erfassten Tickets die jeweilige Priorität. Begründen Sie die Einstufung mit der **entscheidenden Regel**. (Je 2 Punkte.)

| Ticket | Sicherheitsgefahr | Kategorie | Außentemperatur | Betriebseinschränkung |
|---|---|---|---:|---|
| A | Nein | HEIZUNG | 4 °C | Nein |
| B | Ja | BELEUCHTUNG | 12 °C | Nein |
| C | Nein | HEIZUNG | 5 °C | Ja |
| D | Nein | TÜR | 8 °C | Nein |

**Lösung**

A = 1 (Heizung unter 5 °C), B = 1 (Sicherheitsgefahr hat Vorrang), C = 2 (5 °C ist nicht unter 5; Betriebseinschränkung), D = 3 (keine Sonderbedingung). Je 1 P. Priorität, 1 P. zutreffende Regel. Kein Abzug für weitere sachlich richtige Erläuterung.

## Aufgabe 2: Werkzeug- und Projektplanung (24 Punkte)

Für eine Barcode-Erweiterung werden zwei Java-Bibliotheken untersucht:

| Merkmal | Bibliothek Alpha | Bibliothek Beta |
|---|---|---|
| Lizenz | Apache License 2.0 | GPL Version 3 |
| Letzte Sicherheitsprüfung | vor 2 Wochen, kein offener kritischer Befund | vor 18 Monaten, eine ungeklärte kritische Schwachstelle |
| Wartung | regelmäßige Releases | kein bestätigter Maintainer |
| Unterstützung | QR-Code und Data Matrix | nur QR-Code |
| Einbindung | Maven Central | manuelle JAR-Verteilung |

Die geplante Webanwendung soll möglicherweise später als proprietäres Produkt an weitere Kommunen lizenziert werden. Der Barcode-Anwendungsfall verlangt QR-Codes und Data Matrix.

### 2a – 8 Punkte

Vergleichen Sie die Bibliotheken anhand von **vier fachlich unterschiedlichen Kriterien**. Stellen Sie für jedes Kriterium eine konkrete Auswirkung auf FixCampus dar. (Je 2 Punkte.)

**Lösung**

Vier Kriterien mit Folgen: (1) Lizenz: Apache 2.0 ist für proprietäre Weitergabe grundsätzlich leichter einsetzbar, vorbehaltlich Lizenzhinweisen; GPLv3 kann bei Weitergabe eines abgeleiteten Gesamtwerks Copyleft-Pflichten auslösen; die konkrete Kombinationsweise ist rechtlich zu prüfen. (2) Sicherheitsstand: Alpha wurde aktuell geprüft; Beta hat eine ungeklärte kritische Lücke, die vor Einsatz ausgeschlossen oder behoben werden muss. (3) Wartung: Alpha wird gepflegt; Beta hat unklare Sicherheits- und Fehlerbehebung. (4) Fachlicher Umfang: Alpha unterstützt beide verpflichtenden Codearten; Beta erfüllt Data Matrix nicht. Ergänzend vertretbar: Maven-Verfügbarkeit.

**Bewertung**

 je Kriterium 1 P. richtiger Vergleich, 1 P. projektspezifische Auswirkung. Eine rechtlich pauschale Aussage, GPL sei für interne Nutzung verboten, ist nicht korrekt.

### 2b – 6 Punkte

Für die Implementierung einer Teilfunktion werden **84 Personentage** Netto-Entwicklungsaufwand geschätzt. Vier Entwickler stehen jeweils **20 Arbeitstage pro Monat** zur Verfügung; davon sind **60 % produktive Entwicklungszeit**. Zusätzlich sollen **20 % Aufwandspuffer auf die 84 Personentage** eingeplant werden. Berechnen Sie (1) die produktiven Personentage pro Monat, (2) den Gesamtaufwand mit Puffer und (3) die rechnerische Dauer in Monaten. Runden Sie nur die Dauer auf **zwei Nachkommastellen**. (Je 2 Punkte.)

**Lösung**

Produktive Kapazität = 4 × 20 × 0,60 = **48 Personentage/Monat** (2 P.). Aufwand mit Puffer = 84 × 1,20 = **100,8 Personentage** (2 P.). Dauer = 100,8 / 48 = **2,10 Monate** (2 P.). Numerisch äquivalente richtige Rechenwege akzeptieren; sinnvoller Einheitenfehler: Teilpunkte für Ansatz.

### 2c – 6 Punkte

Analysieren Sie **zwei unterschiedliche Risiken** bei der Ablösung des E-Mail-Verfahrens, darunter mindestens ein Datenschutz- oder Sicherheitsrisiko. Nennen Sie je Risiko eine **konkrete Auswirkung** und eine **geeignete Maßnahme**. (Je 3 Punkte.)

**Lösung**

Beispiel 1: Unberechtigter Zugriff auf Standort- und Personaldaten führt zu Datenschutzverletzungen; rollenbasierte Zugriffskontrolle, serverseitige Autorisierung und Protokollierung mindern das Risiko. Beispiel 2: Alt-E-Mails werden beim Übergang nicht vollständig übertragen, Folge: verlorene Tickets; Stichtagsmigration mit Abgleich der Ticketanzahl und Parallelkontrolle. Je 1 P. konkretes Risiko, 1 P. Auswirkung, 1 P. passende Maßnahme. Andere unabhängige Risiken möglich.

### 2d – 4 Punkte

Vor jedem Release soll eine automatisierte CI-Prüfung durchgeführt werden. Schlagen Sie **zwei voneinander verschiedene automatisierbare Qualitätsprüfungen** vor und erläutern Sie für jede Prüfung, welches Problem sie im Projekt erkennen soll. (Je 2 Punkte.)

**Lösung**

(1) Automatische Java-Unit-Tests prüfen Prioritätsregeln einschließlich 5-°C-Grenze und Regelvorrang. (2) Statische Sicherheits-/Abhängigkeitsanalyse kontrolliert bekannte verwundbare Dependency-Versionen. Je 1 P. benannte Automatisierung, 1 P. zutreffender Fehlernutzen. Gleichwertig Integrationstests, Lint, Build-, Vertrags-Tests.

## Aufgabe 3: Schnittstellen und UML-Sequenzdiagramm (26 Punkte)

Ein Technikerkonto darf ein Ticket erst dann abschließen, wenn dessen Status **IN_ARBEIT** ist. Beim Abschluss ist ein **nicht leerer Arbeitsbericht** Pflicht. Der Abschlussprozess läuft wie folgt:

1. Die Technikerin löst in der Weboberfläche den Abschluss für Ticket 42 mit einem Arbeitsbericht aus.
2. Die Oberfläche sendet `POST /tickets/42/abschluss` mit JSON-Arbeitsbericht an die API.
3. Die API übergibt Ticket-ID und Bericht an den TicketService.
4. Der Service lädt das Ticket über das TicketRepository aus der Datenbank (das Repository kapselt den Datenbankzugriff).
5. **Wenn** der Status `IN_ARBEIT` ist und der Bericht nicht leer ist, speichert der Service den Status `ERLEDIGT` samt Bericht über das Repository. Das Repository bestätigt die Speicherung; die API antwortet mit HTTP 200, die Oberfläche zeigt die Bestätigung.
6. **Andernfalls** löst der Service einen fachlichen Konflikt aus. Die API antwortet mit HTTP 409; die Oberfläche zeigt die Fehlermeldung. Eine Speicherung findet nicht statt.
7. Die Authentifizierung und die Prüfung der Rolle erfolgen bereits vor diesem Ablauf. Das Ticket existiert.

### 3a – 16 Punkte

Erstellen Sie ein **UML-Sequenzdiagramm** mit den fünf Beteiligten `Technikerin`, `Weboberfläche`, `API`, `TicketService` und `TicketRepository`. Zeigen Sie die wesentlichen Nachrichten, Rückgaben und die zwei alternativen Abläufe mit einem `alt`-Fragment sowie ihren Bedingungen. Das Diagramm kann handschriftlich erstellt werden.

**Lösung**

[Lösungsdiagramm in sim02_planen_softwareprodukt_lösung_3a.uxf](./sim02_planen_softwareprodukt_lösung_3a.uxf).


Referenzablauf des UML-Sequenzdiagramms:

```text
Technikerin -> Weboberfläche: abschliessen(42, bericht)
Weboberfläche -> API: POST /tickets/42/abschluss {bericht}
API -> TicketService: abschliessen(42, bericht)
TicketService -> TicketRepository: finde(42)
TicketRepository --> TicketService: Ticket(status, ...)
alt [status == IN_ARBEIT && bericht nicht leer]
  TicketService -> TicketRepository: speichere(status=ERLEDIGT, bericht)
  TicketRepository --> TicketService: gespeichert
  TicketService --> API: erfolgreich
  API --> Weboberfläche: HTTP 200
  Weboberfläche --> Technikerin: Bestätigung
else [sonst]
  TicketService --> API: Konflikt
  API --> Weboberfläche: HTTP 409
  Weboberfläche --> Technikerin: Fehlermeldung
end
```


**Bewertung**

 fünf korrekte Beteiligte 3 P.; Startanfrage und API-Aufruf 2 P.; Service und Repository (Abruf/Rückgabe) 3 P.; richtig beschriftetes `alt` mit Bedingungen 3 P.; ausschließlich im Erfolgszweig Speichern samt Rückmeldung 3 P.; Konflikt-/Fehlerzweig ohne Speichern 2 P. Gleichwertige Folge mit logisch äquivalenten Rückgaben ist zulässig. Das Diagramm darf mehr interne Nachrichten zeigen. Fehler: fachliche Bedingung allein in der Oberfläche geprüft.

### 3b – 6 Punkte

Nennen Sie für die folgenden drei **anderen** Situationen jeweils einen geeigneten HTTP-Statuscode und erläutern Sie die Bedeutung: (1) Ticket-ID ist nicht vorhanden; (2) angemeldete Person hat keine Berechtigung zum Abschließen; (3) Request-JSON ist syntaktisch ungültig. (Je 2 Punkte.)

**Lösung**

(1) **404 Not Found**: Ressource unter der URI existiert nicht. (2) **403 Forbidden**: authentifiziert, aber nicht berechtigt. (3) **400 Bad Request**: syntaktisch ungültiger Request. Je 1 P. Status, 1 P. Erklärung. Ein 401 bei bereits angemeldeter, nicht berechtigter Person ist nicht gleichwertig.

### 3c – 4 Punkte

Zwei gleichzeitig eintreffende gültige Abschlussanfragen könnten denselben alten Status lesen. Beschreiben Sie **ein geeignetes technisches Verfahren**, mit dem doppelte oder sich widersprechende Aktualisierungen verhindert werden, und erläutern Sie dessen Wirkung.

**Lösung**

Beispiel *optimistic locking*: Ticket besitzt Version; Update schreibt nur, wenn ID, alter Status und erwartete Version übereinstimmen. Falls parallel geändert wurde, betrifft der zweite Update null Zeilen und wird als Konflikt behandelt. 2 P. technisch geeignetes Verfahren, 2 P. Wirkungsbeschreibung. Ebenfalls korrekt: Transaktion mit Zeilensperre und Prüfung, atomarer bedingter UPDATE mit Prüfung der betroffenen Zeilen. Ein reines Sperren eines UI-Buttons genügt nicht.

## Aufgabe 4: Oberfläche und Qualitätskontrolle (25 Punkte)

Die bestehende mobile Ticketansicht hat folgende Eigenschaften:

- Die Information zur Priorität wird **nur durch Rot, Gelb oder Grün** dargestellt.
- Schaltflächen zeigen ausschließlich nicht erläuterte Piktogramme.
- Ein Arbeitsbericht mit 800 Zeichen wird bei Eingabe ohne Hinweis nach 300 Zeichen abgeschnitten.
- Beim Abschließen verschwindet die Ticketansicht, ohne den neuen Status anzuzeigen.

### 4a – 9 Punkte

Wählen Sie **drei verschiedene Probleme** aus dieser Beschreibung. Erläutern Sie jeweils die konkrete Auswirkung und eine passende Verbesserung. (Je 3 Punkte.)

**Lösung**

Drei Beispiele zu je 3 P.: (1) Nur Farbe: bei Farbenfehlsichtigkeit/Monochromdarstellung unklar; Priorität zusätzlich in Klartext und Symbol mit zugänglichem Namen. (2) Nur Piktogramm: Funktion nicht erkennbar / Screenreader ohne Namen; sichtbare Beschriftung und Accessible Name. (3) Stilles Abschneiden: dokumentierte Arbeit geht verloren; Zeichenlimit vorab sichtbar, serverseitig prüfen und bei Überschreitung mit konkreter Fehlermeldung zurückweisen. (4) Fehlende Rückmeldung: unklarer Speicherausgang und doppelte Vorgänge; Statuswechsel mit verständlicher Erfolgsmeldung. Je Problem 1 P., Auswirkung 1 P., Verbesserung 1 P.

### 4b – 9 Punkte

Entwerfen Sie **drei Testfälle** auf unterschiedlichen Testebenen: (1) ein Modultest für die Prioritätseinstufung, (2) ein Integrationstest für den Ticketabschluss und (3) ein Abnahme- oder Systemtest für die mobile Oberfläche. Geben Sie für jeden Testfall **Voraussetzung/Eingabe, Durchführung und erwartetes Ergebnis** an. (Je 3 Punkte.)

**Lösung**

(1) Modultest: Heizungsstörung 5 °C, keine Gefahr, mit Betriebseinschränkung → Priorität 2; Algorithmus aufrufen → 2. (2) Integrationstest: Ticket 42 IN_ARBEIT, nicht leerer Bericht, berechtigte Fachkraft → API aufrufen, Repository anschließend prüfen → HTTP 200, Status ERLEDIGT, Bericht gespeichert. (3) System-/Abnahmetest: mobile Ansicht per Tastatur oder Screenreader bedienen → Prioritätsangabe eindeutig, Aktion benannt, Abschlussbestätigung verständlich. Je 1 P. Eingabe/Voraussetzung, 1 P. konkrete Handlung, 1 P. prüfbares Ergebnis.

### 4c – 7 Punkte

Das Team stellt fest, dass nach Releases vermehrt Tickets den falschen Bearbeiter erhalten. Beschreiben Sie **zwei komplementäre Qualitätssicherungsmaßnahmen**, die dieses Problem vor einem Release möglichst früh erkennen helfen (je 3 Punkte), und erläutern Sie **eine geeignete Kennzahl** zur Wirksamkeitskontrolle einschließlich Zähler und Nenner (1 Punkt).

**Lösung**

(1) Code-Review und statische Analyse des Zuordnungsweges mit Fokus auf ID-Verwechslungen, Zustandsprüfungen und Autorisierung (3 P. für konkrete Durchführung und Fehlerbezug). (2) Automatisierter regressiver Integrations-/End-to-End-Test mit realistischen Kombinationen von Fachgebieten und Paralleländerungen, der die gespeicherte Zuordnung überprüft (3 P.). Kennzahl beispielsweise **Fehlzuordnungsquote = nachträglich als falsch eingestufte Zuordnungen / geprüfte Zuordnungen × 100 %** mit vergleichbarem Beobachtungszeitraum (1 P.). In beiden Maßnahmen eine identische manuelle Sichtprüfung ohne zusätzliche Perspektive ist nicht komplementär.

**Ende des Prüfungsbereichs.**
