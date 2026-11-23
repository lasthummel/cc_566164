# Prüfungssimulation sim01 – Entwicklung und Umsetzung von Algorithmen

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100  
**Aufgaben:** 4

- Java-Version: 21. 
- SQL-Dialekt: PostgreSQL 18.

## Ausgangssituation

Die **NordLog GmbH** transportiert temperaturempfindliche Produkte. Ein neues Auswertungssystem verarbeitet Messwerte aus Transportbehältern. Für jeden Behälter gelten individuelle Temperaturgrenzen. Die Daten kommen in zeitlicher Reihenfolge an, können aber ungültige Sensormessungen enthalten. Die Software soll Temperaturwarnungen auslösen, die Datenhaltung prüfen und Versanddaten auswerten.

## Aufgabe 1: Warnfenster programmieren (30 Punkte)

Ein Transportbehälter gilt als kritisch, wenn innerhalb seiner Messreihe mindestens drei gültige Grenzwertverletzungen unmittelbar aufeinanderfolgen. Eine ungültige Messung unterbricht die Folge. Eine gültige Messung innerhalb der Grenzen unterbricht sie ebenfalls. Messungen genau auf einer Grenze sind zulässig. Für die Bewertung spielt die Zeitspanne zwischen Messungen keine Rolle.

Gegeben sind folgende Java-Klassen (Konstruktoren sind vorhanden)

```java
import java.time.LocalDateTime;
import java.util.List;

class Messung {
    private LocalDateTime zeitpunkt;
    private double temperatur;
    private boolean gueltig;

    // Konstruktor ist vorhanden

    public LocalDateTime zeitpunkt() { return zeitpunkt; }
    public double temperatur() { return temperatur; }
    public boolean gueltig() { return gueltig; }
}

class Behaelter {
    private String nummer;
    private double minimum;
    private double maximum;
    private List<Messung> messungen;
    
    // Konstruktor ist vorhanden

    public String nummer() { return nummer; }
    public double minimum() { return minimum; }
    public double maximum() { return maximum; }
    public List<Messung> messungen() { return messungen; }
    
}
```

Für die Implementierungen gelten folgende Voraussetzungen:

- Der übergebene Behälter ist niemals `null`.
- Die Liste der Messungen und ihre Elemente sind niemals `null`; die Liste kann jedoch leer sein.
- Die Messungen stehen zeitlich aufsteigend und enthalten keine doppelten Zeitpunkte.
- Für alle Behälter gilt `minimum <= maximum`.
- Die Temperaturgrenzen und die Temperaturen gültiger Messungen sind endliche `double`-Werte.
- Die Liste der Messungen darf nicht verändert werden.


### 1a – 18 Punkte.

Implementieren Sie die Methode vollständig in Java:

```java
public static boolean istKritisch(Behaelter b)
```

Geben Sie true zurück, sobald drei gültige Messungen hintereinander eine Temperatur außerhalb des inklusiven Intervalls [minimum, maximum] haben; andernfalls false. 

Schreiben Sie die vollständige Methode, einschließlich der notwendigen Kontrollstrukturen.

**Lösung**  

```java
public static boolean istKritisch(Behaelter b) {
    int folge = 0;
    for (Messung m : b.messungen()) {
        if (!m.gueltig()) {
            folge = 0;
            continue;
        }
        boolean verletzt = m.temperatur() < b.minimum()
                         || m.temperatur() > b.maximum();
        if (verletzt) {
            folge++;
            if (folge >= 3) {
                return true;
            }
        } else {
            folge = 0;
        }
    }
    return false;
}
```

**Punkteverteilung:** vollständige Iteration (3), gültige Messungen korrekt identifizieren (3), Invalid-Messungen setzen Folge zurück (3), exklusive Überschreitung trotz inklusiver Grenzwerte (3), Zähler bei normalem Wert zurücksetzen (2), Schwellwertprüfung und frühzeitiges true (2), korrektes false bei leerer/unkritischer Liste (2). Eine andere korrekt implementierte Kontrollstruktur ist zulässig. Keine Pflicht zu Zeitdifferenzprüfungen.


### 1b – 6 Punkte.

Bestimmen Sie das Ergebnis der Methode für die folgenden zwei Messreihen. Die Grenzen sind 2,0 °C und 8,0 °C. `u` steht für ungültig, `g` für gültig. Begründen Sie jede Antwort anhand des Verlaufs des Zählers.

| Reihe | Messungen in Reihenfolge |
|---|---|
| A | (9,g), (10,g), (7,g), (9,g), (10,g), (11,g) |
| B | (9,g), (10,g), (12,u), (9,g), (10,g), (8,g) |

