# Aufgabe – Aktualisierung einer Benutzeroberfläche

Ein Entwicklungsteam erstellt eine Desktop-Anwendung zur Verwaltung von Dateien auf einem Server.

Die grafische Benutzeroberfläche (GUI) enthält zwei voneinander unabhängige Komponenten:
- Speicherübersicht: Zeigt die Gesamtgröße aller gespeicherten Dateien sowie den noch verfügbaren Speicherplatz innerhalb des zugewiesenen Speicherkontingents an.
- Dateiliste: Zeigt die Dateien des aktuell ausgewählten Ordners sowie deren Anzahl an.

Wenn eine Datei gelöscht wird, sollen beide GUI-Komponenten automatisch über die Änderung informiert werden und ihre angezeigten Informationen aktualisieren.

Die Dateiverwaltung soll dabei unabhängig von den konkreten GUI-Komponenten implementiert werden. Zukünftig sollen weitere Komponenten hinzugefügt werden können, die ebenfalls auf Änderungen reagieren.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen der Dateiverwaltung und der GUI-Komponenten sowie den Ablauf nach dem Löschen einer Datei ein.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Erweiterbarkeit der Anwendung.

d) Erstellen Sie ein Klassendiagramm, das die Lösung skizziert. 