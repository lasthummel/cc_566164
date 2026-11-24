# Prüfungssimulation sim03 – Planen eines Softwareproduktes

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

### 1b – 8 Punkte

Für die Verwendungsbuchung notiert das Team: "Eine Materiallieferung muss zuverlässig einer Charge zugeordnet werden können." Erstellen Sie eine kurze **fachliche Spezifikation** der Operation `bucheMaterialverwendung` mit **genau vier Festlegungen**: (1) erforderliche Eingabeparameter mit Datentypen, (2) mindestens eine fachliche Eingabeprüfung mit Fehlerverhalten, (3) Verhalten bei erfolgreicher erstmaliger Buchung und (4) Verhalten bei erneuter Übermittlung derselben Meldungs-ID, sowohl mit identischen als auch mit abweichenden Buchungsdaten. Legen Sie erforderliche Annahmen ausdrücklich fest. (Je Festlegung 2 Punkte.)

### 1c – 8 Punkte

Das Team plant zwei jeweils fünfwöchige Entwicklungsabschnitte. Der Vertrieb möchte die Benutzeroberfläche vollständig am Anfang festschreiben; die QS erwartet dagegen Erkenntnisse erst aus einem frühen Prototyp. **Entscheiden und begründen** Sie, welches Vorgehen (streng sequenziell oder iterativ-inkrementell) zum Projekt besser passt. Geben Sie **zwei fallbezogene Gründe** und **eine geeignete Methode zur kontrollierten Behandlung späterer Änderungswünsche** an. (Entscheidung 2, Gründe je 2, Methode 2 Punkte.)

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

### 2b – 8 Punkte

Erstellen Sie eine **Berechtigungsmatrix** für die Rollen Wareneingang, Fertigung, QS, Vertrieb und die Aktionen "Lieferung erfassen", "Verwendung buchen", "Mangel melden", "Sperre freigeben". Tragen Sie pro Zelle **erlaubt** oder **verboten** ein. Nur die in der Ausgangssituation ausdrücklich genannten Berechtigungen gelten. (16 Einträge à 0,5 Punkte.)

### 2c – 6 Punkte

Ein Entwickler schlägt vor, dass die Anwendung lediglich bei nicht berechtigten Benutzern die Schaltfläche "Rückrufsperre freigeben" versteckt. **Erläutern** Sie, warum dies als Zugriffsschutz nicht genügt, und **beschreiben** Sie zwei serverseitige Kontrollen für diese Funktion. (Problem 2, Kontrollen je 2 Punkte.)

## Aufgabe 3: Schnittstelle, Zuverlässigkeit und Sicherheit (25 Punkte)

Die Fertigung meldet einen Materialverbrauch als JSON-Nachricht. Vorgesehen ist eine einzelne Nachricht pro Verwendungsbuchung. Jede Nachricht besitzt eine über alle Sender hinweg eindeutige `meldungId`.

### 3a – 9 Punkte

Vergleichen Sie die direkte synchrone Verarbeitung per HTTPS-Anfrage mit einer **persistenten Nachrichtenwarteschlange** anhand von **drei** für FadenSpur relevanten Kriterien: Verhalten bei temporärem Netzausfall, Entkopplung bei Lastspitzen und Rückmeldung an den Sender. Erläutern Sie pro Kriterium den wesentlichen Unterschied. (Je 3 Punkte.)

### 3b – 8 Punkte

Eine Fertigungsanlage sendet eine Meldung, empfängt aber wegen eines Verbindungsabbruchs keine Antwort und wiederholt sie. **Beschreiben Sie einen technisch konsistenten Ablauf**, der die Verwendungsbuchung trotz dieser Wiederholung genau einmal wirksam werden lässt. Berücksichtigen Sie die eindeutige Meldungs-ID, die Datenbank, konkurrierende Anfragen und den Umgang mit einer fehlgeschlagenen Verarbeitung. (Vier fachlich notwendige Aspekte à 2 Punkte.)

### 3c – 8 Punkte

Die Rückrufdaten sind geschäftskritisch. **Nennen und begründen** Sie vier geeignete technische oder organisatorische Sicherheits- bzw. Verfügbarkeitsmaßnahmen. Decken Sie dabei mindestens **Integrität**, **Vertraulichkeit**, **Nachvollziehbarkeit** und **Wiederherstellbarkeit** ab. (Je 2 Punkte.)

## Aufgabe 4: Oberfläche und Qualitätssicherung (25 Punkte)

Die QS benötigt die folgende Ansicht "Rückrufprüfung". Sie zeigt eine ausgewählte mangelhafte Materiallieferung und die davon betroffenen Chargen. Je Charge sind Chargen-ID, Artikel, verwendete Menge und aktueller Sperrstatus sichtbar. Vor der Freigabe muss eine Begründung eingegeben und die betroffene Auswahl ausdrücklich bestätigt werden. Die Oberfläche wird überwiegend mit Tastatur und Bildschirmlesegerät verwendet.

### 4a – 9 Punkte

Entwerfen Sie als beschriftete textuelle Skizze die Ansicht mit **sechs fachlich geeigneten Anzeige- oder Bedienelementen**. Sie muss die Lieferungs-ID und eine strukturierte Liste betroffener Chargen anzeigen, die Auswahl zu sperrender Chargen ermöglichen sowie Begründung, Freigabeschritt und Rückmeldung unterstützen. Geben Sie pro Element dessen Zweck an. (Sechs Elemente à 1,5 Punkte.)

### 4b – 8 Punkte

Beschreiben Sie vier **konkrete ergonomische beziehungsweise barrierefreie Gestaltungsregeln** für diese Ansicht und begründen Sie jeweils die Auswirkung auf den beschriebenen Arbeitsablauf. (Je 2 Punkte.)

### 4c – 8 Punkte

Entwickeln Sie **zwei unterschiedliche Prüfmaßnahmen** für die Rückrufsperre: einen automatisierten Integrationstest gegen den Berechtigungs- und Persistenzpfad sowie einen manuellen Abnahmetest für die QS-Oberfläche. Geben Sie jeweils **Vorbedingungen, Schritte, Soll-Ergebnis und überprüfte Qualitätseigenschaft** an. (Je 4 Punkte.)
