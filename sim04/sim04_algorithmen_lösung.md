# Prüfungssimulation sim04 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100  
**Aufgaben:** 4 
**Programmiersprache:** Java 21
**SQL-Dialekt:** PostgreSQL 18  

## Ausgangssituation

Das System **ProbenNet** der RegioAnalytik GmbH verwaltet Laborproben und die zugehörigen Messwerte. Ein Messwert ist fachlich gültig, wenn sein Kennzeichen `gueltig` wahr ist. Eine Probe kann wiederholt gemessen werden. Für die Auswertung werden Einzelmessungen chronologisch verarbeitet. Zusätzlich müssen Messreihen getestet und relationale Daten ausgewertet werden.

## Aufgabe 1: Auffällige Messfenster (30 Punkte)

Gegeben sind diese Java-21-Datentypen:

```java
import java.util.List;

class Messwert {
    private int minute;
    private double wert;
    private boolean gueltig;

    Messwert(int minute, double wert, boolean gueltig) {
        this.minute = minute;
        this.wert = wert;
        this.gueltig = gueltig;
    }

    int minute() {
        return minute;
    }

    double wert() {
        return wert;
    }

    boolean gueltig() {
        return gueltig;
    }
}

class Messreihe {
    private String probenId;
    private List<Messwert> werte;

    Messreihe(String probenId, List<Messwert> werte) {
        this.probenId = probenId;
        this.werte = werte;
    }

    String probenId() {
        return probenId;
    }

    List<Messwert> werte() {
        return werte;
    }
}
```
Die Listen und ihre Elemente sind niemals `null`, die Messminuten sind innerhalb einer Reihe strikt aufsteigend und nicht negativ. `Double.NaN` und unendliche Messwerte kommen nicht vor. Die Eingabe darf nicht verändert werden.

Ein **auffälliges Messfenster** liegt vor, wenn es **zwei aufeinanderfolgende gültige Messwerte** gibt, deren zeitlicher Abstand **höchstens zehn Minuten** beträgt und deren **absolute Wertdifferenz größer als 4,0** ist. Zwischen diesen beiden gültigen Werten dürfen beliebig viele ungültige Messungen liegen; diese werden für die Paarbildung ignoriert. Die Rückgabe ist die Anzahl auffälliger Paare **aufeinanderfolgender gültiger Messwerte**, auch wenn sich Paare eine gültige Messung teilen. Ein Abstand von genau zehn Minuten ist zulässig, eine Differenz von genau 4,0 ist nicht auffällig. Bei weniger als zwei gültigen Messwerten lautet das Ergebnis null.

### 1a – 18 Punkte

Entwickeln Sie den Algorithmus `zaehleAuffaelligePaare(Messreihe r)`, der die Anzahl auffälliger Paare als Ganzzahl zurückgibt. Schreiben Sie die vollständige Methode **entweder in Java oder in Java-ähnlichem Pseudocode**. Bei einer Java-Lösung verwenden Sie die Signatur `public static int zaehleAuffaelligePaare(Messreihe r)` und nur die Java-Standardbibliothek. Im Pseudocode dürfen Sie sprachtypische Vereinfachungen (z. B. `FÜR`, `WENN`, `ENDE WENN`) verwenden; die Verarbeitung und alle Bedingungen müssen eindeutig sein. Die vorgegebenen Zugriffsmethoden der Klassen stehen in beiden Darstellungsformen zur Verfügung.

**Hinweis:** `Math.abs(x)` liefert in Java den absoluten Wert von `x`, also dessen Betrag ohne Vorzeichen (z. B. `Math.abs(-5.0)` ergibt `5.0`). Im Pseudocode darf dafür `abs(x)` verwendet werden.

**Musterlösung 1 – Java**

```java
public static int zaehleAuffaelligePaare(Messreihe r) {
    Messwert vorher = null;
    int anzahl = 0;
    for (Messwert aktuell : r.werte()) {
        if (!aktuell.gueltig()) {
            continue;
        }
        if (vorher != null) {
            int abstand = aktuell.minute() - vorher.minute();
            double differenz = Math.abs(aktuell.wert() - vorher.wert());
            if (abstand <= 10 && differenz > 4.0) {
                anzahl++;
            }
        }
        vorher = aktuell;
    }
    return anzahl;
}
```

**Musterlösung 2 – Java-ähnlicher Pseudocode (ohne `null`)**

