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

**Lösung**  
Beispielhafte vier Anforderungen:

1. Nach Anmeldung kann eine berechtigte Person verfügbare Geräte nach Standort und Zeitraum suchen; das System zeigt ausschließlich verfügbare Geräte an.
2. Das Ausgabepersonal kann einen offenen Ausleihantrag bestätigen; das System wechselt dessen Status auf "bestätigt" und speichert Zeitpunkt und Bearbeiterkennung.
3. Das System verweigert die Bestätigung, wenn für das Gerät im beantragten Zeitraum bereits ein bestätigter Ausleihantrag existiert; der neue Antrag bleibt "offen" und eine entsprechende Meldung wird angezeigt.
4. Das System verweigert die Bestätigung eines Ausleihantrags für ein Gerät mit aktiver Wartungssperre; der Antrag bleibt "offen" und die Anwendung zeigt eine entsprechende Fehlermeldung an.

**Bewertung**  
Je 1 Punkt für einen fachlich zutreffenden Funktionsumfang und 1 Punkt für ein überprüfbares Ergebnis. Alternativ zulässig: Rückgabe dokumentieren, Schaden erfassen, eigene Ausleihen anzeigen, Rollenverwaltung. Keine Punkte für vage Ziele ohne prüfbare Funktion.


### 1b – 9 Punkte.
Die Projektgruppe notiert: "Die Anwendung soll schnell, sicher und benutzerfreundlich sein." Überarbeiten Sie diese drei Aussagen zu jeweils **einem konkret überprüfbaren nichtfunktionalen Akzeptanzkriterium**. Definieren Sie dafür erforderliche Messbedingungen oder Prüfverfahren. Sie dürfen plausible zusätzliche Zielwerte festlegen; kennzeichnen Sie diese als Projektfestlegung. (Je 3 Punkte.)

**Lösung**  

- **Schnell:** Als Projektfestlegung gilt: Bei 80 gleichzeitig aktiven virtuellen Nutzenden, die nach einem festgelegten Lastprofil Gerätesuchen ausführen, beträgt die serverseitige Antwortzeit der Suchfunktion für mindestens 95 % von 200 gemessenen Suchanfragen höchstens zwei Sekunden. Der Test erfolgt mit festgelegtem Datenbestand in einer produktionsnahen Lasttestumgebung; Lastprofil und Cache-Zustand werden dokumentiert.
- **Sicher:** Als Projektfestlegung gilt: Versucht ein angemeldeter Entleihender, einen Ausleihantrag über die Bestätigungs-API freizugeben, antwortet die Anwendung mit HTTP 403 Forbidden und ändert den Antragsstatus nicht. Alle 20 entsprechenden Negativtests müssen diese Ablehnung erfolgreich nachweisen.
- **Benutzerfreundlich:** Als Projektfestlegung gilt: Mindestens acht von zehn repräsentativen Testpersonen können einen Antrag ohne Hilfestellung in höchstens drei Minuten vollständig absenden; die Aufgaben, Stichprobe und Messmethode werden protokolliert.

**Bewertung**  
Auch andere messbare Anforderungen (z. B. Erfolgsrate) sind anzuerkennen. Reine Behauptungen wie "ist nutzerfreundlich" sind nicht ausreichend.

Je 1 Punkt für überprüfbares Ziel, 1 Punkt für Messbedingungen/Prüfverfahren, 1 Punkt für klar definierte Kriterien.


### 1c – 8 Punkte.
Ein Gerät ist vom 12.11.2026, 09:00 Uhr, bis 12.11.2026, 12:00 Uhr, bereits durch einen bestätigten Ausleihantrag reserviert. Die beantragten Zeiträume werden als halboffene Zeitintervalle [Beginn, Ende) interpretiert. Beurteilen Sie die vier nachstehenden Anträge. Begründen Sie je Antrag kurz, ob er **unter dem Gesichtspunkt der Zeitüberschneidung** zulässig ist. (Je 2 Punkte.)

