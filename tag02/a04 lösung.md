## Aufgabe 4 – Architektur eines Terminservice (35 Minuten, 16 Punkte)

Der Terminservice soll folgende Aufgaben erfüllen:
- HTTP-Anfragen entgegennehmen und Eingaben validieren;
- Geschäftsregeln prüfen, etwa ob ein Endzeitpunkt nach dem Startzeitpunkt liegt;
- Termine dauerhaft speichern;
- Daten als JSON an Clients zurückgeben;
- automatisierte Tests ermöglichen, ohne bei jedem Test eine echte Datenbank zu benötigen.

a) Schlage eine einfache Architektur mit mindestens drei Verantwortungsbereichen vor. Benenne für jeden Bereich seine Aufgabe.

Eine mögliche Lösung:

1. HTTP-/Controller-Schicht: nimmt HTTP-Anfragen entgegen, prüft Transportdaten und bildet Ergebnisse auf Statuscodes und Antwortkörper ab.
2. Service-/Anwendungsschicht: setzt Anwendungsfälle um, koordiniert Abläufe und führt Geschäftsregeln aus.
3. Persistenzschicht/Repository: kapselt das Speichern und Laden von Terminen.
4. Domänenmodell: repräsentiert Termin und fachliche Invarianten, beispielsweise Startzeitpunkt vor Endzeitpunkt.

Die genaue Schichtenzahl kann variieren. Entscheidend sind klare Verantwortlichkeiten und kontrollierte Abhängigkeiten.

b) Beschreibe den Ablauf einer Anfrage zum Anlegen eines Termins vom HTTP-Eingang bis zur Speicherung und Antwort.

1. Der Controller empfängt die POST-Anfrage und deserialisiert den JSON-Body in ein Eingabeobjekt.
2. Eingaben werden syntaktisch und fachlich validiert; ungültige Daten führen zu einer geeigneten Fehlerantwort.
3. Der Service wendet Geschäftsregeln an und beauftragt das Repository mit dem Speichern.
4. Der Controller gibt beispielsweise `201 Created`, die ID bzw. Repräsentation und optional eine `Location`-Angabe zurück.

c) Erkläre, wie eine klare Schnittstelle zwischen Geschäftslogik und Speicherung die Testbarkeit verbessert.

Wenn der Service von einem Repository-Interface abhängt, kann im Unit-Test ein Fake oder Mock eingesetzt werden. Tests der Geschäftsregeln benötigen dann keine echte Datenbank. Integrations- und Repositorytests bleiben trotzdem notwendig, um Mapping, Transaktionen und tatsächliche Datenbankinteraktion zu prüfen.

d) Ordne MVC ein: Welche Verantwortung übernehmen Model, View und Controller im klassischen MVC-Konzept? Erläutere, warum MVC nicht automatisch die gesamte Systemarchitektur beschreibt.

- Model: Daten und fachlicher Zustand bzw. Anwendungsdaten;
- View: Darstellung der Informationen;
- Controller: nimmt Eingaben entgegen, steuert die Verarbeitung und wählt die passende Darstellung bzw. Antwort.

MVC beschreibt primär die Trennung von Verantwortlichkeiten in einer Anwendung bzw. Präsentationsarchitektur. Persistenz, Deployment, Authentifizierung oder die gesamte Systemlandschaft werden dadurch nicht automatisch vollständig festgelegt.
