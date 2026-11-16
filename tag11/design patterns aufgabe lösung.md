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

**Lösung.**  

Facade-Pattern (Fassadenmuster), ein Strukturmuster.

Es stellt einem aufrufenden Programmteil eine vereinfachte Schnittstelle zur Verfügung und verbirgt darüber die Zusammenarbeit mehrerer Komponenten eines Subsystems.


b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie auf die Rollen der Weboberfläche, der neuen Komponente und der drei vorhandenen Dienste sowie auf die Reihenfolge der Aufrufe ein.

**Lösung.**  

Die **BerichtsAnsicht** (Weboberfläche) übernimmt die Rolle des **Clients**. Sie benötigt nur eine Referenz auf die **BerichtsFassade** und ruft deren Methode `erstelleMonatsbericht(jahr : int, monat : int) : byte[]` auf.

Die **BerichtsFassade** übernimmt die Rolle der **Facade**. Sie kennt die drei vorhandenen Dienste und koordiniert deren Aufrufe. Die fachlichen Einzeloperationen bleiben in den jeweiligen Diensten implementiert.

**DatenService**, **StatistikService** und **PdfService** bilden die Komponenten des **Subsystems**.

Die Erstellung des Monatsberichts läuft beispielsweise so ab:

1. Die BerichtsAnsicht ruft `BerichtsFassade.erstelleMonatsbericht(jahr, monat)` auf.
2. Die BerichtsFassade ruft `DatenService.ladeBuchungen(jahr, monat)` auf und erhält die Buchungsliste.
3. Sie übergibt die Buchungsliste an `StatistikService.berechneKennzahlen(...)` und erhält die berechneten Kennzahlen.
4. Sie übergibt die Kennzahlen an `PdfService.erstellePdf(...)` und erhält die PDF-Datei als Byte-Array.
5. Die BerichtsFassade gibt dieses Byte-Array an die BerichtsAnsicht zurück.

Die Weboberfläche muss weder die drei Dienste direkt aufrufen noch ihre Aufrufreihenfolge oder die Zwischenergebnisse verwalten.

Die Fassade ersetzt die vorhandenen Dienste nicht. Sie stellt einen zusätzlichen, vereinfachten Zugriff auf deren Zusammenarbeit bereit. Die Dienste können bei Bedarf auch von anderen Komponenten unabhängig von der Fassade verwendet werden.


c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Wartbarkeit oder Erweiterbarkeit der Anwendung.

**Lösung.**  

**Zentrale Kapselung der Aufrufabfolge:** Ändert sich beispielsweise die Reihenfolge der Verarbeitung oder wird vor der PDF-Erstellung ein weiterer fachlicher Schritt benötigt, kann die erforderliche Koordination in der BerichtsFassade angepasst werden. Die Weboberfläche und weitere Clients bleiben unverändert, solange die öffentliche Methode der Fassade dieselbe Schnittstelle und das erwartete Verhalten beibehält.

Zudem müssen neue Clients nicht erneut wissen, wie die drei Dienste zusammenarbeiten. Sie können die vorhandene Fassadenoperation wiederverwenden.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie die Weboberfläche, die neue Komponente, die drei Dienste, ihre wesentlichen Methoden und die Abhängigkeiten beziehungsweise Beziehungen dar. Die Datentypen `Buchung` und `Kennzahlen` müssen nicht als eigene Klassen dargestellt werden.

**Lösung in der Datei `Facade.uxf`.**  

Die wesentlichen Elemente sind:

- **BerichtsAnsicht (Client):** Besitzt eine gerichtete Beziehung zur BerichtsFassade und verwendet deren öffentliche Operation.
- **BerichtsFassade (Facade):** Besitzt Referenzen auf DatenService, StatistikService und PdfService. Sie stellt `erstelleMonatsbericht(jahr : int, monat : int) : byte[]` als vereinfachten Einstiegspunkt bereit.
- **DatenService, StatistikService und PdfService (Subsystem):** Stellen jeweils ihre eigenen öffentlichen Fachoperationen bereit.
- **Beziehungen:** Die Weboberfläche verwendet die Fassade; die Fassade verwendet die drei Dienste. Eine direkte Beziehung von der Weboberfläche zu den drei Diensten ist für diese Berichtsfunktion nicht erforderlich.

Die gerichteten Assoziationen veranschaulichen, welche Objekte andere Objekte kennen beziehungsweise verwenden. **Vererbung ist für das Facade-Pattern nicht erforderlich.** Auch eine Komposition mit exklusiver Lebensdauerbindung ist nicht vorausgesetzt, da die Subsystemdienste unabhängig nutzbar bleiben.

Das Diagramm ist bewusst vereinfacht: Die Datenklassen `Buchung` und `Kennzahlen` sowie Fehlerbehandlung und die konkrete Bereitstellung der Dienstobjekte sind nicht dargestellt.
