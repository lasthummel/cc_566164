# Prüfungssimulation sim01 – Planen eines Softwareproduktes

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100  
**Aufgaben:** 4 
**Hinweis:** Diese Prüfungssimulation ist eigenständig erstellt und keine offizielle IHK-Prüfung. Beantworten Sie die Fragen schriftlich. Begründen Sie Entscheidungen mit Bezug zur Ausgangssituation.

## Ausgangssituation

Die kommunale **WerkService GmbH** betreibt drei offene Ausbildungswerkstätten. Aktuell wird die Ausleihe von Werkzeugkoffern und Messgeräten in einer gemeinsamen Tabellenkalkulation geführt. Doppelbuchungen, fehlende Übergabeprotokolle und nicht erkannte Wartungssperren führen zu Ausfällen.

Ein neues Websystem mit dem Arbeitstitel **LeihWerk** soll die Ausleihe organisieren. Es gibt drei Benutzergruppen:

- **Entleihende:** authentifizieren sich mit dem bestehenden Firmenkonto, prüfen die Verfügbarkeit, beantragen die Ausleihe und sehen eigene Ausleihen.
- **Ausgabepersonal:** gibt reservierte Geräte aus, erfasst Rückgaben und dokumentiert Schäden.
- **Administration:** verwaltet Geräte, Wartungssperren und Rollen.

Für jedes Gerät sind Inventarnummer, Bezeichnung, Standort und Wartungsstatus hinterlegt. Beginn und Ende werden je Ausleihantrag erfasst. Ein neuer Ausleihantrag hat zunächst den Status "offen". Nur das Ausgabepersonal darf ihn bestätigen; nach der Bestätigung ist das Gerät für den beantragten Zeitraum reserviert. Die tatsächliche Geräteausgabe und die Rückgabe werden gesondert dokumentiert. Überlappende bestätigte Ausleihanträge für dasselbe Gerät sind ausgeschlossen. Eine aktive Wartungssperre verhindert die Bestätigung neuer Ausleihanträge. Das System soll mit dem vorhandenen Identitätsanbieter (OpenID Connect) zusammenarbeiten. Personenbezogene Daten dürfen nur für den Geschäftszweck verarbeitet werden.

Zusätzliche Rahmenbedingungen: 600 aktive Beschäftigte, im Spitzenfall 80 gleichzeitig aktive Nutzende; Budget für die erstmalige Implementierung 65.000 Euro, Bereitstellung innerhalb von vier Monaten. Das Entwicklerteam besteht aus vier Personen. Die Anforderungen der drei Werkstätten werden sich während des Projekts voraussichtlich noch ändern.

## Aufgabe 1: Anforderungen und Spezifikation (25 Punkte)

### 1a – 8 Punkte. 
Leiten Sie **vier funktionale Anforderungen** aus dem Szenario ab. Formulieren Sie diese jeweils als überprüfbare Aussage und nennen Sie für jede Anforderung ein beobachtbares Ergebnis. (Je 2 Punkte.)

### 1b – 9 Punkte.
Die Projektgruppe notiert: "Die Anwendung soll schnell, sicher und benutzerfreundlich sein." Überarbeiten Sie diese drei Aussagen zu jeweils **einem konkret überprüfbaren nichtfunktionalen Akzeptanzkriterium**. Definieren Sie dafür erforderliche Messbedingungen oder Prüfverfahren. Sie dürfen plausible zusätzliche Zielwerte festlegen; kennzeichnen Sie diese als Projektfestlegung. (Je 3 Punkte.)

### 1c – 8 Punkte.
Ein Gerät ist vom 12.11.2026, 09:00 Uhr, bis 12.11.2026, 12:00 Uhr, bereits durch einen bestätigten Ausleihantrag reserviert. Die beantragten Zeiträume werden als halboffene Zeitintervalle [Beginn, Ende) interpretiert. Beurteilen Sie die vier nachstehenden Anträge. Begründen Sie je Antrag kurz, ob er **unter dem Gesichtspunkt der Zeitüberschneidung** zulässig ist. (Je 2 Punkte.)

| Antrag | Beginn | Ende |
|---|---|---|
| A | 12.11., 08:00 | 12.11., 09:00 |
| B | 12.11., 11:30 | 12.11., 13:00 |
| C | 12.11., 12:00 | 12.11., 14:00 |
| D | 12.11., 09:00 | 12.11., 12:00 |


## Aufgabe 2: Technische Planung und Schnittstelle (24 Punkte)

Für LeihWerk stehen zwei Lösungsvarianten zur Wahl:

- **Variante A:** Webanwendung auf einem Server des Betreibers mit relationaler Datenbank. Betrieb und Updates erfolgen durch die WerkService GmbH.
- **Variante B:** externer Cloud-Service mit verwalteter Datenbank. Betrieb der Infrastruktur und Datenbanksicherungen übernimmt ein Anbieter; der Betreiber verantwortet weiterhin Anwendungskonfiguration und Zugriffsrechte.

### 2a – 6 Punkte.
Nennen und erläutern Sie **drei konkrete Kriterien** für die Wahl zwischen A und B, jeweils bezogen auf das Szenario. (Je 2 Punkte.)

