# Aufgabe – Automatische Paketklassifizierung

## Ausgangssituation

Ein Logistikunternehmen betreibt eine automatisierte Versandstation. Die Software der Versandstation soll eine Liste von Sendungen auswerten und ermitteln, wie viele Sendungen den jeweiligen Versandkategorien zugeordnet werden.

Für jede Sendung stehen folgende Daten zur Verfügung:

| Attribut | Datentyp | Bedeutung |
|---|---|---|
| `kennung` | String | Eindeutige Kennung der Sendung |
| `gewichtKg` | Integer | Gewicht in Kilogramm |
| `prioritaet` | Boolean | Kennzeichnung einer priorisierten Sendung |

Das Gewicht ist stets eine nichtnegative ganze Zahl. Die Liste kann auch leer sein.

## Beschreibung des Programmablaufs

Für die Auswertung der Sendungen ist folgender Ablauf vorgesehen:

1. Zu Beginn werden die vier Zähler für abgelehnte Sendungen, Sperrgutsendungen, Expresssendungen und Standardsendungen jeweils auf 0 gesetzt.

2. Solange noch nicht verarbeitete Sendungen in der Liste vorhanden sind, wird die nächste Sendung aus der Liste gelesen.

3. Zunächst wird die Kennung der Sendung überprüft. Ist die Kennung nicht vorhanden (`null`), leer oder besteht sie ausschließlich aus Leerzeichen, wird der Zähler für abgelehnte Sendungen um 1 erhöht. Weitere Prüfungen dieser Sendung entfallen.

4. Bei einer gültigen Kennung wird das Gewicht der Sendung überprüft. Beträgt das Gewicht mehr als 20 kg, wird der Zähler für Sperrgutsendungen um 1 erhöht. Weitere Prüfungen dieser Sendung entfallen.

5. Beträgt das Gewicht höchstens 20 kg, wird die Priorität der Sendung überprüft:
   - Bei gesetzter Priorität wird der Zähler für Expresssendungen um 1 erhöht.
   - Andernfalls wird der Zähler für Standardsendungen um 1 erhöht.

6. Nach der Klassifizierung wird die Verarbeitung mit der nächsten Sendung fortgesetzt. Jede Sendung darf nur einmal verarbeitet und genau einer Kategorie zugeordnet werden.

7. Nachdem alle Sendungen verarbeitet wurden, werden die Werte der vier Zähler ausgegeben. Anschließend endet der Programmablauf.

## Auftrag - Aktivitätsdiagramm erstellen

Erstellen Sie ein UML-Aktivitätsdiagramm für den beschriebenen Programmablauf.

Stellen Sie die Aktionen, Entscheidungen, Verzweigungsbedingungen, Zusammenführungen und Wiederholungen mithilfe der entsprechenden UML-Notation dar.

Eine konkrete Programmiersprache ist nicht vorgegeben.

**Lösung**  

Die mögliche Lösung befindet sich in der Datei 'a02 lösung versandstation.uxf'.

Java-Code für die Ativität:

```java
int abgelehnt = 0;
int sperrgut = 0;
int express = 0;
int standard = 0;

int i = 0;

while (i < sendungen.size()) {
    var s = sendungen.get(i);

    if (s.kennung == null || s.kennung.trim().isEmpty()) {
        abgelehnt++;
    } else if (s.gewichtKg > 20) {
        sperrgut++;
    } else if (s.prioritaet) {
        express++;
    } else {
        standard++;
    }

    i++;
}

System.out.println(abgelehnt);
System.out.println(sperrgut);
System.out.println(express);
System.out.println(standard);
```
