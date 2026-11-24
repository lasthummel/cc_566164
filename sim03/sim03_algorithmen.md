# Prüfungssimulation sim03 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100 Punkte  
**Aufgaben:** 4 Hauptaufgaben. 

- Java 21
- PostgreSQL 18; 
- Alle nicht anders bezeichneten Mengenangaben sind ganze Zahlen.

## Ausgangssituation

Die TextilKreis GmbH entwickelt für **FadenSpur** eine Funktion zur Materialbedarfsplanung. Freigegebene Produktionsaufträge benötigen definierte Mengen an Materialien; zurückgestellte Aufträge dürfen nicht berücksichtigt werden. Zusätzlich werden Chargenverwendungen in einer relationalen Datenbank gespeichert. Die Aufgaben 1 bis 4 bearbeiten unterschiedliche Teilsysteme desselben Projekts.

## Aufgabe 1: Materialbedarfsplanung in Java (30 Punkte)

Ein Auftrag enthält mehrere Materialpositionen. Je Position werden ein Materialcode und die benötigte Menge in Metern gespeichert. Mehrere Positionen desselben Materials können innerhalb eines Auftrags oder über Aufträge hinweg vorkommen. Ein Auftrag zählt nur, wenn sein Status genau `FREIGEGEBEN` lautet.

Die Java-21-Datentypen sind vollständig vorgegeben:

```java
import java.util.List;
import java.util.Map;

class Position {
    private String material;
    private int meter;

    Position (String material, int meter) {
        this.material = material;
        this.meter = meter;
    }

    String material() { return material; }
    int meter() { return meter; }
}

class Fertigungsauftrag {
    private int id;
    private String status;
    private List<Position> positionen;

    Fertigungsauftrag (int id, String status, List<Position> positionen) {
        this.id = id;
        this.status = status;
        this.positionen = positionen;
    }

    int id() { return id; }
    String status() { return status; }
    List<Position> positionen() { return positionen; }
}
```

Die übergebene Liste `auftraege`, ihre Elemente, Statuswerte, Materialcodes und Positionslisten sind niemals `null`; alle Materialcodes sind nicht leer, `meter > 0`. Die Liste kann leer sein. Materialcodes sind case-sensitiv. Die Summe je Material überschreitet nicht den Wertebereich von `int`. Die Eingabelisten dürfen nicht verändert werden.

### 1a – 18 Punkte

Entwickeln Sie einen Algorithmus zur Ermittlung des gesamten Materialbedarfs aus einer Liste von Fertigungsaufträgen.

Die Methode hat folgende vorgegebene Signatur:

```java
public static Map<String, Integer> bedarf(List<Fertigungsauftrag> auftraege)
```

Für jedes Material, das in mindestens einem freigegebenen Auftrag enthalten ist, soll die Gesamtmenge in Metern ermittelt werden.

Beachten Sie folgende Anforderungen:

- Berücksichtigen Sie ausschließlich Aufträge mit dem Status `FREIGEGEBEN`.
- Addieren Sie die Mengen aller Positionen mit demselben Materialcode, auch wenn diese in unterschiedlichen Aufträgen vorkommen.
- Materialien aus nicht freigegebenen Aufträgen dürfen nicht im Ergebnis enthalten sein.
- Falls keine geeignete Materialposition existiert, ist eine leere Map zurückzugeben.
- Die übergebenen Eingabelisten dürfen nicht verändert werden.

**Hinweise zu Maps und zur Methode `merge`:**

Eine `Map` ist eine Datenstruktur, die Werte über eindeutige Schlüssel verwaltet. Im vorliegenden Fall ist der Materialcode (`String`) der Schlüssel und die gesamte benötigte Materialmenge (`Integer`) der zugehörige Wert.

In Java kann die Methode `merge` verwendet werden, um Mengen je Materialcode aufzusummieren:

```java
ergebnis.merge(materialcode, menge, Integer::sum);
```

- Ist der Materialcode noch nicht in der Map vorhanden, wird er mit der angegebenen Menge eingefügt.
- Ist der Materialcode bereits vorhanden, wird seine bisherige Menge um die neue Menge erhöht.

Sie dürfen `merge` verwenden, können die Addition aber auch durch eigene Anweisungen realisieren.

**Bearbeitungshinweis:**

