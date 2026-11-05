# Aufgabe – Austauschbare Preisberechnung

Ein Entwicklungsteam erstellt für einen Onlineshop eine Komponente zur Berechnung von Verkaufspreisen.

Ausgehend von einem übergebenen Betrag sollen unterschiedliche Berechnungsarten unterstützt werden:

- Normalpreis: Der übergebene Betrag wird unverändert als Verkaufspreis zurückgegeben.
- Rabattpreis: Auf den übergebenen Betrag wird ein Rabatt von 10 % gewährt.

Die Anwendung soll die gewünschte Berechnungsart zur Laufzeit auswählen und auch bei einem bereits vorhandenen Preisrechner wechseln können. Der Preisrechner soll die konkreten Berechnungsregeln nicht selbst enthalten und unabhängig von den einzelnen Berechnungsarten bleiben.

Zukünftig sollen weitere Preisberechnungen, beispielsweise für Sonderaktionen oder Kunden mit besonderen Konditionen, ergänzt werden können, ohne die vorhandene Preisberechnungskomponente für jede neue Variante ändern zu müssen.

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen des Preisrechners und der unterschiedlichen Berechnungsarten sowie auf den Ablauf einer Preisberechnung und den Wechsel der Berechnungsart zur Laufzeit ein.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Erweiterbarkeit der Anwendung.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie dabei die für die Preisberechnung erforderlichen Klassen beziehungsweise Schnittstellen, deren wesentliche Methoden sowie die Beziehungen zwischen ihnen dar.
