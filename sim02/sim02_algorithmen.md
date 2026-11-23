# Prüfungssimulation sim02 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100 Punkte  
**Aufgaben:** 4 Hauptaufgaben.
**Hinweis:** Java 21; SQL-Dialekt PostgreSQL 18.

## Ausgangssituation

Die BauRaum Service GmbH erweitert **FixCampus** um die automatische Technikerzuweisung, Bearbeitungssteuerung und Auswertung von Störungen. Jede Technikerin und jeder Techniker besitzt eine Menge zulässiger Fachgebiete; die Disposition berücksichtigt Arbeitslast und Verfügbarkeit.

## Aufgabe 1: Geeignete Fachkräfte auswählen (29 Punkte)

Die Datentypen sind vollständig festgelegt:

```java
import java.util.List;
import java.util.Set;
record Auftrag(int id, String fachgebiet, boolean sicherheitsgefahr,
               int dauerMinuten) {}
record Fachkraft(int id, Set<String> fachgebiete, boolean verfuegbar,
                 int restkapazitaetMinuten, int aktiveTickets) {}
```

Die übergebenen Listen und Elemente sowie alle Mengen sind niemals `null`; Strings sind nicht `null`. Die ID jeder Fachkraft ist eindeutig. `dauerMinuten > 0`, `restkapazitaetMinuten >= 0` und `aktiveTickets >= 0`. Die Fachkräfte sind **nicht** vorsortiert. Die Liste darf leer sein.

Eine Fachkraft ist genau dann **geeignet**, wenn sie (1) verfügbar ist, (2) das Fachgebiet des Auftrags besitzt und (3) ihre Restkapazität mindestens der Auftragsdauer entspricht. Für einen Auftrag mit Sicherheitsgefahr dürfen außerdem **höchstens zwei aktive Tickets** vorliegen. Für einen Auftrag ohne Sicherheitsgefahr gibt es keine Begrenzung der Anzahl aktiver Tickets.

Unter den geeigneten Fachkräften wird die mit der **kleinsten Anzahl aktiver Tickets** ausgewählt. Bei Gleichstand gewinnt die mit der **größten Restkapazität**; bei erneutem Gleichstand die mit der **kleinsten ID**. Falls niemand geeignet ist, wird `null` zurückgegeben. Die Eingabedaten bleiben unverändert.

### 1a – 20 Punkte

Implementieren Sie die vollständige Java-21-Methode (auf derselben Ebene wie eine statische Hilfsmethode):

```java
public static Fachkraft waehle( Auftrag a, List<Fachkraft> fachkraefte )
```

### 1b – 6 Punkte

Bestimmen Sie für beide Aufträge die ausgewählte Fachkraft-ID anhand der folgenden Daten und begründen Sie die Auswahl und relevante Ausschlüsse.

| ID | Fachgebiete | Verfügbar | Restkapazität (min) | Aktive Tickets |
|---:|---|---|---:|---:|
| 7 | ELEKTRO, HEIZUNG | ja | 120 | 2 |
| 3 | HEIZUNG | ja | 80 | 2 |
| 9 | HEIZUNG | ja | 120 | 2 |
| 4 | HEIZUNG | ja | 180 | 3 |
| 2 | HEIZUNG | nein | 300 | 0 |

Auftrag A: Fachgebiet HEIZUNG, Sicherheitsgefahr **ja**, Dauer 90 Minuten.  
Auftrag B: Fachgebiet HEIZUNG, Sicherheitsgefahr **nein**, Dauer 70 Minuten.

### 1c – 3 Punkte

Bestimmen und begründen Sie die asymptotische Laufzeit in Abhängigkeit von `n` Fachkräften sowie den zusätzlichen Speicherbedarf. Nehmen Sie an, dass `Set.contains` im Mittel konstante Zeit benötigt.

## Aufgabe 2: Fehlerkorrektur und Testfallermittlung (23 Punkte)