Stellen Sie den Algorithmus wahlweise in **Java oder in Java-ähnlichem Pseudocode** dar. Eine vollständig kompilierbare Java-Implementierung ist nicht erforderlich. Die Verarbeitungsschritte, Bedingungen, Schleifen und die Ermittlung der Ergebnismengen müssen eindeutig nachvollziehbar sein.

Verwenden Sie die vorgegebenen Datenstrukturen. Es dürfen keine zusätzlichen externen Bibliotheken vorausgesetzt werden.

### 1b – 8 Punkte

Gegeben sind die folgenden Fertigungsaufträge:

| Auftrag-ID | Status | Materialpositionen |
|---:|---|---|
| 10 | FREIGEGEBEN | (BAUMWOLLE, 12), (GARN, 3), (BAUMWOLLE, 8) |
| 11 | ZURUECKGESTELLT | (GARN, 100), (REISSVERSCHLUSS, 2) |
| 12 | FREIGEGEBEN | (GARN, 5), (REISSVERSCHLUSS, 4) |

Ermitteln Sie die vollständige Ergebnis-Map, die der in Teilaufgabe 1a beschriebene Algorithmus für diese Eingabedaten liefern soll.

Geben Sie für jeden Materialcode die berechnete Gesamtmenge in Metern an.

Erläutern Sie außerdem, welche Auswirkungen der Status `ZURUECKGESTELLT` des Auftrags 11 auf das Ergebnis hat.

### 1c – 4 Punkte

Betrachten Sie den Algorithmus zur Materialbedarfsberechnung aus Teilaufgabe 1a.

Bestimmen Sie seine asymptotische erwartete Laufzeit und seinen zusätzlichen Speicherbedarf mithilfe der O-Notation.

Verwenden Sie folgende Größen:

- `a`: Anzahl aller Fertigungsaufträge
- `p`: Gesamtzahl aller Materialpositionen in freigegebenen Aufträgen
- `m`: Anzahl unterschiedlicher Materialcodes in freigegebenen Aufträgen

Gehen Sie davon aus, dass Einfüge-, Lese- und Aktualisierungsoperationen einer HashMap im Durchschnitt jeweils konstante Laufzeit benötigen.

Begründen Sie Ihre Ergebnisse kurz.


## Aufgabe 2: Fehleranalyse und Testdaten (22 Punkte)

Die App soll den Lagerbestand einer Lieferung nach einer Verbrauchsmeldung verringern. Eine Buchung ist zulässig, wenn die Menge positiv ist und den aktuellen Bestand nicht überschreitet. Bei unzulässiger Menge muss eine `IllegalArgumentException` geworfen werden; der Bestand darf sich nicht ändern. Bei zulässiger Menge wird der neue Bestand zurückgegeben. Anfangsbestand ist nicht negativ; während dieses Aufrufs gibt es keine parallelen Änderungen.

Der folgende Vorschlag enthält drei voneinander unabhängige Fehler:

```java
public static int verbuche(int bestand, int menge) {
    if (menge < 0 && menge > bestand) {
         throw new IllegalArgumentException();  
    }
    bestand = bestand + menge;
    return bestand;
}
```

### 2a – 9 Punkte

Der vorgegebene Java-Code enthält drei Fehler, die zu einer Verletzung der fachlichen Anforderungen führen.

Identifizieren Sie die drei Fehler und erläutern Sie jeweils:

- Warum die betreffende Anweisung fehlerhaft ist.
- Wie sich der Fehler anhand eines konkreten Eingabebeispiels auswirkt.

Geben Sie für jedes Beispiel die Eingabewerte `bestand` und `menge` sowie das tatsächliche und das erwartete Verhalten der Methode an.

(Je 3 Punkte.)

### 2b – 5 Punkte

Korrigieren Sie die drei in Teilaufgabe 2a identifizierten Fehler.

Schreiben Sie dazu die vollständig korrigierte Java-Methode `verbuche` mit unveränderter Methodensignatur.

Die Methode muss sämtliche fachlichen Anforderungen aus der Ausgangssituation erfüllen.

### 2c – 8 Punkte

Entwickeln Sie vier Testfälle, mit denen die korrekte Funktionsweise der in Teilaufgabe 2b geforderten Methode `verbuche` überprüft werden kann.

Berücksichtigen Sie dabei die folgenden Fälle:

- Eine Entnahme mit der Menge 0.
- Eine Entnahme, deren Menge den vorhandenen Bestand überschreitet.
- Eine Entnahme, deren Menge genau dem vorhandenen Bestand entspricht.
- Eine reguläre Teilentnahme.

