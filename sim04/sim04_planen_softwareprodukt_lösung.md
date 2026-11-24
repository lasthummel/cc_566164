# Prüfungssimulation sim04 – Planen eines Softwareproduktes

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100  
**Aufgaben:** 4.


## Ausgangssituation

Die **RegioAnalytik GmbH** untersucht im Auftrag kommunaler Betriebe Wasserproben. Ihre bisherige Probenverwaltung besteht aus Papierformularen und einer gemeinsam verwendeten Tabellenkalkulation. Die Anwendung **ProbenNet** soll Probenentnahmen, Laboreingang, Qualitätsprüfung und die Freigabe von Prüfberichten dokumentieren.

**Außendienst:** erfasst Proben bei der Entnahme auf Tablets, zum Teil ohne Netzverbindung. Erfasst werden eine eindeutige Proben-ID, Entnahmestelle, Entnahmezeitpunkt und ein Freitextkommentar. Später werden Daten an den Server übertragen.

**Labor:** prüft eingegangene Proben auf Vollständigkeit und Plausibilität, erfasst die Untersuchungsergebnisse und kann fehlerhafte oder unvollständige Angaben zur Korrektur an den Außendienst zurückgeben.

**Laborleitung:** gibt ausschließlich vollständig dokumentierte und fachlich bestandene Proben zur Berichterstellung frei. Bei nicht bestandener Qualitätsprüfung wird die Probe gesperrt; eine Freigabe ist dann nicht möglich. Eine gesperrte Probe kann nur nach einer erneuten fachlichen Untersuchung mit neuen Ergebnissen erneut geprüft werden. Eine fachliche Nachprüfung gehört nicht zum hier modellierten Freigabeablauf.

**Auftraggeber:** kann nach Freigabe ausschließlich den eigenen Prüfbericht im Webportal abrufen. Untersuchungsrohdaten und interne Kommentare sind für Auftraggeber nicht sichtbar.

Die Datenübertragung kann bei schlechtem Mobilfunk mehrfach unterbrochen werden. Werden die Daten einer Probe auf zwei Geräten während einer Offlinephase verändert, muss ein Konflikt erkannt werden; eine Änderung darf nicht unbemerkt die andere überschreiben. Der Server verwaltet für jede Probe eine ganzzahlige Versionsnummer, die bei jeder erfolgreichen Änderung um 1 erhöht wird. Änderungen dürfen nur mit der aktuellen Versionsnummer erfolgen.

Anforderungen: 25 Außendienstgeräte, höchstens 100 gleichzeitige Portalzugriffe, personenbezogene Daten in Mitarbeiterkommentaren möglich; der erste produktive Einsatz ist in drei Monaten vorgesehen. Das Labor muss auch während eines kurzfristigen Ausfalls des Mobilfunknetzes weiterarbeiten können.

## Aufgabe 1: Spezifikation und Prüfbedingungen (24 Punkte)

### 1a – 8 Punkte

Formulieren Sie **vier unterschiedliche funktionale Anforderungen** an ProbenNet als prüfbare Aussagen. Nennen Sie jeweils ein konkretes beobachtbares Akzeptanzergebnis. (Je 2 Punkte.)

**Lösung**

Beispielantworten:
1. Das System lässt Außendienstmitarbeitende eine Probe mit eindeutiger Proben-ID, Entnahmestelle und Zeitpunkt anlegen. **Akzeptanz:** nach dem Speichern ist ein Datensatz mit genau diesen Angaben vorhanden.
2. Offline erfasste Proben werden nach Wiederherstellung der Verbindung übertragen. **Akzeptanz:** der Datensatz ist nach erfolgreicher Synchronisation auf dem Server abrufbar.
3. Das Labor kann unvollständige Proben zur Korrektur zurückgeben. **Akzeptanz:** der Außendienst sieht die Korrekturanforderung und kann fehlende Angaben ergänzen.
4. Auftraggeber sehen nur eigene freigegebene Berichte. **Akzeptanz:** ein anderer Auftraggeber erhält auf die fremde Bericht-ID keine Berichtsdaten.

**Je 1 Punkt** für klar benannte Systemfunktion, **je 1 Punkt** für konkret überprüfbares Ergebnis. Keine Punkte für isolierte Schlagwörter ohne Prüfmöglichkeit. Auch andere Funktionen aus dem Szenario sind zulässig.

