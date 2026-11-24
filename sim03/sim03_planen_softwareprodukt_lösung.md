# Prüfungssimulation sim03 – Planen eines Softwareproduktes (Aufgaben und Lösungen)

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100 Punkte  
**Aufgaben:** 4.  

## Ausgangssituation

Die **TextilKreis GmbH** produziert Berufsbekleidung in mehreren Chargen. Eine Charge bezeichnet eine eindeutig identifizierte Produktionsmenge eines bestimmten Artikels. Verarbeitete Materiallieferungen werden bisher in E-Mails dokumentiert. Kommt es zu einem nachträglich gemeldeten Materialmangel, dauert es zu lange, alle betroffenen Produktionschargen zu finden.

Das Projekt **FadenSpur** soll die Rückverfolgbarkeit gewährleisten. Folgende Rollen sind vorgesehen:

- **Wareneingang:** erfasst Materiallieferungen mit Lieferant, Liefernummer und Materialcode.
- **Fertigung:** dokumentiert, welche Materiallieferungen in welcher Produktionscharge verarbeitet wurden.
- **Qualitätssicherung (QS):** meldet einen Materialmangel, lässt die betroffenen Produktionschargen ermitteln und entscheidet, ob eine Rückrufsperre auszulösen ist.
- **Vertrieb:** darf den Sperrstatus einer Charge abfragen, jedoch keine Materialmängel erfassen oder Sperren ändern.
- **Administration:** verwaltet Benutzerrollen.

Jede Materiallieferung erhält eine systemweit eindeutige ID. Eine Produktionscharge kann Material aus mehreren Lieferungen enthalten; eine Lieferung kann in mehreren Chargen verwendet werden. Beim Anlegen einer Verknüpfung zwischen Lieferung und Charge wird die verwendete Menge als positive ganze Zahl in Metern erfasst. Ein Mangel bezieht sich immer auf genau eine vorhandene Materiallieferung. Das System soll danach **sämtliche direkt damit verknüpften Produktionschargen** anzeigen; andere Abhängigkeitsstufen sind nicht Gegenstand dieses Projekts.

Ein QS-Mitarbeiter muss die Sperre für jede betroffene Charge ausdrücklich freigeben. Eine Sperre wird protokolliert mit Zeitpunkt, Benutzerkennung und Begründung. Externe Fertigungssysteme senden Materialverwendungsdaten über eine Schnittstelle. Pro Tag werden rund 18.000 solche Meldungen erwartet. Das Netz zur externen Fertigung fällt gelegentlich für einige Minuten aus. Für Rückrufentscheidungen darf keine Meldung verloren gehen oder unbemerkt doppelt als Verwendung gebucht werden.

Für ein erstes nutzbares Release stehen zehn Wochen zur Verfügung. Der Umfang der Meldungsdaten und einzelne Eingabemasken sind bekannt, Detailanforderungen der QS können sich nach Anwenderfeedback ändern.

## Aufgabe 1: Anforderungen und Projektsteuerung (24 Punkte)

### 1a – 8 Punkte

Formulieren Sie **vier verschiedene, prüfbare funktionale Anforderungen** für FadenSpur. Nennen Sie jeweils eine beobachtbare Akzeptanzbedingung. (Je 2 Punkte.)

**Lösung**

Beispiele:


1. Wareneingang kann eine Lieferung mit Lieferant, Liefernummer und Materialcode anlegen; nach Speicherung existiert genau ein Datensatz mit eindeutiger Lieferungs-ID.
2. Fertigung übermittelt eine Verwendung mit Meldungs-ID, Lieferung, Charge und positiver Meterzahl; die Verwendung wird einmal verbucht.
3. QS meldet einen Mangel zu einer vorhandenen Lieferung; alle unmittelbar verknüpften Chargen werden angezeigt.
4. QS kann nach Prüfung ausgewählte Chargen sperren; jede Freigabe wird mit Benutzer, Begründung und Zeitpunkt protokolliert.