Eine Funktion soll die Anzahl **vollständig abgeschlossener** Tickets eines Tages zählen. Ein Ticket gilt als vollständig abgeschlossen, wenn (1) sein Status `ERLEDIGT` ist, (2) es einen nicht leeren Arbeitsbericht besitzt und (3) seine Abnahme dokumentiert ist. Ein Bericht gilt als leer, wenn er `null` ist oder ausschließlich Leerraum enthält.

```java
record Ticket(String status, String bericht, boolean abgenommen) {}

public static int zaehle(List<Ticket> tickets) {
    int ergebnis = 0;
    for (int i = 0; i <= tickets.size(); i++) {
        Ticket t = tickets.get(i);
        if (t.status().equals("ERLEDIGT") ||
                !t.bericht().isEmpty() && t.abgenommen()) {
            ergebnis++;
        }
    }
    return ergebnis;
}
```

`tickets` und die enthaltenen `Ticket`-Objekte sind nicht null; `status` ist nicht null. `bericht` kann null sein. Die Liste kann leer sein.

### 2a – 9 Punkte

Identifizieren und erläutern Sie **drei unabhängige Fehlerursachen** im Code mit jeweils einer möglichen Auswirkung. Beachten Sie die fachliche Vollständigkeitsregel.

### 2b – 8 Punkte

Schreiben Sie die vollständige korrigierte Java-Methode mit derselben Signatur. Verwenden Sie die Standardbibliothek von Java 21.

### 2c – 6 Punkte

Definieren Sie **drei gezielte Testfälle** mit Eingabeliste und erwartetem Rückgabewert. Decken Sie damit mindestens eine leere Liste, einen null- oder Leerraumbericht und einen gültigen Abschluss ab. Legen Sie die einzelnen Ticketwerte eindeutig fest.

## Aufgabe 3: Ticket-Zustände modellieren (22 Punkte)

Ein Ticket der Anwendung durchläuft ausschließlich folgende fachliche Zustände: `OFFEN`, `ZUGEWIESEN`, `IN_ARBEIT`, `ERLEDIGT` und `GESCHLOSSEN`. Das Ticket startet in `OFFEN`. Übergänge:

- `zuweisen`: `OFFEN` -> `ZUGEWIESEN`;
- `beginnen`: `ZUGEWIESEN` -> `IN_ARBEIT`;
- `abschliessen`: `IN_ARBEIT` -> `ERLEDIGT`, nur wenn der Arbeitsbericht nicht leer ist;
- `abnehmen`: `ERLEDIGT` -> `GESCHLOSSEN`;
- `nacharbeit`: `ERLEDIGT` -> `IN_ARBEIT`;
- `zurueckziehen`: `ZUGEWIESEN` -> `OFFEN`.

Nach `GESCHLOSSEN` gibt es keine weiteren Übergänge. Es gibt keinen weiteren fachlichen Pfad. Ein vollständiger UML-Zustandsautomat muss einen Startknoten und einen Endknoten enthalten.

### 3a – 14 Punkte

Erstellen Sie einen **UML-Zustandsautomaten** für ein Ticket mit Zuständen, Anfangszustand, Endzustand, allen Übergängen und der Bedingung für `abschliessen`. Das Diagramm kann handschriftlich erstellt werden.

### 3b – 4 Punkte

Ein Ticket steht auf `ERLEDIGT`. Der Auftraggeber lehnt den Bericht ab. Beschreiben Sie die zulässige Folge der nächsten **zwei Ereignisse** bis zum erneuten Zustand `ERLEDIGT` und nennen Sie die jeweilige Zustandsänderung. Gehen Sie davon aus, dass danach ein nicht leerer korrigierter Bericht vorliegt.

### 3c – 4 Punkte

Erläutern Sie anhand eines konkreten Beispiels aus diesem Automaten, wie eine serverseitige Zustandsprüfung eine unerlaubte Bearbeitung verhindert, auch wenn die grafische Benutzeroberfläche fehlerhaft ist.