```text
FUNKTION zaehleAuffaelligePaare(r: Messreihe): int
    anzahl = 0
    hatVorher = false
    vorherMinute = 0
    vorherWert = 0.0

    FÜR jeden aktuell IN r.werte()
        WENN aktuell.gueltig() DANN
            WENN hatVorher DANN
                abstand = aktuell.minute() - vorherMinute
                differenz = abs(aktuell.wert() - vorherWert)
                WENN abstand <= 10 UND differenz > 4.0 DANN
                    anzahl = anzahl + 1
                ENDE WENN
            ENDE WENN

            vorherMinute = aktuell.minute()
            vorherWert = aktuell.wert()
            hatVorher = true
        ENDE WENN
    ENDE FÜR

    RÜCKGABE anzahl
ENDE FUNKTION
```

Die boolesche Variable `hatVorher` zeigt an, ob bereits eine gültige Messung verarbeitet wurde. Die initialen Werte von `vorherMinute` und `vorherWert` werden erst verwendet, nachdem sie durch eine gültige Messung ersetzt wurden. Ungültige Messungen verändern diese Werte nicht. So wird kein `null`-Wert benötigt.

**Bewertung (für beide Lösungsformen)**

korrekte Initialisierung und Ergebniszählung 3 P; vollständige Schleife 3 P; ungültige Messungen überspringen, ohne Vorgänger zu ändern, 4 P; nur unmittelbar aufeinanderfolgende gültige Werte vergleichen 3 P; beide Grenzbedingungen exakt 3 P; Rückgabe und Randfälle 2 P. Eine `null`-Prüfung oder eine boolesche Kennzeichnung für das Vorhandensein eines gültigen Vorgängers sind gleichwertig. Entscheidend ist die korrekte Logik, nicht die konkrete Syntax.

### 1b – 8 Punkte

Führen Sie die Methode für die folgende Reihe als Schreibtischtest aus. Geben Sie jedes Paar aufeinanderfolgender **gültiger** Messungen mit Zeitabstand, absoluter Wertdifferenz und Entscheidung an. Ermitteln Sie den Rückgabewert.

| Minute | Wert | gültig |
|---:|---:|---|
| 0 | 5,0 | ja |
| 4 | 10,0 | ja |
| 6 | 100,0 | nein |
| 14 | 14,0 | ja |
| 25 | 20,0 | ja |
| 33 | 15,0 | ja |

**Lösung**

| gültiges Paar (Minute) | Zeitabstand | Absolutdifferenz | auffällig? |
|---|---:|---:|---|
| 0 / 4 | 4 | 5,0 | ja |
| 4 / 14 | 10 | 4,0 | nein |
| 14 / 25 | 11 | 6,0 | nein |
| 25 / 33 | 8 | 5,0 | ja |

**Rückgabe = 2**. Die ungültige Messung bei Minute 6 bildet kein Paar und beeinflusst den gültigen Vorgänger nicht. 

**Bewertung**  
je 1,5 P für alle vier Paarentscheidungen mit Abständen/Differenzen (6 P) und 2 P für Rückgabe samt Umgang mit ungültigem Wert.

### 1c – 4 Punkte

Bestimmen Sie Laufzeit und zusätzlichen Speicherbedarf in Abhängigkeit von der Anzahl `n` der Messungen. Begründen Sie kurz.

**Lösung**

Zeit **O(n)**: jedes Element wird einmal betrachtet (2 P). Zusätzlicher Speicher **O(1)**: nur Vorgänger, Zähler und Hilfsvariablen (2 P). Eingabeliste wird nicht kopiert.

## Aufgabe 2: Validierung, Fehlerkorrektur, Tests (22 Punkte)

Eine Messreihe wird nur dann als "vollständig" bewertet, wenn sie mindestens eine Messung enthält und **jede** Messung gültig ist. Die Liste und ihre Elemente sind niemals `null`. Der folgende Code soll diese Bedingung prüfen:

```java
public static boolean vollstaendig(List<Messwert> werte) {
    boolean ok = true;
    for (int i = 0; i < werte.size() - 1; i++) {
        if (werte.get(i).gueltig()) {
            ok = false;
        }
    }
    return ok;
}
```

### 2a – 9 Punkte

Identifizieren Sie **drei voneinander unabhängige Fehler** in der Implementierung und erläutern Sie zu jedem ein konkretes fehlerhaftes Verhalten. (Je 3 Punkte.)

**Lösung**