Geben Sie für jeden Testfall die Eingabewerte `bestand` und `menge` sowie das erwartete Ergebnis an.

Das erwartete Ergebnis ist entweder der zurückgegebene neue Bestand oder die auszulösende Exception.

(Je 2 Punkte.)


## Aufgabe 3: Datenmodell und Normalisierung (23 Punkte)

Für die Rückverfolgbarkeit gelten folgende fachliche Regeln:

- Eine **Materiallieferung** gehört zu genau einem **Lieferanten**; ein Lieferant kann keine, eine oder mehrere Lieferungen bereitstellen.
- Eine **Produktionscharge** kann Material aus mehreren Lieferungen verwenden; eine Lieferung kann in mehreren Produktionschargen verwendet werden.
- Je Kombination aus Materiallieferung und Produktionscharge wird die gesamte verwendete Menge in Metern gespeichert. Jede Kombination darf nur einmal vorkommen.
- Ein Materialcode wird in der Lieferung gespeichert. Ein Lieferant besitzt ID und Namen. Eine Charge besitzt ID und Artikelcode.

Vorgegebene fachliche Attribute: `lieferant_id`, `lieferant_name`, `lieferung_id`, `materialcode`, `charge_id`, `artikelcode`, `menge_meter`.

### 3a – 13 Punkte

Leiten Sie aus den beschriebenen fachlichen Anforderungen ein relationales Datenbankschema in der **dritten Normalform (3NF)** ab.

Geben Sie für jede Tabelle an:

- Tabellenname und Attribute
- Primärschlüssel (PK)
- Fremdschlüssel (FK) einschließlich der referenzierten Tabellen

Erläutern Sie außerdem:

- Wie die n:m-Beziehung zwischen Materiallieferungen und Produktionschargen im relationalen Schema umgesetzt wird.
- Wie sichergestellt wird, dass jede Kombination aus Materiallieferung und Produktionscharge nur einmal gespeichert werden kann.

Geben Sie abschließend die Kardinalitäten sämtlicher Beziehungen an.

Eine kompakte Darstellung in relationaler Notation ist ausreichend. Vollständige SQL-Anweisungen sind nicht erforderlich.

(Schema: 9 Punkte; Beziehungen und Kardinalitäten: 4 Punkte.)

### 3b – 6 Punkte

In einer Importdatei werden Informationen über Lieferanten, Materiallieferungen und Produktionschargen in einer einzigen Tabelle gespeichert:

| lieferung_id | lieferant_id | lieferant_name | charge_id | artikelcode | menge_meter |
|---:|---:|---|---:|---|---:|
| 80 | 5 | Faserwerk | 100 | JACK-01 | 15 |
| 80 | 5 | Faserwerk | 101 | HOSE-04 | 20 |
| 81 | 6 | Garnhaus | 101 | HOSE-04 | 5 |

Die dargestellte Tabellenstruktur soll dauerhaft zur Speicherung dieser Informationen verwendet werden.

Erläutern Sie anhand der Daten jeweils eine mögliche:

- Änderungsanomalie
- Einfügeanomalie
- Löschanomalie

Beschreiben Sie für jede Anomalie eine konkrete Situation und erläutern Sie, welches Problem dadurch entsteht.

(Je 2 Punkte.)

### 3c – 4 Punkte

Eine neue Materiallieferung wurde erfasst, aber bisher noch in keiner Produktionscharge verwendet.

Erläutern Sie anhand des normalisierten relationalen Schemas aus Teilaufgabe 3a, warum diese Lieferung trotzdem gespeichert werden kann, ohne einen fiktiven Datensatz in der Tabelle `Verwendung` anzulegen.

Gehen Sie dabei auf die beteiligten Tabellen und die Kardinalität ihrer Beziehung ein.


## Aufgabe 4: SQL-Datenhaltung (25 Punkte)

Es gilt dieses **vollständige PostgreSQL-18-Schema**, unabhängig von Ihrer Lösung zu Aufgabe 3:

