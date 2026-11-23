# Prüfungssimulation sim02 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100 Punkte  
**Aufgaben:** 4 Hauptaufgaben.

**Hinweis:** Java 21; SQL-Dialekt PostgreSQL 16.

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

**Lösung**

Vollständige Java-21-Methode (Records aus der Aufgabe vorhanden):

```java
public static Fachkraft waehle(Auftrag a, List<Fachkraft> fachkraefte) {
    Fachkraft beste = null;
    for (Fachkraft f : fachkraefte) {
        if (!f.verfuegbar()
                || !f.fachgebiete().contains(a.fachgebiet())
                || f.restkapazitaetMinuten() < a.dauerMinuten()) {
            continue;
        }
        if (a.sicherheitsgefahr() && f.aktiveTickets() > 2) {
            continue;
        }
        if (beste == null
                || f.aktiveTickets() < beste.aktiveTickets()
                || (f.aktiveTickets() == beste.aktiveTickets()
                    && f.restkapazitaetMinuten() > beste.restkapazitaetMinuten())
                || (f.aktiveTickets() == beste.aktiveTickets()
                    && f.restkapazitaetMinuten() == beste.restkapazitaetMinuten()
                    && f.id() < beste.id())) {
            beste = f;
        }
    }
    return beste;
}
```

**Bewertung**

 Iteration aller Kandidaten 2 P.; Verfügbarkeit 2 P.; Fachgebiet 2 P.; Kapazität einschließlich Gleichheit 2 P.; Sonderregel ausschließlich für Sicherheitsgefahr 3 P.; erste Sortierstufe aktive Tickets 3 P.; zweite Stufe Restkapazität 2 P.; dritte Stufe ID 2 P.; null bei fehlenden Treffern und unveränderte Daten 2 P. Alternative lexikografische Tupelvergleiche zulässig. `null`-Eingaben müssen nicht behandelt werden.

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

**Lösung**

Auftrag A → **ID 7**, weil ID 3 nur 80 Minuten hat, ID 9 bei gleicher Ticketzahl und Kapazität die größere ID besitzt, ID 4 mehr als zwei aktive Tickets aufweist, ID 2 nicht verfügbar ist (3 P.). Auftrag B → **ID 7**; geeignet: IDs 7, 3, 9 und 4. Die aktiven Tickets 2 schlagen 3; zwischen 7, 3 und 9 gewinnt die größte Restkapazität 120, zwischen 7 und 9 die kleinere ID 7 (3 P.).

### 1c – 3 Punkte

Bestimmen und begründen Sie die asymptotische Laufzeit in Abhängigkeit von `n` Fachkräften sowie den zusätzlichen Speicherbedarf. Nehmen Sie an, dass `Set.contains` im Mittel konstante Zeit benötigt.

**Lösung**

Zeit **O(n)** bei durchschnittlich konstantem `Set.contains` (2 P. für Wert und Grund); zusätzlicher Speicher **O(1)** für Vergleichs- und Referenzvariablen (1 P.).

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

**Lösung**

Drei Fehlerursachen: (1) `i <= tickets.size()` ruft `get(size())` auf und wirft auch bei leerer Liste eine `IndexOutOfBoundsException`; korrekt `i < size()`. (2) `||` statt notwendiger Konjunktion aller drei Bedingungen zählt Tickets, obwohl Voraussetzungen fehlen. (3) `t.bericht().isEmpty()` wirft bei null eine `NullPointerException`; außerdem zählt der Ausdruck mit `isEmpty` allein einen reinen Leerraumbericht fälschlich als nicht leer.

**Bewertung**

 je 1 P. Fehlerstelle, 1 P. Auswirkung, 1 P. klare fachliche Korrekturrichtung. Für (3) gelten null/Leerraum zusammen als ein Fehlerkomplex; alternativ korrekt differenziert bewertet.

### 2b – 8 Punkte

Schreiben Sie die vollständige korrigierte Java-Methode mit derselben Signatur. Verwenden Sie die Standardbibliothek von Java 21.

**Lösung**