1. **Leere Liste:** Anfangswert true und kein Sonderfall; `[]` wird fälschlich als vollständig bewertet.
2. **Schleifenende:** `i < werte.size() - 1` lässt das letzte Element aus; bei `[true,false]` wird die letzte ungültige Messung nicht untersucht.
3. **Negierte Fachbedingung:** `if (werte.get(i).gueltig()) ok = false` markiert gerade gültige Werte als Fehler; `[true,true]` ergibt false.


### 2b – 7 Punkte

Schreiben Sie die vollständig korrigierte Methode mit derselben Signatur. Beachten Sie insbesondere die leere Liste.

**Lösung**

```java
public static boolean vollstaendig(List<Messwert> werte) {
    if (werte.isEmpty()) {
        return false;
    }
    for (Messwert w : werte) {
        if (!w.gueltig()) {
            return false;
        }
    }
    return true;
}
```

**Bewertung**  
Leere Liste korrekt 2; alle Elemente erfassen 2; ungültige Messung richtig ablehnen 2; korrekte Schlussrückgabe 1. Auch korrekte Indexschleife zulässig.

### 2c – 6 Punkte

Formulieren Sie drei Testfälle mit Eingabe (Gültigkeitskennzeichen reichen) und erwartetem Rückgabewert. Prüfen Sie dabei die leere Liste, eine vollständig gültige Liste und eine Liste, bei der ausschließlich das letzte Element ungültig ist. (Je 2 Punkte.)

**Lösung**

| Eingabe (gültig) | Soll |
|---|---|
| [] | false |
| [true, true] | true |
| [true, true, false] | false |

**Hinweis**  
Der dritte Testfall überprüft, ob auch das letzte Element berücksichtigt wird. Bei der ursprünglichen fehlerhaften Implementierung wird dessen Wirkung durch die ebenfalls fehlerhafte Gültigkeitsprüfung verdeckt. Nach isolierter Korrektur dieser Gültigkeitsprüfung würde der Testfall die falsche Schleifengrenze aufdecken.

**Bewertung**  
Je 1 P Eingabe mit passendem Randfall, 1 P erwarteter Wert.

## Aufgabe 3: Relationales Modell und Integrität (23 Punkte)

Für die Probenverwaltung gelten diese Regeln:

- Jede Entnahmestelle hat eine eindeutige ID und einen Namen.
- Eine Entnahmestelle kann beliebig viele Proben besitzen; jede Probe gehört genau einer Entnahmestelle.
- Für jede Probe sind Proben-ID und Entnahmezeitpunkt hinterlegt.
- Ein Analyseverfahren hat eine eindeutige ID, eine Bezeichnung und eine Einheit.
- Jede Probe kann nach mehreren Analyseverfahren untersucht werden und jedes Analyseverfahren kann bei mehreren Proben verwendet werden.
- Für eine Kombination aus Probe und Analyseverfahren darf **höchstens ein aktuelles Ergebnis** mit Zahlenwert und Prüfdatum vorliegen. Nicht jedes Verfahren wird bei jeder Probe ausgeführt.
- Die Geschäftsanforderung verlangt, dass eine Probe ohne bisheriges Analyseergebnis bereits gespeichert werden kann.

### 3a – 13 Punkte

Entwerfen Sie ein relationales Schema in **dritter Normalform**, inklusive Tabellennamen, Attributen, Primärschlüsseln, Fremdschlüsseln sowie notwendiger Eindeutigkeitsregeln. Kennzeichnen Sie die Beziehungen einschließlich der Kardinalitäten. (Schema 9 P; Beziehungen/Kardinalitäten 4 P.)

**Lösung**

Ein Schema in 3NF:

```text
ENTNAHMESTELLE(
    stelle_id PK,
    name NOT NULL
)

PROBE(
    probe_id PK,
    stelle_id FK NOT NULL -> ENTNAHMESTELLE,
    entnahmezeitpunkt NOT NULL
)

VERFAHREN(
    verfahren_id PK,
    bezeichnung NOT NULL,
    einheit NOT NULL
)

ERGEBNIS(
    probe_id FK -> PROBE,
    verfahren_id FK -> VERFAHREN,
    wert NOT NULL,
    pruefdatum NOT NULL,
    PK(probe_id, verfahren_id)
)
```

Kardinalitäten: 

