## Aufgabe 3 – REST und JSON prüfen

Ein Terminservice stellt Termine unter `/api/appointments` bereit. Jeder Termin hat eine ID, einen Betreff und einen Startzeitpunkt.

Ein Kollege schlägt folgende API vor:

| Zweck | HTTP-Methode und Pfad | Vorgeschlagene Antwort |
|---|---|---|
| Alle Termine abrufen | `GET /api/appointments` | `200 OK` |
| Einen Termin abrufen | `GET /api/appointments/42` | `200 OK` oder `404 Not Found` |
| Neuen Termin anlegen | `GET /api/appointments/new` | `200 OK` |
| Termin mit ID 42 ändern | `PUT /api/appointments/42` | `200 OK` |
| Termin mit ID 42 löschen | `DELETE /api/appointments/42` | `204 No Content` |

a) Beurteile die API-Zuordnung. Korrigiere die Operation zum Anlegen eines Termins und erkläre, warum `GET` dafür ungeeignet ist.

Ein neues Objekt wird typischerweise über `POST /api/appointments` angelegt. `GET` ist für das Abrufen von Repräsentationen vorgesehen und sollte keine fachliche Zustandsänderung auslösen. Ein erfolgreicher POST kann beispielsweise `201 Created` mit einer `Location`-Angabe auf die neu erzeugte Ressource zurückgeben.

b) Nenne je einen passenden Statuscode für erfolgreiches Anlegen, erfolgreiche Änderung mit Antwortkörper, erfolgreiche Löschung ohne Antwortkörper und eine ungültige Anfrage.

- Anlegen erfolgreich: `201 Created`;
- Änderung erfolgreich mit Repräsentation: `200 OK` (alternativ bei geeigneter Semantik `204 No Content`, dann aber ohne Antwortkörper);
- Löschen erfolgreich ohne Antwortkörper: `204 No Content`;
- syntaktisch oder fachlich ungültige Anfrage: häufig `400 Bad Request`.


c) Prüfe dieses JSON. Benenne alle Syntaxfehler und gib eine korrigierte Fassung aus. (5 Punkte)

```json
{
  "id": 42,
  "subject": "Projektbesprechung",
  "start": "2026-11-02T09:30:00",
  "participants": ["Mira", "Jonas",],
}
```

Fehler: Das letzte Element eines Arrays darf kein nachgestelltes Komma haben. Auch nach dem letzten Objektattribut darf kein Komma stehen.

Korrigierte Fassung:

```json
{
  "id": 42,
  "subject": "Projektbesprechung",
  "start": "2026-11-02T09:30:00",
  "participants": ["Mira", "Jonas"]
}
```

d) Ein Client erhält `401 Unauthorized`, obwohl der Termin existiert. Erkläre, warum daraus nicht folgt, dass der Termin nicht vorhanden ist, und nenne eine sinnvolle nächste Prüfung. (2 Punkte)

`401 Unauthorized` weist typischerweise auf fehlende oder ungültige Authentifizierungsinformationen hin. Daraus lässt sich nicht auf die Existenz der Ressource schließen. Zu prüfen sind beispielsweise Token, Ablaufzeit, Authentifizierungsschema und Header. `403 Forbidden` wäre eher eine Berechtigungsablehnung bei bereits erkannter Identität.

e) Erläutere kurz den Unterschied zwischen Endpoint, HTTP-Methode und JSON-Body. (3 Punkte)

- Endpoint: adressierbarer API-Zugang, in der Praxis meist durch HTTP-Methode und URL/Pfad bestimmt;
- HTTP-Methode: beschreibt die beabsichtigte Operation bzw. Semantik, etwa GET, POST, PUT oder DELETE;
HTTP-Methode (Verb): Definiert die Aktion, die auf dem Endpoint ausgeführt werden soll (z. B. GET für Lesen, POST für Erstellen, DELETE für Löschen). Sie beantwortet die Frage: Was will ich damit tun?

- JSON-Body: Nutzdaten einer Anfrage oder Antwort, serialisiert im JSON-Format.