Pro Anforderung 1 Punkt für eigenständige zutreffende Funktion und 1 Punkt für beobachtbare Prüfbarkeit. Auch Rollenverwaltung oder Sperrstatusabfrage sind korrekt.

### 1b – 8 Punkte

Für die Verwendungsbuchung notiert das Team: "Eine Materiallieferung muss zuverlässig einer Charge zugeordnet werden können." Erstellen Sie eine kurze **fachliche Spezifikation** der Operation `bucheMaterialverwendung` mit **genau vier Festlegungen**: (1) erforderliche Eingabeparameter mit Datentypen, (2) mindestens eine fachliche Eingabeprüfung mit Fehlerverhalten, (3) Verhalten bei erfolgreicher erstmaliger Buchung und (4) Verhalten bei erneuter Übermittlung derselben Meldungs-ID, sowohl mit identischen als auch mit abweichenden Buchungsdaten. Legen Sie erforderliche Annahmen ausdrücklich fest. (Je Festlegung 2 Punkte.)

**Lösung**

Beispiel einer fachlichen Spezifikation der Operation `bucheMaterialverwendung`:

1. **Eingabeparameter:** `meldungId: UUID` (alternativ eindeutig formatierter `String`), `lieferungId: int`, `chargeId: int`, `meter: int`. Die Identität des Senders ergibt sich aus der Authentifizierung. Annahme: Jede `meldungId` bezeichnet genau eine fachliche Verwendungsbuchung und bleibt bei Wiederholungen unverändert.
2. **Fachliche Validierung:** `meter > 0`; außerdem müssen die referenzierte Materiallieferung und Produktionscharge existieren. Bei ungültiger Eingabe meldet die Operation einen fachlichen Fehler und nimmt keine Buchung vor.
3. **Erfolgreiche Erstbuchung:** Die Verwendung wird genau einmal gespeichert. Die eindeutige `meldungId` wird zusammen mit den Buchungsdaten in derselben Datenbanktransaktion dauerhaft gespeichert. Erst nach erfolgreichem Commit wird der Erfolg bestätigt.
4. **Erneute Übermittlung:** Ist die `meldungId` bereits erfolgreich verarbeitet und stimmen die Buchungsdaten mit der ursprünglichen Meldung überein, erfolgt keine weitere Buchung; die Operation bestätigt idempotent "bereits verarbeitet". Bei derselben `meldungId` mit abweichenden Buchungsdaten wird ein Konflikt zurückgegeben, ohne die bestehende Buchung zu verändern. Für diesen Vergleich sind die ursprünglichen Buchungsdaten oder ein geeigneter Vergleichswert gespeichert.

**Bewertung**

- Festlegung 1: 2 Punkte für vollständige Buchungsparameter mit plausiblen Datentypen.
- Festlegung 2: 2 Punkte für mindestens eine richtige fachliche Prüfung einschließlich Ablehnungsverhalten.
- Festlegung 3: 2 Punkte für eindeutige Erstbuchung und dauerhaft konsistente Speicherung.
- Festlegung 4: je 1 Punkt für die idempotente Behandlung einer identischen Wiederholung und für die Konfliktbehandlung bei abweichenden Buchungsdaten.

Andere fachlich konsistente Datentypen, Rückgabewerte und API-Formate sind zulässig. Die technische Absicherung konkurrierender Wiederholungen ist Gegenstand von Aufgabe 3b.

### 1c – 8 Punkte

Das Team plant zwei jeweils fünfwöchige Entwicklungsabschnitte. Der Vertrieb möchte die Benutzeroberfläche vollständig am Anfang festschreiben; die QS erwartet dagegen Erkenntnisse erst aus einem frühen Prototyp. **Entscheiden und begründen** Sie, welches Vorgehen (streng sequenziell oder iterativ-inkrementell) zum Projekt besser passt. Geben Sie **zwei fallbezogene Gründe** und **eine geeignete Methode zur kontrollierten Behandlung späterer Änderungswünsche** an. (Entscheidung 2, Gründe je 2, Methode 2 Punkte.)

