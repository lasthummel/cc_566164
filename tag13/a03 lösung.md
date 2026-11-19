# Aufgabe – n:m-Beziehung relational abbilden

Ein Unternehmen verwaltet Rezepte und Zutaten.

- Ein Rezept kann mehrere Zutaten enthalten.
- Jede Zutat kann in mehreren Rezepten verwendet werden.
- Zu jeder Zutat eines Rezepts werden die benötigte Menge und deren Einheit gespeichert.
- Dieselbe Zutat darf innerhalb eines Rezepts nur einmal als Position vorkommen.

- Eine Zutat kann nicht mehrfach als Position im selben Rezept erfasst werden.


## A1.
Bestimmen Sie die Kardinalität zwischen 'Rezept' und 'Zutat'.

**Lösung**  
'Rezept' und 'Zutat' stehen in einer n:m-Beziehung.

## A2.
Leiten Sie die benötigten relationalen Tabellen mit Attributen ab. Markieren Sie PK und FK.

**Lösung**  
Mögliches Schema:

    REZEPT(RezeptID PK, Name)

    ZUTAT(ZutatenID PK, Bezeichnung)

    REZEPT_ZUTAT(
        RezeptID PK/FK -> REZEPT.RezeptID,
        ZutatenID PK/FK -> ZUTAT.ZutatenID,
        Menge,
        Einheit)

Der zusammengesetzte PK von REZEPT_ZUTAT besteht aus RezeptID und ZutatenID. Beide Attribute sind zugleich FKs.

**dieselbe Lösung, verständlichere Notation**  

    REZEPT(
        RezeptID,
        Name
    )
    PK: RezeptID

    ZUTAT(
        ZutatenID,
        Bezeichnung
    )
    PK: ZutatenID

    REZEPT_ZUTAT(
        RezeptID,
        ZutatenID,
        Menge,
        Einheit
    )
    PK: (RezeptID, ZutatenID)
    FK: RezeptID  -> REZEPT(RezeptID)
    FK: ZutatenID -> ZUTAT(ZutatenID)


## A3.
Begründen Sie, wo die Attribute Menge und Einheit gespeichert werden.

**Lösung**  
Menge und Einheit gehören zur konkreten Verwendung einer Zutat in einem Rezept, nicht ausschließlich zum Rezept oder zur Zutat. Deshalb werden sie in REZEPT_ZUTAT gespeichert.

## A4.
Erklären Sie, wie das Schema doppelte Zutatenpositionen innerhalb desselben Rezepts verhindert.

**Lösung**  
Der zusammengesetzte PK lässt jede Kombination aus RezeptID und ZutatenID höchstens einmal zu. Eine zweite identische Kombination verletzt den Schlüssel.

## A5. Optional (Implementierung nach Modellierung)
Erstellen Sie die SQL-Anweisungen, mit denen die drei Tabellen REZEPT, ZUTAT und REZEPT_ZUTAT in einer relationalen Datenbank angelegt werden.

Berücksichtigen Sie folgende Anforderungen:
- Verwenden Sie geeignete SQL-Datentypen für sämtliche Spalten.
- Definieren Sie die Primärschlüssel der Tabellen.
- Definieren Sie die Fremdschlüssel der Tabelle REZEPT_ZUTAT.
- Stellen Sie sicher, dass dieselbe Zutat innerhalb eines Rezepts nur einmal als Position vorkommen kann.
Verwenden Sie dazu den SQL-Befehl CREATE TABLE.

**Lösung**  

```sql
CREATE TABLE REZEPT (
    RezeptID INTEGER PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);

CREATE TABLE ZUTAT (
    ZutatenID INTEGER PRIMARY KEY,
    Bezeichnung VARCHAR(100) NOT NULL
);

CREATE TABLE REZEPT_ZUTAT (
    RezeptID INTEGER NOT NULL,
    ZutatenID INTEGER NOT NULL,
    Menge DECIMAL(10,2) NOT NULL,
    Einheit VARCHAR(20) NOT NULL,

    PRIMARY KEY (RezeptID, ZutatenID),

    FOREIGN KEY (RezeptID)
        REFERENCES REZEPT(RezeptID),

    FOREIGN KEY (ZutatenID)
        REFERENCES ZUTAT(ZutatenID)
);
```