**Lösung**  

- **A:** Zählerverlauf 1, 2, 0, 1, 2, 3 -> **true**. (3 Punkte: 2 Verlauf, 1 Ergebnis)
- **B:** Zählerverlauf 1, 2, 0 [ungültig], 1, 2, 0 [Grenze 8] -> **false**. (3 Punkte entsprechend)


### 1c – 6 Punkte

Bestimmen Sie für die Methode `istKritisch` aus Teilaufgabe 1a:

- die asymptotische Laufzeitkomplexität im schlechtesten Fall (Worst-Case),
- die asymptotische zusätzliche Speicherkomplexität.

Verwenden Sie die O-Notation und begründen Sie Ihre Antworten kurz.

Dabei bezeichnet `n` die Anzahl der Messungen in der Liste.

**Lösung**  

**Laufzeitkomplexität: O(n)**

Im schlechtesten Fall werden alle `n` Messungen durchlaufen, beispielsweise wenn keine drei aufeinanderfolgenden gültigen Grenzwertverletzungen auftreten. Jede Messung wird höchstens einmal geprüft. Der Aufwand pro Schleifendurchlauf ist konstant.

Daher beträgt die asymptotische Laufzeitkomplexität im Worst-Case **O(n)**.

**Zusätzliche Speicherkomplexität: O(1)**

Die Methode verwendet nur eine konstante Anzahl lokaler Variablen, beispielsweise den Zähler `folge` und die boolesche Variable `verletzt`.

Es werden keine zusätzlichen Datenstrukturen erzeugt, deren Speicherbedarf von der Anzahl der Messungen abhängt.

Daher beträgt die zusätzliche Speicherkomplexität **O(1)**.

Ergänzender Hinweis: Im günstigsten Fall (Best-Case) kann die Methode bereits nach drei Messungen mit `true` beendet werden. Die Best-Case-Laufzeit ist daher O(1).


## Aufgabe 2: Fehleranalyse und Testverfahren (22 Punkte)

Für die Temperaturreihe eines Behälters soll der Durchschnitt ausschließlich gültiger Messungen berechnet werden. Ist kein gültiger Messwert vorhanden, soll die Fehlermeldung "Keine gültigen Werte gefunden!" ausgegeben werden und `Double.NaN` zurückgegeben werden.

Eine Person schlägt folgenden Java-Code vor:

```java
public static double mittelwert(List<Messung> daten) {
    double summe = 0.0;
    int anzahl = 0;
    for (int i = 0; i < daten.size(); i++) {
        Messung m = daten.get(i);
        summe += m.temperatur();
        if (m.gueltig()) {
            anzahl++;
        }
    }
    if (anzahl == 0) {
        return Double.NaN;
    }
    return summe / (anzahl + 1);
}
```

- Die Liste `daten` ist niemals `null` und enthält keine `null`-Elemente.

### 2a – 9 Punkte.

Identifizieren Sie drei unterschiedliche fachliche oder technische Fehler im Code. Benennen Sie jeweils die Ursache und beschreiben Sie eine konkrete Auswirkung. (Je 3 Punkte.)

**Lösung**  

1. **Ungültige Messungen werden addiert:** `summe += m.temperatur()` steht außerhalb der Gültigkeitsprüfung. Dadurch können ungültige Temperaturen den Mittelwert verfälschen. Beispiel: Für `(0,g), (30,u)` gibt die fehlerhafte Methode `15.0` statt `0.0` zurück. Der falsche Divisor allein hätte bei korrekter Summenbildung für diese Eingabe keinen Einfluss, da die gültige Summe null ist.

2. **Falscher Divisor:** Durch `anzahl + 1` wird die Summe durch eine um eins zu große Zahl geteilt. Der Mittelwert ist dadurch bei einer von null verschiedenen Summe auch dann falsch, wenn alle Messungen gültig sind. Beispiel: Für `(5,g), (7,g)` ergibt sich `12 / 3 = 4.0` statt `6.0`.

3. **Fehlende Fehlermeldung:** Wenn `anzahl == 0` gilt, wird zwar `Double.NaN` zurückgegeben, die vorgeschriebene Ausgabe `Keine gültigen Werte gefunden!` fehlt. Das ist beispielsweise bei einer leeren Liste unabhängig von den anderen beiden Fehlern beobachtbar.

