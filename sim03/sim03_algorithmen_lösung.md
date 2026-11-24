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

**Lösung**

Eine vollständige Java-21-Lösung:


```java
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Position(String material, int meter) {}
record Fertigungsauftrag(int id, String status, List<Position> positionen) {}

public class Materialbedarf {
    public static Map<String, Integer> bedarf(List<Fertigungsauftrag> auftraege) {
        Map<String, Integer> ergebnis = new HashMap<>();
        for (Fertigungsauftrag a : auftraege) {
            if (!"FREIGEGEBEN".equals(a.status())) {
                continue;
            }
            for (Position p : a.positionen()) {
                ergebnis.merge(p.material(), p.meter(), Integer::sum);
            }
        }
        return ergebnis;
    }
}
```

Alternative Lösung mit Pseudocode:   

```text
Funktion bedarf(auftraege):
    ergebnis = neue leere Map

    Für jeden auftrag in auftraege:
        Wenn auftrag.status == "FREIGEGEBEN":
            Für jede position in auftrag.positionen:
                material = position.material
                menge = position.meter

                Wenn ergebnis enthält Schlüssel material:
                    ergebnis[material] =
                        ergebnis[material] + menge
                Sonst:
                    ergebnis[material] = menge

    Rückgabe ergebnis
```

**Bewertung**

Ergebnismap initialisieren (2); alle Aufträge durchlaufen (3); Status mit exakter Gleichheit beachten (3); alle Positionen ausgewählter Aufträge durchlaufen (3); Material als Schlüssel behandeln (2); Werte einschließlich mehrfach vorkommender Codes aufaddieren (4); korrekte Rückgabe/Leerfall (1). Höchstens 18 Punkte. Eine explizite `getOrDefault`-Lösung ist gleichwertig. Teilpunkte für sachlich korrekte Abschnitte auch bei einem einzelnen Folgefehler.

Java und Java-ähnlicher Pseudocode sind gleichwertig zu bewerten. Entscheidend sind die korrekte algorithmische Verarbeitung, die Einhaltung der fachlichen Anforderungen und eine eindeutig nachvollziehbare Darstellung. Die Verwendung der Java-Methode merge ist nicht erforderlich. Fachlich gleichwertige Lösungen, beispielsweise mit getOrDefault oder einer expliziten Schlüsselprüfung, sind zulässig.


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

**Lösung**

Die Ergebnis-Map enthält folgende Schlüssel-Wert-Paare:

```text
{
    BAUMWOLLE = 20,
    GARN = 8,
    REISSVERSCHLUSS = 4
}
```

**Berechnung:**

| Materialcode | Berechnung | Gesamtmenge |
|---|---|---:|
| BAUMWOLLE | 12 + 8 | 20 m |
| GARN | 3 + 5 | 8 m |
| REISSVERSCHLUSS | 4 | 4 m |

**Auswirkung des Auftragsstatus:**

Der Auftrag 11 hat den Status `ZURUECKGESTELLT` und wird daher vollständig von der Materialbedarfsberechnung ausgeschlossen.

Insbesondere werden die Positionen `GARN` mit 100 m und `REISSVERSCHLUSS` mit 2 m nicht berücksichtigt.

**Bewertung (8 Punkte):**

- BAUMWOLLE korrekt mit 20 m berechnet: 2 Punkte
- GARN korrekt mit 8 m berechnet: 2 Punkte
- REISSVERSCHLUSS korrekt mit 4 m berechnet: 2 Punkte
- Ausschluss des Auftrags 11 einschließlich seiner Materialmengen nachvollziehbar erläutert: 2 Punkte

Die Reihenfolge der Map-Einträge ist beliebig.

### 1c – 4 Punkte

Betrachten Sie den Algorithmus zur Materialbedarfsberechnung aus Teilaufgabe 1a.

Bestimmen Sie seine asymptotische erwartete Laufzeit und seinen zusätzlichen Speicherbedarf mithilfe der O-Notation.

Verwenden Sie folgende Größen:

- `a`: Anzahl aller Fertigungsaufträge
- `p`: Gesamtzahl aller Materialpositionen in freigegebenen Aufträgen
- `m`: Anzahl unterschiedlicher Materialcodes in freigegebenen Aufträgen

Gehen Sie davon aus, dass Einfüge-, Lese- und Aktualisierungsoperationen einer HashMap im Durchschnitt jeweils konstante Laufzeit benötigen.