**Lösung**

Iterativ-inkrementelles Vorgehen (2); QS-Anforderungen werden durch einen frühen nutzbaren Stand präzisiert (2), das zehnwöchige Projekt kann nach fünf Wochen Feedback nutzen und einen zweiten Inkrementumfang nachsteuern (2); Änderungswünsche werden in einem priorisierten Product Backlog mit Akzeptanzkriterien, Aufwandsschätzung und dokumentierter Entscheidung geprüft (2). Ein sequenzielles Vorgehen bei angeführter zwingender Fixierung aller Anforderungen wäre hier schwächer; eine alternative Entscheidung erhält die Entscheidungspunkte nur bei fachlich tragfähiger fallbezogener Begründung.

## Aufgabe 2: Anwendungsfälle und Berechtigungen (26 Punkte)

### 2a – 12 Punkte

Erstellen Sie ein **UML-Anwendungsfalldiagramm** innerhalb einer Systemgrenze "FadenSpur". Modellieren Sie **ausschließlich** die vier Akteure **Wareneingang**, **Fertigung**, **QS** und **Vertrieb** sowie genau diese sechs Anwendungsfälle:

1. Materiallieferung erfassen
2. Materialverwendung buchen
3. Materialmangel melden
4. Betroffene Chargen ermitteln
5. Rückrufsperre freigeben
6. Sperrstatus anzeigen

Stellen Sie die direkten Akteur-Anwendungsfall-Verbindungen gemäß Ausgangssituation dar. Die QS kann **"Betroffene Chargen ermitteln" auch eigenständig** für einen bereits gemeldeten Materialmangel aufrufen. Bei jeder neuen Mangelmeldung wird dieser Anwendungsfall außerdem **zwingend** im Rahmen von "Materialmangel melden" ausgeführt. Modellieren Sie diese Einbeziehung mit einer geeigneten UML-Beziehung. Andere Include-/Extend-Beziehungen werden nicht verlangt.

**Lösung**

Das vollständige UML-Anwendungsfalldiagramm ist in der editierbaren UMLet-Datei [sim03_planen_softwareprodukt_lösung_2a.uxf](./sim03_planen_softwareprodukt_lösung_2a.uxf) dargestellt.

**Bewertung**

- Systemgrenze "FadenSpur": 1 Punkt.
- Vier korrekt dargestellte Akteure: 2 Punkte.
- Sechs korrekt benannte Anwendungsfälle: 3 Punkte.
- Sechs direkte Assoziationen zwischen Akteuren und Anwendungsfällen, einschließlich der **eigenständigen** QS-Interaktion "Betroffene Chargen ermitteln": 4 Punkte.
- Gestrichelte `«include»`-Beziehung mit offenem Pfeil **von "Materialmangel melden" zu "Betroffene Chargen ermitteln"**: 2 Punkte.

Die zusätzliche direkte QS-Assoziation ist fachlich begründet, weil die Chargenermittlung auch unabhängig von einer neuen Mangelmeldung aufgerufen werden kann. Andere sinnvolle Layouts sind gleichwertig.

### 2b – 8 Punkte

Erstellen Sie eine **Berechtigungsmatrix** für die Rollen Wareneingang, Fertigung, QS, Vertrieb und die Aktionen "Lieferung erfassen", "Verwendung buchen", "Mangel melden", "Sperre freigeben". Tragen Sie pro Zelle **erlaubt** oder **verboten** ein. Nur die in der Ausgangssituation ausdrücklich genannten Berechtigungen gelten. (16 Einträge à 0,5 Punkte.)

**Lösung**

16 Zellen je 0,5 Punkte; nicht ausdrücklich gewährte Rechte sind verboten.


