# Aufgabe  Projekt "RaumPilot"

Eine Berufsschule entwickelt "RaumPilot", eine Webanwendung zur Reservierung von Besprechungsräumen: 

- Nutzer erfassen Raum, Datum, Beginn, Ende und Teilnehmerzahl. 
- Ein Raum darf im gleichen Zeitraum nicht doppelt gebucht sein. 
- Die Raumkapazität darf nicht überschritten werden. 
- Eine Reservierung ist nur innerhalb der Öffnungszeiten 08:00–18:00 Uhr möglich. 
- Der Beginn muss vor dem Ende liegen; Reservierungen beginnen und enden minutengenau. 
- Nur angemeldete Personen dürfen reservieren. 
- Bestehende Reservierungen sind für die jeweiligen Ersteller sichtbar. 
- Eine Aktion `reservieren` gibt entweder eine Buchungsnummer oder eine spezifische Fehlermeldung zurück.

Die folgende bewusst fehlerhafte Java-Methode wird im Projekt verwendet. Sie ist als *Review-Artefakt* gedacht, nicht als Musterlösung.

```java
public class BookingCheck {
    // Times are minutes since midnight; intervals are [start, end).
    // Existing reservations contain int[]{start, end}.
    public static boolean mayBook(int start, int end, int people,
                                  int capacity, int[][] existing) {
        // 480 min = 8 hours
        // 1080 min = 18 hours
        if (start < 480 || end > 1080) {
            return false;
        }
        if (people < 0 || people > capacity) {
            return false;
        }
        if (start > end) {
            return false;
        }
        for (int[] booking : existing) {
            if (start <= booking[1] && end >= booking[0]) {
                return false;
            }
        }
        return true;
    }
}
```

## A1 – Code Review: Fehler und Grenzfälle

Analysieren Sie die Methode `mayBook` ohne Programmausführung.

Identifizieren Sie drei unterschiedliche logische Fehler, durch die eine Reservierung entgegen den fachlichen Anforderungen zugelassen oder abgelehnt wird.

Geben Sie für jeden Fehler an:

1. Welche Bedingung im Java-Code fehlerhaft ist.
2. Einen konkreten Gegenbeispielfall mit Eingabewerten.
3. Ob die Methode `true` oder `false` zurückgibt und welches Ergebnis fachlich korrekt wäre.

**Hinweise:**
- `existing` enthält ausschließlich gültige Reservierungsintervalle und darf leer sein.
- Alle Zeitangaben sind ganzzahlige Minuten seit Mitternacht.
- Reservierungen werden als halboffene Intervalle `[start, end)` betrachtet.
- Eine Reservierung muss mindestens eine Person umfassen.
- Betrachten Sie ausschließlich die fachliche Entscheidungslogik der Methode, nicht die Authentifizierung oder die Erzeugung von Buchungsnummern.


**Lösung.**

### 1. Fehler: Reservierungen ohne Teilnehmer werden zugelassen

Fehlerhafte Bedingung:

```java
if (people < 0 || people > capacity)
```

Die Bedingung lässt `people == 0` zu, obwohl eine Reservierung mindestens eine Person umfassen muss.

**Gegenbeispiel:**
- `start = 600` (10:00 Uhr)
- `end = 660` (11:00 Uhr)
- `people = 0`
- `capacity = 8`
- `existing = {}`

Tatsächliches Ergebnis: `true`

Erwartetes Ergebnis: `false`

Korrektur:

```java
if (people <= 0 || people > capacity)
```

### 2. Fehler: Reservierungen ohne positive Dauer werden zugelassen

Fehlerhafte Bedingung:

```java
if (start > end)
```

Die Bedingung lässt `start == end` zu. Ein Beginn um 10:00 Uhr und ein Ende um 10:00 Uhr erfüllen jedoch nicht die Anforderung, dass der Beginn vor dem Ende liegen muss.

**Gegenbeispiel:**
- `start = 600` (10:00 Uhr)
- `end = 600` (10:00 Uhr)
- `people = 3`
- `capacity = 8`
- `existing = {}`

Tatsächliches Ergebnis: `true`

Erwartetes Ergebnis: `false`

Korrektur:

```java
if (start >= end)
```

### 3. Fehler: Angrenzende Reservierungen werden fälschlich als Überschneidung erkannt

Fehlerhafte Bedingung:

```java
if (start <= booking[1] && end >= booking[0])
```

Die Methode verwendet `<=` und `>=`. Bei halboffenen Intervallen `[start, end)` dürfen Reservierungen jedoch unmittelbar aufeinanderfolgen, ohne sich zu überschneiden.

**Gegenbeispiel:**
- `start = 660` (11:00 Uhr)
- `end = 720` (12:00 Uhr)
- `people = 3`
- `capacity = 8`
- `existing = {{600, 660}}` (10:00–11:00 Uhr)

Tatsächliches Ergebnis: `false`

Erwartetes Ergebnis: `true`

Korrektur:

```java
if (start < booking[1] && end > booking[0])
```

Diese Bedingung erkennt eine Überschneidung nur dann, wenn der Beginn der neuen Reservierung vor dem Ende der bestehenden Reservierung liegt und zugleich das Ende der neuen Reservierung nach dem Beginn der bestehenden Reservierung liegt.

## A2 – Fehlerkorrektur in Java

Erstellen Sie in VS Code die Java-Klasse `BookingCheck` mit der vorgegebenen Methode `mayBook`.

Korrigieren Sie die in A1 identifizierten logischen Fehler, sodass die Methode die fachlichen Anforderungen an die Raumreservierung erfüllt.

Beachten Sie dabei insbesondere:

- Eine Reservierung muss mindestens eine Person umfassen.
- Der Beginn einer Reservierung muss vor ihrem Ende liegen.
- Zwei unmittelbar aufeinanderfolgende Reservierungen dürfen sich nicht gegenseitig ausschließen.

Behalten Sie die Methodensignatur und den Rückgabetyp `boolean` bei.

Überprüfen Sie anschließend Ihre Korrekturen anhand der in A1 angegebenen Gegenbeispiele.

**Hinweis zur Programmiersprache:**

Sie dürfen die korrigierte Funktion auch in einer anderen Programmiersprache Ihrer Wahl implementieren. Dabei müssen die fachlichen Anforderungen vollständig erfüllt werden. Die ursprüngliche Funktionalität sowie die Bedeutung der Parameter und des Rückgabewerts müssen erhalten bleiben. Sprachspezifische Anpassungen der Funktionssignatur sind zulässig.

**Lösung.**  
```java
public class BookingCheck {

    // Times are minutes since midnight; intervals are [start, end).
    // Existing reservations contain int[]{start, end}.
    public static boolean mayBook(int start, int end, int people,
                                  int capacity, int[][] existing) {

        // Opening hours: 08:00–18:00
        if (start < 480 || end > 1080) {
            return false;
        }

        // At least one person; room capacity must not be exceeded
        if (people <= 0 || people > capacity) {
            return false;
        }

        // Start must be strictly before end
        if (start >= end) {
            return false;
        }

        // Check for overlapping reservations
        for (int[] booking : existing) {
            if (start < booking[1] && end > booking[0]) {
                return false;
            }
        }

        return true;
    }
}
```