Begründen Sie Ihre Ergebnisse kurz.

**Lösung**

**1. Asymptotische erwartete Laufzeit: `O(a + p)` (2 Punkte)**

Die äußere Schleife durchläuft alle `a` Fertigungsaufträge.

Für jeden freigegebenen Auftrag werden dessen Materialpositionen verarbeitet. Insgesamt werden dadurch `p` Positionen durchlaufen.

Für jede Position wird eine Map-Operation ausgeführt, deren durchschnittliche Laufzeit gemäß Aufgabenstellung konstant ist (`O(1)`).

Daraus ergibt sich: O(a+p)


**2. Zusätzlicher Speicherbedarf: `O(m)` (2 Punkte)**

Für die Berechnung wird eine Ergebnismap angelegt.

Diese enthält für jeden unterschiedlichen Materialcode aus freigegebenen Aufträgen genau einen Eintrag. Somit werden höchstens `m` Schlüssel-Wert-Paare gespeichert.

Die übrigen Hilfsvariablen benötigen nur konstanten zusätzlichen Speicher.

Daraus ergibt sich: O(m)

**Bewertung (4 Punkte):**

- Laufzeit `O(a + p)` korrekt angegeben und begründet: 2 Punkte
- Speicherbedarf `O(m)` korrekt angegeben und begründet: 2 Punkte

Fachlich gleichwertige asymptotische Darstellungen sind zulässig.


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

**Lösung**

**Fehler 1: Falscher logischer Operator `&&`**

Die beiden Bedingungen `menge < 0` und `menge > bestand` werden mit dem logischen UND (`&&`) verknüpft.

Da der Anfangsbestand laut Aufgabenstellung nicht negativ ist, können beide Bedingungen niemals gleichzeitig wahr sein. Deshalb wird keine ungültige Entnahme zurückgewiesen.

Die beiden Fehlerbedingungen müssen mit dem logischen ODER (`||`) verknüpft werden.

**Fehler 2: Nullmenge wird nicht zurückgewiesen**

Die Bedingung `menge < 0` erfasst nur negative Mengen.

Laut fachlicher Anforderung muss die Entnahmemenge jedoch größer als 0 sein. Deshalb muss auch der Wert 0 zurückgewiesen werden.

Die Bedingung muss `menge <= 0` lauten.

**Fehler 3: Addition statt Subtraktion**

Die Anweisung `bestand = bestand + menge` erhöht den Bestand, statt ihn zu verringern.

Die Berechnung muss durch eine Subtraktion erfolgen:

```java
bestand = bestand - menge;
```

**Bewertung (9 Punkte):**

Je Fehler:

- Fehlerursache korrekt identifiziert und erläutert: 1 Punkt
- Geeignetes konkretes Eingabebeispiel angegeben: 1 Punkt
- Tatsächliches und erwartetes Verhalten korrekt beschrieben: 1 Punkt

Insgesamt 9 Punkte.

**Bewertungshinweis:**

Die beiden Fehler im `if`-Ausdruck sind getrennt zu bewerten:

- Fehler 1 betrifft den logischen Verknüpfungsoperator `&&`.
- Fehler 2 betrifft den Vergleichsoperator `<`.


### 2b – 5 Punkte

Korrigieren Sie die drei in Teilaufgabe 2a identifizierten Fehler.

Schreiben Sie dazu die vollständig korrigierte Java-Methode `verbuche` mit unveränderter Methodensignatur.

Die Methode muss sämtliche fachlichen Anforderungen aus der Ausgangssituation erfüllen.

**Lösung**

```java
public static int verbuche(int bestand, int menge) {
    if (menge <= 0 || menge > bestand) {
        throw new IllegalArgumentException("Ungueltige Entnahmemenge");
    }
    return bestand - menge;
}
```

**Bewertung**

Null/negative Menge zurückweisen (2); Überentnahme zurückweisen (1); Exception vor Berechnung (1); Subtraktion/Rückgabe (1). Andere Texte der Exception sind zulässig.

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

**Lösung**

| Testfall | Eingabe `(bestand, menge)` | Erwartetes Ergebnis |
|---|---|---|
| Nullmenge | `(10, 0)` | `IllegalArgumentException` |
| Überentnahme | `(10, 11)` | `IllegalArgumentException` |
| Vollständige Entnahme | `(10, 10)` | Rückgabewert `0` |
| Reguläre Teilentnahme | `(10, 3)` | Rückgabewert `7` |