| Beziehung | Kardinalität | Bedeutung |
|---|---|---|
| Entnahmestelle – Probe | 1 : 0..n | Jede Probe gehört genau einer Entnahmestelle; eine Entnahmestelle kann keine, eine oder mehrere Proben haben |
| Probe – Ergebnis | 1 : 0..n | Jedes Ergebnis gehört genau einer Probe; eine Probe kann keine, ein oder mehrere Ergebnisse haben |
| Verfahren – Ergebnis | 1 : 0..n | Jedes Ergebnis gehört genau einem Verfahren; ein Verfahren kann in keinem, einem oder mehreren Ergebnissen verwendet werden |

Daraus folgt Probe–Verfahren n:m mit assoziativer Entität Ergebnis, maximal eine Kombination pro Paar. 

**Bewertung**  
- Schema: vier passende Tabellen 4; Attribute 2; Schlüssel 2; Eindeutigkeit/Fremdschlüssel 1.  
- Beziehungen: Entnahmestelle–Probe 2; Probe–Ergebnis/Verfahren–Ergebnis 2. 
- Eine Darstellung in anderen tabellarischen Notationen ist zulässig. 
- Die FKs in ERGEBNIS sind zugleich zusammengesetzter Primärschlüssel.


### 3b – Datenanomalien (6 Punkte)

Die Daten der Probenverwaltung werden zunächst in einer einzigen, nicht normalisierten Tabelle gespeichert:

`MESSDATEN(probe_id, entnahmestelle_id, entnahmestelle_name, verfahren_id, verfahren_name, wert)`

Jede Tabellenzeile repräsentiert eine Probe, die mit einem bestimmten Analyseverfahren untersucht wurde, einschließlich des zugehörigen Ergebnisses.

Der zusammengesetzte Primärschlüssel besteht aus `probe_id` und `verfahren_id`.

Nennen und erläutern Sie **drei unterschiedliche Datenanomalien**, die bei dieser Tabellenstruktur auftreten können.

Geben Sie für jede Anomalie ein konkretes Beispiel aus der Probenverwaltung an.

**(Je 2 Punkte.)**

**Lösung**

- Änderungsanomalie: Der Name einer Entnahmestelle wird in mehreren Zeilen gespeichert. Wird er nicht überall aktualisiert, entstehen widersprüchliche Angaben.
- Einfügeanomalie: Eine neue Probe ohne Analyseergebnis kann nicht als eigenständiger Datensatz gespeichert werden, da eine Tabellenzeile eine Kombination aus Probe und Verfahren repräsentiert und verfahren_id zum Primärschlüssel gehört.
- Löschanomalie: Wird die letzte Ergebniszeile einer Entnahmestelle gelöscht, gehen gleichzeitig deren einzige gespeicherte Stammdaten verloren.

**Bewertung**  
Je eine konkrete Erklärung erforderlich; reine Begriffsaufzählung höchstens 1 Punkt pro Fall.

### 3c – Abbildung einer n:m-Beziehung (4 Punkte)

Ein Entwickler schlägt vor, die Beziehung zwischen Proben und Analyseverfahren durch eine zusätzliche Fremdschlüsselspalte `verfahren_id` in der Tabelle `PROBE` abzubilden.

**Begründen Sie**, warum dieser Vorschlag die fachlichen Anforderungen nicht vollständig erfüllt.

**Erläutern Sie**, wie die Beziehung stattdessen mithilfe der Tabelle `ERGEBNIS` korrekt abgebildet wird.

**Lösung**

Eine einzelne Fremdschlüsselspalte `verfahren_id` in `PROBE` könnte jeder Probe höchstens ein Analyseverfahren zuordnen. Da eine Probe mit mehreren Verfahren untersucht werden kann und jedes Verfahren für mehrere Proben verwendet werden kann, liegt eine n:m-Beziehung vor.

Diese Beziehung wird durch die Zwischentabelle `ERGEBNIS` aufgelöst. Sie enthält die Fremdschlüssel `probe_id` und `verfahren_id`, die gemeinsam den zusammengesetzten Primärschlüssel bilden. Jede Zeile verknüpft genau eine Probe mit genau einem Analyseverfahren und enthält das zugehörige Ergebnis.

**Bewertung**

- Einschränkung durch eine einzelne Fremdschlüsselspalte korrekt erläutert: 1 Punkt.
- n:m-Kardinalität aus den fachlichen Anforderungen begründet: 1 Punkt.
- Zwischentabelle `ERGEBNIS` mit beiden Fremdschlüsseln korrekt erläutert: 1 Punkt.
- Zusammengesetzten Primärschlüssel korrekt angegeben: 1 Punkt.


