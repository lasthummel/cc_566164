# Aufgabe – Funktionale Abhängigkeiten und Normalisierung

Gegeben ist die Relation:

**ANMELDUNG(LernendenID, KursID, LernendenName, Kursname, Anmeldedatum)**

Es gelten die fachlichen Regeln:

- LernendenID identifiziert genau einen Lernenden und bestimmt dessen Namen.
- KursID identifiziert genau einen Kurs und bestimmt dessen Namen.
- Ein Lernender kann an mehreren Kursen teilnehmen.
- Pro Kombination aus LernendenID und KursID gibt es höchstens eine Anmeldung.
- Anmeldedatum beschreibt die jeweilige Anmeldung.

## A1. 
Bestimmen Sie einen geeigneten Primärschlüssel.

**Lösung**  
Der zusammengesetzte PK ist (LernendenID, KursID), da pro Kombination höchstens eine Anmeldung erlaubt ist.

## A2. 
Gebn Sie die funktionalen Abhängigkeiten an, die sich aus den Regeln ergeben.

**Lösung**  
- LernendenID -> LernendenName
- KursID -> Kursname
- (LernendenID, KursID) -> Anmeldedatum

Der zusammengesetzte Schlüssel bestimmt alle Attribute der Ausgangsrelation. Die Abhängigkeiten für die Namen zeigen jedoch, dass diese jeweils nur von einem Schlüsselteil abhängen.

## A3. 
Erklären Sie, warum die Relation 1NF erfüllen kann, aber gegen 2NF verstößt.

**Lösung**  
Wenn jedes Feld einen einzelnen Wert enthält, kann die Relation 1NF erfüllen. Sie verletzt 2NF, weil LernendenName nur von LernendenID und Kursname nur von KursID abhängt. Beide Attribute hängen damit nur von einem echten Teil des zusammengesetzten PK ab.


## A4. 
Zerlegen Sie die Relation in Tabellen, die die 2NF erreichen.

**Lösung**  

    LERNENDER(LernendenID PK, LernendenName)

    KURS(KursID PK, Kursname)

    ANMELDUNG(LernendenID PK/FK -> LERNENDER.LernendenID,
    KursID PK/FK -> KURS.KursID, Anmeldedatum)

In ANMELDUNG hängt Anmeldedatum vom gesamten zusammengesetzten Schlüssel ab.

## A5. 
Begründen Sie, warum die resultierenden Tabellen auch die typische 3NF-Problematik dieser Ausgangsrelation vermeiden.

**Lösung**  
In den resultierenden Tabellen hängen die Nichtschlüsselattribute im betrachteten Modell direkt vom Schlüssel der jeweiligen Tabelle ab. Die Namen werden nicht mehr über einen Schlüsselteil in ANMELDUNG gespeichert. Damit ist die typische Abhängigkeitsproblematik der Ausgangsrelation beseitigt.

Die formale Definition der Normalformen ist präziser; zusätzliche fachliche Regeln oder funktionale Abhängigkeiten könnten eine erneute Prüfung erfordern.

