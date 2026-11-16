# Aufgabe – Vereinfachter Zugriff auf die Berichtserstellung

Ein Unternehmen entwickelt eine Webanwendung, mit der Mitarbeiter einen Monatsbericht über die Umsätze als PDF-Datei herunterladen können.

Für die Erstellung des Berichts existieren bereits drei voneinander unabhängige Dienste:

- **DatenService:** Lädt die Buchungen des gewünschten Monats mit `ladeBuchungen(jahr : int, monat : int) : List<Buchung>`.
- **StatistikService:** Berechnet aus diesen Buchungen die benötigten Kennzahlen mit `berechneKennzahlen(buchungen : List<Buchung>) : Kennzahlen`.
- **PdfService:** Erzeugt aus den Kennzahlen die PDF-Datei mit `erstellePdf(kennzahlen : Kennzahlen) : byte[]`.

Bisher müsste die Weboberfläche alle drei Dienste selbst aufrufen und die Reihenfolge der Aufrufe kennen.

Die Anwendung soll so überarbeitet werden, dass die Weboberfläche zur Erstellung eines Monatsberichts nur noch **eine einzige öffentliche Methode** aufrufen muss. Die neue Komponente soll intern die vorhandenen Dienste in der erforderlichen Reihenfolge verwenden. Die bestehenden Dienste sollen weiterhin unabhängig voneinander nutzbar bleiben.

Weitere Oberflächen, beispielsweise eine Desktop-Anwendung, sollen dieselbe vereinfachte Funktion nutzen können, ohne die einzelnen Verarbeitungsschritte kennen zu müssen.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie auf die Rollen der Weboberfläche, der neuen Komponente und der drei vorhandenen Dienste sowie auf die Reihenfolge der Aufrufe ein.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Wartbarkeit oder Erweiterbarkeit der Anwendung.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie die Weboberfläche, die neue Komponente, die drei Dienste, ihre wesentlichen Methoden und die Abhängigkeiten beziehungsweise Beziehungen dar. Die Datentypen `Buchung` und `Kennzahlen` müssen nicht als eigene Klassen dargestellt werden.