**Bewertung**  
Je Fehler **1 Punkt** für die korrekte Fehlerstelle bzw. Ursache, **1 Punkt** für die fachliche Erläuterung und **1 Punkt** für eine zutreffende konkrete Auswirkung. Die drei Fehler lassen sich mit geeigneten Eingaben unabhängig nachweisen: nur gültige Messungen für den falschen Divisor, eine gültige Messung mit Temperatur 0 und eine ungültige Messung für die fehlerhafte Summenbildung sowie eine leere Liste für die fehlende Fehlermeldung.


### 2b – 7 Punkte.
Implementieren Sie eine korrigierte, vollständige Java-Methode mit identischer Signatur. Behandeln Sie auch die leere Liste und Messreihen ohne gültige Werte.

**Lösung**  

```java
public static double mittelwert(List<Messung> daten) {
    double summe = 0.0;
    int anzahl = 0;
    for (Messung m : daten) {
        if (m.gueltig()) {
            summe += m.temperatur();
            anzahl++;
        }
    }
    if (anzahl == 0) {
        System.out.println("Keine gültigen Werte gefunden!");
        return Double.NaN;
    }
    return summe / anzahl;
}
```

**Bewertung:** vollständige Iteration über die Liste (1), nur gültige Werte addieren und zählen (2), Fehlermeldung bei fehlenden gültigen Messungen (1), `Double.NaN` bei null gültigen Messungen (1), korrekte Division durch `anzahl` (2).

### 2c – 6 Punkte

Entwickeln Sie drei Testfälle, mit denen die korrekte Funktionalität der in Teilaufgabe 2b implementierten Methode `mittelwert` überprüft werden kann.

Berücksichtigen Sie dabei unterschiedliche Eingabesituationen und Randfälle.

Stellen Sie Ihre Testfälle in einer Tabelle mit folgenden Angaben dar:

- Eingabedaten
- Erwarteter Rückgabewert, erwartete Konsolenausgabe
- Zweck des Testfalls

Für die Eingabedaten genügt die Angabe der Temperatur und der Gültigkeitskennung (`g` für gültig, `u` für ungültig).

| Eingabedaten | Erwartetes Ergebnis | Erwartete Konsolenausgabe    | Testintention |  
|---|---|---|---|
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |


**Lösung**  

| Eingabedaten | Erwartetes Ergebnis | Erwartete Konsolenausgabe    | Testintention |  
|---|---|---|---|
| [] | NaN | Keine gültigen Werte gefunden! | leere Liste korrekt behandeln |
| (0,g), (30,u) | 0.0 | keine | ungültige Werte ignorieren |
| (5,g), (7,g) | 6.0 | keine | korrekte Berechnung bei ausschließlich gültigen Werten |

Je Testfall **1 Punkt** für geeignete Eingabedaten für eine relevante Testsituation, **1 Punkt** für korrekte erwartete Ergebnisse einschließlich Konsolenausgabe und nachvollziehbare Testintention.


## Aufgabe 3: Objektorientiertes Modell und Datenmodell (23 Punkte)

Das System soll künftig verschiedene Alarmregeln unterstützen:

- Für jeden Behälter ist genau **eine** aktive Alarmregel zugeordnet.
- Eine **Grenzwertregel** ist verletzt, wenn der Temperaturwert unter dem Minimum oder über dem Maximum liegt.
- Eine **Sprungregel** ist verletzt, wenn die absolute Differenz zwischen der aktuellen und der unmittelbar vorherigen gültigen Temperatur größer als ein festgelegter Schwellwert ist. Der Schwellwert für die Sprungregel ist nichtnegativ.
- Beide Regelarten haben eine einheitliche Operation `istVerletzt(aktuell: double, vorherigeGueltige: double): boolean`.
- Wenn kein vorheriger gültiger Messwert vorhanden ist, wird für den Parameter `vorherigeGueltige` der aktuelle Temperaturwert übergeben.
- Ein Behälter besitzt beliebig viele Messungen. Jede Messung gehört zu genau einem Behälter.

### 3a – 13 Punkte. 
Erstellen Sie ein UML-Klassendiagramm mit den Klassen `Behaelter`, `Messung`, `Grenzwertregel`, `Sprungregel` sowie einer geeigneten gemeinsamen Abstraktion `Alarmregel`. Geben Sie die fachlich notwendigen Attribute und die oben beschriebene Operation an. Modellieren Sie Vererbung bzw. Schnittstellenimplementierung und Assoziationen einschließlich Multiplizitäten. Arbeiten Sie auf Papier oder mit einem UML-Werkzeug.

**Lösung**  

Lösung in der Datei `sim01_algorithmen_lösung_3a.uxf`.  

