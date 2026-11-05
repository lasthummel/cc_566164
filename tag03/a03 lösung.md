## Aufgabe CampusLab-Geräteausleihe

Eine Bildungseinrichtung verwaltet 120 Leihgeräte (Notebooks und Tablets) bislang über Tabellen. Auszubildende sollen Geräte für Lernprojekte reservieren können. Mitarbeitende der Ausgabe geben Geräte aus und nehmen sie zurück. Die IT-Administration pflegt den Gerätebestand. Das Portal soll über den vorhandenen Browser erreichbar sein. Für die erste Ausbaustufe ist ausschließlich Deutsch vorgesehen.

Im Anforderungsgespräch fallen diese Aussagen:

1. "Alle Auszubildenden sollen freie Geräte finden können."
2. "Reservieren darf man nur nach Anmeldung mit dem vorhandenen Schul-Account."
3. "Das System muss schnell sein."
4. "Eine Reservierung gilt höchstens sieben Kalendertage."
5. "Die Ausgabe muss ein Gerät auch ohne Reservierung verleihen können."
6. "Geräte dürfen niemals doppelt vergeben werden."
7. "Wir brauchen möglichst bald auch eine mobile App."
8. "Die IT soll defekte Geräte sperren können."
9. "Alle Daten bleiben auf unseren vorhandenen Servern."
10. "Reservierungen sollen automatisch bestätigt werden."
11. "Die Ausgabe möchte Geräte auch nach einer Rückgabe als defekt markieren."
12. "Eine Reservierung muss spätestens nach 24 Stunden automatisch verfallen, falls das Gerät nicht abgeholt wurde."
13. "Reservierungen sollen für die gesamte geplante Leihdauer gültig bleiben."
14. "Auszubildende sollen ausschließlich ihre eigenen Reservierungen sehen."

**Arbeitsannahme:** Eine Reservierung blockiert ein Gerät bis zur Ausgabe oder bis zum Verfall. Die tatsächliche Ausleihe beginnt erst bei Ausgabe. Nicht festgelegte Sachverhalte sind zu kennzeichnen und nicht stillschweigend zu ergänzen.

**1.** Anforderungen gewinnen

Leiten Sie mindestens acht eigenständige Anforderungen aus den Aussagen ab. Verwenden Sie eindeutige Kennungen (R01, R02 usw.).

Ordnen Sie jeder Anforderung genau eine primäre Kategorie zu: funktional, nichtfunktional oder Randbedingung.

| ID | Anforderung | Primäre Kategorie |
|---|---|---|
| R01 | Das System muss Auszubildenden ermöglichen, aktuell verfügbare Leihgeräte zu suchen. | funktional |
| R02 | Anmeldung über Schul-Account vor Reservierung | funktional (mit Integrationsvorgabe) |
| R03 | Antwortzeit der Suche | nichtfunktional, noch unbestimmt |
| R04 | Reservierungsdauer höchstens 7 Kalendertage | funktionale Geschäftsregel |
| R05 | Ausgabe ohne Reservierung | funktional |
| R06 | Doppelvergabe verhindern | funktionale Integritätsregel |
| R07 | Mobile App | funktionale Erweiterung / Produktumfang, zeitlich unklar |
| R08 | Defekte Geräte sperren | funktional |
| R09 | Betrieb auf vorhandenen Servern | Randbedingung |
| R10 | Reservierung automatisch bestätigen | funktional |
| R11 | Defekt bei Rückgabe erfassen | funktional |
| R12 | Verfall nach 24 Stunden ohne Abholung | funktionale Geschäftsregel |
| R13 | Reservierung während Leihdauer gültig | funktional, widersprüchlich/mehrdeutig |
| R14 | Nur eigene Reservierungen anzeigen | funktionale Zugriffsregel |

**Hinweis:** Sicherheits- und Leistungsanforderungen können zugleich Qualitätsziele berühren. Bei R07 ist eine App kein bloßes Qualitätsmerkmal.


**2.** Qualitätsprüfung

