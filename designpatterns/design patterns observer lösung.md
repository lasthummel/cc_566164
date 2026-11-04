# Aufgabe – Aktualisierung einer Benutzeroberfläche

Ein Entwicklungsteam erstellt eine Desktop-Anwendung zur Verwaltung von Dateien auf einem Server.

Die grafische Benutzeroberfläche (GUI) enthält zwei voneinander unabhängige Komponenten:
- Speicherübersicht: Zeigt die Gesamtgröße aller gespeicherten Dateien sowie den noch verfügbaren Speicherplatz innerhalb des zugewiesenen Speicherkontingents an.
- Dateiliste: Zeigt die Dateien des aktuell ausgewählten Ordners sowie deren Anzahl an.

Wenn eine Datei gelöscht wird, sollen beide GUI-Komponenten automatisch über die Änderung informiert werden und ihre angezeigten Informationen aktualisieren.

Die Dateiverwaltung soll dabei unabhängig von den konkreten GUI-Komponenten implementiert werden. Zukünftig sollen weitere Komponenten hinzugefügt werden können, die ebenfalls auf Änderungen reagieren.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

Observer (Beobachter)

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen der Dateiverwaltung und der GUI-Komponenten sowie den Ablauf nach dem Löschen einer Datei ein.

Die Dateiverwaltung übernimmt die Rolle des Subjects (Observable). Sie verwaltet den Zustand der Dateien und ermöglicht es interessierten Komponenten, sich als Beobachter (Observer) zu registrieren oder abzumelden.

Die Speicherübersicht und die Dateiliste übernehmen die Rolle der Observer. Beide implementieren eine gemeinsame Benachrichtigungsschnittstelle, beispielsweise eine Methode `update`.

Beim Löschen einer Datei läuft die Aktualisierung wie folgt ab:

1. Die Dateiverwaltung löscht die Datei und aktualisiert ihren verwalteten Zustand.
2. Anschließend benachrichtigt sie die registrierten Observer über die Änderung, beispielsweise durch Aufruf ihrer `update`-Methoden.
3. Die Speicherübersicht ermittelt die neue Gesamtgröße der gespeicherten Dateien und den verbleibenden Speicherplatz und aktualisiert ihre Anzeige.
4. Die Dateiliste prüft beziehungsweise ermittelt die Dateien des aktuell ausgewählten Ordners erneut und aktualisiert ihre Anzeige..

Die Dateiverwaltung kennt dafür nur die gemeinsame Observer-Schnittstelle, nicht die konkreten GUI-Klassen. Die Daten können bei der Aktualisierung vom Observer abgefragt werden (Pull-Prinzip) oder zusammen mit der Benachrichtigung übertragen werden (Push-Prinzip).

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Erweiterbarkeit der Anwendung.


Durch die lose Kopplung zwischen Subject und Observer können weitere GUI-Komponenten hinzugefügt werden, ohne die Dateiverwaltung für jede neue Komponente anpassen zu müssen.

Beispielsweise kann eine Aktivitätsanzeige, die gelöschte Dateien protokolliert, die gemeinsame Observer-Schnittstelle implementieren und sich bei der Dateiverwaltung registrieren. Sie erhält dann ebenfalls Benachrichtigungen über Dateiänderungen.

Voraussetzung ist, dass die Dateiverwaltung ihre Benachrichtigungen über die gemeinsame Schnittstelle an die registrierten Observer sendet. Dadurch bleibt sie unabhängig von Anzahl und konkreter Implementierung der Beobachter.

d) Erstellen Sie ein Klassendiagramm, das die Lösung skizziert. 

Das Diagramm liegt in einer uxf-Datei vor.

