# Prüfungssimulation sim05 – Planen eines Softwareproduktes

**Bearbeitungszeit:** 90 Minuten
**Erreichbare Punkte:** 100
**Aufgaben:** 4 Hauptaufgaben.

## Ausgangssituation

Die fiktive **KommunaSoft GmbH** entwickelt für mehrere Gemeinden das Portal **GrünAntrag**. Bürgerinnen, Bürger und örtliche Vereine können darin Zuschüsse für Dachbegrünungen beantragen. Sachbearbeitende prüfen die Anträge; eine bewilligte Auszahlung wird später von der kommunalen Kasse erfasst. Die bisherige Bearbeitung erfolgt per Post und Tabellenkalkulation.

Ein Förderprogramm besitzt eine Kennung, einen Titel, eine Einreichungsfrist und einen Höchstbetrag pro Antrag. Antragstellende besitzen eine eindeutige Kennung. Ein Antrag gehört genau einem Antragstellenden und genau einem Programm. Zu einem Antrag können beliebig viele PDF-Nachweise erfasst werden. Jeder Nachweis ist ausschließlich Bestandteil seines Antrags und besitzt keinen eigenständigen fachlichen Lebenszyklus. Jede fachliche Prüfung wird als eigenständig identifizierbarer Protokolldatensatz mit Zeitpunkt und Entscheidung erfasst und gehört genau einem Antrag. Ein Antrag kann mehrfach geprüft werden; frühere Prüfungsdatensätze bleiben auch nach einer erneuten Prüfung bestehen. Prüfungsdatensätze werden unabhängig von den Nachweisdateien verwaltet. Eine Lösch- oder Aufbewahrungsregel für Prüfungsdatensätze bei Löschung eines Antrags ist nicht Gegenstand dieser Aufgabe.

Für die aktuelle Pilotversion gelten ausdrücklich folgende **betriebliche Regeln**. Die Einreichungsfrist und der Höchstbetrag sind für alle Förderprogramme der Pilotversion einheitlich; die jeweiligen Programmattribute enthalten diese Werte:

- Anträge können bis einschließlich **30.11.2026** eingereicht werden.
- Ein Antrag ist vollständig, wenn das unterschriebene Antragsformular und ein Kostenvoranschlag als PDF vorhanden sind. Je Datei höchstens **10 MiB**.
- Pro Antragstellerkennung darf im **gleichen Programm und Kalenderjahr** nur ein Antrag erfolgreich eingereicht werden. Bereits eingereichte Anträge zählen unabhängig vom späteren Bearbeitungsstatus; gespeicherte Entwürfe und bei der Vorprüfung zurückgewiesene Einreichungsversuche zählen nicht. Ein Antrag in einem anderen Programm ist zulässig.
- Der beantragte Betrag muss größer als 0 Euro und höchstens **5.000 Euro** sein.
- Für die fachliche Vorprüfung werden die folgenden Prüfergebnisse benötigt: **ANNEHMEN**, **FRIST**, **UNVOLLSTAENDIG**, **DOPPELT**, **BETRAG**.
- Bei mehreren Regelverletzungen hat diese Reihenfolge Vorrang: Frist, Doppelantrag, Unvollständigkeit, Betrag.
- Die öffentliche Erfassungsoberfläche darf Antragstellerdaten nur der angemeldeten Person selbst zugänglich machen. Sachbearbeitende dürfen zugewiesene Anträge lesen und prüfen, aber keine Auszahlungen freigeben.

## Aufgabe 1: Spezifikation und Änderungssteuerung (25 Punkte)

### 1a – 9 Punkte

Formulieren Sie drei eigenständige, eindeutig testbare funktionale Anforderungen für die Einreichung eines Antrags. Beziehen Sie sich dabei auf **Frist**, **Nachweisdokumente** und **Mehrfacheinreichung**. Formulieren Sie jeweils die erforderliche Reaktion der Anwendung. Berücksichtigen Sie bei mehreren Regelverletzungen die vorgegebene Prüfreihenfolge.

### 1b – 10 Punkte

Ermitteln Sie für jeden der folgenden Fälle das **eine** fachliche Prüfergebnis nach den beschriebenen Regeln. Alle Einreichungen erfolgen 2026. Die beantragten Beträge stehen in der Tabelle. "Doppelt" bedeutet: Ein Antrag derselben Antragstellerkennung im gleichen Programm und im gleichen Kalenderjahr wurde bereits erfolgreich eingereicht.

| Fall | Eingangsdatum | Vollständig | Doppelt | Beantragt | Prüfergebnis |
|---|---|---|---|---:|---|
| A | 30.11.2026 | ja | nein | 5.000 € | ? |
| B | 01.12.2026 | nein | ja | 7.000 € | ? |
| C | 21.11.2026 | nein | nein | 2.000 € | ? |
| D | 21.11.2026 | ja | ja | 2.000 € | ? |
| E | 21.11.2026 | ja | nein | 5.001 € | ? |

### 1c – 6 Punkte

Die Auftraggeberin beantragt nach der Abnahme der Spezifikation zusätzlich den Upload von Bilddateien. Beschreiben Sie **drei konkrete Schritte** eines kontrollierten Änderungsverfahrens und jeweils dessen Nutzen für das Projekt.