```sql
CREATE TABLE lieferant (
  lieferant_id INTEGER PRIMARY KEY,
  name VARCHAR(60) NOT NULL
);
CREATE TABLE lieferung (
  lieferung_id INTEGER PRIMARY KEY,
  lieferant_id INTEGER NOT NULL REFERENCES lieferant(lieferant_id),
  materialcode VARCHAR(30) NOT NULL
);
CREATE TABLE charge (
  charge_id INTEGER PRIMARY KEY,
  artikelcode VARCHAR(30) NOT NULL,
  gesperrt BOOLEAN NOT NULL DEFAULT FALSE
);
CREATE TABLE verwendung (
  lieferung_id INTEGER NOT NULL REFERENCES lieferung(lieferung_id),
  charge_id INTEGER NOT NULL REFERENCES charge(charge_id),
  menge_meter INTEGER NOT NULL CHECK (menge_meter > 0),
  PRIMARY KEY (lieferung_id, charge_id)
);
```

**lieferant**

| lieferant_id | name |
|---:|---|
| 5 | Faserwerk |
| 6 | Garnhaus |
| 7 | Stoffmarkt |
| 8 | TextilNord |

**lieferung**

| lieferung_id | lieferant_id | materialcode |
|---:|---:|---|
| 80 | 5 | BAUMWOLLE |
| 81 | 6 | GARN |
| 82 | 5 | BAUMWOLLE |
| 83 | 7 | FUTTER |

**charge**

| charge_id | artikelcode | gesperrt |
|---:|---|---|
| 100 | JACK-01 | false |
| 101 | HOSE-04 | true |
| 102 | JACK-02 | false |
| 103 | WESTE-01 | false |

**verwendung**

| lieferung_id | charge_id | menge_meter |
|---:|---:|---:|
| 80 | 100 | 15 |
| 80 | 101 | 20 |
| 81 | 101 | 5 |
| 82 | 102 | 10 |
| 82 | 101 | 8 |

### 4a – 6 Punkte

Schreiben Sie eine SQL-Abfrage, die für **jeden Lieferanten** folgende Spalten ausgibt:

- `lieferant_id`
- `name`
- `anzahl_lieferungen` – Anzahl der diesem Lieferanten zugeordneten Materiallieferungen

Berücksichtigen Sie auch Lieferanten, denen bisher keine Materiallieferung zugeordnet ist. Für diese Lieferanten muss `anzahl_lieferungen` den Wert `0` enthalten.

Sortieren Sie das Ergebnis aufsteigend nach `lieferant_id`.

### 4b – 7 Punkte

Schreiben Sie eine SQL-Abfrage, die alle Produktionschargen ermittelt, in denen Material aus **mindestens zwei unterschiedlichen Materiallieferungen** verwendet wurde.

Geben Sie für jede dieser Chargen folgende Spalten aus:

- `charge_id` – ID der Produktionscharge
- `anzahl_lieferungen` – Anzahl der unterschiedlichen Materiallieferungen, die in der Charge verwendet wurden
- `gesamt_meter` – Summe aller in dieser Charge verwendeten Materialmengen in Metern

Sortieren Sie das Ergebnis aufsteigend nach `charge_id`.

Geben Sie außerdem die vollständige erwartete Ergebnistabelle anhand der bereitgestellten Testdaten an.

### 4c – 6 Punkte

Schreiben Sie eine SQL-Abfrage mit **`NOT EXISTS`**, die alle Materiallieferungen ermittelt, die **in keiner gesperrten Produktionscharge verwendet werden**.

Berücksichtigen Sie dabei auch Materiallieferungen, die bisher keiner Produktionscharge zugeordnet wurden.

Geben Sie folgende Spalten aus:

- `lieferung_id` – ID der Materiallieferung
- `materialcode` – Materialcode der Lieferung

Sortieren Sie das Ergebnis aufsteigend nach `lieferung_id`.

Geben Sie außerdem die vollständige erwartete Ergebnistabelle anhand der bereitgestellten Testdaten an.

### 4d – 6 Punkte

Bei einer Qualitätskontrolle wurde festgestellt, dass die Materiallieferung mit der ID `82` möglicherweise fehlerhaft ist.

Aus Sicherheitsgründen sollen alle Produktionschargen gesperrt werden, in denen Material aus dieser Lieferung verwendet wurde.

Schreiben Sie eine SQL-`UPDATE`-Anweisung, die folgende Anforderungen erfüllt:

- Setzen Sie das Attribut `gesperrt` für die betroffenen Produktionschargen auf `TRUE`.
- Ermitteln Sie die betroffenen Produktionschargen mithilfe einer `SELECT`-Unterabfrage auf die Tabelle `verwendung`.
- Alle anderen Produktionschargen müssen unverändert bleiben.

Geben Sie außerdem an, welche Produktionschargen nach der Ausführung der Anweisung gesperrt sind.
