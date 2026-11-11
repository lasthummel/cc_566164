# Fallstudie 2: Technische Lösung, Bedienoberfläche und Qualität

**Zeit:** 90 Minuten  
**Voraussetzung:** Fallstudie 1 zur Nordlicht Bildungstechnik GmbH  
**Kompetenzschwerpunkte:** technische Alternativen anhand von Kriterien vergleichen; UI-Mängel konkret beurteilen; QS-Maßnahmen risikobasiert auswählen und begründen.

**Hinweis:** Wo keine verbindlichen Anforderungen vorliegen, müssen Annahmen als solche gekennzeichnet werden.


## Fortsetzung des Szenarios

In Teil 1 haben Sie Anforderungen und offene fachliche Fragen für die geplante Ausleihverwaltung der Nordlicht Bildungstechnik GmbH untersucht. Nun sollen Sie eine erste Empfehlung zur technischen Umsetzung vorbereiten und einen frühen Entwurf der Bedienoberfläche sowie den geplanten Qualitätssicherungsansatz bewerten.

Die Geschäftsleitung möchte die erste Version in etwa zwölf Wochen einsetzen. Die IT-Abteilung weist darauf hin, dass die Anwendung im vorhandenen Unternehmensnetz betrieben werden muss. Die genaue Infrastruktur und ihre Leistungsfähigkeit sind noch zu prüfen.

## Technische Lösungsalternativen

Das Team hat zwei realistische Optionen für die erste Version zusammengestellt:

| Kriterium | **Option A: Bestehende interne Plattform erweitern** | **Option B: Eigenständige Webanwendung entwickeln** |
|---|---|---|
| Entwicklungsbasis | Vorhandene interne Java-/Spring-Boot-Anwendung erweitern | Neue Java-/Spring-Boot-Anwendung mit eigenem Projektaufbau |
| Teamkenntnisse | Eine Person kennt die Plattform; die zweite kennt JavaScript und muss sich in vorhandene Konventionen einarbeiten | Java-/Spring-Boot-Kenntnisse sind bei einer Person vorhanden; die zweite kann die Oberfläche mit JavaScript umsetzen |
| Infrastruktur | Betrieb und Monitoring sind bereits vorhanden; Eignung und Kapazität müssen geprüft werden | Deployment und Monitoring müssen eingerichtet oder aus vorhandenen Betriebsdiensten übernommen werden |
| Integration | Anmeldung über den bestehenden Verzeichnisdienst ist bereits integriert | Anbindung an den Verzeichnisdienst muss umgesetzt werden |
| Wartbarkeit | Bestehende Architektur und Abhängigkeiten müssen verstanden und eingehalten werden | Architektur kann für den konkreten Zweck schlank gestaltet werden; Verantwortung für Struktur und Dokumentation liegt beim Team |
| Erweiterbarkeit | Abhängig von den Erweiterungsmöglichkeiten und Konventionen der Plattform | REST-Schnittstellen und modulare Komponenten können gezielt vorgesehen werden |
| Kosten und Zeit | Potenziell geringerer Startaufwand; tatsächlicher Aufwand hängt von der Erweiterbarkeit der Plattform ab | Höherer initialer Einrichtungsaufwand möglich; langfristiger Aufwand hängt von Wartung und Betrieb ab |
| Risiken | Unbekannte technische Schulden oder Einschränkungen der bestehenden Plattform | Zusätzlicher Integrations-, Betriebs- und Einführungsaufwand |

**Wichtig:** Die Tabelle enthält keine belastbaren Zahlen zu Entwicklungsdauer oder Kosten. Sie erlaubt einen qualitativen Vergleich, aber keine exakte Wirtschaftlichkeitsrechnung.

## A1 – Technische Lösung beurteilen (25 Minuten)

### a)
Vergleichen Sie Option A und Option B anhand von mindestens fünf Kriterien aus der Tabelle. (10 min)

### b)
Geben Sie eine begründete Empfehlung für das weitere Vorgehen. Legen Sie sich nicht allein aufgrund einer Präferenz für ein Framework oder eine Architektur fest. (10 min)

### c) 
Nennen Sie zwei Prüfungen, die vor einer endgültigen Entscheidung noch durchgeführt werden sollten. (5 min)

Ihre Antwort soll mindestens enthalten:
- je zwei Vorteile und Risiken der Optionen,
- eine begründete Empfehlung oder eine begründete bedingte Empfehlung,
- Kriterien, anhand derer die Empfehlung überprüft werden kann.