| Rolle | Lieferung erfassen | Verwendung buchen | Mangel melden | Sperre freigeben |
|---|---|---|---|---|
| Wareneingang | erlaubt | verboten | verboten | verboten |
| Fertigung | verboten | erlaubt | verboten | verboten |
| QS | verboten | verboten | erlaubt | erlaubt |
| Vertrieb | verboten | verboten | verboten | verboten |

### 2c – 6 Punkte

Ein Entwickler schlägt vor, dass die Anwendung lediglich bei nicht berechtigten Benutzern die Schaltfläche "Rückrufsperre freigeben" versteckt. **Erläutern** Sie, warum dies als Zugriffsschutz nicht genügt, und **beschreiben** Sie zwei serverseitige Kontrollen für diese Funktion. (Problem 2, Kontrollen je 2 Punkte.)

**Lösung**

Eine versteckte Schaltfläche verhindert keinen direkt konstruierten HTTP-Request (2). Server prüft bei jeder Anfrage authentifizierte Identität und QS-Rolle (2), serverseitig den aktuellen Charge-Status, zulässige Auswahl und zwingende Begründung; die Freigabe wird manipulationsgeschützt protokolliert (2). Auch serverseitige Objektberechtigung und transaktionale Freigabe sind vertretbar.

## Aufgabe 3: Schnittstelle, Zuverlässigkeit und Sicherheit (25 Punkte)

Die Fertigung meldet einen Materialverbrauch als JSON-Nachricht. Vorgesehen ist eine einzelne Nachricht pro Verwendungsbuchung. Jede Nachricht besitzt eine über alle Sender hinweg eindeutige `meldungId`.

### 3a – 9 Punkte

Vergleichen Sie die direkte synchrone Verarbeitung per HTTPS-Anfrage mit einer **persistenten Nachrichtenwarteschlange** anhand von **drei** für FadenSpur relevanten Kriterien: Verhalten bei temporärem Netzausfall, Entkopplung bei Lastspitzen und Rückmeldung an den Sender. Erläutern Sie pro Kriterium den wesentlichen Unterschied. (Je 3 Punkte.)

**Lösung**

| Aspekt | Direkter synchroner HTTPS-Aufruf | Persistente Queue |
|---|---|---|
| Unterbrochene Verbindung | Sender muss Fehler erkennen und nach dauerhaft lokaler Zwischenspeicherung erneut senden | Bei erreichbarem Broker kann eine bestätigte Annahme persistiert und später verarbeitet werden; bei nicht erreichbarem Broker braucht der Sender ebenfalls Wiederholung/Zwischenspeicher |
| Lastspitzen | Verarbeitungsserver muss Anfragen sofort übernehmen oder abweisen | Worker verarbeiten gepufferte Meldungen kontrolliert; Brokerkapazität und Rückstauüberwachung nötig |
| Rückmeldung | Fachliches Ergebnis kann im selben HTTP-Aufruf erfolgen | Annahmebestätigung ist nicht gleich erfolgreiche Fachverarbeitung; separater Status-/Fehlerkanal nötig |

Je 3 Punkte für korrekten Unterschied, konkrete Konsequenz und eindeutige technische Einschränkung. "Queue garantiert exakt einmal" ist nicht korrekt.

### 3b – 8 Punkte

Eine Fertigungsanlage sendet eine Meldung, empfängt aber wegen eines Verbindungsabbruchs keine Antwort und wiederholt sie. **Beschreiben Sie einen technisch konsistenten Ablauf**, der die Verwendungsbuchung trotz dieser Wiederholung genau einmal wirksam werden lässt. Berücksichtigen Sie die eindeutige Meldungs-ID, die Datenbank, konkurrierende Anfragen und den Umgang mit einer fehlgeschlagenen Verarbeitung. (Vier fachlich notwendige Aspekte à 2 Punkte.)

**Lösung**

