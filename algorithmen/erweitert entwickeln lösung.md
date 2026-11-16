# Aufgabe 'Weitere algorithmische Verfahren'

Für alle Teilaufgaben gelten folgende Vereinbarungen:

- Arrays, Listen und Zeichenketten sind ab Index `0` indiziert.
- `LÄNGE(text)` liefert die Anzahl der Zeichen einer Zeichenkette.
- Bei `FÜR i VON a BIS b` werden beide Grenzen einschließlich durchlaufen. Ist `a > b`, wird die Schleife nicht ausgeführt.
- `=` bezeichnet eine Zuweisung, `==` einen Vergleich auf Gleichheit und `!=` einen Vergleich auf Ungleichheit.
- `UND`, `ODER`, `WAHR` und `FALSCH` sind logische Operatoren bzw. Wahrheitswerte.
- Bei Zeichenketten fügt `+` zwei Zeichenketten aneinander. `GROSSBUCHSTABE(zeichen)` wandelt einen Kleinbuchstaben in den entsprechenden Großbuchstaben um; andere Zeichen bleiben unverändert.
- Zeitpunkte haben das Format `JJJJ-MM-TT HH:MM`, liegen in derselben Zeitzone und werden chronologisch verglichen. Zeitintervalle enthalten ihren Beginn, aber nicht ihr Ende: `[beginn, ende)`. Für alle vorgegebenen Intervalle gilt `beginn < ende`.
- `a MOD b` liefert den Rest der ganzzahligen Division von `a` durch `b` (hier nur für nichtnegative `a` und positive `b` verwendet).
- `GANZZAHLWURZEL(n)` liefert für `n >= 0` die größte ganze Zahl, deren Quadrat höchstens `n` ist. Beispiel: `GANZZAHLWURZEL(30) = 5`.
- Soweit nicht anders angegeben, beendet `ZURÜCKGEBEN ...` die Funktion unmittelbar.

## A1 - Zeichenkettenanalyse und -verarbeitung

Entwickeln Sie mit Pseudocode die Funktion `normalisiereKennung(kennung : String) : String`. Die Funktion erstellt aus dem übergebenen String einen neuen String dadurch, dass:

- Leerzeichen entfernt werden, 
- Bindestriche durch Unterstriche ersetzt werden,
- Buchstaben in Großbuchstaben umgewandelt werden,
- andere Zeichen erhalten bleiben.

Die Funktion liefert den erstellten String zurück.

**Lösung.**  

```text
FUNKTION normalisiereKennung(kennung)
    ergebnis = ""

    FÜR i VON 0 BIS LÄNGE(kennung) - 1
        zeichen = kennung[i]

        WENN zeichen != " " DANN
            WENN zeichen == "-" DANN
                ergebnis = ergebnis + "_"
            SONST
                ergebnis = ergebnis + GROSSBUCHSTABE(zeichen)
            ENDE WENN
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: ergebnis
ENDE FUNKTION
```


## A2 - Datums- und Zeitintervallverarbeitung

Ein System für Maschinenwartungen prüft, ob sich zwei reservierte Zeitintervalle überschneiden. Direkt aneinandergrenzende Intervalle gelten nicht als Überschneidung.

Entwickeln Sie die Funktion `ueberschneidetSich(beginnA, endeA, beginnB, endeB) : Boolean`, die diese Aufgabe übernimmt.
Folgende Hinweise sind zu beachten:  

- beginnA, endeA sind die Grenzen des halboffenenen Intervals A: [beginnA, endeA) 
- beginnB, endeB sind die Grenzen des halboffenenen Intervals B: [beginnB, endeB) 
- die Funktion liefert WAHR, wenn sich die Intervale überschneiden

**Lösung**  

```text
FUNKTION ueberschneidetSich(beginnA, endeA, beginnB, endeB)
    WENN beginnA < endeB UND beginnB < endeA DANN
        ZURÜCKGEBEN: WAHR
    ENDE WENN

    ZURÜCKGEBEN: FALSCH
ENDE FUNKTION
```

Beispiele der Ergebnisse:

    beginA endeA beginB endeB - FALSCH durch beginB < endeA
    beginB endeB beginA endeA - FALSCH durch beginA < endeB
    beginA beginB endeA endeB - WAHR
    beginA beginB endeB endeA - WAHR
    beginB beginA endeB endeA - WAHR
    beginB beginA endeA endeB - WAHR

## A3 - Eingabevalidierung und Plausibilitätsprüfung

Bei einer Online-Reservierung dürfen zwischen 1 und 8 Personen angemeldet werden.

Eine Funktion soll entwickelt werden, die folgende Bedingungen erfüllt:

- Die vom System übermittelte Anzahl freier Plätze darf nicht negativ sein.
- Die Reservierung darf die Anzahl freier Plätze nicht überschreiten.
- Die Anzahl der freien Plätze muss positiv sein. 
- Die maxmale Anzahl der freien Plätze ist 12. 
- Der Algorithmus gibt die Meldung zur ersten erkannten Verletzung zurück oder "OK", wenn es keine Verletzungen gibt.

Entwickeln Sie die Funktion `pruefeReservierung(anzahlPersonen, freiePlaetze) : String`, die diese Aufgabe übernimmt.

**Lösung**  

```text
FUNKTION pruefeReservierung(anzahlPersonen, freiePlaetze)
    WENN anzahlPersonen < 1 ODER anzahlPersonen > 8 DANN
        ZURÜCKGEBEN: "Ungültige Personenzahl"
    ENDE WENN

    WENN freiePlaetze < 0 ODER freiePlaetze > 12 DANN
        ZURÜCKGEBEN: "Ungültige Kapazität"
    ENDE WENN

    WENN anzahlPersonen > freiePlaetze DANN
        ZURÜCKGEBEN: "Nicht genügend freie Plätze"
    ENDE WENN

    ZURÜCKGEBEN: "OK"
ENDE FUNKTION
```

## A4 - Zustandsabhängige Verarbeitung

Ein Unternehmen verwendet ein Ticketsystem zur Bearbeitung von Supportanfragen. Jedes Ticket besitzt einen Bearbeitungsstatus. Mitarbeiter können durch bestimmte Aktionen den Status eines Tickets ändern.

Das Ticketsystem unterscheidet folgende Zustände:
- "NEU": Das Ticket wurde erfasst, aber noch nicht bearbeitet.
- "IN_BEARBEITUNG": Das Ticket wird aktuell bearbeitet.
- "PAUSIERT": Die Bearbeitung wurde vorübergehend unterbrochen.
- "ERLEDIGT": Die Bearbeitung ist abgeschlossen.

Für die Bearbeitung gelten folgende Regeln:
1. Ein neues Ticket kann durch die Aktion "STARTEN" in den Status "IN_BEARBEITUNG" wechseln.
2. Ein Ticket im Status "IN_BEARBEITUNG" kann durch die Aktion "PAUSIEREN" in den Status "PAUSIERT" wechseln.
3. Ein pausiertes Ticket kann durch die Aktion "FORTSETZEN" wieder in den Status "IN_BEARBEITUNG" wechseln.
4. Ein Ticket im Status "IN_BEARBEITUNG" kann durch die Aktion "ABSCHLIESSEN" in den Status "ERLEDIGT" wechseln.
5. Alle anderen Kombinationen aus Status und Aktion sind unzulässig. Insbesondere dürfen erledigte Tickets nicht erneut bearbeitet werden.

Entwickeln Sie einen Algorithmus in Pseudocode für die Funktion `verarbeiteAktion(status, aktion)`, die abhängig vom aktuellen Bearbeitungsstatus und der angeforderten Aktion den neuen Status eines Tickets ermittelt.

Berücksichtigen Sie folgende Anforderungen:
- Die Funktion erhält den aktuellen Bearbeitungsstatus status und die auszuführende Aktion aktion als Parameter.
- Bei einem zulässigen Zustandsübergang gibt die Funktion den neuen Bearbeitungsstatus zurück.
- Bei einem unzulässigen Zustandsübergang gibt die Funktion die Zeichenkette "UNZULÄSSIG" zurück.
- Die Funktion verändert den gespeicherten Status des Tickets nicht selbst.
Hinweis: Verwenden Sie geeignete bedingte Anweisungen zur Prüfung der zulässigen Zustandsübergänge.

**Lösung**  

```text
FUNKTION verarbeiteAktion(status, aktion)
    WENN status == "NEU" UND aktion == "STARTEN" DANN
        ZURÜCKGEBEN: "IN_BEARBEITUNG"
    ENDE WENN

    WENN status == "IN_BEARBEITUNG" UND aktion == "PAUSIEREN" DANN
        ZURÜCKGEBEN: "PAUSIERT"
    ENDE WENN

    WENN status == "PAUSIERT" UND aktion == "FORTSETZEN" DANN
        ZURÜCKGEBEN: "IN_BEARBEITUNG" 
    ENDE WENN

    WENN status == "IN_BEARBEITUNG" UND aktion == "ABSCHLIESSEN" DANN
        ZURÜCKGEBEN: "ERLEDIGT"
    ENDE WENN

    ZURÜCKGEBEN: "UNZULÄSSIG" 
ENDE FUNKTION
```