**Bewertung (8 Punkte):**

Je Testfall:

- Geeignete Eingabewerte für die geforderte Testkategorie: 1 Punkt
- Korrektes erwartetes Ergebnis (Rückgabewert oder Exception): 1 Punkt

Insgesamt 8 Punkte.

Andere geeignete Eingabewerte sind zulässig, sofern die jeweilige Testkategorie korrekt abgedeckt wird.

Bei unzulässigen Entnahmemengen muss eine `IllegalArgumentException` erwartet werden. Ein regulärer Rückgabewert ist in diesen Fällen nicht korrekt.


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

**Lösung**

Mögliches 3NF-Schema:


```text
Lieferant(lieferant_id PK, lieferant_name)
Lieferung(lieferung_id PK, lieferant_id FK -> Lieferant.lieferant_id, materialcode)
Charge(charge_id PK, artikelcode)
Verwendung(lieferung_id PK/FK -> Lieferung.lieferung_id,
           charge_id PK/FK -> Charge.charge_id, menge_meter)
```

- Eindeutigkeit der Paarung durch **zusammengesetzten Primärschlüssel** (`lieferung_id, charge_id`).
- Lieferung–Lieferant: jede Lieferung genau 1 Lieferant, Lieferant 0..* Lieferungen.
- Verwendung–Lieferung: jede Verwendung genau 1 Lieferung, Lieferung 0..* Verwendungen.
- Verwendung–Charge: jede Verwendung genau 1 Charge, Charge 0..* Verwendungen.
- Folglich Lieferung–Charge fachlich n:m, aufgelöst über Verwendung.

**Optional gibt es das Schema in der Datei 'sim03_algorithmen_lösung_3a.drawio'.** Aber für die Aufgabe reicht das kompakte Textschema.

**Bewertung**

vier Tabellen sinnvoll getrennt (3); alle Attribute korrekt zugeordnet (2); Einzel-PKs (2); drei FKs und Schlüssel der Beziehungstabelle (2); drei korrekte 1:n-Kardinalitäten (3); n:m-Bedeutung (1). Insgesamt 13. 

Alternative fachlich korrekte relationale Schemata sind zulässig. Insbesondere kann die Tabelle Verwendung einen eigenen Primärschlüssel besitzen, wenn die Eindeutigkeit der Kombination aus lieferung_id und charge_id zusätzlich durch eine UNIQUE-Bedingung sichergestellt wird.


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

**Lösung**

**1. Änderungsanomalie (2 Punkte)**

Der Lieferant mit der ID `5` ist in mehreren Zeilen mit dem Namen `Faserwerk` gespeichert.

Wird der Lieferant in `Faserwerk GmbH` umbenannt, müssen alle entsprechenden Zeilen aktualisiert werden.

Wird eine Zeile vergessen, stehen unterschiedliche Namen für denselben Lieferanten in der Tabelle. Dadurch entstehen widersprüchliche Daten.

**2. Einfügeanomalie (2 Punkte)**

Ein neuer Lieferant soll erfasst werden, obwohl er noch keine Materiallieferung bereitgestellt hat.

In der zusammengeführten Tabellenstruktur existiert kein eigenständiger Datensatz für Lieferanten.

Deshalb müsste der Lieferant zusammen mit nicht vorhandenen Lieferungs- und Verwendungsdaten gespeichert werden. Dafür wären `NULL`-Werte oder fiktive Angaben erforderlich, sofern eine solche Speicherung technisch überhaupt zugelassen ist.

Die unabhängige Speicherung von Lieferantenstammdaten ist somit nicht sauber möglich.

**3. Löschanomalie (2 Punkte)**

Die einzige Materialverwendung der Lieferung `81` für die Charge `101` wird gelöscht.

Dadurch verschwindet die Tabellenzeile mit der Lieferung `81` und dem Lieferanten `6` (`Garnhaus`).

Obwohl lediglich eine Materialverwendung entfernt werden sollte, gehen gleichzeitig Lieferungs- und Lieferantenstammdaten verloren.

**Bewertung (6 Punkte):**

Je Anomalie:

- Konkrete und fachlich passende Situation beschrieben: 1 Punkt
- Entstehendes Problem korrekt erläutert: 1 Punkt

Insgesamt 6 Punkte.