**Bewertung:**  fünf korrekte Typen und sinnvolle Attribute (5), einheitliche Operation/Signatur (2), Implementierungs-/Vererbungsbeziehungen korrekt (2), Behälter–Regel inkl. beidseitigen Multiplizitäten (2), Behälter–Messung inkl. beidseitigen Multiplizitäten (2). Zulässig ist eine abstrakte Basisklasse statt Interface.

### 3b – 6 Punkte.
Leiten Sie aus dem Modell ein relationales Datenbankschema mit den Tabellen `BEHAELTER`, `MESSUNG` und `REGEL` ab. Notieren Sie je Tabelle einen sinnvollen Primärschlüssel und die erforderlichen Fremdschlüssel. Eine Regel gehört genau einem Behälter, ein Behälter hat genau eine Regel. Die konkreten Regelparameter dürfen in dieser Aufgabe in der Tabelle `REGEL` zusammengefasst werden; die Regelart unterscheidet die zulässigen Parameter.

**Lösung**  

```text
BEHAELTER(behaelter_id PK, nummer UNIQUE)
MESSUNG(messung_id PK, behaelter_id FK -> BEHAELTER,
        zeitpunkt, temperatur, gueltig)
REGEL(regel_id PK, behaelter_id FK UNIQUE NOT NULL -> BEHAELTER,
      regelart, minimum, maximum, max_sprung)
```

Für **genau eine Regel je Behälter** ist zusätzlich zum gezeigten Fremdschlüssel und UNIQUE eine Integritätsbedingung auf Anwendungs- oder Transaktionsebene bzw. eine geeignete Datenbankkonstruktion nötig; allein `UNIQUE NOT NULL` auf `REGEL.behaelter_id` garantiert nur höchstens eine Regel je Behälter. 

**Punkte:** je Tabelle richtiger Primärschlüssel (3), Messung-FK (1), Regel-FK mit 1:1-Einschränkung (1), Hinweis auf zwingende Gesamtteilnahme/geeignete Absicherung (1).

Auch Modellierung mit nicht-nullbarem `BEHAELTER.regel_id UNIQUE` und gültigem FK ist möglich, sofern eindeutig beschrieben.

### 3c – 4 Punkte.
Erläutern Sie, warum bei Messungen die Behälterbezeichnung nicht zusätzlich redundant gespeichert werden sollte. Nennen Sie eine mögliche Anomalie und eine Maßnahme zur Vermeidung.

**Lösung**  

Die Bezeichnung hängt fachlich von `BEHAELTER.behaelter_id` ab, nicht von einer Messung. Redundante Speicherung würde z. B. bei einer Umbenennung des Behälters zu unterschiedlichen Bezeichnungen in Messzeilen führen (Änderungsanomalie). Die Bezeichnung wird nur in BEHAELTER gespeichert und bei Bedarf über den Fremdschlüssel verknüpft.

**Bewertung**  je **2 Punkte** für schlüssig erklärte Anomalie und Maßnahme.


## Aufgabe 4: SQL-Abfragen und Änderungen (25 Punkte)

Ein separates, bereits vorhandenes PostgreSQL-Datenbankschema dient der Auswertung von Transporten. Es ist unabhängig von dem in Aufgabe 3 zu entwerfenden Schema.

```sql
CREATE TABLE kunde (
    kunde_id INTEGER PRIMARY KEY,
    name VARCHAR(80) NOT NULL
);
CREATE TABLE transport (
    transport_id INTEGER PRIMARY KEY,
    kunde_id INTEGER NOT NULL REFERENCES kunde(kunde_id),
    start_am DATE NOT NULL,
    status VARCHAR(20) NOT NULL
);
CREATE TABLE messwert (
    messwert_id INTEGER PRIMARY KEY,
    transport_id INTEGER NOT NULL REFERENCES transport(transport_id),
    temperatur NUMERIC(5,2) NOT NULL,
    gueltig BOOLEAN NOT NULL
);
```

In `transport.status` stehen nur die Werte `OFFEN`, `UNTERWEGS` und `ABGESCHLOSSEN`. Für Aufgabe 4 gelten die nachstehenden Daten.

**kunde**

| kunde_id | name |
|---:|---|
| 1 | Fracht Nord |
| 2 | Frisch Markt |
| 3 | KühlLogik |

**transport**

| transport_id | kunde_id | start_am | status |
|---:|---:|---|---|
| 10 | 1 | 2026-10-20 | ABGESCHLOSSEN |
| 11 | 1 | 2026-10-21 | UNTERWEGS |
| 12 | 2 | 2026-10-20 | ABGESCHLOSSEN |
| 13 | 3 | 2026-10-21 | OFFEN |

**messwert**