```java
public static int zaehle(List<Ticket> tickets) {
    int ergebnis = 0;
    for (Ticket t : tickets) {
        if ("ERLEDIGT".equals(t.status())
                && t.bericht() != null
                && !t.bericht().isBlank()
                && t.abgenommen()) {
            ergebnis++;
        }
    }
    return ergebnis;
}
```

**Bewertung**

 korrekte Iteration 2 P.; Statusprüfung 1 P.; Null-Schutz 2 P.; Leerraumerkennung 1 P.; Abnahmeprüfung 1 P.; korrektes Zähler-/Rückgabeverhalten bei leeren Listen 1 P. `trim().isEmpty()` kann für ASCII-Leerraum akzeptiert werden, wenn die Grenzen erklärt werden.

### 2c – 6 Punkte

Definieren Sie **drei gezielte Testfälle** mit Eingabeliste und erwartetem Rückgabewert. Decken Sie damit mindestens eine leere Liste, einen null- oder Leerraumbericht und einen gültigen Abschluss ab. Legen Sie die einzelnen Ticketwerte eindeutig fest.

**Lösung**

Drei explizite Testfälle: (1) `[]` → 0; (2) `[Ticket("ERLEDIGT", null, true), Ticket("ERLEDIGT", "   ", true)]` → 0; (3) `[Ticket("ERLEDIGT", "Lampe gewechselt", true), Ticket("ERLEDIGT", "ok", false)]` → 1. Je 1 P. eindeutige Eingabe, 1 P. korrektes Soll. Andere ausreichende Grenz- und Negativtests zulässig.

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

**Lösung**

Zustandsdiagramm als textuelle UML-Zustandsautomaten-Notation:

```text
[Start] -> OFFEN
OFFEN --zuweisen--> ZUGEWIESEN
ZUGEWIESEN --beginnen--> IN_ARBEIT
ZUGEWIESEN --zurueckziehen--> OFFEN
IN_ARBEIT --abschliessen [bericht nicht leer]--> ERLEDIGT
ERLEDIGT --nacharbeit--> IN_ARBEIT
ERLEDIGT --abnehmen--> GESCHLOSSEN
GESCHLOSSEN --> [Ende]
```

[Lösungsdiagramm in Datei ](./sim02_algorithmen_lösung_3a.uxf).

**Bewertung**

 fünf korrekte Zustände 5 P.; Startknoten mit Transition 1 P.; Ende von GESCHLOSSEN aus 1 P.; sechs korrekte benannte Übergänge 6 P. (zuweisen, beginnen, abschliessen, abnehmen, nacharbeit, zurueckziehen); Guard bei `abschliessen` 1 P. Darstellung als UML-Zustandsautomat und nicht Prozessfluss vorausgesetzt; nur semantisch falsche Notationsentscheidungen abwerten.

### 3b – 4 Punkte

Ein Ticket steht auf `ERLEDIGT`. Der Auftraggeber lehnt den Bericht ab. Beschreiben Sie die zulässige Folge der nächsten **zwei Ereignisse** bis zum erneuten Zustand `ERLEDIGT` und nennen Sie die jeweilige Zustandsänderung. Gehen Sie davon aus, dass danach ein nicht leerer korrigierter Bericht vorliegt.

**Lösung**

`nacharbeit`: ERLEDIGT → IN_ARBEIT (2 P.), danach `abschliessen` mit nicht leerem korrigiertem Bericht: IN_ARBEIT → ERLEDIGT (2 P.).

### 3c – 4 Punkte

Erläutern Sie anhand eines konkreten Beispiels aus diesem Automaten, wie eine serverseitige Zustandsprüfung eine unerlaubte Bearbeitung verhindert, auch wenn die grafische Benutzeroberfläche fehlerhaft ist.

**Lösung**

Beispiel: Ein Client sendet `abnehmen`, während Ticket noch OFFEN ist. Der Service prüft serverseitig den gespeicherten Status, erlaubt `abnehmen` aber nur aus ERLEDIGT und weist den Request zurück; der Status bleibt OFFEN. 2 P. konkrete unerlaubte Transition und 2 P. Wirkung. Absicherung allein über deaktivierte UI-Buttons genügt nicht.

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

**Lösung**

