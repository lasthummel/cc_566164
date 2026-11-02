## Aufgabe 3 – Git-Workflow nachvollziehen

Eine Entwicklerin soll eine neue Exportfunktion ergänzen, ohne die laufende Entwicklung auf `main` direkt zu verändern.

### 3.1 Konzepte erklären

Beschreiben Sie knapp den Zweck von Repository, Working Tree, Staging Area, Commit, Branch, Merge und Remote-Repository.

- Repository: verwaltet Projektdateien und Versionsgeschichte.
- Working Tree: ausgecheckte Dateien, an denen gearbeitet wird.
- Staging Area: Auswahl der Änderungen für den nächsten Commit.
- Commit: dokumentierter Versionsstand mit Metadaten und Bezug auf vorherige Zustände.
- Branch: benannte Entwicklungslinie für unabhängig bearbeitbare Änderungen.
- Merge: integriert Änderungen aus einer Entwicklungslinie in eine andere.
- Remote-Repository: über eine Verbindung erreichbares Repository zum Austausch von Änderungen.

### 3.2 Ablauf entwerfen

Beschreiben Sie einen sinnvollen Ablauf von der Erstellung eines Branches bis zur Übernahme der geprüften Funktion in `main`. Nennen Sie mindestens sechs Schritte in sinnvoller Reihenfolge.

1. Aktuellen Stand von `main` aktualisieren.
2. Feature-Branch von `main` erstellen.
3. Funktion implementieren.
4. Änderungen prüfen und Tests ausführen.
5. Gewünschte Änderungen stagen.
6. Aussagekräftigen Commit erstellen.
7. Branch zum Remote-Repository übertragen.
8. Änderungen überprüfen lassen, beispielsweise in einem Pull Request.
9. Nach erfolgreicher Prüfung in `main` mergen.
10. Integrierten Stand erneut testen.

Ein Pull Request ist nicht in jedem Prozess zwingend; entscheidend sind nachvollziehbare, überprüfbare und kontrolliert integrierte Änderungen.

### 3.3 Fehler erkennen

Ein Teammitglied arbeitet direkt auf `main`, speichert Dateien und sendet sie per E-Mail an das Team. Es gibt weder Commits noch eine gemeinsame Versionsgeschichte.

Nennen Sie drei konkrete Risiken und ordnen Sie jedem eine geeignete Gegenmaßnahme zu.

| Risiko | Gegenmaßnahme |
|---|---|
| Unklarer oder widersprüchlicher Dateistand | Gemeinsames Git-Repository und nachvollziehbare Commits |
| Überschreiben paralleler Änderungen | Feature-Branches und kontrolliertes Mergen |
| Nicht nachvollziehbare Fehlerursache | Commit-Historie, Review und automatisierte Tests |
| Unterschiedliche Bibliotheksversionen | Gemeinsame Build-Konfiguration und deklarierte Abhängigkeiten |

Drei unterschiedliche Paare genügen.