(1) Jede Meldung trägt eine global eindeutige, stabile `meldungId` für sämtliche Wiederholungen (2). (2) Datenbanktabelle mit UNIQUE/PRIMARY KEY auf Meldungs-ID; Prüfung und Insert dürfen nicht nur als nicht-atomare Vorabfrage erfolgen (2). (3) Eintrag der Meldungs-ID und fachliche Buchung in **derselben Datenbanktransaktion**; konkurrierende Wiederholungen werden durch Unique-Constraint und geeignete Konfliktbehandlung serialisiert/abgewiesen (2). (4) Nach Commit ACK mit bereits verarbeitetem Resultat; bei Fehler vor Commit Rollback und erneute Zustellung möglich, fehlerhafte Nachrichten nach begrenzten Retries in einen überwachten Fehlerkanal (2). Zulässig sind Inbox-/Idempotent-Consumer-Ansätze mit gleicher Sicherheit. Eine getrennte Speicherung der ID vor der eigentlichen Buchung ohne atomaren Zusammenhang ist nicht ausreichend.

### 3c – 8 Punkte

Die Rückrufdaten sind geschäftskritisch. **Nennen und begründen** Sie vier geeignete technische oder organisatorische Sicherheits- bzw. Verfügbarkeitsmaßnahmen. Decken Sie dabei mindestens **Integrität**, **Vertraulichkeit**, **Nachvollziehbarkeit** und **Wiederherstellbarkeit** ab. (Je 2 Punkte.)

**Lösung**

Integrität: Prüfsummen/Signaturen bzw. Transaktions- und Constraint-Kontrollen verhindern oder erkennen Manipulation und inkonsistente Buchungen (2). Vertraulichkeit: TLS auf Transportwegen, rollenbasierte Zugriffsrechte und Verschlüsselung gespeicherter vertraulicher Daten (2). Nachvollziehbarkeit: unveränderlich/zugriffsgeschützt geführte Audit-Logs für Sperrfreigaben mit Identität, Zeitpunkt und Entscheidung (2). Wiederherstellbarkeit: regelmäßige Backups, zeitpunktbezogene Wiederherstellung, Wiederherstellungstest und getrennte Aufbewahrung (2). Gleichwertige konkrete Maßnahmen sind akzeptabel, wenn ihre Wirkung erklärt wird.

## Aufgabe 4: Oberfläche und Qualitätssicherung (25 Punkte)

Die QS benötigt die folgende Ansicht "Rückrufprüfung". Sie zeigt eine ausgewählte mangelhafte Materiallieferung und die davon betroffenen Chargen. Je Charge sind Chargen-ID, Artikel, verwendete Menge und aktueller Sperrstatus sichtbar. Vor der Freigabe muss eine Begründung eingegeben und die betroffene Auswahl ausdrücklich bestätigt werden. Die Oberfläche wird überwiegend mit Tastatur und Bildschirmlesegerät verwendet.

### 4a – 9 Punkte

Entwerfen Sie als beschriftete textuelle Skizze die Ansicht mit **sechs fachlich geeigneten Anzeige- oder Bedienelementen**. Sie muss die Lieferungs-ID und eine strukturierte Liste betroffener Chargen anzeigen, die Auswahl zu sperrender Chargen ermöglichen sowie Begründung, Freigabeschritt und Rückmeldung unterstützen. Geben Sie pro Element dessen Zweck an. (Sechs Elemente à 1,5 Punkte.)

**Lösung**

Die **editierbare Oberflächenskizze** befindet sich in [`sim03_planen_softwareprodukt_lösung_4a.drawio`](./sim03_planen_softwareprodukt_lösung_4a.drawio). Sie zeigt die Ansicht "Rückrufprüfung" sowie einen gesonderten Bestätigungsschritt vor der endgültigen Sperrfreigabe. Die dargestellten Lieferungs-, Chargen- und Artikeldaten sind ausschließlich Beispieldaten.

Die **sechs nummerierten Elemente** der Skizze und ihre Zwecke:

1. **Lieferungsanzeige:** Überschrift "Rückrufprüfung" mit Lieferungs-ID zur eindeutigen Zuordnung des Rückruffalls.
2. **Chargentabelle:** Strukturierte Auflistung mit Chargen-ID, Artikel, verwendeter Menge in Metern und Sperrstatus.
3. **Chargenauswahl:** Je Charge eine einzeln beschriftete Checkbox zur Auswahl der zu sperrenden Chargen.
4. **Begründungsfeld:** Mehrzeiliges Pflichtfeld zur Dokumentation der fachlichen Begründung.
5. **Zweistufige Freigabe:** "Auswahl prüfen" öffnet eine Bestätigungsansicht mit den ausgewählten Chargen und der Begründung; erst "Sperre endgültig freigeben" löst die Sperre aus.
6. **Statusregion:** Verständliche Erfolgs- und Fehlerrückmeldung mit Angabe der betroffenen Chargen; für Tastatur und Bildschirmlesegeräte zugänglich.

**Bewertung**

Je Element 0,5 Punkt für die sinnvolle grafische beziehungsweise textuelle Darstellung und 1 Punkt für den beschriebenen, konkret erfüllten Zweck (insgesamt 9 Punkte). Andere übersichtliche Skizzen sind gleichwertig; mehr als sechs dargestellte UI-Elemente sind nicht automatisch falsch, sofern sechs geforderte Funktionen klar zugeordnet sind. Eine reine Aufzählung ohne Skizze erfüllt den Operator "Entwerfen" dagegen nicht vollständig.

### 4b – 8 Punkte

Beschreiben Sie vier **konkrete ergonomische beziehungsweise barrierefreie Gestaltungsregeln** für diese Ansicht und begründen Sie jeweils die Auswirkung auf den beschriebenen Arbeitsablauf. (Je 2 Punkte.)

**Lösung**

Viermal 2: permanente sichtbare Feld-/Spaltenbeschriftungen statt ausschließlich Platzhalter → Screenreader/Orientierung; vollständige Tastaturbedienbarkeit mit sichtbarem Fokus und logischer Reihenfolge → ohne Maus nutzbar; Status nicht nur durch Farbe, sondern Text und zugängliche Statusregion → eindeutig wahrnehmbar; Fehlermeldungen am Feld und mit programmatischer Verknüpfung, Fokus auf Fehlerzusammenfassung → korrigierbar. Alternative geeignete Maßnahmen: semantische Tabelle mit Überschriften, ausreichender Kontrast, keine unvorhersehbaren Kontextwechsel.

### 4c – 8 Punkte

Entwickeln Sie **zwei unterschiedliche Prüfmaßnahmen** für die Rückrufsperre: einen automatisierten Integrationstest gegen den Berechtigungs- und Persistenzpfad sowie einen manuellen Abnahmetest für die QS-Oberfläche. Geben Sie jeweils **Vorbedingungen, Schritte, Soll-Ergebnis und überprüfte Qualitätseigenschaft** an. (Je 4 Punkte.)

**Lösung**

Integrationstest: Voraussetzung vorhandene ungesperrte Charge, Materialmangel, Testidentitäten "Vertrieb" und "QS"; Schritte direkter POST der Freigabe zunächst als Vertrieb, danach mit QS und gültiger Begründung; Soll: Vertrieb erhält 403 und keine Mutation, QS erhält Bestätigung, Charge wird gesperrt, Audit-Eintrag vorhanden; Eigenschaft: Zugriffsschutz, Datenintegrität und Persistenz. Je 1 Punkt Vorbedingung, Schritte, überprüfbares Soll, Eigenschaft.

Abnahmetest: QS testet mit Tastatur und Screenreader einen vorbereiteten Rückruffall mit zwei betroffenen Chargen, wählt eine aus, gibt die Begründung ein und bestätigt; Soll: vollständige verständliche Ansagen, Fokus sichtbar, nur ausgewählte Charge gesperrt, Rückmeldung mit Chargen-ID; Eigenschaft: Benutzbarkeit/Barrierefreiheit und fachliche Korrektheit. Je 1 Punkt.
