# Aufgabe – Austauschbare Preisberechnung

Ein Entwicklungsteam erstellt für einen Onlineshop eine Komponente zur Berechnung von Verkaufspreisen.

Ausgehend von einem übergebenen Betrag sollen unterschiedliche Berechnungsarten unterstützt werden:

- Normalpreis: Der übergebene Betrag wird unverändert als Verkaufspreis zurückgegeben.
- Rabattpreis: Auf den übergebenen Betrag wird ein Rabatt von 10 % gewährt.

Die Anwendung soll die gewünschte Berechnungsart zur Laufzeit auswählen und auch bei einem bereits vorhandenen Preisrechner wechseln können. Der Preisrechner soll die konkreten Berechnungsregeln nicht selbst enthalten und unabhängig von den einzelnen Berechnungsarten bleiben.

Zukünftig sollen weitere Preisberechnungen, beispielsweise für Sonderaktionen oder Kunden mit besonderen Konditionen, ergänzt werden können, ohne die vorhandene Preisberechnungskomponente für jede neue Variante ändern zu müssen.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

Strategy-Pattern (Strategiemuster), ein Verhaltensmuster.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen des Preisrechners und der unterschiedlichen Berechnungsarten sowie auf den Ablauf einer Preisberechnung und den Wechsel der Berechnungsart zur Laufzeit ein.

Der **Preisrechner** übernimmt die Rolle des **Context (Kontext)**. Er enthält eine Referenz auf eine **Preisstrategie** und delegiert die eigentliche Berechnung an das aktuell ausgewählte Strategieobjekt. Die konkreten Berechnungsregeln sind daher nicht im Preisrechner implementiert.

Das Interface **Preisstrategie** legt die gemeinsame Methode `berechnePreis(betrag : double) : double` fest. Die beiden konkreten Strategien implementieren diese Schnittstelle:

- **NormalpreisStrategie** gibt den übergebenen Betrag unverändert zurück.
- **RabattStrategie** berechnet den Betrag abzüglich 10 % Rabatt, also 90 % des Ausgangsbetrags.

Die Preisberechnung läuft beispielsweise wie folgt ab:

1. Die Anwendung stellt dem Preisrechner eine konkrete Strategie über `setStrategie(s : Preisstrategie)` zur Verfügung, beispielsweise `NormalpreisStrategie`.
2. Die Anwendung ruft am Preisrechner `berechnePreis(100.0)` auf.
3. Der Preisrechner ruft die gleichnamige Methode der aktuell hinterlegten Strategie auf und übergibt den Betrag.
4. Die Strategie berechnet den Preis und liefert ihn zurück; der Preisrechner gibt dieses Ergebnis an die aufrufende Anwendung weiter. Bei `NormalpreisStrategie` beträgt das Ergebnis `100.0`.
5. Soll künftig der Rabatt gelten, übergibt die Anwendung demselben Preisrechner mit `setStrategie(...)` ein Objekt der Klasse `RabattStrategie`. Ein erneuter Aufruf von `berechnePreis(100.0)` liefert dann `90.0`.

Der Preisrechner arbeitet nur mit der gemeinsamen Schnittstelle `Preisstrategie`. Für den Wechsel ist deshalb keine Änderung an seiner Klasse erforderlich; die konkrete Strategie kann zur Laufzeit ausgetauscht werden.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Erweiterbarkeit der Anwendung.

Durch die Trennung zwischen dem Preisrechner und den konkreten Berechnungsalgorithmen können neue Preisberechnungsarten ergänzt werden, ohne die Klasse `Preisrechner` oder die vorhandenen Strategien zu verändern.

Beispielsweise kann eine **SonderaktionsStrategie** die Schnittstelle `Preisstrategie` implementieren und eigene Rabattregeln bereitstellen. Ein Objekt dieser Klasse lässt sich dem bestehenden Preisrechner ebenfalls über `setStrategie(...)` übergeben.

Voraussetzung ist, dass die neue Strategie die gemeinsame Schnittstelle einhält. Der Code, der die jeweils passende Strategie auswählt beziehungsweise erzeugt, muss gegebenenfalls um die neue Variante ergänzt werden. Der Preisrechner selbst bleibt davon unabhängig.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie dabei die für die Preisberechnung erforderlichen Klassen beziehungsweise Schnittstellen, deren wesentliche Methoden sowie die Beziehungen zwischen ihnen dar.

Das Klassendiagramm liegt bereits in der Datei `strategy.uxf` vor.
