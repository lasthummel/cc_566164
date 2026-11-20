# Aufgabe – Erweiterbare Textausgabe

Ein Entwicklungsteam erstellt eine Anwendung, die Statusmeldungen als Text ausgibt. Die bestehende Schnittstelle `TextAusgabe` stellt die Methode `getText() : String` bereit. Die Klasse `EinfacherText` implementiert diese Schnittstelle und liefert über `getText()` einen bei der Erzeugung übergebenen Text zurück.

Für unterschiedliche Einsatzsituationen soll die Ausgabe einzelner Textobjekte um zusätzliche Funktionen erweitert werden:

- **Großschreibung:** Der ausgegebene Text wird vollständig in Großbuchstaben umgewandelt.
- **Rahmen:** Vor und nach dem ausgegebenen Text werden die Zeichen `[` und `]` ergänzt.

Die zusätzlichen Funktionen sollen einzeln oder kombiniert verwendet werden können. Die Reihenfolge der Funktionen soll durch die Zusammensetzung der Objekte festgelegt werden. Insbesondere soll die Anwendung einen bereits erzeugten Text zur Laufzeit mit einer oder mehreren Funktionen versehen können.

Die Schnittstelle `TextAusgabe` und die Klasse `EinfacherText` dürfen dafür nicht verändert werden. Auch soll nicht für jede mögliche Kombination der Zusatzfunktionen eine neue Unterklasse von `EinfacherText` angelegt werden.

Beispiel: Für den ursprünglichen Text `Server gestartet` soll durch zuerst angewendete Großschreibung und anschließend hinzugefügten Rahmen die Ausgabe `[SERVER GESTARTET]` entstehen.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der Textausgabe. Gehen Sie auf die Rollen der Schnittstelle `TextAusgabe`, der Klasse `EinfacherText` und der neu benötigten Klassen ein. Erläutern Sie anhand des Beispiels, in welcher Reihenfolge die Methoden aufgerufen werden und wie das Ergebnis entsteht.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Erweiterbarkeit der Anwendung.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie die Schnittstelle, den einfachen Text, eine gemeinsame abstrakte Basisklasse für die Zusatzfunktionen und die beiden konkreten Zusatzfunktionen dar. Geben Sie die wesentlichen Attribute und Methoden sowie die Beziehungen zwischen den Klassen und der Schnittstelle an.
