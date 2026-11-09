# Aufgabe – Formular und Fehlerrückmeldungen

Eine Anwendung ermöglicht Dienstreiseanträge mit Zielort, Beginn, Ende, Zweck und voraussichtlichen Kosten.

Anforderungen:
- Zielort, Beginn und Ende sind Pflicht.
- Ende darf nicht vor Beginn liegen.
- Zweck ist Pflicht und mindestens 10 Zeichen lang.
- Kosten müssen größer als 0 € sein.
- Korrekte Eingaben bleiben bei Fehlern erhalten.
- Antrag kann abgesendet oder abgebrochen werden.
- Erfolgreiche Übermittlung wird bestätigt.

## A1
Beschreiben Sie die Formularelemente, die für die Erfassung eines Dienstreiseantrags erforderlich sind.

Nennen Sie für jedes Eingabefeld eine geeignete Art des Bedienelements (z. B. Textfeld, Datumsfeld) und beschreiben Sie kurz die sinnvolle Anordnung der Elemente im Formular.

Berücksichtigen Sie auch die Bedienelemente zum Absenden und Abbrechen des Antrags.

Eine grafische Darstellung (Wireframe oder Mockup) ist nicht erforderlich.

**Lösung.**
Das Formular erhält die Überschrift "Dienstreise beantragen". Die Eingabefelder werden untereinander in einer logischen Reihenfolge angeordnet.

1. Zielort: Ein einzeiliges Textfeld zur Eingabe des Reiseziels. Das Feld ist als Pflichtfeld gekennzeichnet.

2. Beginn: Ein Datumsfeld mit Datumsauswahl (Datepicker) zur Eingabe des Reisebeginns. Das Feld ist ein Pflichtfeld.

3. Ende: Ein Datumsfeld mit Datumsauswahl zur Eingabe des Reiseendes. Das Feld ist ebenfalls ein Pflichtfeld und wird direkt unter dem Beginn angeordnet.

4. Zweck: Ein mehrzeiliges Textfeld (Textarea) zur Beschreibung des Reisezwecks. Das Feld ist ein Pflichtfeld.

5. Voraussichtliche Kosten: Ein numerisches Eingabefeld zur Eingabe des geschätzten Betrags in Euro. Die Währung (€) wird am Feld angezeigt.

6. Absenden: Eine primäre Schaltfläche mit der Beschriftung "Antrag absenden".

7. Abbrechen: Eine sekundäre Schaltfläche mit der Beschriftung "Abbrechen".

## A2
Beschreiben Sie kurz, welche Validierungsprüfungen vor dem Absenden des Dienstreiseantrags durchgeführt werden müssen.

**Lösung.**
Pflichtfelder, Mindestlänge des Zwecks, zeitliche Reihenfolge und positive Kosten werden überprüft.

## A3
Erläutern Sie kurz, wie das Formular bei fehlerhaften Eingaben reagieren soll.

**Lösung.**
Fehlermeldungen erscheinen möglichst direkt am betroffenen Eingabefeld. Korrekte Eingaben bleiben erhalten.

## A4
Beschreiben Sie kurz, wie die Anwendung nach erfolgreicher Übermittlung des Dienstreiseantrags reagieren soll.

**Lösung.**
Nach erfolgreicher Übermittlung erscheint eine verständliche Bestätigung.

## A5
Formulieren Sie mindestens fünf konkrete Fehlermeldungen.

**Lösung.**
- "Bitte geben Sie einen Zielort ein."
- "Bitte geben Sie ein gültiges Reisedatum für den Beginn ein."
- "Das Reiseende darf nicht vor dem Reisebeginn liegen."
- "Der Zweck der Dienstreise muss mindestens 10 Zeichen enthalten."
- "Die voraussichtlichen Kosten müssen größer als 0 € sein."


## A6
Erläutern Sie kurz, warum "Ungültige Eingabe" ungeeignet ist.

**Lösung.**
"Ungültige Eingabe" beschreibt weder Ursache noch Korrekturmöglichkeit.

## A7
Nennen und begründen Sie zwei Maßnahmen, durch die das Formular für Dienstreiseanträge benutzerfreundlicher und einfacher zu bedienen ist.

**Lösung.**
1. Geeignete Eingabeelemente verwenden

Für den Reisebeginn und das Reiseende werden Datumsfelder mit Kalenderauswahl verwendet. Dadurch können Benutzer das Datum einfach auswählen und Eingabefehler durch falsche Datumsformate werden reduziert.

2. Eingabefelder logisch anordnen

Die Eingabefelder werden in einer nachvollziehbaren Reihenfolge angeordnet: Zielort, Beginn, Ende, Zweck und Kosten. Dadurch können Benutzer das Formular systematisch ausfüllen und müssen weniger Zeit für die Orientierung aufwenden.