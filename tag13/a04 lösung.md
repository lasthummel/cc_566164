# Aufgabe – Integrierte Fallstudie

Ein kleiner Webshop verwaltet Kunden, Bestellungen und bestellte Artikel. Die Anforderungen lauten:

- Jeder Kunde wird durch eine KundenID identifiziert und hat einen Namen sowie eine E-Mail-Adresse.
- Ein Kunde kann mehrere Bestellungen aufgeben. Jede Bestellung gehört genau einem Kunden.
- Jede Bestellung kann mehrere Artikel enthalten. Derselbe Artikel kann in mehreren Bestellungen vorkommen.
- Zu jeder bestellten Artikelposition werden die bestellte Menge und der zum Bestellzeitpunkt vereinbarte Einzelpreis gespeichert.
- Eine Bestellung kann mehrere Positionen enthalten; derselbe Artikel darf in einer Bestellung höchstens einmal als Position vorkommen.
- Artikel haben eine ArtikelID, eine Bezeichnung und einen aktuellen Listenpreis.

## A1. 
Leiten Sie die Entitätstypen und ihre wesentlichen Attribute ab.

**Lösung**  
- KUNDE: KundenID, Name, EMail
- BESTELLUNG: BestellID, KundenID
- ARTIKEL: ArtikelID, Bezeichnung, Listenpreis
- BESTELLPOSITION: BestellID, ArtikelID, Menge, EinzelpreisZumBestellzeitpunkt

Ein Bestelldatum könnte ergänzt werden, ist aber in der Aufgabenstellung nicht ausdrücklich gefordert.


## A2. 
Bestimmen Sie die Kardinalitäten zwischen KUNDE und BESTELLUNG sowie zwischen BESTELLUNG und ARTIKEL.

**Lösung**  
- KUNDE 1:n BESTELLUNG: Ein Kunde kann mehrere Bestellungen aufgeben; jede Bestellung gehört genau einem Kunden.
- BESTELLUNG n:m ARTIKEL: Eine Bestellung kann mehrere Artikel enthalten und ein Artikel kann in mehreren Bestellungen vorkommen. BESTELLPOSITION löst die n:m-Beziehung auf.


## A3. 
Entwicklen Sie ein relationales Schema mit PKs und FKs.

**Lösung**  

    KUNDE(KundenID PK, Name, EMail)

    BESTELLUNG(BestellID PK, KundenID FK → KUNDE.KundenID)

    ARTIKEL(ArtikelID PK, Bezeichnung, Listenpreis)

    BESTELLPOSITION(
      BestellID PK/FK -> BESTELLUNG.BestellID,
      ArtikelID PK/FK -> ARTIKEL.ArtikelID,
      Menge,
      EinzelpreisZumBestellzeitpunkt
    )

Der zusammengesetzte PK (BestellID, ArtikelID) setzt um, dass derselbe Artikel in einer Bestellung höchstens einmal als Position vorkommt. Die FKs verhindern Verweise auf nicht existierende Bestellungen oder Artikel.


## A4. 
Erklären Sie, warum Einzelpreis und Menge in der Positionstabelle und nicht in ARTIKEL gespeichert werden.

**Lösung**  
Menge ist eine Eigenschaft der konkreten Bestellposition. Der vereinbarte Einzelpreis gehört ebenfalls zur Position, weil sich der aktuelle Listenpreis später ändern kann. Würden alte Bestellungen ausschließlich auf den aktuellen Listenpreis verweisen, könnten historische Bestellwerte nachträglich verfälscht werden.

Die Menge hängt immer von der Kombination aus dieser einen Bestellung und diesem einen Artikel ab.

## A5. 
Nennen Sie eine sinnvolle Integritätsregel, die das Schema zusätzlich absichern sollte.

**Lösung**  
Mögliche Antworten:
- Menge muss größer als 0 sein.
- Listenpreis und vereinbarter Einzelpreis müssen nichtnegativ sein, sofern fachlich keine negativen Preise vorgesehen sind.
- KundenID in BESTELLUNG muss gesetzt sein, wenn jede Bestellung genau einem Kunden gehört.
- EMail kann als Pflichtfeld abgesichert werden, wenn dies fachlich gefordert ist.

Eine Regel muss aus den Anforderungen folgen oder als zusätzliche Absicherung begründet werden.