a). Identifizieren Sie mindestens vier Qualitätsprobleme der Aussagen, darunter mindestens einen Widerspruch. Formulieren Sie eine klärende Rückfrage zu jedem identifizierten Problem.

Aussage 3 – "Das System muss schnell sein."	
Problem: Kein messbares Leistungsziel.
Frage: Welche maximale Antwortzeit soll die Gerätesuche bei welcher gleichzeitigen Nutzerlast haben?

Aussage 4 – "Höchstens sieben Kalendertage."
Problem: Beginn der Frist ist unklar.
Frage: Beginnt die Siebentagefrist mit der Reservierung, der geplanten Abholung oder der tatsächlichen Ausgabe?

Aussage 10 – "Automatisch bestätigt."	
Problem: Zeitpunkt und Form der Bestätigung unklar.
Frage: Soll die Bestätigung direkt im Portal erscheinen oder zusätzlich per E-Mail versendet werden?

Aussage 12 – "Nach 24 Stunden verfallen."
Problem: Fristbeginn ist nicht definiert.
Frage: Beginnen die 24 Stunden bei Bestätigung oder zum geplanten Abholzeitpunkt?

Aussage 13 – "Während der Leihdauer gültig."
Problem: Reservierung und Ausleihe werden möglicherweise vermischt.
Frage: Soll nach der Ausgabe die Reservierung abgeschlossen und durch einen Ausleihvorgang ersetzt werden?

Potentieller Widerspruch in den Aussagen 12 und 13.
- Aussage 12 verlangt den automatischen Verfall einer nicht abgeholten Reservierung nach spätestens 24 Stunden.
- Aussage 13 verlangt, dass eine Reservierung während der gesamten geplanten Leihdauer gültig bleibt.
Wenn Aussage 13 bedeutet, dass jede Reservierung unabhängig von der Abholung bis zum Ende der geplanten Leihdauer bestehen bleiben muss, widerspricht sie Aussage 12, sofern diese Leihdauer über den Verfallszeitpunkt hinausreicht.
Frage: Soll eine Reservierung bei Nichtabholung auch dann spätestens nach 24 Stunden verfallen, wenn die ursprünglich geplante Leihdauer noch nicht beendet ist?

b). Formulieren Sie vier Anforderungen so um, dass sie objektiv überprüfbar sind. Fehlen Vorgaben, formulieren Sie ausdrücklich einen Entscheidungsvorschlag, keine angebliche Kundenvorgabe.

**Vorschläge, nicht bestätigte Kundenanforderungen**:

- Q01: Bei 100 gleichzeitig aktiven Nutzern werden mindestens 95 % der Suchanfragen innerhalb von 2 Sekunden beantwortet; Messfenster und Testumgebung vor Abnahme festlegen.

- Q02: Nach erfolgreicher Reservierung zeigt das Portal binnen 5 Sekunden eine Bestätigungsnummer.

- Q03: Das System muss eine Reservierung automatisch auf den Status "verfallen" setzen, wenn das Gerät nicht innerhalb von 24 Stunden nach Bestätigung der Reservierung ausgegeben wurde. Spätestens zu diesem Zeitpunkt muss das Gerät wieder reservierbar sein, sofern keine andere Sperre oder Ausleihe entgegensteht. Für eine vollständig messbare Anforderung müsste zusätzlich definiert werden, wie der Zeitpunkt des Verfalls und die Statusänderung überprüft werden.

- Q04: Ein gesperrtes Gerät erscheint nicht in den reservierbaren Suchergebnissen.

c). Formulieren Sie fünf konkrete Rückfragen an verschiedene Stakeholder. Nennen Sie den jeweiligen Stakeholder.

Ausgabe – Ist Ausgabe ohne Reservierung für alle Personen erlaubt? 
IT – Welche Identitätsschnittstelle ist vorhanden? 
Leitung – Gehört die App zum ersten Release? 
Datenschutzbeauftragte – Wie lange bleiben personenbezogene Daten gespeichert? 
Auszubildende – Welche Filter werden bei der Suche benötigt?

