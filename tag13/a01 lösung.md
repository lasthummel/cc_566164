# Aufgabe - Relationale Datenmodelle und Normalisierung

## A1 – Grundbegriffe und Schlüssel

Gegeben sind die Tabellen:

**KUNDE**

| KundenID | Name | Ort |
|---:|---|---|
| 101 | Jana Wolf | Mainz |
| 102 | Amir Kaya | Bonn |
| 103 | Jana Wolf | Mainz |

**AUFTRAG**

| AuftragsID | KundenID | Bestelldatum |
|---:|---:|---|
| 5001 | 101 | 2026-03-01 |
| 5002 | 101 | 2026-03-04 |
| 5003 | 102 | 2026-03-05 |

1. Bestimmen Sie für jede Tabelle einen geeigneten Primärschlüssel.
2. Bestimmen Sie den Fremdschlüssel und die Tabelle, in der er liegt.
3. Erläutern Sie, warum Name kein geeigneter Primärschlüssel für KUNDE ist.
4. Erklären Sie, warum KundenID in AUFTRAG mehrfach vorkommen darf.

**Lösung**  

1. KUNDE: KundenID; AUFTRAG: AuftragsID.
2. KundenID in AUFTRAG ist ein FK auf KUNDE(KundenID).
3. Name ist nicht eindeutig: Jana Wolf kommt zweimal vor. Namen können sich außerdem ändern.
4. Ein Kunde kann mehrere Aufträge besitzen. Deshalb darf dieselbe KundenID in mehreren Zeilen von AUFTRAG vorkommen. Ein FK muss nicht eindeutig sein.


## A2 – Kardinalitäten aus fachlichen Aussagen ableiten

Ordnen Sie jeder Beziehung eine Kardinalität zu.

1. Ein Kunde kann mehrere Aufträge besitzen. Jeder Auftrag gehört genau einem Kunden.
2. Ein Mitarbeitender ist genau einer Abteilung zugeordnet. Eine Abteilung kann mehrere Mitarbeitende haben.
3. Ein Lernender kann mehrere Kurse besuchen. Jeder Kurs kann von mehreren Lernenden besucht werden.

**Lösung**  
1. Kunde – Auftrag - 1:n
2. Abteilung – Mitarbeitender - 1:n
3. Lernender – Kurs - n:m


## Aufgabe 4 – Normalformen erkennen

Die folgende Tabelle wird verwendet, um Kursanmeldungen zu verwalten:

| LernendenID | LernendenName | KursIDs | Kursnamen |
|---:|---|---|---|
| 10 | Eva Roth | K1, K2 | SQL, Java |
| 11 | Noah Beck | K2 | Java |

1. Beschreiben Sie den Verstoß gegen die 1. Normalform.
2. Erklären Sie, warum das bloße Aufteilen der Zeichenfolge in einzelne Zeilen noch nicht automatisch 2NF garantiert.
3. Nennen Sie zwei mögliche Anomalien, die durch Redundanzen entstehen können.

**Lösung**  

1. Verstoß gegen 1NF: KursIDs und Kursnamen enthalten mehrere Werte in einer Zelle. Für das dargestellte Modell sind das keine einzelnen Attributwerte.

2. Einzelne Zeilen pro Anmeldung beseitigen die mehrwertigen Zellen, lösen aber noch keine Abhängigkeiten auf. LernendenName hängt nur von LernendenID ab, Kursname nur von KursID. Bei einem zusammengesetzten Schlüssel (LernendenID, KursID) wären dies partielle Abhängigkeiten und damit ein Verstoß gegen 2NF.

3. Mögliche Anomalien:
   - Änderungsanomalie: Ein Kursname muss in mehreren Anmeldungen geändert werden.
   - Einfügeanomalie: Ein Kurs kann möglicherweise nicht ohne Anmeldung gespeichert werden.
   - Löschanomalie: Beim Löschen der letzten Anmeldung gehen eventuell die einzigen Kursdaten verloren.
