## Aufgabe 1 – Diagnose: Bibliothek oder Framework?

Ordne jede Aussage als 'Bibliothek', 'Framework', 'beides möglich' oder 'fachlich nicht ausreichend bestimmt' ein. Begründe die Zuordnung jeweils in einem Satz.

1. Eine Anwendung ruft bei Bedarf eine Funktion `formatCurrency(betrag)` eines Pakets auf.
2. Ein Web-Framework ruft eine vom Entwickler registrierte Controller-Methode auf, wenn eine passende HTTP-Anfrage eingeht.
3. Ein Paket enthält Hilfsfunktionen für Datum und Zeichenketten.
4. Ein System stellt Routing, Lebenszyklus und Erweiterungspunkte bereit; der Anwendungscode wird in diesen Ablauf integriert.

Beantworte anschließend: Welches Kriterium ist für die Abgrenzung besonders aussagekräftig?

1. Bibliothek: Der Anwendungscode entscheidet, wann die Funktion aufgerufen wird.
2. Framework: Das Framework steuert den Ablauf und ruft registrierten Anwendungscode auf (Inversion of Control).
3. Bibliothek: Es werden wiederverwendbare Hilfsfunktionen bereitgestellt, die gezielt aufgerufen werden.
4. Framework: Routing, Lebenszyklus und Erweiterungspunkte strukturieren den Ablauf; der Anwendungscode wird eingebunden.

Das wichtigste Abgrenzungskriterium ist typischerweise die Kontrollflussverantwortung. Eine Bibliothek wird vom Anwendungscode aufgerufen; bei einem Framework ruft das Framework häufig den Anwendungscode an definierten Erweiterungspunkten auf. Reale Produkte können sowohl Bibliotheken als auch Framework-Komponenten enthalten.