| Antrag | Beginn | Ende |
|---|---|---|
| A | 12.11., 08:00 | 12.11., 09:00 |
| B | 12.11., 11:30 | 12.11., 13:00 |
| C | 12.11., 12:00 | 12.11., 14:00 |
| D | 12.11., 09:00 | 12.11., 12:00 |

**Lösung**  

Halboffene Intervalle überschneiden sich genau dann, wenn `neuerBeginn < bestehendesEnde UND bestehenderBeginn < neuesEnde`.

| Antrag | Zeitüberschneidung? | Erläuterung |
|---|---|---|
| A | Nein | Ende um 09:00 grenzt nur an den bestehenden Beginn. |
| B | Ja | Zwischen 11:30 und 12:00 besteht eine Überschneidung. |
| C | Nein | Beginn um 12:00 grenzt nur an das bestehende Ende. |
| D | Ja | Das Zeitintervall ist identisch. |

**Bewertung**  
Je 1 Punkt für die korrekte Beurteilung und 1 Punkt für die fachlich passende Begründung. Die Antwort bezieht sich ausschließlich auf Zeitüberschneidung, nicht auf Rollen, Sperren oder weitere Prüfungen.


## Aufgabe 2: Technische Planung und Schnittstelle (24 Punkte)

Für LeihWerk stehen zwei Lösungsvarianten zur Wahl:

- **Variante A:** Webanwendung auf einem Server des Betreibers mit relationaler Datenbank. Betrieb und Updates erfolgen durch die WerkService GmbH.
- **Variante B:** externer Cloud-Service mit verwalteter Datenbank. Betrieb der Infrastruktur und Datenbanksicherungen übernimmt ein Anbieter; der Betreiber verantwortet weiterhin Anwendungskonfiguration und Zugriffsrechte.

### 2a – 6 Punkte.
Nennen und erläutern Sie **drei konkrete Kriterien** für die Wahl zwischen A und B, jeweils bezogen auf das Szenario. (Je 2 Punkte.)

**Lösung**  

- **Verfügbarkeit/Betriebsverantwortung:** Variante A erfordert eigene Wartung, Überwachung und Ausfallsicherung; B lagert Infrastrukturmaßnahmen an den Anbieter aus, ohne die Anwendungspflichten abzugeben.
- **Datenschutz und Standort der Datenverarbeitung:** Bei B sind Auftragsverarbeitung, Zugriffs-/Löschkonzepte und tatsächliche Datenflüsse zu prüfen; A vermeidet diese Prüfung nicht vollständig.
- **Gesamtkosten und Personal:** Für beide Varianten sind die Gesamtkosten über denselben Betrachtungszeitraum (Total Cost of Ownership, TCO) zu vergleichen. A verursacht eigene Infrastruktur-, Personal- und Betriebskosten; B insbesondere laufende Anbietergebühren und mögliche Kosten einer Migration (Anbieterabhängigkeit).
- Weitere zulässige Kriterien: Skalierbarkeit bis mindestens 80 gleichzeitige Nutzende, Integration mit Firmen-Identitätsanbieter, Wiederanlauf, Support.

**Bewertung**  
Je 1 Punkt für passendes Kriterium und 1 Punkt für den konkreten Vergleichsbezug.


### 2b – 8 Punkte.
Entwerfen Sie für die Bestätigung eines Ausleihantrags eine REST-Schnittstelle. Legen Sie fest:  
(1) geeignete HTTP-Methode und Ressourcen-URI, 
(2) Beispiel eines JSON-Request-Bodys mit benötigten fachlichen Angaben,  
(3) passenden Erfolgscode und  
(4) zwei unterschiedliche Fehlerfälle mit jeweils sinnvollem HTTP-Statuscode.  

Gehen Sie davon aus, dass ein Ausleihantrag mit einer numerischen ID und dem Status "offen" bereits existiert und dass die Identität des Bearbeitenden aus der Authentifizierung stammt.

**Lösung**  

Eine mögliche Spezifikation:

```http
POST /api/ausleihantraege/4711/bestaetigung HTTP/1.1
Content-Type: application/json
Authorization: Bearer <Token>
```

