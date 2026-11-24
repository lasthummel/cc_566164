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

### 1c – 4 Punkte

Bestimmen Sie Laufzeit und zusätzlichen Speicherbedarf in Abhängigkeit von der Anzahl `n` der Messungen. Begründen Sie kurz.


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

### 2b – 7 Punkte

Schreiben Sie die vollständig korrigierte Methode mit derselben Signatur. Beachten Sie insbesondere die leere Liste.

### 2c – 6 Punkte

Formulieren Sie drei Testfälle mit Eingabe (Gültigkeitskennzeichen reichen) und erwartetem Rückgabewert. Prüfen Sie dabei die leere Liste, eine vollständig gültige Liste und eine Liste, bei der ausschließlich das letzte Element ungültig ist. (Je 2 Punkte.)


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

### 3b – Datenanomalien (6 Punkte)

Die Daten der Probenverwaltung werden zunächst in einer einzigen, nicht normalisierten Tabelle gespeichert:

`MESSDATEN(probe_id, entnahmestelle_id, entnahmestelle_name, verfahren_id, verfahren_name, wert)`

Jede Tabellenzeile repräsentiert eine Probe, die mit einem bestimmten Analyseverfahren untersucht wurde, einschließlich des zugehörigen Ergebnisses.

Der zusammengesetzte Primärschlüssel besteht aus `probe_id` und `verfahren_id`.

Nennen und erläutern Sie **drei unterschiedliche Datenanomalien**, die bei dieser Tabellenstruktur auftreten können.

Geben Sie für jede Anomalie ein konkretes Beispiel aus der Probenverwaltung an.

(Je 2 Punkte.)

### 3c – Abbildung einer n:m-Beziehung (4 Punkte)

Ein Entwickler schlägt vor, die Beziehung zwischen Proben und Analyseverfahren durch eine zusätzliche Fremdschlüsselspalte `verfahren_id` in der Tabelle `PROBE` abzubilden.

**Begründen Sie**, warum dieser Vorschlag die fachlichen Anforderungen nicht vollständig erfüllt.

**Erläutern Sie**, wie die Beziehung stattdessen mithilfe der Tabelle `ERGEBNIS` korrekt abgebildet wird.


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

### 4b – 7 Punkte

Formulieren Sie eine SQL-Abfrage, die je Analyseverfahren mit **mindestens zwei Ergebnissen** dessen Bezeichnung und den Durchschnitt der Messwerte liefert. Spalten: `bezeichnung`, `durchschnitt`. Sortieren Sie aufsteigend nach der Bezeichnung. (7 P.)

### 4c – 7 Punkte

Formulieren Sie eine SQL-Abfrage, die IDs **aller Proben ohne ein einziges nicht bestandenes Ergebnis** liefert, auch wenn eine Probe noch **gar kein Ergebnis** besitzt. Sortieren Sie nach ID aufsteigend. Verwenden Sie eine korrelierte Unterabfrage mit `NOT EXISTS`. (7 P.)

### 4d – Analyseergebnis aktualisieren (5 Punkte)

Bei der Probe mit der ID `11` wurde das Ergebnis des Analyseverfahrens **Nitrat** nach einer erfolgreichen Nachprüfung korrigiert.

Der Messwert soll auf `19.00` und das Kennzeichen `bestanden` auf `TRUE` gesetzt werden.

Formulieren Sie eine SQL-`UPDATE`-Anweisung, die **ausschließlich den betreffenden Ergebnisdatensatz** aktualisiert.