## Entwurf der Bedienoberfläche

Ein erster Wireframe für die Ausleihanfrage wurde vorgestellt. Der Entwurf enthält folgende Eigenschaften:

1. Die Seite zeigt oben die Überschrift "Geräte buchen".
2. Die Felder "Gerät", "Startdatum" und "Rückgabedatum" haben keine dauerhaft sichtbaren Beschriftungen. Stattdessen stehen die Feldnamen als Platzhalter in den Eingabefeldern.
3. Pflichtfelder sind nicht gekennzeichnet.
4. Bei einem ungültigen Zeitraum erscheint die Meldung "Fehlerhafte Eingabe" am oberen Seitenrand. Die eingegebenen Werte werden teilweise zurückgesetzt.
5. Der Verfügbarkeitsstatus wird ausschließlich durch eine grüne oder rote Fläche dargestellt.
6. Die Schaltfläche "Anfrage senden" bleibt auch nach einem Klick aktiv. Es gibt keine sichtbare Rückmeldung über den Bearbeitungsstatus.
7. Auf schmalen Bildschirmen stehen die drei Eingabefelder weiterhin nebeneinander.
8. Die Geräteverwaltung kann eine Anfrage mit einem einzigen Klick verbindlich annehmen. Vorher wird nicht erneut geprüft, ob inzwischen eine andere Anfrage für dasselbe Gerät angenommen wurde.
9. Es ist nicht erkennbar, ob die Anfrage nur übermittelt oder bereits verbindlich reserviert wurde.

## A2 – UI beurteilen und Verbesserungen entwickeln (20 Minuten)

### a)
Analysieren Sie mindestens fünf konkrete Probleme des Entwurfs. Ordnen Sie die Probleme passenden Aspekten zu, beispielsweise Verständlichkeit, Fehlertoleranz, Barrierefreiheit, responsives Design oder fachliche Rückmeldung. (10 min)

### b)
Entwickeln Sie für drei der Probleme konkrete Verbesserungen. Beschreiben Sie jeweils, wie sich das Verhalten für die Benutzerin oder den Benutzer verändert. (10 min)

Vermeiden Sie reine Wertungen wie "unübersichtlich" oder "nicht benutzerfreundlich". Benennen Sie das konkrete Problem und dessen Auswirkung.

## Qualitätssicherung und Risikobeurteilung

Die IT-Abteilung hat folgende Risiken benannt:

- R1: Zwei nahezu gleichzeitig bearbeitete Anfragen führen zu überlappenden verbindlichen Reservierungen desselben Geräts.
- R2: Benutzer ohne Berechtigung können Anfragen annehmen oder Gerätedaten ändern.
- R3: Eine Anfrage wird nach einem Speicherfehler als erfolgreich angezeigt, obwohl sie nicht dauerhaft gespeichert wurde.
- R4: Änderungen an der Anwendung beschädigen bereits funktionierende Such- oder Ausleihfunktionen.
- R5: Die Anwendung ist auf mobilen Geräten schwer bedienbar.
- R6: Die vorhandene Plattform oder eine ausgewählte Bibliothek verursacht unerwarteten Wartungs- oder Betriebsaufwand.

Zur Auswahl stehen:
- Anforderungs- und Architekturreview,
- Code Review,
- statische Codeanalyse,
- automatisierte Unit-Tests,
- Integrationstests mit Datenbank und Berechtigungsprüfung,
- manuelle Usability-Prüfung auf Desktop und Smartphone,
- Prüfung von Bibliothekslizenzen, Wartungsstatus und Abhängigkeiten,
- gezielte Tests für konkurrierende Reservierungsversuche.

## A3 – Qualitätssicherung planen (20 Minuten)

### a)
Ordnen Sie mindestens vier Risiken jeweils einer geeigneten QS-Maßnahme zu. (10 min)

### b)
Begründen Sie für drei Zuordnungen, warum die Maßnahme zum jeweiligen Risiko passt und welche Grenze sie hat. (7 min)

### c)
Erläutern Sie kurz, warum eine erfolgreiche Testsuite allein nicht beweist, dass die Anwendung fehlerfrei ist. (3 min)

Eine Maßnahme darf mehreren Risiken zugeordnet werden. Entscheidend ist die fachliche Begründung, nicht die bloße Anzahl genannter Werkzeuge.
