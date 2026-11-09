# Aufgabe - Wartungstermine

Ein webbasiertes System verwaltet Wartungstermine für Maschinen. Eingaben: Maschine, Wartungsdatum, Startzeit, Endzeit, Beschreibung.

Regeln:
- Maschine ist Pflicht.
- Wartungsdatum ist Pflicht und darf nicht in der Vergangenheit liegen.
- Start- und Endzeit sind Pflicht.
- Endzeit muss nach Startzeit liegen.
- Beschreibung: 20–500 Zeichen.
- Keine Überschneidung mit einem vorhandenen Wartungstermin derselben Maschine.
- Endgültiges Löschen soll bestätigt werden.
- Fehlermeldungen müssen verständlich und eindeutig zugeordnet sein.


## A1
Nennen Sie fünf Anforderungen an die Eingabevalidierung.  

**Lösung.**  
1. Maschine (Pflichtfeld): Es muss eine Maschine ausgewählt bzw. angegeben werden.  
2. Wartungsdatum: Das Datum muss angegeben werden, ein gültiges Datum sein und darf nicht in der Vergangenheit liegen.  
3. Start- und Endzeit: Beide Zeitangaben müssen vorhanden und in einem gültigen Zeitformat eingegeben sein.  
4. Zeitliche Reihenfolge: Die Endzeit muss nach der Startzeit liegen. Gleiche Uhrzeiten oder eine Endzeit vor der Startzeit sind unzulässig.  
5. Überschneidungsprüfung: Der neue Wartungstermin darf sich zeitlich nicht mit einem bestehenden Wartungstermin derselben Maschine überschneiden.

## A2
Erläutern Sie den Unterschied zwischen syntaktischer und semantischer Eingabevalidierung. Nennen Sie für jede Validierungsart ein Beispiel aus dem beschriebenen Szenario.  

**Lösung.**  
Syntaktische Validierung: Überprüft, ob eine Eingabe einem vorgegebenen Format bzw. einer definierten Struktur entspricht.

Beispiel: Das Wartungsdatum muss ein gültiges Datum im Format TT.MM.JJJJ sein.

Semantische Validierung: Überprüft, ob eine formal gültige Eingabe die fachlichen Regeln und Bedingungen erfüllt.

Beispiel: Das Wartungsdatum darf nicht in der Vergangenheit liegen.

## A3
Formulieren Sie für fünf unterschiedliche Eingabevalidierungen des beschriebenen Systems jeweils eine konkrete Validierungsregel und eine passende, benutzerfreundliche Fehlermeldung.

**Lösung.**  
1. Es muss eine Maschine ausgewählt sein.  
"Bitte wählen Sie eine Maschine aus."
2. Das Wartungsdatum darf nicht vor dem aktuellen Datum liegen.  
"Das Wartungsdatum darf nicht in der Vergangenheit liegen."
3. Die Startzeit muss angegeben werden.  
"Bitte geben Sie eine Startzeit ein."
4. Die Endzeit muss nach der Startzeit liegen.  
"Die Endzeit muss nach der Startzeit liegen."
5. Die Beschreibung muss zwischen 20 und 500 Zeichen enthalten.  
"Die Beschreibung muss mindestens 20 und höchstens 500 Zeichen enthalten."

## A4
Bestimmen Sie für das Überprüfen der Anzahl der Zeichen der Beschreibung vier Testwerte mit erwartetem Ergebnis.

**Lösung.**  
| Zeichen | Ergebnis |
|---:|---|
| 19 | ungültig |
| 20 | gültig |
| 500 | gültig |
| 501 | ungültig |


## A5
Begründen Sie, warum die Prüfung auf Terminüberschneidungen nicht ausschließlich im Browser erfolgen darf.  

**Lösung.**  
Die Prüfung auf Terminüberschneidungen darf nicht ausschließlich im Browser erfolgen, da clientseitige Validierungen manipuliert oder umgangen werden können.

Außerdem können bei parallelen Zugriffen mehrere Benutzer gleichzeitig denselben Zeitraum als verfügbar angezeigt bekommen.

Deshalb muss die Überschneidungsprüfung auch serverseitig erfolgen. Dabei muss sichergestellt werden, dass konkurrierende Speichervorgänge nicht zu überschneidenden Wartungsterminen führen.