```json
{
    "entscheidung": "BESTAETIGEN",
    "bemerkung": "Ausgabe vorbereitet"
}
```

**Erfolgsfall: HTTP 200 OK.** Die Antwort enthält den neuen Antragsstatus, beispielsweise:

```json
{
    "antragId": 4711,
    "status": "BESTAETIGT"
}
```

Die Identität des Ausgabepersonals wird serverseitig aus der Authentifizierung ermittelt und nicht aus manipulierbaren Angaben im Request-Body übernommen.

**Fehlerfälle:**

- **HTTP 403 Forbidden:** Die authentifizierte Person besitzt keine Berechtigung, Ausleihanträge zu bestätigen.
- **HTTP 409 Conflict:** Für dasselbe Gerät besteht im beantragten Zeitraum bereits ein bestätigter, zeitlich überlappender Ausleihantrag. Auch eine aktive Wartungssperre kann einen solchen Konflikt auslösen.

**Bewertung**  
2 Punkte für geeignete HTTP-Methode und ressourcenbezogene URI. 2 Punkte für syntaktisch korrektes JSON mit fachlich geeigneten Angaben; die Bearbeiteridentität darf nicht manipulierbar aus dem Body übernommen werden. 1 Punkt für einen passenden Erfolgscode, z. B. HTTP 200 mit aktualisiertem Status oder HTTP 204 ohne Antwortkörper. 3 Punkte für die Fehlerfälle: je 1 Punkt für zwei unterschiedliche fachlich passende Fehlerfälle mit zugehörigem HTTP-Statuscode und 1 Punkt für nachvollziehbare Begründungen.

Auch ein ressourcenorientiertes `PATCH /api/ausleihantraege/4711` mit Statusänderung und passenden Fehlern ist zulässig; `PUT` nur bei semantisch vollständiger Ersetzung. HTTP 401 Unauthorized ist bei fehlender oder ungültiger Authentifizierung möglich. Eine andere konsistente REST-Modellierung darf ebenfalls anerkannt werden.


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

**Lösung**  

Nutzwert = Summe(Bewertung × Gewicht):

- X = 5 × 0,40 + 2 × 0,25 + 4 × 0,20 + 3 × 0,15 = **3,75**.
- Y = 4 × 0,40 + 4 × 0,25 + 3 × 0,20 + 4 × 0,15 = **3,80**.
- Z = 0 × 0,40 + 5 × 0,25 + 5 × 0,20 + 5 × 0,15 = **3,00**.

**Bewertung**  
6 Punkte: je Produkt 1 Punkt für die richtige gewichtete Berechnung/Ansatz und 1 Punkt für den richtigen Endwert. 4 Punkte: Y als besten zulässigen Kandidaten auswählen (2), den Ausschluss von Z unabhängig von dessen Nutzwert nachvollziehbar begründen (2). X bleibt zulässig, hat aber den geringeren Nutzwert. Das Ausschlusskriterium darf nicht durch gute Bewertungen in anderen Kriterien ausgeglichen werden. Punkte für Rundungen nur bei korrektem Ansatz vergeben.


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

**Lösung**  
- Nur Platzhalter statt dauerhaft sichtbarer und programmatisch zugeordneter Feldbeschriftungen: Der Feldzweck ist nach Eingabe nicht mehr sichtbar; assistive Technologien können den Zweck eines Feldes möglicherweise nicht zuverlässig vermitteln.
- Unspezifische Fehlermeldung am Formularanfang: Nutzende erkennen weder betroffene Eingabe noch Korrekturweg.
- Standortauswahl nicht per Tastatur nutzbar: Benutzende ohne Maus können das Formular nicht vollständig bedienen.

**Bewertung**  
Je Beispiel 1 Punkt für das zutreffende Erkennen eines eigenständigen Problems und 2 Punkte für die konkrete Auswirkung auf Benutzbarkeit oder Barrierefreiheit.

Auch freie Datumstexte (Mehrdeutigkeit), "OK" (unklares Handlungsziel) und fehlende Bestätigung (unsicherer Systemstatus) sind als eigenständige Probleme zulässig. Nicht dieselbe Ursache dreimal bewerten.