## Aufgabe 4: SQL zu Laborergebnissen (25 Punkte)

Es gilt unabhängig von Ihrer Lösung aus Aufgabe 3 exakt dieses PostgreSQL-16-Schema:

```sql
CREATE TABLE stelle (
  stelle_id INTEGER PRIMARY KEY,
  name VARCHAR(60) NOT NULL
);
CREATE TABLE probe (
  probe_id INTEGER PRIMARY KEY,
  stelle_id INTEGER NOT NULL REFERENCES stelle(stelle_id),
  freigabe BOOLEAN NOT NULL
);
CREATE TABLE verfahren (
  verfahren_id INTEGER PRIMARY KEY,
  bezeichnung VARCHAR(60) NOT NULL
);
CREATE TABLE ergebnis (
  probe_id INTEGER NOT NULL REFERENCES probe(probe_id),
  verfahren_id INTEGER NOT NULL REFERENCES verfahren(verfahren_id),
  wert NUMERIC(8,2) NOT NULL,
  bestanden BOOLEAN NOT NULL,
  PRIMARY KEY (probe_id, verfahren_id)
);
```
**Ausgangsdaten:**

| stelle_id | name |
|---:|---|
| 1 | Nord |
| 2 | Süd |
| 3 | West |

| probe_id | stelle_id | freigabe |
|---:|---:|---|
| 10 | 1 | true |
| 11 | 1 | false |
| 12 | 2 | true |
| 13 | 3 | false |
| 14 | 2 | false |

| verfahren_id | bezeichnung |
|---:|---|
| 1 | Nitrat |
| 2 | pH |
| 3 | Leitfähigkeit |

| probe_id | verfahren_id | wert | bestanden |
|---:|---:|---:|---|
| 10 | 1 | 12,00 | true |
| 10 | 2 | 7,20 | true |
| 11 | 1 | 60,00 | false |
| 12 | 1 | 18,00 | true |
| 12 | 3 | 400,00 | true |
| 13 | 2 | 6,50 | false |

### 4a – Freigegebene Proben je Entnahmestelle (6 Punkte)

Formulieren Sie eine SQL-Abfrage, die **für jede Entnahmestelle** die Anzahl ihrer **freigegebenen Proben** ausgibt.

Auch Entnahmestellen, die keine freigegebenen Proben besitzen, müssen mit der Anzahl **0** im Ergebnis erscheinen.

Die Ausgabe soll die Spalten `name` und `anzahl_freigegeben` enthalten und alphabetisch aufsteigend nach `name` sortiert sein.


**Lösung**

```sql
SELECT s.name,
       COUNT(p.probe_id) AS anzahl_freigegeben
FROM stelle s
LEFT JOIN probe p
  ON p.stelle_id = s.stelle_id AND p.freigabe = TRUE
GROUP BY s.stelle_id, s.name
ORDER BY s.name ASC;
```

**Bewertung (6 Punkte)**

Erwartet: Nord 1, Süd 1, West 0. 

- Alle Entnahmestellen werden berücksichtigt, auch ohne freigegebene Proben (LEFT JOIN): 2 P.
- Ausschließlich freigegebene Proben werden gezählt: 1 P.
- Korrekte Aggregation mit Ergebnis 0 bei fehlenden freigegebenen Proben: 1 P.
- Korrekte Gruppierung nach Entnahmestelle: 1 P.
- Geforderte Spaltennamen und alphabetische Sortierung: 1 P.

Auch fachlich gleichwertige SQL-Abfragen sind zulässig, insbesondere Lösungen mit bedingter Aggregation.

### 4b – 7 Punkte

Formulieren Sie eine SQL-Abfrage, die je Analyseverfahren mit **mindestens zwei Ergebnissen** dessen Bezeichnung und den Durchschnitt der Messwerte liefert. Spalten: `bezeichnung`, `durchschnitt`. Sortieren Sie aufsteigend nach der Bezeichnung. (7 P.)

**Lösung**

```sql
SELECT v.bezeichnung, AVG(e.wert) AS durchschnitt
FROM verfahren v
JOIN ergebnis e ON e.verfahren_id = v.verfahren_id
GROUP BY v.verfahren_id, v.bezeichnung
HAVING COUNT(*) >= 2
ORDER BY v.bezeichnung ASC;
```

