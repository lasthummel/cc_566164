# Aufgabe – Validierungsregeln ableiten

Ein internes System erfasst Supporteinsätze. Eingaben: Mitarbeiter-ID, Einsatzdatum, Beginn, Ende, Anzahl bearbeiteter Tickets und Beschreibung.

Anforderungen:
- Mitarbeiter-ID erforderlich.
- Einsatzdatum erforderlich und nicht in der Zukunft.
- Beginn und Ende erforderlich.
- Ende darf nicht vor Beginn liegen.
- Ticketanzahl ist eine Ganzzahl von 0 bis 100.
- Beschreibung erforderlich, mindestens 20 Zeichen.
- Nach erfolgreicher Speicherung Bestätigung.

## A1
Analysieren Sie die Anforderungen an die Eingabefelder des Systems.

Geben Sie für jedes Eingabefeld den geeigneten Datentyp bzw. das Eingabeformat sowie die erforderlichen Validierungsregeln an. Formulieren Sie außerdem jeweils eine passende Fehlermeldung für den Fall einer ungültigen Eingabe.

**Lösung.**

1. Mitarbeiter-ID
- Pflichtfeld: Ja
- Datentyp/Format: ID / String
- Validierungsregel: Es muss eine gültige Mitarbeiter-ID angegeben werden.
- Fehlermeldung: "Bitte geben Sie eine gültige Mitarbeiter-ID ein."

2. Einsatzdatum
- Pflichtfeld: Ja
- Datentyp/Format: Datum
- Validierungsregel: Das Datum darf nicht in der Zukunft liegen.
- Fehlermeldung: "Das Einsatzdatum darf nicht in der Zukunft liegen."

3. Beginn
- Pflichtfeld: Ja
- Datentyp/Format: Uhrzeit (HH:MM)
- Validierungsregel: Es muss eine gültige Uhrzeit angegeben werden.
- Fehlermeldung: "Bitte geben Sie eine gültige Beginnzeit ein."

4. Ende
- Pflichtfeld: Ja
- Datentyp/Format: Uhrzeit (HH:MM)
- Validierungsregel: Die Endzeit darf nicht vor der Beginnzeit liegen.
- Fehlermeldung: "Die Endzeit darf nicht vor der Beginnzeit liegen."

5. Anzahl bearbeiteter Tickets
- Pflichtfeld: Ja
- Datentyp/Format: Ganzzahl (Integer)
- Validierungsregel: Der Wert muss zwischen 0 und 100 liegen (einschließlich).
- Fehlermeldung: "Bitte geben Sie eine ganze Zahl zwischen 0 und 100 ein."

6. Beschreibung
- Pflichtfeld: Ja
- Datentyp/Format: Text (String)
- Validierungsregel: Die Beschreibung muss mindestens 20 Zeichen enthalten.
- Fehlermeldung: "Die Beschreibung muss mindestens 20 Zeichen enthalten."

## A2
Unterscheiden Sie für mindestens vier Regeln syntaktische und semantische Validierung.

**Lösung.**

- Syntaktische Validierung prüft, ob eine Eingabe dem vorgegebenen Datentyp bzw. Format entspricht.

- Semantische Validierung prüft, ob eine formal korrekte Eingabe auch die fachlichen Regeln der Anwendung erfüllt.

1. Mitarbeiter-ID
- Syntaktisch: Die Mitarbeiter-ID muss dem festgelegten Format entsprechen, beispielsweise MA-1234.
- Semantisch: Die Mitarbeiter-ID muss einem tatsächlich vorhandenen Mitarbeiter zugeordnet sein.

2. Einsatzdatum
- Syntaktisch: Die Eingabe muss ein gültiges Datum enthalten, beispielsweise 05.11.2026. 31.02.2026 ist ungültig.
- Semantisch: Das Einsatzdatum darf nicht in der Zukunft liegen.

3. Beginn und Ende
- Syntaktisch: Beide Eingaben müssen gültige Uhrzeiten im Format HH:MM enthalten, beispielsweise 09:30.
- Semantisch: Die Endzeit muss größer oder gleich der Beginnzeit sein. Beispielsweise ist Beginn 14:00 und Ende 13:30 unzulässig.

4. Anzahl bearbeiteter Tickets
- Syntaktisch: Die Eingabe muss eine Ganzzahl sein. Beispielsweise ist 15 gültig, 3,5 dagegen ungültig.
- Semantisch: Die Ticketanzahl muss zwischen 0 und 100 einschließlich liegen. Beispielsweise ist 120 unzulässig.

5. Beschreibung  
Bei einem Freitextfeld wie Beschreibung ist eine eindeutige Unterscheidung zwischen syntaktischer und semantischer Validierung nicht immer möglich.
Für das Feld Beschreibung gilt die Anforderung: "Die Beschreibung muss mindestens 20 Zeichen enthalten." Diese Regel lässt sich aus zwei Perspektiven betrachten:

- Syntaktische Perspektive  
Die Anwendung prüft die Anzahl der eingegebenen Zeichen. Weniger als 20 Zeichen: ungültig  

Es handelt sich um eine formale Längenprüfung. Der Inhalt und seine Bedeutung spielen bei dieser Prüfung keine Rolle.

- Fachliche Perspektive  
Die Mindestlänge wurde als fachliche Anforderung festgelegt. Sie soll dazu beitragen, dass Supporteinsätze nicht mit extrem kurzen Beschreibungen dokumentiert werden.

In diesem Sinne verfolgt die Regel einen fachlichen Zweck.

## A3
Bestimmen Sie fünf sinnvolle Testwerte für die Validierung der Ticketanzahl unter Berücksichtigung der zulässigen Wertebereichsgrenzen. Geben Sie zu jedem Testwert an, ob er gültig oder ungültig ist.

**Lösung.**
Testwerte für Tickets: 

-1 ungültig, 0 gültig, 1 gültig, 100 gültig, 101 ungültig.


## A4
Begründen Sie, warum die Prüfung von Beginn und Ende eine feldübergreifende Validierung ist.

**Lösung.**

Die Prüfung von Beginn und Ende ist eine feldübergreifende Validierung, weil die Gültigkeit der Endzeit nicht allein anhand ihres eigenen Wertes bestimmt werden kann.

Beide Uhrzeiten können einzeln betrachtet formal gültig sein. Erst durch den Vergleich der beiden Eingabefelder lässt sich feststellen, ob die fachliche Regel eingehalten wird.
