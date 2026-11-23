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

---

# Musterlösung

## a) Geeignetes Entwurfsmuster

**Decorator-Pattern (Dekorierer, Strukturmuster).**

## b) Funktionsweise und Rollen

- **Component – `TextAusgabe`:** Die gemeinsame Schnittstelle definiert `getText() : String`. Sowohl einfache Texte als auch alle Dekoratoren sind über diese Schnittstelle verwendbar.
- **ConcreteComponent – `EinfacherText`:** Speichert den ursprünglichen Text und gibt ihn über `getText()` unverändert zurück.
- **Decorator – `TextDecorator`:** Eine abstrakte Klasse, die `TextAusgabe` implementiert und eine Referenz `komponente : TextAusgabe` auf das umhüllte Objekt hält. Die Klasse delegiert `getText()` an `komponente.getText()`; ihre Unterklassen ergänzen dieses Verhalten.
- **ConcreteDecorators – `GrossbuchstabenDecorator` und `RahmenDecorator`:** Beide erben von `TextDecorator`. Die erste Klasse wandelt den von der umhüllten Komponente gelieferten Text in Großbuchstaben um, die zweite setzt `[` und `]` um das Ergebnis.

Die Objekte können zur Laufzeit ineinander verschachtelt werden. Ein mögliches Java-Beispiel zur Zusammensetzung:

```java
TextAusgabe ausgabe = new EinfacherText("Server gestartet");
ausgabe = new GrossbuchstabenDecorator(ausgabe);
ausgabe = new RahmenDecorator(ausgabe);
System.out.println(ausgabe.getText()); // [SERVER GESTARTET]
```

Beim Aufruf von `getText()` fragt der äußere `RahmenDecorator` zunächst den inneren `GrossbuchstabenDecorator` ab. Dieser ruft `getText()` auf dem `EinfacherText` auf und erhält `Server gestartet`. Daraus erzeugt er `SERVER GESTARTET`. Der äußere Dekorator ergänzt die eckigen Klammern und liefert `[SERVER GESTARTET]`.

**Wichtig:** Bei umgekehrter Verschachtelung würde die Großschreibung auch den vom inneren Rahmen hinzugefügten Text verarbeiten. Bei den hier verwendeten Klammern ergibt das zufällig denselben sichtbaren Text. Allgemein kann die Reihenfolge von Dekoratoren unterschiedliche Ergebnisse liefern.

## c) Vorteil hinsichtlich der Erweiterbarkeit

Zusätzliche Funktionen lassen sich als neue konkrete Dekoratoren ergänzen, ohne `EinfacherText` oder die vorhandenen Dekoratoren zu verändern. Ein neuer `ZeitstempelDecorator` könnte beispielsweise einen Zeitstempel voranstellen. Da jeder Dekorator dieselbe Schnittstelle implementiert und ein `TextAusgabe`-Objekt umhüllt, können die Funktionen flexibel kombiniert werden. Dadurch müssen nicht für jede Kombination eigene Unterklassen erstellt werden.

## d) UML-Klassendiagramm

Die Datei `decorator.uxf` enthält ein mögliches UML-Klassendiagramm.

Der Erwartungshorizont umfasst insbesondere:

- `TextAusgabe` als Interface mit `+ getText() : String`.
- `EinfacherText` implementiert `TextAusgabe` und besitzt `- text : String`.
- Die abstrakte Klasse `TextDecorator` implementiert ebenfalls `TextAusgabe`, hält `# komponente : TextAusgabe` und delegiert `getText()` an die enthaltene Komponente.
- `GrossbuchstabenDecorator` und `RahmenDecorator` erben von `TextDecorator` und überschreiben `getText()`.
- Eine gerichtete Assoziation von `TextDecorator` zu `TextAusgabe` modelliert die umhüllte Komponente. Die Implementierungsbeziehungen sind gestrichelt mit hohler Dreiecksspitze, die Generalisierungen durchgezogen mit hohler Dreiecksspitze.

**Fachlicher Hinweis:** Die gerichtete Assoziation ist hier bewusst keine UML-Komposition. Ein Dekorator hält eine Referenz auf ein Objekt, dessen Lebensdauer nicht zwingend an die Lebensdauer des Dekorators gebunden ist. Die Bezeichnung "Komposition" beim Decorator-Pattern meint primär das Entwurfsprinzip der Objektzusammensetzung und erzwingt keine UML-Kompositionsbeziehung.

## Didaktischer Hinweis

Die Aufgabe entspricht hinsichtlich der Operatoren und des Umfangs den vorhandenen Design-Pattern-Aufgaben. Die Priorisierung als Vertiefung folgt der projektinternen Auswertung `designpatterns auswertung.md`; sie stellt keine dokumentierte IHK-Vorgabe dar.