## A5 - Teilbarkeitsprüfung und Modulo-Verfahren

Ein Industrieunternehmen möchte die Wartungsplanung seiner Produktionsanlagen automatisieren. Hierzu soll nach jedem abgeschlossenen Produktionszyklus ermittelt werden, ob eine Wartung erforderlich ist.

Es gelten folgende Regeln:

- Nach jedem zweiten Produktionszyklus ist eine **kleine Wartung** erforderlich.
- Nach jedem sechsten Produktionszyklus ist eine **große Wartung** erforderlich.
- Wenn beide Wartungsbedingungen gleichzeitig erfüllt sind, hat die große Wartung Vorrang. Es wird somit nur eine Wartungsart zurückgegeben.
- In allen anderen Fällen ist keine Wartung erforderlich.
- Die Nummerierung der Produktionszyklen beginnt bei `1`. Kleinere Werte sind ungültig.

Entwickeln Sie eine Funktion `wartungsart(zyklusnummer)` in Pseudocode, die anhand der übergebenen Zyklusnummer die erforderliche Wartungsart ermittelt.

Die Funktion erhält die Zyklusnummer als Ganzzahl und gibt eine der folgenden Zeichenketten zurück:

- "GROSSE_WARTUNG" – eine große Wartung ist erforderlich.
- "KLEINE_WARTUNG" – eine kleine Wartung ist erforderlich.
- "KEINE_WARTUNG" – es ist keine Wartung erforderlich.
- "UNGÜLTIG" – die übergebene Zyklusnummer ist ungültig.

**Hinweis:** Der Modulo-Operator `MOD` liefert den Rest einer ganzzahligen Division.

**Lösung.**  

```text
FUNKTION wartungsart(zyklusnummer)
    WENN zyklusnummer < 1 DANN
        ZURÜCKGEBEN: "UNGÜLTIG" 
    ENDE WENN

    WENN zyklusnummer MOD 6 == 0 DANN
        ZURÜCKGEBEN: "GROSSE_WARTUNG" 
    ENDE WENN

    WENN zyklusnummer MOD 2 == 0 DANN
        ZURÜCKGEBEN: "KLEINE_WARTUNG" 
    ENDE WENN

    ZURÜCKGEBEN: "KEINE_WARTUNG" 
ENDE FUNKTION
```

## A6 - Primzahlprüfung

Eine Anwendung soll prüfen, ob eine eingegebene ganze Zahl eine Primzahl ist.

Eine Primzahl ist eine natürliche Zahl größer als 1, die ausschließlich durch 1 und sich selbst ohne Rest teilbar ist.

Entwickeln Sie eine Funktion `istPrimzahl(zahl)` in Pseudocode, die prüft, ob die übergebene ganze Zahl eine Primzahl ist.

Berücksichtigen Sie folgende Anforderungen:

- Die Funktion erhält eine ganze Zahl `zahl` als Parameter und gibt einen booleschen Wert zurück.
- Für Zahlen kleiner als 2 wird `FALSCH` zurückgegeben.
- Die Funktion prüft, ob die Zahl durch eine ganze Zahl außer 1 und sich selbst ohne Rest teilbar ist.
- Um unnötige Berechnungen zu vermeiden, sollen ausschließlich mögliche Teiler von 2 bis einschließlich der ganzzahligen Quadratwurzel der übergebenen Zahl geprüft werden.
- Sobald ein Teiler gefunden wird, soll die Funktion unmittelbar `FALSCH` zurückgeben.
- Wenn kein Teiler gefunden wird, soll die Funktion `WAHR` zurückgeben.

**Hinweise:**

- Der Operator `MOD` liefert den Rest einer ganzzahligen Division.
- Die Funktion `GANZZAHLWURZEL(n)` liefert den ganzzahligen Anteil der Quadratwurzel von `n`. Beispielsweise ergibt `GANZZAHLWURZEL(20)` den Wert `4`.
- Wenn eine Zahl keinen Teiler zwischen 2 und ihrer ganzzahligen Quadratwurzel besitzt, ist sie eine Primzahl, sofern sie größer als 1 ist.

**Lösung**  

```text
FUNKTION istPrimzahl(zahl)
    WENN zahl < 2 DANN
        ZURÜCKGEBEN: FALSCH 
    ENDE WENN

    FÜR teiler VON 2 BIS GANZZAHLWURZEL(zahl)
        WENN zahl MOD teiler == 0 DANN
            ZURÜCKGEBEN: FALSCH 
        ENDE WENN
    ENDE FÜR

    ZURÜCKGEBEN: WAHR 
ENDE FUNKTION
```