**3.** Priorisierung und Abnahme

a). Priorisieren Sie Ihre Anforderungen nach Muss/Soll/Kann. 
Begründen Sie drei Prioritäten unter der Annahme: Die erste Version muss Reservierung, Ausgabe und Rückgabe sicher abwickeln; die mobile App gehört nicht zum ersten Release.

**MUSS:**
R01 – Grundlage zur Auswahl eines reservierbaren Geräts
R02 – Verhindert Reservierungen ohne authentifizierte Identität
R05 – Explizit geforderter Ausgabeprozess
R04	- Geschäftsregel für die Reservierungsdauer
R06 – Zwingend für die sichere Bestandsverwaltung
R08 – Verhindert weitere Vergabe nicht einsatzfähiger Geräte
R09 - Vorgegebene Betriebsrandbedingung
R10	- Automatische Bestätigung ausdrücklich gefordert
R11 – Unterstützt die sichere Rückgabe und Bestandsaktualisierung
R12	- Freigabe nicht abgeholter Geräte
R14 - Zugriffsschutz für personenbezogene Reservierungsdaten

**SOLL:**
R03 – Leistungsziel ist noch nicht konkretisiert

**KANN:**
R07 – Für spätere Version vorgesehen, nicht Teil von Release 1

**UNKLAR**
R13	- Bedeutung und Konflikt noch offen

b). Erstellen Sie für "Reservierung nur nach Anmeldung" und "keine Doppelvergabe" jeweils zwei überprüfbare Akzeptanzkriterien im Schema **Gegeben – Wenn – Dann**.

Akzeptanzkriterien:
1. Gegeben ein nicht angemeldeter Nutzer, wenn er eine Reservierung absendet, dann wird keine Reservierung angelegt und die Anmeldung verlangt.
2. Gegeben ein angemeldeter Nutzer, wenn er ein verfügbares Gerät reserviert, dann wird genau eine bestätigte Reservierung mit Kennung angelegt.
3. Gegeben ein bereits für Zeitraum T reserviertes Gerät, wenn eine zweite Reservierung für überlappenden Zeitraum T angelegt werden soll, dann wird diese abgelehnt.
4. Gegeben zwei gleichzeitig eingehende Reservierungsversuche für dasselbe Gerät und denselben Zeitraum, wenn beide verarbeitet werden, dann wird höchstens eine bestätigt.

c). Leiten Sie aus den Kriterien drei Testfälle mit Eingabe/Vorbedingung und erwartetem Ergebnis ab.

**Testfall T01** – Reservierung ohne Anmeldung

Bezug:Akzeptanzkriterium 1
Vorbedingung: Nutzer ist nicht angemeldet; Gerät G001 ist verfügbar
Eingabe: Reservierungsversuch für G001
Erwartetes Ergebnis: Keine Reservierung wird angelegt. Das System verlangt eine Anmeldung. G001 bleibt verfügbar.

**Testfall T02** – Erfolgreiche Reservierung

Bezug: Akzeptanzkriterium 2
Vorbedingung: Nutzer ist mit seinem Schul-Account angemeldet; G001 ist verfügbar
Eingabe: Reservierungsversuch für G001
Erwartetes Ergebnis: Genau eine bestätigte Reservierung mit eindeutiger Kennung wird angelegt. G001 ist anschließend nicht mehr reservierbar, solange die Reservierung es blockiert.

**Testfall T03** – Gleichzeitige Reservierungsversuche

Bezug: Akzeptanzkriterium 4
Vorbedingung: G001 ist verfügbar. Zwei unterschiedliche Nutzer sind angemeldet.
Eingabe: Beide Nutzer senden gleichzeitig einen Reservierungsversuch für G001 und denselben Zeitraum ab.
Erwartetes Ergebnis: Höchstens eine Reservierung wird bestätigt. Der andere Versuch erhält eine Ablehnung. Es existieren niemals zwei gleichzeitig wirksame Reservierungen für G001.