### 3b – 9 Punkte.
Entwerfen Sie eine **textuelle Eingabemaske** mit sechs sinnvoll benannten Anzeige-, Eingabe- oder Bedienelementen für einen Ausleihantrag. Nennen Sie für jedes Element den Datentyp oder die Bedienelementart und den fachlichen Zweck. Die Eingabe muss mindestens Gerät, Beginn und Ende erfassen. (Sechs Elemente je 1,5 Punkte.)

**Lösung**  
Eine mögliche textuelle Maske mit sechs Elementen:

| Element | Datentyp / Bedienelement | Zweck |
|---|---|---|
| Gerät | Suchauswahl (ID + Bezeichnung) | eindeutiges Leihobjekt |
| Standort (Suchfilter) | Auswahlfeld | Geräteauswahl auf den gewünschten Werkstattstandort eingrenzen; der Standort des gewählten Geräts wird nicht unabhängig geändert |
| Beginn | Datum-Uhrzeit-Eingabe | Startintervall |
| Ende | Datum-Uhrzeit-Eingabe | Endintervall |
| Zweck | mehrzeiliges Textfeld, optional | Nutzungskontext |
| Antrag senden | eindeutig beschriftete Schaltfläche | Übermittlung |

**Bewertung**  
Pro Element 0,5 Punkte für sinnvolle Bezeichnung, 0,5 Punkt für passende Eingabeart, 0,5 Punkt für fachlich sinnvollen Zweck. Gleichwertig: Statusanzeige oder Verfügbarkeitshinweis statt Zweck, sofern sechs unterscheidbare, auf die Aufgabe passende Elemente entstehen.


### 3c – 6 Punkte.
Beschreiben Sie das Verhalten des Systems bei  
(1) einem Endzeitpunkt vor dem Beginn,  
(2) einer fehlenden Pflichtangabe und  
(3) erfolgreichem Absenden.  

Geben Sie jeweils eine konkrete Rückmeldung an die Benutzer an. (Je 2 Punkte.)

**Lösung**  

1. **Ende vor Beginn:** Absenden verhindern; Fehlermeldung am Ende-Feld "Das Ende muss nach dem Beginn liegen."
2. **Pflichtfeld fehlt:** nicht versenden; Feld markieren und z. B. "Bitte wählen Sie ein Gerät aus" anzeigen.
3. **Erfolgreiches Absenden:** Antrag persistieren und Eingabe quittieren; "Antrag LW-2026-081 eingereicht, Status: offen" anzeigen.

**Bewertung**  
Je 1 Punkt für geeignetes Systemverhalten und 1 Punkt für eine konkrete, verständliche Rückmeldung.


## Aufgabe 4: Qualität, Sicherheit und Vorgehen (27 Punkte)

### 4a – 12 Punkte.
Analysieren Sie **vier unterschiedliche Risiken** des Systems und beschreiben Sie für jedes Risiko  
(1) eine konkrete mögliche Auswirkung und  
(2) eine geeignete vorbeugende oder begrenzende Maßnahme.  

Berücksichtigen Sie mindestens ein Risiko der IT-Sicherheit und mindestens eines der Datenqualität. (Vier Risiken je 3 Punkte.)

**Lösung**  

| Risiko | Auswirkung | Geeignete Maßnahme |
|---|---|---|
| Fehlende serverseitige Rechteprüfung | Unbefugte bestätigen Ausleihen | zentrale rollenbasierte Autorisierung je Endpunkt und Negativtests |
| Unzureichend abgesicherte parallele Bestätigungsversuche | Doppelbuchung eines Geräts trotz vorheriger Verfügbarkeitsprüfung | Kollisionsprüfung und Bestätigung in einer gemeinsamen Transaktion; geeignete Sperrstrategie oder Isolationsebene, sodass nicht beide kollidierenden Bestätigungen erfolgreich abgeschlossen werden |
| Uneinheitliche Inventarnummern beim Import | Geräte nicht eindeutig zuordenbar | Importvalidierung, Eindeutigkeitsbedingung und Bereinigungsprotokoll |
| Datenbankausfall | Ausgabe und Rückgabe können nicht regulär dokumentiert werden | Regelmäßige Sicherungen mit Wiederherstellungstests und dokumentierter Notfallprozess zur Begrenzung der Ausfallfolgen |