Andere fachlich korrekte Beispiele sind zulässig.

### 3c – 4 Punkte

Eine neue Materiallieferung wurde erfasst, aber bisher noch in keiner Produktionscharge verwendet.

Erläutern Sie anhand des normalisierten relationalen Schemas aus Teilaufgabe 3a, warum diese Lieferung trotzdem gespeichert werden kann, ohne einen fiktiven Datensatz in der Tabelle `Verwendung` anzulegen.

Gehen Sie dabei auf die beteiligten Tabellen und die Kardinalität ihrer Beziehung ein.

**Lösung**

Die Materiallieferungen werden in der eigenständigen Tabelle `Lieferung` gespeichert. Die tatsächlichen Materialverwendungen werden getrennt davon in der Tabelle `Verwendung` erfasst.

Zwischen diesen beiden Tabellen besteht eine 1:n-Beziehung.

Dabei gilt:

- Eine Lieferung kann mit **0 bis n Verwendungsdatensätzen** verbunden sein.
- Jeder Verwendungsdatensatz muss auf **genau eine vorhandene Lieferung** verweisen.

Da für eine Lieferung auch null Verwendungen zulässig sind, kann ein Datensatz in `Lieferung` existieren, ohne dass ein zugehöriger Datensatz in `Verwendung` vorhanden ist.

Es müssen deshalb weder fiktive Verwendungsdatensätze noch Platzhalter für nicht vorhandene Verwendungen angelegt werden.

**Bewertung (4 Punkte):**

| Bewertungskriterium | Punkte |
|---|---:|
| Eigenständige Speicherung der Lieferungen in der Tabelle `Lieferung` erläutert | 1 |
| Separate Speicherung der Verwendungen in `Verwendung` erläutert | 1 |
| Kardinalität korrekt beschrieben: eine Lieferung kann 0 bis n Verwendungen besitzen | 1 |
| Schlussfolgerung korrekt: keine fiktive Verwendung erforderlich | 1 |
| **Gesamt** | **4** |

Sinngemäß gleichwertige Erläuterungen sind zulässig.


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

**Lösung**

```sql
SELECT
    l.lieferant_id,
    l.name,
    COUNT(v.lieferung_id) AS anzahl_lieferungen
FROM lieferant l
LEFT JOIN lieferung v
    ON v.lieferant_id = l.lieferant_id
GROUP BY l.lieferant_id, l.name
ORDER BY l.lieferant_id ASC;
```

**Erwartetes Ergebnis:**

| lieferant_id | name | anzahl_lieferungen |
|---:|---|---:|
| 5 | Faserwerk | 2 |
| 6 | Garnhaus | 1 |
| 7 | Stoffmarkt | 1 |
| 8 | TextilNord | 0 |

**Bewertung (6 Punkte):**

| Kriterium | Punkte |
|---|---:|
| Richtige Tabellen und korrekte Verknüpfung mit `LEFT JOIN` | 2 |
| `COUNT` auf einer geeigneten Spalte der Tabelle `lieferung`, sodass Lieferanten ohne Lieferung den Wert 0 erhalten | 2 |
| Korrekte Gruppierung nach Lieferanten | 1 |
| Richtige Ausgabespalten einschließlich Alias und aufsteigender Sortierung | 1 |
| **Gesamt** | **6** |

**Bewertungshinweise:**

- `COUNT(*)` ist bei einem `LEFT JOIN` nicht gleichwertig, da Lieferanten ohne Lieferung dadurch fälschlicherweise mit 1 gezählt werden.
- Ein `INNER JOIN` erfüllt die Anforderungen nicht, weil Lieferanten ohne Lieferung fehlen würden.
- Fachlich gleichwertige Alternativen, beispielsweise mit einer korrelierten Unterabfrage, sind ebenfalls zulässig.

### 4b – 7 Punkte

Schreiben Sie eine SQL-Abfrage, die alle Produktionschargen ermittelt, in denen Material aus **mindestens zwei unterschiedlichen Materiallieferungen** verwendet wurde.

Geben Sie für jede dieser Chargen folgende Spalten aus:

- `charge_id` – ID der Produktionscharge
- `anzahl_lieferungen` – Anzahl der unterschiedlichen Materiallieferungen, die in der Charge verwendet wurden
- `gesamt_meter` – Summe aller in dieser Charge verwendeten Materialmengen in Metern

Sortieren Sie das Ergebnis aufsteigend nach `charge_id`.

