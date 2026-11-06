# Aufgabe 'System zur Verwaltung von Support-Tickets'

## Ausgangssituation

Ein Unternehmen möchte ein internes System zur Verwaltung von Support-Tickets entwickeln.

Aus der Anforderungsanalyse liegen folgende Anforderungen vor:

- Mitarbeiter können neue Tickets erstellen.
- Mitarbeiter können ihre eigenen offenen Tickets bearbeiten.
- Administratoren können alle Tickets bearbeiten.
- Tickets können geschlossen werden.
- Ein Ticket besitzt die Zustände "offen", "in Bearbeitung" und "geschlossen".
- Beim Schließen eines Tickets wird eine Abschlussmeldung angezeigt.
- Ein externes Benachrichtigungssystem kann über Änderungen an Tickets informiert werden.
- Der Ablauf der Ticketbearbeitung soll nachvollziehbar dokumentiert werden.

## A1 – Diagrammwahl

Begründen Sie, welchen UML-Diagrammtypen Sie verwenden würden, um die Nutzung des Systems durch Mitarbeiter und Administratoren darzustellen.

**Lösung:**

Use-Case-Diagramm.
Das Use-Case-Diagramm stellt dar, welche Akteure welche Systemfunktionen nutzen.

## A2 – Diagrammwahl

Begründen Sie, welchen UML-Diagrammtypen Sie verwenden würden, um die Zustandsänderungen eines Tickets darzustellen.

**Lösung:**

Zustandsdiagramm.
Das Zustandsdiagramm stellt die möglichen Zustände eines Tickets und die Übergänge zwischen diesen Zuständen dar.

## A3 – Use Cases

Entwickeln Sie ein Use-Case-Diagramm mit mindestens:

- zwei Akteuren,
- fünf Use Cases,
- einer Systemgrenze.

**Lösung in uxf-Datei.**

Bemerkung:  
Da nicht ausdrücklich festgelegt ist, wer Tickets schließen darf, ist das Schließen im Diagramm dem Administrator zugeordnet.

## A4 – Zustandsdiagramm

Entwickeln Sie ein Zustandsdiagramm für ein Ticket.

Berücksichtigen Sie mindestens die Zustände:

- offen
- in Bearbeitung
- geschlossen

Modellieren Sie sinnvolle Übergänge.

**Lösung in uxf-Datei.**

## A5 – Beurteilung

Ein Teilnehmer schlägt vor:

> "Wir sollten das gesamte System mit einem einzigen UML-Diagramm darstellen."

Beurteilen Sie diesen Vorschlag.

**Lösung:**  

Die Aussage ist nicht sinnvoll.

Begründung:

Unterschiedliche UML-Diagrammtypen stellen unterschiedliche Perspektiven eines Systems dar. Ein Use-Case-Diagramm zeigt beispielsweise Akteure und Systemfunktionen, während ein Zustandsdiagramm Zustände und Übergänge eines Objekts darstellt. Ein einzelnes Diagramm würde die unterschiedlichen Fragestellungen nicht angemessen abbilden.

Bessere Begründung:
Ein Softwaresystem ist viel zu komplex, um alle Aspekte in einem einzigen Diagramm abzubilden