### 1b – 8 Punkte

Die Auftraggeberseite notiert zwei Anforderungen: "Synchronisation muss zuverlässig sein" und "Das Portal muss schnell reagieren". Formulieren Sie für beide **je ein messbares nichtfunktionales Akzeptanzkriterium** einschließlich einer sinnvollen Messbedingung. Plausible Zielwerte dürfen als Projektfestlegung eingeführt werden. (Je 4 Punkte.)

**Lösung**

Beispielhafte **Projektfestlegungen**:
- Zuverlässigkeit: Nach einer simulierten 20-minütigen Unterbrechung und anschließender stabiler Netzverbindung werden **mindestens 99 von 100** zuvor lokal bestätigten, konfliktfreien neuen Probeaufträgen innerhalb von **60 Sekunden** erfolgreich auf dem Server gespeichert. **Kein Auftrag darf verloren gehen:** Jeder bis dahin nicht bestätigte Auftrag bleibt lokal persistent gespeichert und ist eindeutig als "noch nicht synchronisiert" erkennbar. Prüfen mit 100 vorbereiteten Aufträgen, simuliertem Netzausfall und Kontrolle von Serverdaten, lokaler Warteschlange und Synchronisationsstatus nach 60 Sekunden.
- Geschwindigkeit: Bei 100 gleichzeitigen Portalnutzern werden 95 % der Abrufe eigener freigegebener Berichte in höchstens zwei Sekunden serverseitig beantwortet. Prüfen mit einem dokumentierten Lasttest und definierten Beispielberichten.

Je Kriterium: **2 Punkte** messbares Ergebnis/Zielwert, **1 Punkt** klare Messbedingungen, **1 Punkt** zum passenden Qualitätsmerkmal gehörende fachliche Aussage. Zielwerte sind didaktische Beispiele, keine offiziellen Vorgaben.

### 1c – 8 Punkte

Der Server besitzt für Probe P-510 die Version 7. Bei jedem erfolgreich angenommenen Änderungsauftrag wird die Version um genau 1 erhöht. Ankommende Aufträge werden in der dargestellten Reihenfolge vollständig nacheinander verarbeitet. Ein Auftrag wird genau dann angenommen, wenn seine übermittelte Basisversion der aktuellen Serverversion entspricht; abgelehnte Aufträge verändern keine Daten.

| Auftrag | Gerät | übermittelte Basisversion |
|---|---|---:|
| A | Tablet Ost | 7 |
| B | Tablet West | 7 |
| C | Tablet Ost | 8 |
| D | Tablet West | 9 |

Ermitteln Sie für jeden Auftrag, ob er angenommen wird und welche Serverversion **unmittelbar danach** vorliegt. (Vier Zeilen à 2 Punkte.)

**Lösung**

| Auftrag | Entscheidung | Version direkt danach | Begründung |
|---|---|---:|---|
| A | angenommen | 8 | Basis 7 = Server 7 |
| B | abgelehnt | 8 | Basis 7 ≠ Server 8 |
| C | angenommen | 9 | Basis 8 = Server 8 |
| D | angenommen | 10 | Basis 9 = Server 9 |

Je 1 Punkt Entscheidung, 1 Punkt korrekte Folgever­sion. Der abgelehnte Auftrag B darf die Serverversion nicht erhöhen.

## Aufgabe 2: Architektur, Schnittstelle und Risiken (26 Punkte)

### 2a – 8 Punkte

Vergleichen Sie (A) eine Webanwendung, die bei jedem Speichervorgang eine permanente Serververbindung voraussetzt, und (B) eine App mit lokaler Zwischenspeicherung und nachträglicher Synchronisation. Erläutern Sie **je zwei fallbezogene Vor- oder Nachteile** pro Variante. (Vier Aspekte à 2 Punkte.)

**Lösung**