Geben Sie außerdem die vollständige erwartete Ergebnistabelle anhand der bereitgestellten Testdaten an.

**Lösung**

```sql
SELECT
    v.charge_id,
    COUNT(DISTINCT v.lieferung_id) AS anzahl_lieferungen,
    SUM(v.menge_meter) AS gesamt_meter
FROM verwendung v
GROUP BY v.charge_id
HAVING COUNT(DISTINCT v.lieferung_id) >= 2
ORDER BY v.charge_id ASC;
```

**Erwartetes Ergebnis:**

| charge_id | anzahl_lieferungen | gesamt_meter |
|---:|---:|---:|
| 101 | 3 | 33 |

**Erläuterung:**

In der Charge `101` wurden Materialien aus den Lieferungen `80`, `81` und `82` verwendet.

Die insgesamt verwendete Materialmenge beträgt `20 + 5 + 8 = 33` Meter.

Die Chargen `100` und `102` enthalten jeweils nur Material aus einer Lieferung. Charge `103` enthält keine Verwendung. Diese Chargen erfüllen die Bedingung nicht und erscheinen deshalb nicht im Ergebnis.

**Bewertung (7 Punkte):**

| Bewertungskriterium | Punkte |
|---|---:|
| Korrekte Gruppierung nach `charge_id` | 1 |
| Anzahl unterschiedlicher Lieferungen korrekt mit `COUNT(DISTINCT ...)` oder gleichwertig ermittelt | 2 |
| Gesamtmenge korrekt mit `SUM(menge_meter)` berechnet | 1 |
| Korrekte Filterbedingung mit `HAVING` | 1 |
| Aufsteigende Sortierung nach `charge_id` | 1 |
| Vollständige und korrekte Ergebnistabelle | 1 |
| **Gesamt** | **7** |

**Bewertungshinweise:**

Da `(lieferung_id, charge_id)` der zusammengesetzte Primärschlüssel der Tabelle `verwendung` ist, kann innerhalb einer Charge jede Lieferung höchstens einmal vorkommen.

Deshalb ist `COUNT(*)` in dieser Abfrage gleichwertig zu `COUNT(DISTINCT lieferung_id)`.

Ein zusätzlicher Join mit der Tabelle `charge` ist nicht erforderlich, aber bei korrekter Verknüpfung zulässig.

Fachlich gleichwertige SQL-Abfragen sind anzuerkennen.


### 4c – 6 Punkte

Schreiben Sie eine SQL-Abfrage mit **`NOT EXISTS`**, die alle Materiallieferungen ermittelt, die **in keiner gesperrten Produktionscharge verwendet werden**.

Berücksichtigen Sie dabei auch Materiallieferungen, die bisher keiner Produktionscharge zugeordnet wurden.

Geben Sie folgende Spalten aus:

- `lieferung_id` – ID der Materiallieferung
- `materialcode` – Materialcode der Lieferung

Sortieren Sie das Ergebnis aufsteigend nach `lieferung_id`.

Geben Sie außerdem die vollständige erwartete Ergebnistabelle anhand der bereitgestellten Testdaten an.

**Lösung**

```sql
SELECT
    l.lieferung_id,
    l.materialcode
FROM lieferung l
WHERE NOT EXISTS (
    SELECT 1
    FROM verwendung v
    JOIN charge c
        ON c.charge_id = v.charge_id
    WHERE v.lieferung_id = l.lieferung_id
      AND c.gesperrt = TRUE
)
ORDER BY l.lieferung_id ASC;
```

**Erwartetes Ergebnis:**

| lieferung_id | materialcode |
|---:|---|
| 83 | FUTTER |

**Erläuterung:**

Die Lieferungen `80`, `81` und `82` werden jeweils in der gesperrten Charge `101` verwendet. Sie dürfen deshalb nicht im Ergebnis erscheinen.

Die Lieferung `83` ist bisher keiner Produktionscharge zugeordnet. Daher existiert insbesondere keine Verwendung in einer gesperrten Charge.

Die Unterabfrage liefert für diese Lieferung keine Zeile. Die Bedingung `NOT EXISTS` ist somit erfüllt.

**Bewertung (6 Punkte):**