## Aufgabe 2: Werkzeuge, Architektur und Sicherheit (25 Punkte)

Die Anwendung soll mithilfe einer Java-Bibliothek PDF-Dateien auf zulässiges Dateiformat und Seitenzahl prüfen. Zwei technisch kompatible Kandidaten wurden anhand einer Skala von 1 (schlecht) bis 5 (sehr gut) bewertet. Beide können nach ihrer dokumentierten Lizenz für den vorgesehenen Einsatz eingesetzt werden.

| Kriterium | Gewicht | Bibliothek A | Bibliothek B |
|---|---:|---:|---:|
| Wartbarkeit | 40 % | 4 | 3 |
| Funktionsumfang | 30 % | 3 | 5 |
| Sicherheitspflege | 20 % | 4 | 2 |
| Einarbeitungsaufwand (5 = wenig Aufwand) | 10 % | 2 | 5 |

### 2a – 8 Punkte

Berechnen Sie für beide Alternativen den gewichteten Nutzwert (Skala 1 bis 5). Stellen Sie den Rechenweg dar, nennen Sie den rechnerischen Sieger und geben Sie die Differenz zwischen den beiden Nutzwerten an.

### 2b – 5 Punkte

Wählen Sie unter Berücksichtigung des kleinen Unterschieds der Nutzwerte eine der Bibliotheken aus und begründen Sie die Entscheidung mit **zwei konkreten Kriterien** aus der Tabelle und **einem fachlichen Risiko**.

### 2c – 8 Punkte

Entwickeln Sie **vier konkrete technische Schutzmaßnahmen** für den Upload und die spätere Anzeige der Antragsdokumente. Geben Sie jeweils an, welches Risiko dadurch reduziert wird.

### 2d – 4 Punkte

Erläutern Sie je **einen Unterschied** zwischen einer Bibliothek und einem Framework sowie zwischen einer lokalen Build-Prüfung und einer automatisierten Prüfung in einer CI-Pipeline.

## Aufgabe 3: Objektorientierte Fachmodellierung (25 Punkte)

Für eine Architekturentscheidung soll ein **UML-Klassendiagramm** auf Fachebene erstellt werden. Modellieren Sie diese Klassen mit den jeweils genannten Attributen:

- **Antragsteller:** id: int, name: String
- **Foerderprogramm:** id: int, titel: String, einreichungsfrist: Date, maxBetrag: Decimal
- **Antrag:** id: int, eingangsdatum: Date, status: String, beantragt: Decimal
- **Nachweis:** id: int, typ: String, dateiname: String
- **Pruefung:** id: int, zeitpunkt: DateTime, entscheidung: String

### 3a – 17 Punkte

Erstellen Sie das vollständige UML-Klassendiagramm mit den fünf Klassen, allen genannten Attributen, den fachlich zutreffenden **vier Beziehungen** und deren Multiplizitäten. Stellen Sie den Nachweis als Teil des Antrags mit **Komposition** dar; die anderen Beziehungen sind normale Assoziationen.

### 3b – 8 Punkte

Erläutern Sie anhand Ihres Diagramms, weshalb für Nachweise eine **Komposition** festgelegt wurde, für Prüfungen dagegen eine **normale Assoziation**. Erläutern Sie zusätzlich anhand einer konkreten Beziehung Ihres Diagramms die Bedeutung der Multiplizitäten **1** und **0..*** an ihren beiden Enden.

## Aufgabe 4: Bedienoberfläche und Qualitätskontrolle (25 Punkte)

Ein früher Entwurf des Antragsformulars sieht so aus:

```text
ANTRAG STELLEN
Antragsteller-ID: [ 174 ]
Programm:        [ Dachbegrünung v ]
Beantragter Betrag: [ 5000 ]
Datei:           [ Datei wählen ]  (unbegrenzte Anzahl/Größe)
[ABSENDEN]
Bei fehlerhafter Eingabe: "Fehler 12".
Nach dem Absenden erscheint unabhängig vom Ergebnis: "Gespeichert".
```

### 4a – 9 Punkte

Analysieren Sie **drei konkrete Probleme** hinsichtlich Benutzbarkeit, Rückmeldung oder Sicherheit/Barrierefreiheit. Beschreiben Sie jeweils eine geeignete Verbesserung und ihren Nutzen.

### 4b – 8 Punkte

Skizzieren oder beschreiben Sie eine überarbeitete Eingabemaske einschließlich der erforderlichen Felder und zweier Maßnahmen zur barrierearmen Bedienung. Berücksichtigen Sie die betrieblichen Regeln und zeigen Sie die Einreichungsfrist als Information an.

### 4c – 8 Punkte

Erstellen Sie eine **Testfalltabelle mit vier Testfällen** für die Einreichungsprüfung. Zu jedem Testfall sind **Eingabe/Bedingung** und das **konkret erwartete Ergebnis** anzugeben. Mindestens zwei Testfälle müssen Grenz- oder Fehlerfälle abdecken.

**Ende des Prüfungsbereichs – 100 Punkte.**