```sql
SELECT g.gebaeude_id, g.name, COUNT(t.ticket_id) AS anzahl_tickets
FROM gebaeude AS g
LEFT JOIN ticket AS t ON t.gebaeude_id = g.gebaeude_id
GROUP BY g.gebaeude_id, g.name
ORDER BY g.gebaeude_id;
```

Ergebnis: (1, Rathaus, 2), (2, Grundschule, 2), (3, Sporthalle, 3), (4, Bibliothek, 0).

**Bewertung**

 LEFT JOIN 2 P.; korrekte Beziehung 1 P.; `COUNT(t.ticket_id)` 1 P.; GROUP BY 1 P.; Projektion/Sortierung 1 P. `COUNT(*)` ist falsch.

### 4b – 8 Punkte

Schreiben Sie eine SQL-Abfrage, die ausschließlich Gebäude mit **mindestens zwei erledigten Tickets der Priorität 1** ausgibt. Gewünschte Spalten: `name`, `anzahl` und `gesamtkosten` (Summe der Kosten dieser Tickets). Sortieren Sie alphabetisch nach Name. Geben Sie die erwartete Ergebniszeile an.

**Lösung**

```sql
SELECT g.name, COUNT(*) AS anzahl, SUM(t.kosten) AS gesamtkosten
FROM gebaeude AS g
JOIN ticket AS t ON t.gebaeude_id = g.gebaeude_id
WHERE t.status = 'ERLEDIGT' AND t.prioritaet = 1
GROUP BY g.gebaeude_id, g.name
HAVING COUNT(*) >= 2
ORDER BY g.name;
```

Erwartete Ergebniszeile: **Sporthalle | 2 | 350.00**.

**Bewertung**

 JOIN/ON 2 P.; beide Filter 2 P.; Gruppierung 1 P.; HAVING 1 P.; SUM/Projektion/Sortierung 1 P.; Ergebnistabelle 1 P. Ein INNER JOIN ist hier ausreichend. SUM aggregiert ausschließlich die durch WHERE gefilterten Zeilen.

### 4c – 7 Punkte

Schreiben Sie eine SQL-Abfrage, die `fachkraft_id` und `name` aller Fachkräfte ausgibt, die **an keinem offenen Ticket beteiligt** sind. Verwenden Sie dafür eine korrelierte Unterabfrage mit `NOT EXISTS`. Sortieren Sie aufsteigend nach Fachkraft-ID.

**Lösung**

```sql
SELECT f.fachkraft_id, f.name
FROM fachkraft AS f
WHERE NOT EXISTS (
    SELECT 1
    FROM ticket AS t
    WHERE t.fachkraft_id = f.fachkraft_id
      AND t.status = 'OFFEN'
)
ORDER BY f.fachkraft_id;
```

Ergebnis wären IDs **10 und 12** (Kaya und Stein), eine Ergebnistabelle wird nicht verlangt.

**Bewertung**

 NOT EXISTS 2 P.; korrelierte FK-Bedingung 2 P.; OFFEN-Filter 1 P.; korrekte äußere Auswahl 1 P.; Sortierung 1 P. Alternative ohne NOT EXISTS ist wegen explizitem Operator nicht vollständig, kann inhaltliche Teilpunkte erhalten.

### 4d – 5 Punkte

Ein bislang nicht zugewiesenes Ticket 104 soll Fachkraft 12 erhalten, **aber nur wenn es weiterhin `OFFEN` und noch nicht zugewiesen ist**. Schreiben Sie genau eine entsprechende `UPDATE`-Anweisung.

**Lösung**

```sql
UPDATE ticket
SET fachkraft_id = 12
WHERE ticket_id = 104
  AND status = 'OFFEN'
  AND fachkraft_id IS NULL;
```

**Bewertung**

 korrektes UPDATE/SET 2 P.; genaue Ticket-ID 1 P.; Statusbedingung 1 P.; `IS NULL` 1 P. Zusätzliche Transaktionskontrolle ist möglich, aber nicht verlangt.

**Ende des Prüfungsbereichs.**

**SQL-Kontrollwerte:** 4a Gebäudeanzahlen 2/2/3/0, 4b Sporthalle mit 350, 4c IDs 10/12, 4d exakt eine Zeile vor Änderung.

**Punktesumme:** 29 + 23 + 22 + 26 = **100**.