| Bewertungskriterium | Punkte |
|---|---:|
| Korrekte Korrelation zwischen äußerer Abfrage und Unterabfrage über `lieferung_id` | 2 |
| `NOT EXISTS` korrekt verwendet | 2 |
| Richtige Verknüpfung mit `charge` und Filterung nach gesperrten Chargen | 1 |
| Korrekte Ausgabespalten, Sortierung und vollständiges Ergebnis | 1 |
| **Gesamt** | **6** |

**Bewertungshinweise:**

- `NOT EXISTS` muss auf die jeweilige Lieferung bezogen sein.
- Lieferungen ohne Verwendungsdatensatz müssen ebenfalls berücksichtigt werden.
- Eine Lieferung mit mindestens einer Verwendung in einer gesperrten Charge darf nicht ausgegeben werden, auch wenn sie zusätzlich in nicht gesperrten Chargen verwendet wird.
- `c.gesperrt` ist als Alternative zu `c.gesperrt = TRUE` zulässig.
- Fachlich gleichwertige Varianten der Unterabfrage sind anzuerkennen, solange die geforderte Verwendung von `NOT EXISTS` eingehalten wird.

### 4d – 6 Punkte

Bei einer Qualitätskontrolle wurde festgestellt, dass die Materiallieferung mit der ID `82` möglicherweise fehlerhaft ist.

Aus Sicherheitsgründen sollen alle Produktionschargen gesperrt werden, in denen Material aus dieser Lieferung verwendet wurde.

Schreiben Sie eine SQL-`UPDATE`-Anweisung, die folgende Anforderungen erfüllt:

- Setzen Sie das Attribut `gesperrt` für die betroffenen Produktionschargen auf `TRUE`.
- Ermitteln Sie die betroffenen Produktionschargen mithilfe einer `SELECT`-Unterabfrage auf die Tabelle `verwendung`.
- Alle anderen Produktionschargen müssen unverändert bleiben.

Geben Sie außerdem an, welche Produktionschargen nach der Ausführung der Anweisung gesperrt sind.


**Lösung**

```sql
UPDATE charge
SET gesperrt = TRUE
WHERE charge_id IN (
    SELECT charge_id
    FROM verwendung
    WHERE lieferung_id = 82
);
```

**Erläuterung:**

Die `SELECT`-Unterabfrage ermittelt zunächst alle Produktionschargen, in denen Material aus der Lieferung `82` verwendet wurde:

```sql
SELECT charge_id
FROM verwendung
WHERE lieferung_id = 82;
```

Das Ergebnis enthält die Chargen `102` und `101`.

Die äußere `UPDATE`-Anweisung setzt anschließend das Attribut `gesperrt` für diese beiden Chargen auf `TRUE`.

**Ergebnis nach der Aktualisierung:**

| charge_id | gesperrt vorher | gesperrt nachher |
|---:|---|---|
| 100 | FALSE | FALSE |
| 101 | TRUE | TRUE |
| 102 | FALSE | TRUE |
| 103 | FALSE | FALSE |

Nach Ausführung der Anweisung sind die Produktionschargen `101` und `102` gesperrt.

Die Charge `101` war bereits gesperrt. Ihr Sperrstatus bleibt unverändert.

**Bewertung (6 Punkte):**

| Bewertungskriterium | Punkte |
|---|---:|
| `UPDATE` auf der richtigen Tabelle `charge` | 1 |
| Korrekte Aktualisierung mit `SET gesperrt = TRUE` | 1 |
| `SELECT`-Unterabfrage auf der Tabelle `verwendung` | 1 |
| Richtige Einschränkung auf `lieferung_id = 82` | 1 |
| Korrekte Verknüpfung der Abfrageergebnisse mit `WHERE charge_id IN (...)` | 1 |
| Beide nach der Aktualisierung gesperrten Chargen (`101` und `102`) korrekt angegeben | 1 |
| **Gesamt** | **6** |

**Bewertungshinweise:**

- Die Lösung verwendet ausschließlich standardisierte SQL-Konstrukte.
- Eine fachlich gleichwertige Lösung mit einer korrelierten `EXISTS`-Unterabfrage ist ebenfalls zulässig.
- Die Chargen `100` und `103` dürfen nicht aktualisiert werden.
- Charge `101` ist bereits gesperrt und bleibt gesperrt.
- Charge `102` wird durch die Anweisung zusätzlich gesperrt.
- Eine `UPDATE`-Anweisung ohne die geforderte `SELECT`-Unterabfrage erfüllt die Aufgabenstellung nicht vollständig.
