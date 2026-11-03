## Aufgabe 5. Integrierte IHK-nahe Fallstudie

### Szenario

Ein Unternehmen entwickelt eine interne Bestellplattform.

Das System soll:

- von mehreren Entwicklern weiterentwickelt werden,
- langfristig wartbar sein,
- externe Bibliotheken für JSON-Verarbeitung verwenden,
- eine Webschnittstelle für andere Anwendungen bereitstellen,
- eine relationale Datenbank verwenden.

Das Team diskutiert folgende Architektur:

```text
Webbrowser
    ↓
Controller
    ↓
Datenbank
```

Ein Entwickler schlägt außerdem vor:

> „Wir können die JSON-Bibliothek einfach als JAR-Datei in das Projekt kopieren. Dann haben wir keine Probleme mit Dependency Management.“

Ein anderer Entwickler schlägt vor:

> „Wir sollten für jede Funktion ein Singleton verwenden, damit überall dieselben Objekte benutzt werden.“

### Aufgaben

**1.**  
Beurteilen Sie die vorgeschlagene Architektur.

Die Architektur ist problematisch, weil der Controller direkt auf die Datenbank zugreift.

Eine sinnvolle Struktur wäre beispielsweise:

**2.**  
Entwickeln Sie eine geeignete vereinfachte Schichtenstruktur.


Beispiel:

```text
Web/API
   ↓
Controller
   ↓
Service / Geschäftslogik
   ↓
Repository / Datenzugriff
   ↓
Datenbank
```

**3.**  
Erläutern Sie zwei Vorteile der Trennung von Geschäftslogik und Datenzugriff.

Beispielsweise:

- Geschäftslogik ist unabhängig vom konkreten Datenzugriff.
- Datenzugriff kann leichter ausgetauscht werden.
- Geschäftslogik kann isolierter getestet werden.
- Verantwortlichkeiten sind klarer getrennt.

**4.**  
Beurteilen** Sie den Vorschlag, die JSON-Bibliothek manuell als JAR-Datei zu verwalten.

Manuelle JAR-Verwaltung führt unter anderem zu:

- Versionsproblemen
- fehlender Reproduzierbarkeit
- manuellem Aufwand
- schlecht nachvollziehbaren Abhängigkeiten
- schwieriger Aktualisierung

Ein Dependency-Management-System ist grundsätzlich geeigneter.


**5.**  
Nennen Sie zwei Aspekte, die bei der Auswahl der Bibliothek zusätzlich zur technischen Funktionalität berücksichtigt werden sollten.

Beispielsweise:

- Lizenz
- Wartbarkeit
- Sicherheitsstatus
- Community/Hersteller-Support
- Kompatibilität
- Abhängigkeiten
- Updatepolitik

**6.**  
Die Anwendung soll eine Bestellung über

```text
GET /api/orders/4711
```

abrufen.

Beschreiben Sie, welche Rolle Endpoint, HTTP-Methode und JSON-Response besitzen.

- `GET` beschreibt den Abruf einer Ressource.
- `/api/orders/4711` adressiert die Bestellung 4711.
- JSON dient als strukturiertes Datenaustauschformat für die Response.

**7.**  
Die Bestellung existiert nicht.

Nennen und begründen Sie einen geeigneten HTTP-Statuscode.

404 Not Found: Die adressierte Ressource wurde nicht gefunden.


**8.**  
Beurteilen Sie den Vorschlag, zentrale Komponenten grundsätzlich als Singleton umzusetzen. Gehen Sie insbesondere auf die Testbarkeit ein.

Eine pauschale Verwendung des Singleton-Patterns ist kritisch.

Mögliche Probleme:

- global bzw. schwer kontrollierbarer Zustand
- versteckte Abhängigkeiten
- stärkere Kopplung
- erschwerte Isolation von Tests
- Testreihenfolge bzw. gemeinsam genutzter Zustand kann problematisch werden

Eine gute Antwort muss nicht behaupten, Singleton sei „immer falsch“. Entscheidend ist: das Pattern muss zur konkreten Anforderung passen und seine Nebenwirkungen müssen berücksichtigt werden.