### 2b – 8 Punkte.
Entwerfen Sie für die Bestätigung eines Ausleihantrags eine REST-Schnittstelle. Legen Sie fest:  
(1) geeignete HTTP-Methode und Ressourcen-URI, 
(2) Beispiel eines JSON-Request-Bodys mit benötigten fachlichen Angaben,  
(3) passenden Erfolgscode und  
(4) zwei unterschiedliche Fehlerfälle mit jeweils sinnvollem HTTP-Statuscode.  

Gehen Sie davon aus, dass ein Ausleihantrag mit einer numerischen ID und dem Status "offen" bereits existiert und dass die Identität des Bearbeitenden aus der Authentifizierung stammt.

### 2c – 10 Punkte.
Für die Auswahl eines Monitoring-Werkzeugs wurden drei Produkte im Rahmen eines Tests bewertet (0 = ungeeignet, 5 = sehr gut). Ein Wert von 0 bei "Datenschutzanforderungen" ist ein **Ausschlusskriterium**. Bei "Integrationsaufwand" und "Lizenzkosten" bedeuten höhere Bewertungen einen geringeren Aufwand beziehungsweise geringere Kosten.

| Kriterium | Gewicht | Produkt X | Produkt Y | Produkt Z |
|---|---:|---:|---:|---:|
| Datenschutzanforderungen | 40 % | 5 | 4 | 0 |
| Integrationsaufwand | 25 % | 2 | 4 | 5 |
| Alarmierungsfunktionen | 20 % | 4 | 3 | 5 |
| Lizenzkosten | 15 % | 3 | 4 | 5 |

Berechnen Sie die gewichteten Nutzwerte aller Produkte auf einer Skala von 0 bis 5 (6 Punkte).  
Wählen Sie anschließend unter Beachtung des Ausschlusskriteriums ein Produkt aus und begründen Sie Ihre Entscheidung (4 Punkte).

## Aufgabe 3: Benutzeroberfläche und Eingaben (24 Punkte)

Der bisherige Entwurf des Formulars "Ausleihe beantragen" sieht so aus:

- Alle Eingabefelder werden ausschließlich durch hellgraue Platzhaltertexte erläutert; dauerhaft sichtbare Feldbeschriftungen fehlen.
- Die einzige Fehlermeldung lautet "Eingabe ungültig" und erscheint oberhalb des Formulars.
- Beginn und Ende werden als frei eingegebene Texte erfasst, beispielsweise "morgen nachmittag".
- Der Absenden-Knopf heißt "OK".
- Nach dem Absenden verschwindet das Formular ohne Rückmeldung.
- Über die Tastatur ist die Standortauswahl nicht erreichbar.

### 3a – 9 Punkte.
Analysieren Sie **drei unterschiedliche Probleme** dieses Formulars. Beschreiben Sie jeweils die Auswirkung auf die Benutzbarkeit oder Barrierefreiheit. (Je 3 Punkte.)

### 3b – 9 Punkte.
Entwerfen Sie eine **textuelle Eingabemaske** mit sechs sinnvoll benannten Anzeige-, Eingabe- oder Bedienelementen für einen Ausleihantrag. Nennen Sie für jedes Element den Datentyp oder die Bedienelementart und den fachlichen Zweck. Die Eingabe muss mindestens Gerät, Beginn und Ende erfassen. (Sechs Elemente je 1,5 Punkte.)

### 3c – 6 Punkte.
Beschreiben Sie das Verhalten des Systems bei  
(1) einem Endzeitpunkt vor dem Beginn,  
(2) einer fehlenden Pflichtangabe und  
(3) erfolgreichem Absenden.  

Geben Sie jeweils eine konkrete Rückmeldung an die Benutzer an. (Je 2 Punkte.)


## Aufgabe 4: Qualität, Sicherheit und Vorgehen (27 Punkte)

### 4a – 12 Punkte.
Analysieren Sie **vier unterschiedliche Risiken** des Systems und beschreiben Sie für jedes Risiko  
(1) eine konkrete mögliche Auswirkung und  
(2) eine geeignete vorbeugende oder begrenzende Maßnahme.  

Berücksichtigen Sie mindestens ein Risiko der IT-Sicherheit und mindestens eines der Datenqualität. (Vier Risiken je 3 Punkte.)

### 4b – 9 Punkte.
Entwickeln Sie **drei Prüfmaßnahmen auf unterschiedlichen Testebenen** (Modul-, Integrations- und System- oder Abnahmetest). Geben Sie pro Testebene einen konkreten Testgegenstand, ein Vorgehen und das erwartete Ergebnis an. (Je 3 Punkte.)

### 4c – 6 Punkte.
Entscheiden Sie, ob für dieses Vorhaben eher ein klassisches sequenzielles oder ein iterativ-agiles Vorgehen angemessen ist. Begründen Sie mit **zwei auf das Szenario bezogenen Argumenten** und nennen Sie **eine organisatorische Voraussetzung** für das gewählte Vorgehen. (1 Punkt für die Entscheidung, je 2 Punkte für die beiden Argumente und 1 Punkt für die Voraussetzung.)
