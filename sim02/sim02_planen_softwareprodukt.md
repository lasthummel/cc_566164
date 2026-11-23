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

### 1b – 9 Punkte

Das Projektteam hat drei Aussagen notiert: "Tickets sind sofort sichtbar", "Das System ist zuverlässig" und "Die Suche ist intuitiv". Formulieren Sie jede Aussage in **ein eindeutiges, quantitativ oder anhand eines definierten Verfahrens überprüfbares Akzeptanzkriterium** um. Nennen Sie jeweils eine Messbedingung oder ein Prüfverfahren. Plausible Zielwerte dürfen Sie selbst als **Projektfestlegung** definieren. (Je 3 Punkte.)

### 1c – 8 Punkte

Ermitteln Sie für die folgenden vier neu erfassten Tickets die jeweilige Priorität. Begründen Sie die Einstufung mit der **entscheidenden Regel**. (Je 2 Punkte.)

| Ticket | Sicherheitsgefahr | Kategorie | Außentemperatur | Betriebseinschränkung |
|---|---|---|---:|---|
| A | Nein | HEIZUNG | 4 °C | Nein |
| B | Ja | BELEUCHTUNG | 12 °C | Nein |
| C | Nein | HEIZUNG | 5 °C | Ja |
| D | Nein | TÜR | 8 °C | Nein |

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

### 2b – 6 Punkte

Für die Implementierung einer Teilfunktion werden **84 Personentage** Netto-Entwicklungsaufwand geschätzt. Vier Entwickler stehen jeweils **20 Arbeitstage pro Monat** zur Verfügung; davon sind **60 % produktive Entwicklungszeit**. Zusätzlich sollen **20 % Aufwandspuffer auf die 84 Personentage** eingeplant werden. Berechnen Sie (1) die produktiven Personentage pro Monat, (2) den Gesamtaufwand mit Puffer und (3) die rechnerische Dauer in Monaten. Runden Sie nur die Dauer auf **zwei Nachkommastellen**. (Je 2 Punkte.)

### 2c – 6 Punkte

Analysieren Sie **zwei unterschiedliche Risiken** bei der Ablösung des E-Mail-Verfahrens, darunter mindestens ein Datenschutz- oder Sicherheitsrisiko. Nennen Sie je Risiko eine **konkrete Auswirkung** und eine **geeignete Maßnahme**. (Je 3 Punkte.)

### 2d – 4 Punkte

Vor jedem Release soll eine automatisierte CI-Prüfung durchgeführt werden. Schlagen Sie **zwei voneinander verschiedene automatisierbare Qualitätsprüfungen** vor und erläutern Sie für jede Prüfung, welches Problem sie im Projekt erkennen soll. (Je 2 Punkte.)

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

### 3b – 6 Punkte

Nennen Sie für die folgenden drei **anderen** Situationen jeweils einen geeigneten HTTP-Statuscode und erläutern Sie die Bedeutung: (1) Ticket-ID ist nicht vorhanden; (2) angemeldete Person hat keine Berechtigung zum Abschließen; (3) Request-JSON ist syntaktisch ungültig. (Je 2 Punkte.)

### 3c – 4 Punkte

Zwei gleichzeitig eintreffende gültige Abschlussanfragen könnten denselben alten Status lesen. Beschreiben Sie **ein geeignetes technisches Verfahren**, mit dem doppelte oder sich widersprechende Aktualisierungen verhindert werden, und erläutern Sie dessen Wirkung. 

## Aufgabe 4: Oberfläche und Qualitätskontrolle (25 Punkte)

Die bestehende mobile Ticketansicht hat folgende Eigenschaften:

- Die Information zur Priorität wird **nur durch Rot, Gelb oder Grün** dargestellt.
- Schaltflächen zeigen ausschließlich nicht erläuterte Piktogramme.
- Ein Arbeitsbericht mit 800 Zeichen wird bei Eingabe ohne Hinweis nach 300 Zeichen abgeschnitten.
- Beim Abschließen verschwindet die Ticketansicht, ohne den neuen Status anzuzeigen.

### 4a – 9 Punkte

Wählen Sie **drei verschiedene Probleme** aus dieser Beschreibung. Erläutern Sie jeweils die konkrete Auswirkung und eine passende Verbesserung. (Je 3 Punkte.)

### 4b – 9 Punkte

Entwerfen Sie **drei Testfälle** auf unterschiedlichen Testebenen: (1) ein Modultest für die Prioritätseinstufung, (2) ein Integrationstest für den Ticketabschluss und (3) ein Abnahme- oder Systemtest für die mobile Oberfläche. Geben Sie für jeden Testfall **Voraussetzung/Eingabe, Durchführung und erwartetes Ergebnis** an. (Je 3 Punkte.)

### 4c – 7 Punkte

Das Team stellt fest, dass nach Releases vermehrt Tickets den falschen Bearbeiter erhalten. Beschreiben Sie **zwei komplementäre Qualitätssicherungsmaßnahmen**, die dieses Problem vor einem Release möglichst früh erkennen helfen (je 3 Punkte), und erläutern Sie **eine geeignete Kennzahl** zur Wirksamkeitskontrolle einschließlich Zähler und Nenner (1 Punkt).