## Aufgabe 4: SQL-Auswertungen (26 Punkte)

Das folgende Datenmodell wird **unabhängig vom UML-Zustandsmodell** verwendet. PostgreSQL 16, Primär-/Fremdschlüssel und NOT-NULL-Bedingungen sind wie angegeben definiert.

```sql
CREATE TABLE gebaeude (
  gebaeude_id INTEGER PRIMARY KEY,
  name VARCHAR(60) NOT NULL
);
CREATE TABLE fachkraft (
  fachkraft_id INTEGER PRIMARY KEY,
  name VARCHAR(60) NOT NULL
);
CREATE TABLE ticket (
  ticket_id INTEGER PRIMARY KEY,
  gebaeude_id INTEGER NOT NULL REFERENCES gebaeude(gebaeude_id),
  fachkraft_id INTEGER REFERENCES fachkraft(fachkraft_id),
  prioritaet INTEGER NOT NULL CHECK (prioritaet BETWEEN 1 AND 3),
  status VARCHAR(20) NOT NULL,
  kosten NUMERIC(9,2)
);
```

`kosten` darf `NULL` sein, wenn kein Betrag verbucht ist. `fachkraft_id` darf bei einem noch nicht zugewiesenen Ticket `NULL` sein. Die Daten sind:

**gebaeude**

| gebaeude_id | name |
|---:|---|
| 1 | Rathaus |
| 2 | Grundschule |
| 3 | Sporthalle |
| 4 | Bibliothek |

**fachkraft**

| fachkraft_id | name |
|---:|---|
| 10 | Kaya |
| 11 | Weber |
| 12 | Stein |

**ticket**

| ticket_id | gebaeude_id | fachkraft_id | prioritaet | status | kosten |
|---:|---:|---:|---:|---|---:|
| 101 | 1 | 10 | 1 | ERLEDIGT | 120.00 |
| 102 | 1 | 11 | 2 | OFFEN | NULL |
| 103 | 2 | 10 | 1 | ERLEDIGT | 80.00 |
| 104 | 2 | NULL | 3 | OFFEN | NULL |
| 105 | 3 | 11 | 1 | ERLEDIGT | 150.00 |
| 106 | 3 | 12 | 1 | ERLEDIGT | 200.00 |
| 107 | 3 | 12 | 2 | ERLEDIGT | 50.00 |

### 4a – 6 Punkte

Schreiben Sie eine SQL-Abfrage, die für jedes Gebäude einschließlich der Bibliothek die Spalten `gebaeude_id`, `name` und `anzahl_tickets` ausgibt. Gebäude ohne Tickets haben die Anzahl 0. Sortieren Sie nach Gebäude-ID.

### 4b – 8 Punkte

Schreiben Sie eine SQL-Abfrage, die ausschließlich Gebäude mit **mindestens zwei erledigten Tickets der Priorität 1** ausgibt. Gewünschte Spalten: `name`, `anzahl` und `gesamtkosten` (Summe der Kosten dieser Tickets). Sortieren Sie alphabetisch nach Name. Geben Sie die erwartete Ergebniszeile an.

### 4c – 7 Punkte

Schreiben Sie eine SQL-Abfrage, die `fachkraft_id` und `name` aller Fachkräfte ausgibt, die **an keinem offenen Ticket beteiligt** sind. Verwenden Sie dafür eine korrelierte Unterabfrage mit `NOT EXISTS`. Sortieren Sie aufsteigend nach Fachkraft-ID.

### 4d – 5 Punkte

Ein bislang nicht zugewiesenes Ticket 104 soll Fachkraft 12 erhalten, **aber nur wenn es weiterhin `OFFEN` und noch nicht zugewiesen ist**. Schreiben Sie genau eine entsprechende `UPDATE`-Anweisung.