**Bewertung (7 Punkte)**

**Erwartetes Ergebnis:** Nitrat 30,00; pH 6,85. Das Verfahren Leitfähigkeit wird ausgeschlossen, da nur ein Ergebnis vorliegt.

- Korrekte Verknüpfung von Analyseverfahren und zugehörigen Ergebnissen: 1 P.
- Korrekte Berechnung des Durchschnitts mit `AVG()`: 1 P.
- Korrekte Gruppierung nach Analyseverfahren: 2 P.
- Korrekte Einschränkung auf Verfahren mit mindestens zwei Ergebnissen (z. B. `HAVING COUNT(*) >= 2`): 2 P.
- Geforderte Spaltennamen und aufsteigende Sortierung nach Bezeichnung: 1 P.

Auch fachlich gleichwertige SQL-Abfragen sind zulässig.

### 4c – 7 Punkte

Formulieren Sie eine SQL-Abfrage, die IDs **aller Proben ohne ein einziges nicht bestandenes Ergebnis** liefert, auch wenn eine Probe noch **gar kein Ergebnis** besitzt. Sortieren Sie nach ID aufsteigend. Verwenden Sie eine korrelierte Unterabfrage mit `NOT EXISTS`. (7 P.)

**Lösung**

```sql
SELECT p.probe_id
FROM probe p
WHERE NOT EXISTS (
    SELECT 1
    FROM ergebnis e
    WHERE e.probe_id = p.probe_id
      AND e.bestanden = FALSE
)
ORDER BY p.probe_id ASC;
```

**Bewertung (7 Punkte)**

**Erwartetes Ergebnis:** Proben 10, 12 und 14.

- **Probe 10:** Alle vorhandenen Analyseergebnisse sind bestanden.
- **Probe 12:** Alle vorhandenen Analyseergebnisse sind bestanden.
- **Probe 14:** Es liegen noch keine Analyseergebnisse vor. Die Bedingung `NOT EXISTS` ist deshalb ebenfalls erfüllt.

Die Proben 11 und 13 werden ausgeschlossen, da sie jeweils mindestens ein nicht bestandenes Analyseergebnis besitzen.

- Korrekte Auswahl der Proben-IDs aus der Tabelle `probe`: 1 P.
- Verwendung einer Unterabfrage mit `NOT EXISTS`: 2 P.
- Korrekte Korrelation über `e.probe_id = p.probe_id`: 2 P.
- Korrekte Einschränkung auf nicht bestandene Ergebnisse (`e.bestanden = FALSE`): 1 P.
- Aufsteigende Sortierung nach `probe_id`: 1 P.

Proben ohne Analyseergebnis müssen ebenfalls berücksichtigt werden.

Die Verwendung von `NOT EXISTS` ist ausdrücklich vorgegeben. Fachlich gleichwertige Formulierungen innerhalb dieser Vorgabe sind zulässig.

### 4d – Analyseergebnis aktualisieren (5 Punkte)

Bei der Probe mit der ID `11` wurde das Ergebnis des Analyseverfahrens **Nitrat** nach einer erfolgreichen Nachprüfung korrigiert.

Der Messwert soll auf `19.00` und das Kennzeichen `bestanden` auf `TRUE` gesetzt werden.

Formulieren Sie eine SQL-`UPDATE`-Anweisung, die **ausschließlich den betreffenden Ergebnisdatensatz** aktualisiert.

**Lösung**

```sql
UPDATE ergebnis
SET wert = 19.00,
    bestanden = TRUE
WHERE probe_id = 11 AND verfahren_id = 1;
```

**Bewertung (5 Punkte)**

- Korrekte `UPDATE`-Anweisung für die Tabelle `ergebnis`: 1 P.
- Korrekte Zuweisung `wert = 19.00`: 1 P.
- Korrekte Zuweisung `bestanden = TRUE`: 1 P.
- Korrekte Einschränkung auf `probe_id = 11`: 1 P.
- Korrekte Einschränkung auf `verfahren_id = 1` mit `AND`: 1 P.

**Erwartetes Ergebnis:** Ausschließlich der Datensatz mit dem zusammengesetzten Primärschlüssel `(11, 1)` wird aktualisiert.

Eine `UPDATE`-Anweisung ohne geeignete `WHERE`-Bedingung erfüllt die Anforderung nicht. Die übrigen fachlich korrekten Bestandteile können dennoch mit Teilpunkten bewertet werden.