**Bewertung**  
Weitere akzeptable Risiken: Identitätsanbieter fällt aus, ungenügende Verfügbarkeit, unzulässige Datenhaltung, unentdeckte Wartungssperren. Nur eine abstrakte Risikoliste ohne Auswirkungen/Maßnahmen ist unvollständig.

Pro Risiko 1 Punkt für konkretes Risiko, 1 Punkt für Auswirkung, 1 Punkt für geeignete Maßnahme.


### 4b – 9 Punkte.
Entwickeln Sie **drei Prüfmaßnahmen auf unterschiedlichen Testebenen** (Modul-, Integrations- und System- oder Abnahmetest). Geben Sie pro Testebene einen konkreten Testgegenstand, ein Vorgehen und das erwartete Ergebnis an. (Je 3 Punkte.)

**Lösung**  

- **Modultest:** Intervallprüffunktion mit [09:00,12:00) und [12:00,14:00) prüfen; Ergebnis "keine Überschneidung".
- **Integrationstest:** Freigabeservice und Datenbank mit zwei zunächst offenen, kollidierenden Anträgen für dasselbe verfügbare Gerät prüfen. Zwei Bestätigungsversuche kontrolliert parallel auslösen; genau einer wird bestätigt, der andere wegen eines Konflikts abgewiesen.
- **System-/Abnahmetest:** Als Entleihender anmelden, ein verfügbares Gerät auswählen und einen Antrag absenden. Anschließend als Ausgabepersonal anmelden und den Antrag bestätigen; der Status lautet danach "bestätigt" und ist für berechtigte Nutzende sichtbar.

**Bewertung**  
Je Testebene 1 Punkt für den Testgegenstand, 1 Punkt für das konkrete Vorgehen mit Testdaten und 1 Punkt für das erwartete Ergebnis.

Doppelte Tests derselben Ebene ohne abgrenzbare Testziele ersetzen die drei geforderten Ebenen nicht.


### 4c – 6 Punkte.
Entscheiden Sie, ob für dieses Vorhaben eher ein klassisches sequenzielles oder ein iterativ-agiles Vorgehen angemessen ist. Begründen Sie mit **zwei auf das Szenario bezogenen Argumenten** und nennen Sie **eine organisatorische Voraussetzung** für das gewählte Vorgehen. (1 Punkt für die Entscheidung, je 2 Punkte für die beiden Argumente und 1 Punkt für die Voraussetzung.)

**Lösung**  

**Entscheidung:** Für das Vorhaben ist ein iterativ-agiles Vorgehen besonders geeignet.

**Zwei szenariobezogene Argumente:**

1. Die Anforderungen der drei Werkstätten werden sich voraussichtlich ändern. Kurze Iterationen ermöglichen eine frühe Abstimmung mit den Beteiligten und die Anpassung der Prioritäten.
2. Das vierköpfige Entwicklungsteam kann in kurzen Abstimmungswegen benutzbare Teilergebnisse erstellen und mit Beschäftigten aus den Werkstätten testen. Dadurch werden Fehlannahmen früh erkennbar.

**Organisatorische Voraussetzung:** Ein verbindlich verfügbares fachliches Gegenüber, beispielsweise ein Product Owner mit Priorisierungs- und Entscheidungsbefugnis, koordiniert Rückmeldungen aus den drei Werkstätten.

**Bewertung**  
1 Punkt für eine nachvollziehbare Entscheidung, je 2 Punkte für zwei unterschiedliche, auf das Szenario bezogene Argumente, 1 Punkt für eine zum gewählten Vorgehen passende organisatorische Voraussetzung. Auch ein klassisches sequenzielles Vorgehen kann bei einer konsistenten, konkret auf das Szenario bezogenen Begründung anerkannt werden.