| Variante | Szenariobezogene Bewertung |
|---|---|
| A | Zentrale Datenhaltung vermeidet den zusätzlichen Aufwand für Offline-Synchronisation; Eingaben können bei bestehender Verbindung unmittelbar serverseitig geprüft werden. **Gleichzeitige Änderungen sind dennoch möglich** und erfordern auch hier eine serverseitige Konfliktbehandlung (z. B. Versionsprüfung). |
| A | Bei fehlendem Mobilfunk ist Speichern nicht möglich; Außendienst kann Proben nicht wie gefordert weiter erfassen. |
| B | Offline-Erfassung im Feld ist möglich; ausstehende Aufträge bleiben lokal bis zur Synchronisation erhalten. |
| B | Synchronisationslogik, sichere lokale Speicherung, Konflikterkennung und Wiederholungsbehandlung erhöhen Entwicklungs- und Testaufwand. |

Je 2 Punkte pro zutreffendem, erläutertem Vor- oder Nachteil. Aussagen über vermeintlich automatische Datensicherheit ohne Begründung nicht anerkennen.

### 2b – 10 Punkte

Entwerfen Sie einen REST-Aufruf zum Ändern des Freitextkommentars einer bestehenden Probe P-510. Legen Sie **HTTP-Methode, URI, ein JSON-Beispiel für den neuen Kommentar, die technische Übermittlung der Basisversion, einen geeigneten Erfolgsstatus und zwei passende Fehlerstatusfälle** fest. Die Identität der bearbeitenden Person ist aus einem vorhandenen Zugangstoken bekannt. Gehen Sie davon aus, dass eine Versionsprüfung atomar mit der Änderung erfolgt. (Methode/URI 2; JSON 2; Basisversion 2; Erfolg 1; zwei Fehlerfälle 3 Punkte.)

**Lösung**

Eine mögliche HTTP-Anfrage:

```http
PATCH /api/proben/P-510 HTTP/1.1
Content-Type: application/json
If-Match: "7"
Authorization: Bearer <zugangstoken>

{"kommentar":"Entnahme bei Nordbrunnen um 08:35"}
```
- **2 P:** PATCH für partielle Änderung und ressourcenbezogene URI; alternativ PUT auf eine sauber definierte vollständige Teilressource mit passender Semantik.
- **2 P:** syntaktisch korrektes JSON mit neuem Kommentar.
- **2 P:** Versionskennung im If-Match-Header und serverseitige atomare Vergleichs-/Schreiboperation; alternativ klar spezifizierter Versionsparameter bei konsistenter Konfliktbehandlung.
- **1 P:** Erfolg 200 OK mit Repräsentation oder 204 No Content ohne Antwortkörper.
- **3 P:** z. B. 412 Precondition Failed bei nicht mehr passendem If-Match (2 P), 403 Forbidden bei fehlender Berechtigung (1 P); zulässig sind 404 für unbekannte ID, 400 für fehlerhaftes JSON. Wird für ein reines Geschäftsversionsfeld 409 Conflict statt 412 definiert, ist das bei sauberer Erläuterung ebenfalls fachlich korrekt.

