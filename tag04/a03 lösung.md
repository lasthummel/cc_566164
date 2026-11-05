# Aufgabe 'Use-Case-Diagramm (Anwendungsfalldiagramm)'

## Szenario: Schulungsportal

Ein Unternehmen betreibt ein internes Schulungsportal.

Mitarbeiter können:

- verfügbare Schulungen anzeigen,
- nach Schulungen suchen,
- sich zu einer Schulung anmelden,
- eigene Anmeldungen stornieren.

Administratoren können zusätzlich:

- Schulungen anlegen,
- Schulungen ändern,
- Schulungen löschen.

Nach einer erfolgreichen Anmeldung wird eine Bestätigung per E-Mail versendet. Ein externer E-Mail-Dienst übernimmt den Versand.

## A1
Identifizieren Sie die Akteure.

**Lösung:**  

- Mitarbeiter
- Administrator
- externer E-Mail-Dienst

Der Administrator kann fachlich als spezialisierter Akteur eines Mitarbeiters modelliert werden. Für diese Aufgabe ist auch eine getrennte Darstellung zulässig.

## A2
Identifizieren Sie mindestens sechs sinnvolle Use Cases.

**Lösung:**  

- Schulungen anzeigen
- Schulung suchen
- zu Schulung anmelden
- Anmeldung stornieren
- Schulung anlegen
- Schulung ändern
- Schulung löschen
- Anmeldebestätigung versenden

## A3
Erstellen Sie ein Use-Case-Diagramm.

**Lösung in der uxf-Datei.**

## A4
Modellieren Sie den E-Mail-Dienst als externen Akteur.

**Lösung in der uxf-Datei.**

## A5
Entscheiden Sie, ob die E-Mail-Bestätigung als eigener Use Case modelliert werden sollte. Begründen Sie Ihre Entscheidung.

**Lösung:**

Hier sind unterschiedliche Modellierungen vertretbar.

Man kann das Use Case 'Anmeldebestätigung versenden' einführen, da der Versand der Bestätigung ist eine ausdrücklich geforderte Systemfunktion ist und wenn man untersteichen möchte, dass der Versand über einen externen E-Mail-Dienst erfolgt.

Man könnte aber komplett auf Use-Case verzichten, da es keine Anforderung gibt, dass die Versandfunktion unabhängig aufgerufen wird oder von mehreren anderen Anwendungsfällen verwendet wird. Das Versenden der Bestätigung kann dabei als eine Teilaktivität im Use Case 'zu Schulung anmelden' betrachtet werden.  