| messwert_id | transport_id | temperatur | gueltig |
|---:|---:|---:|---|
| 1 | 10 | 5.0 | true |
| 2 | 10 | 9.0 | true |
| 3 | 10 | 15.0 | false |
| 4 | 11 | 10.0 | true |
| 5 | 11 | 11.0 | true |
| 6 | 12 | 4.0 | true |
| 7 | 12 | 7.0 | true |

### 4a – 6 Punkte.
Schreiben Sie eine SQL-Abfrage, die für alle gültigen Messungen mit einer Temperatur über 8,0 °C die Spalten `transport_id`, `status` (Transportstatus) und `temperatur` ausgibt. Sortieren Sie nach Transport-ID und anschließend nach Temperatur aufsteigend.

**Lösung**  

```sql
SELECT t.transport_id, t.status, m.temperatur
FROM messwert AS m
JOIN transport AS t ON t.transport_id = m.transport_id
WHERE m.gueltig = TRUE AND m.temperatur > 8.0
ORDER BY t.transport_id ASC, m.temperatur ASC;
```

Erwartete Ergebnistabelle:

| transport_id | status | temperatur |
|---:|---|---:|
| 10 | ABGESCHLOSSEN | 9.00 |
| 11 | UNTERWEGS | 10.00 |
| 11 | UNTERWEGS | 11.00 |

**Punkte:** korrekte Tabellenverknüpfung mit einem JOIN und passender ON-Bedingung (2), beide Filterbedingungen (2), Auswahl der geforderten Spalten (1), korrekte Sortierung (1).


### 4b – 8 Punkte.
Schreiben Sie eine SQL-Abfrage, die für jeden Transport, auch wenn keine gültigen Messwerte vorliegen, die Spalten `transport_id`, `status` und `anzahl_gueltig` ausgibt. Gezählt werden ausschließlich gültige Messwerte. Sortieren Sie nach `transport_id` aufsteigend.

**Lösung**  

```sql
SELECT t.transport_id, t.status,
       COUNT(m.messwert_id) AS anzahl_gueltig
FROM transport AS t
LEFT JOIN messwert AS m
       ON m.transport_id = t.transport_id AND m.gueltig = TRUE
GROUP BY t.transport_id, t.status
ORDER BY t.transport_id ASC;
```

Erwartete Ergebnistabelle:

| transport_id | status | anzahl_gueltig |
|---:|---|---:|
| 10 | ABGESCHLOSSEN | 2 |
| 11 | UNTERWEGS | 2 |
| 12 | ABGESCHLOSSEN | 2 |
| 13 | OFFEN | 0 |

**Punkte:** `LEFT JOIN` mit korrekter Verknüpfungsbedingung und Erhaltung aller Transporte (2), Gültigkeitsfilter in der `ON`-Bedingung oder gleichwertige bedingte Aggregation (2), `COUNT(m.messwert_id)` bzw. gleichwertige korrekte Zählung (1), passende Gruppierung (1), geforderte Ausgabespalten (1), Sortierung (1). Ein Filter auf `m.gueltig` in `WHERE` ohne zusätzliche Behandlung würde Transporte ohne gültige Messwerte ausschließen und ist daher nicht korrekt.

### 4c – 6 Punkte.

Schreiben Sie eine SQL-Abfrage, die die `transport_id` aller Transporte ausgibt, für die **mindestens zwei gültige Messwerte mit einer Temperatur über 8,0 °C** vorliegen.

Sortieren Sie das Ergebnis nach `transport_id` aufsteigend.

Geben Sie zusätzlich die erwartete Ergebnistabelle anhand der vorgegebenen Daten an.

**Lösung**  

```sql
SELECT m.transport_id
FROM messwert AS m
WHERE m.gueltig = TRUE AND m.temperatur > 8.0
GROUP BY m.transport_id
HAVING COUNT(*) >= 2
ORDER BY m.transport_id;
```

Erwartete Ergebnistabelle:

| transport_id |
|---:|
| 11 |

**Punkte:** Filter (2), Gruppierung (1), HAVING-Bedingung (2), Ergebnis inkl. Sortierung (1).


### 4d – 5 Punkte.
Schreiben Sie eine SQL-Anweisung, die ausschließlich den Transport mit der ID 13 von `OFFEN` auf `UNTERWEGS` setzt. Die Änderung darf nur erfolgen, wenn der Status aktuell `OFFEN` ist.

**Lösung**  

```sql
UPDATE transport
SET status = 'UNTERWEGS'
WHERE transport_id = 13
  AND status = 'OFFEN';
```

**Punkte:** UPDATE + Tabelle (1), SET (1), ID-Bedingung (2), ursprünglicher Status (1). Kein pauschales Update zulässig.