**Einordnung der Versionskennung:** Für diese Beispiel-API ist festgelegt, dass der Server die aktuelle Versionsnummer der vollständigen Probenrepräsentation als **starken HTTP-ETag** ausgibt: Version 7 entspricht `ETag: "7"`. Der Client übernimmt diesen Validator nach einem vorherigen Abruf in `If-Match: "7"`. Bei erfolgreicher Änderung wird Version 8 und entsprechend `ETag: "8"` vergeben. Ein ETag ist nicht automatisch die Datenbank-Versionsnummer; die Abbildung ist hier eine explizite API-Festlegung. `If-Match` verwendet einen starken Vergleich des Validators. Bei nicht erfüllter Vorbedingung ist grundsätzlich **412 Precondition Failed** passend (siehe [RFC 9110, Abschnitt 13.1.1](https://www.rfc-editor.org/rfc/rfc9110.html#section-13.1.1)). Die Prüfung der Vorbedingung und die Änderung müssen atomar erfolgen, damit keine Lost Updates entstehen. Bei einem separat definierten Geschäftsversionsfeld kann ein begründeter `409 Conflict` akzeptiert werden.

### 2c – 8 Punkte

Analysieren Sie zwei unterschiedliche technische oder organisatorische Risiken beim Einsatz von ProbenNet. Beschreiben Sie zu jedem Risiko **eine konkrete Folge und eine geeignete Gegenmaßnahme**. Die Risiken müssen aus unterschiedlichen Problemfeldern stammen (z. B. Datensicherheit und Ausfallsicherheit). (Je 4 Punkte.)

**Lösung**

- **Risiko Datenschutz:** Persönliche Angaben in Kommentaren könnten unberechtigt eingesehen werden (**Risiko und Folge 2 P**). Gegenmaßnahme: Autorisierung je Rolle und Probe, protokollierte Zugriffe, verschlüsselte Übertragung, Datensparsamkeit (**2 P** für konkrete geeignete Maßnahme).
- **Risiko Offline-/Netzausfälle:** Lokale Änderungen erreichen den Server verspätet oder gehen bei Geräteverlust verloren (**2 P**). Gegenmaßnahme: verschlüsselte dauerhafte lokale Warteschlange, nachvollziehbarer Sync-Status und idempotente Wiederholungen (**2 P**).
Andere zwei voneinander unabhängige, gleichwertige Paare sind zulässig.

## Aufgabe 3: UML-Aktivitätsdiagramm zur Probenfreigabe (24 Punkte)

Für den **hier ausschließlich betrachteten Freigabeablauf** gelten folgende Regeln:

1. Das Labor prüft die Vollständigkeit der Probendokumentation.
2. Bei fehlenden Angaben fordert das Labor eine Korrektur vom Außendienst an. Der Außendienst ergänzt die Angaben. Anschließend prüft das Labor die Vollständigkeit erneut. Dieser Vorgang kann mehrfach auftreten.
3. Ist die Dokumentation vollständig, prüft das Labor die bereits vorliegenden Untersuchungsergebnisse anhand der fachlichen Qualitätskriterien.
4. Bestehen die Ergebnisse die Qualitätsprüfung nicht, sperrt das Labor die Probe und informiert den Außendienst. Der betrachtete Ablauf endet.
5. Bestehen die Ergebnisse die Qualitätsprüfung, gibt die Laborleitung die Probe frei. Das System stellt den zugehörigen Bericht für den Auftraggeber bereit. Der betrachtete Ablauf endet.

### 3a – 16 Punkte

Erstellen Sie ein vollständiges **UML-Aktivitätsdiagramm** für diesen Ablauf mit Start- und Endknoten, Aktivitäten, Entscheidungen, beschrifteten Guard-Bedingungen und der Korrekturschleife. Kennzeichnen Sie die Zuständigkeit von Labor, Außendienst, Laborleitung und System durch Swimlanes oder eine gleichwertig eindeutige Zuordnung.

**Lösung**

**Maßgebliche Musterlösung (UMLet):** [UML-Aktivitätsdiagramm (editierbare .uxf-Datei)](./sim04_planen_softwareprodukt_lösung_3a.uxf).

Das Diagramm verwendet einen gefüllten Startknoten, zwei Aktivitätsendknoten, UML-Aktionsknoten, Entscheidungsrauten mit Guards und einen **Zusammenführungsknoten (Merge)** vor der wiederholbaren Vollständigkeitsprüfung. Die Zuständigkeiten sind in den Aktionsbeschriftungen eindeutig angegeben (Labor, Außendienst, Laborleitung, System). Der Merge verbindet den erstmaligen Eintritt und die Rückkehr nach einer Korrektur **alternativ**, ohne auf zwei gleichzeitig eintreffende Kontrollflüsse zu warten.

**Kontrollfluss zur Überprüfung:**

1. Start → Merge → Labor: Vollständigkeit prüfen → Entscheidung.
2. [nicht vollständig] → Labor: Korrektur anfordern → Außendienst: Angaben ergänzen → zurück zum Merge.
3. [vollständig] → Labor: Ergebnisse fachlich prüfen → Entscheidung.
4. [nicht bestanden] → Labor: Probe sperren → Labor: Außendienst informieren → Aktivitätsende.
5. [bestanden] → Laborleitung: Probe freigeben → System: Prüfbericht bereitstellen → Aktivitätsende.

Die bisherige [Draw.io-Fassung](./sim04_planen_softwareprodukt_lösung_3a.drawio) bleibt unverändert erhalten, ist aber **nicht** die verbindliche Referenz für UML-Notation.

**Bewertung (16 Punkte):**

- Startknoten und fachlich korrekte Aktivitätsendknoten: **2 P**.
- Vollständigkeitsprüfung, Entscheidung und eindeutig beschriftete Guards: **3 P**.
- Korrekturanforderung, Ergänzung und Rückschleife mit korrektem Merge: **4 P**.
- Fachliche Qualitätsprüfung und zweite Entscheidung mit Guards: **3 P**.
- Sperren und Informieren im negativen Zweig: **2 P**.
- Freigabe durch Laborleitung und Berichtsbereitstellung im positiven Zweig: **2 P**.

Die in der Aufgabenstellung geforderten Zuständigkeiten müssen an den jeweiligen Aktionen erkennbar sein (durch Swimlanes oder eindeutige Rollenangaben), damit diese vollständig bewertet werden. Ein direkter Rücksprung auf eine Aktion mit weiterem eingehendem Kontrollfluss **ohne Merge** ist keine gleichwertige UML-Lösung: Mehrere eingehende Kontrollflüsse an einer Aktion haben implizite Synchronisationssemantik. Alternativ ausgeführte Wege sind mit einem Merge zusammenzuführen. Andere fachlich und formal gleichwertige Diagramme sind zulässig.

### 3b – 8 Punkte

Erläutern Sie **eine notwendige serverseitige Berechtigungsprüfung und eine notwendige serverseitige Prüfung der fachlichen Freigabebedingungen** vor dem Freigeben einer Probe. Begründen Sie jeweils kurz, warum eine ausschließliche Prüfung im Frontend nicht ausreicht. (Je 4 Punkte.)

**Lösung**

1. **Serverseitige Autorisierung (2 P) und Begründung (2 P):** Die authentifizierte Person muss für die Freigabe der konkreten Probe berechtigt sein (im Szenario: Rolle Laborleitung). Eine ausgeblendete oder deaktivierte Schaltfläche schützt den API-Endpunkt nicht; direkte HTTP-Aufrufe könnten die Frontend-Prüfung umgehen.
2. **Fachliche Freigabebedingungen (2 P) und Begründung (2 P):** Der Server muss prüfen, dass die Dokumentation vollständig ist, die zugehörigen aktuellen Untersuchungsergebnisse die fachliche Qualitätsprüfung bestanden haben und keine Sperre vorliegt. Der Frontend-Zustand kann veraltet oder manipuliert sein; die Prüfung muss unmittelbar beim Freigabevorgang serverseitig und konsistent mit der Zustandsänderung erfolgen.

## Aufgabe 4: Bedienoberfläche und Qualitätssicherung (26 Punkte)

Auf einem Tablet soll der Außendienst eine neue Probe erfassen. Ein erster Entwurf sieht vor:

- Die Proben-ID kann nur durch einen sehr kleinen, nicht beschrifteten Scan-Knopf erfasst werden.
- Datum und Uhrzeit sind ein einzelnes frei beschreibbares Textfeld.
- Der Status einer Offline-Speicherung wird nicht angezeigt.
- Bei Synchronisationskonflikten zeigt die Anwendung lediglich "Fehler 42".
- Das Kommentarformular besitzt keine beschrifteten Eingabefelder und keine Tastaturnavigation.

### 4a – 9 Punkte

Analysieren Sie **drei unterschiedliche Probleme** und erläutern Sie jeweils eine konkrete Auswirkung auf Benutzbarkeit oder Barrierefreiheit. (Je 3 Punkte.)

**Lösung**

Beispiel:
- Unbeschrifteter kleiner Scan-Knopf: keine eindeutige Funktion, schlecht bedienbar bei motorischen Einschränkungen; Größe/Beschriftung und alternative manuelle Eingabe fehlen.
- Freitext für Datum/Uhrzeit: mehrdeutige Formate führen zu Parsingfehlern; strukturierte Eingabe/Formatvalidierung fehlt.
- Keine Statusanzeige bei Offline-Speicherung: Nutzer können lokales Speichern fälschlich als erfolgreiche Serverübertragung auffassen.
Ebenfalls geeignet: kryptischer Konflikttext oder fehlende Tastaturnavigation. **Je 1 P Problem, 2 P konkrete Wirkung**.

### 4b – 9 Punkte

Entwerfen Sie eine kompakte **textuelle Bedienoberfläche mit genau sechs fachlichen Eingabe- bzw. Anzeigeelementen**. Nennen Sie je Element den Bedienelementtyp und die fachliche Funktion. Die Oberfläche muss eine manuelle Eingabe der Proben-ID ermöglichen und den Offline-/Synchronisationszustand erkennen lassen. Eine zusätzliche Speicheraktion darf ergänzt werden und wird nicht mitgezählt. (Sechs Elemente à 1,5 Punkte.)

**Lösung**

| Element | Typ | Zweck |
|---|---|---|
| Proben-ID | Beschriftetes Texteingabefeld plus Scan-Option | Scan oder manuelle Eingabe |
| Entnahmestelle | Suchbare Auswahlliste | Eindeutige Stelle wählen |
| Entnahmedatum | Datumsauswahl | Kalenderdatum |
| Entnahmeuhrzeit | Zeitfeld (HH:mm) | Uhrzeit eindeutig erfassen |
| Kommentar | Beschriftetes mehrzeiliges Textfeld | Zusatzinformationen |
| Sync-Status | Beschriftete textuelle Statusanzeige (z. B. "Lokal gespeichert / Synchronisiert / Konflikt") | Lokal bestätigte Speicherung von erfolgreicher Server-Synchronisation und Konflikten unterscheiden |

Je 0,5 P eindeutige Bezeichnung, 0,5 P fachlich passender Typ, 0,5 P Funktion. Gleichwertige sechs Eingabe-/Anzeigeelemente mit Pflichtinhalten zulässig. Eine zusätzliche klar beschriftete Aktion "Lokal speichern" ist sinnvoll, zählt aber **nicht** zu den sechs bewerteten Elementen; sie ist nicht zwingend zu zeichnen. Die Scan-Option kann in das Proben-ID-Eingabeelement integriert sein.

### 4c – 8 Punkte

Entwickeln Sie vier voneinander verschiedene Testfälle für die Synchronisation: erfolgreicher Erstversuch, Offline-Zwischenspeicherung, Versionskonflikt und wiederholte Übertragung nach unklarer Antwort. Geben Sie für jeden Test **Ausgangslage/Aktion und erwartetes beobachtbares Ergebnis** an. Für den Wiederholungsfall ist anzunehmen, dass jede Änderung eine unveränderliche, global eindeutige Auftrags-ID erhält und ein bereits erfolgreich verarbeiteter Auftrag beim erneuten Empfang ohne erneute Änderung bestätigt wird. (Vier Testfälle à 2 Punkte.)

**Lösung**

| Fall | Ausgangslage / Aktion | Erwartetes Ergebnis |
|---|---|---|
| Erstversuch | Online, aktuelle Basisversion, eindeutige Auftrags-ID | Änderung genau einmal auf Server; neue Version und Sync-Erfolg angezeigt |
| Offline | Tablet ohne Netz, lokales Speichern | Änderung bleibt lokal persistent und eindeutig als "noch nicht synchronisiert" markiert |
| Konflikt | Serverversion 7; zwei Geräte übermitteln unterschiedliche Änderungen mit Basisversion 7 nacheinander | Erster Auftrag wird übernommen (Serverversion 8); zweiter wird abgelehnt, überschreibt Version 8 **nicht** und bleibt lokal als ungelöster Konflikt mit seinem Änderungsinhalt erhalten; Konfliktstatus ist sichtbar |
| Wiederholung | Server verarbeitet Auftrag X mit Basisversion 7 erfolgreich (neue Version 8), aber die Antwort geht verloren; derselbe Auftrag X wird mit **identischer Auftrags-ID und identischem Inhalt** erneut gesendet | Server erkennt X als bereits verarbeitet, führt keine zweite Änderung durch und erhöht die Version **nicht erneut** (bleibt 8); das Ergebnis der ersten Verarbeitung wird erneut bestätigt |

Je 1 P konkret prüfbare Ausgangslage/Aktion, 1 P zutreffendes erwartetes Ergebnis. Beim Konflikt muss die abgelehnte lokale Änderung zur Klärung erhalten bleiben, ohne die Serverfassung zu überschreiben. Eine erneute Verarbeitung derselben Auftrags-ID darf **keine weitere Versionsänderung** verursachen.

**Punktekontrolle:** 24 + 26 + 24 + 26 = **100**.
