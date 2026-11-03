## Aufgabe 2 – Drittanbieterbibliothek auswählen

Ein Unternehmen entwickelt einen internen Terminservice. Für die Verarbeitung von Kalenderdaten soll eine Drittanbieterbibliothek eingesetzt werden. Drei Kandidaten wurden vorgeprüft.

| Kriterium | ChronosLite | CalendarPro | DateWorks |
|---|---:|---:|---:|
| Erfüllung der benötigten Funktionen (0–5) | 4 | 5 | 3 |
| Dokumentation und Lernaufwand (0–5) | 4 | 3 | 5 |
| Wartungsaktivität und Release-Transparenz (0–5) | 4 | 5 | 2 |
| Integrationsaufwand (0–5) | 5 | 3 | 4 |
| Gesamtkosten über 3 Jahre (0–5; 5 = günstig) | 5 | 3 | 4 |
| Lizenzprüfung | MIT | proprietär, kommerzielle Nutzung laut Angebot erlaubt | GPL-3.0 |

Weitere Rahmenbedingungen:
- Die Anwendung ist proprietär und soll als geschlossene Software an Kunden ausgeliefert werden.
- Der Einkauf verlangt eine nachvollziehbare Prüfung der Lizenzbedingungen.
- Sicherheitsupdates müssen während der geplanten Nutzungsdauer realistisch verfügbar sein.
- Die Werte in der Tabelle sind interne Einschätzungen, keine unabhängigen Messwerte.

a) Nenne mindestens sechs Kriterien, die bei der Auswahl einer Drittanbieterbibliothek relevant sind. Ordne sie den Bereichen Funktionalität, Technik/Betrieb, Wirtschaftlichkeit und Recht/Lizenz zu.

### Funktionalität
Erfüllung der benötigten Funktionen

### Technik/Betrieb
Wartungsaktivität und Release-Transparenz
Dokumentation und Lernaufwand
Integrationsaufwand

### Wirtschaftlichkeit
Gesamtkosten über 3 Jahre

### Recht/Lizenz
Lizenzprüfung


b) Identifiziere mögliche KO-Kriterien oder Risiken aus den Rahmenbedingungen. Erkläre, warum eine reine Addition der Tabellenwerte nicht ausreicht.

Die GPL-3.0-Lizenz von DateWorks kann bei einer proprietären Auslieferung rechtliche Pflichten auslösen. Ob und in welcher Weise sie greifen, hängt unter anderem von der konkreten Nutzung, Verknüpfung, Weitergabe und Lizenzkonstellation ab. Deshalb darf nicht pauschal behauptet werden, GPL bedeute unter allen Umständen automatisch, dass das gesamte Unternehmen seine Software veröffentlichen muss. Die konkrete Lizenzsituation ist fachkundig zu prüfen.

CalendarPro ist laut Szenario kommerziell nutzbar, aber die konkreten Vertragsbedingungen, Gebühren, Laufzeit, Audit- und Weitergaberegeln müssen geprüft werden. ChronosLite verwendet MIT; auch dabei sind Lizenztext, Copyright-Hinweise und sonstige Bedingungen zu beachten.

Unklare Sicherheitswartung kann ein KO-Kriterium sein, wenn notwendige Updates nicht zuverlässig bereitgestellt werden. Eine Nutzwertsumme darf zwingende rechtliche oder betriebliche Anforderungen nicht kompensieren.

c) Erstelle eine gewichtete Nutzwertanalyse. Verwende folgende Gewichte: Funktionalität 30 %, Dokumentation 15 %, Wartung 25 %, Integration 15 %, Kosten 15 %. Berechne die gewichteten Werte für alle drei Kandidaten. Lizenzrechtliche KO-Kriterien sind separat zu behandeln und dürfen nicht durch einen hohen Nutzwert kompensiert werden.

Berechnung: Teilwert = Bewertung (0–5) × Gewicht. Die Summe wird auf einer Skala von 0 bis 5 angegeben.

| Kriterium | Gewicht | ChronosLite | CalendarPro | DateWorks |
|---|---:|---:|---:|---:|
| Funktionalität | 0,30 | 4 × 0,30 = 1,20 | 5 × 0,30 = 1,50 | 3 × 0,30 = 0,90 |
| Dokumentation | 0,15 | 4 × 0,15 = 0,60 | 3 × 0,15 = 0,45 | 5 × 0,15 = 0,75 |
| Wartung | 0,25 | 4 × 0,25 = 1,00 | 5 × 0,25 = 1,25 | 2 × 0,25 = 0,50 |
| Integration | 0,15 | 5 × 0,15 = 0,75 | 3 × 0,15 = 0,45 | 4 × 0,15 = 0,60 |
| Kosten | 0,15 | 5 × 0,15 = 0,75 | 3 × 0,15 = 0,45 | 4 × 0,15 = 0,60 |
| **Summe** | **1,00** | **4,30** | **4,10** | **3,35** |

Die Werte sind eine Modellrechnung auf Grundlage der vorgegebenen Einschätzungen, keine objektiven Qualitätsmessungen. ChronosLite erzielt rechnerisch 4,30; das ist keine automatische Freigabe. Die Lizenz- und Wartungsprüfung bleibt eigenständig.

d) Formuliere eine Entscheidungsempfehlung mit mindestens zwei Argumenten und einem verbleibenden Prüfpunkt.

Eine plausible Empfehlung ist **ChronosLite vorbehaltlich einer technischen und rechtlichen Abschlussprüfung**, weil es den höchsten Nutzwert erreicht, gut integrierbar und kostengünstig ist und eine MIT-Lizenz angegeben ist. Vor der Freigabe sind insbesondere die tatsächliche Wartungsaktivität, bekannte Schwachstellen, unterstützte Laufzeitversionen und die korrekte Erfüllung der Lizenzpflichten zu prüfen.

CalendarPro ist eine sinnvolle Alternative, wenn die höhere funktionale Abdeckung und vertraglich gesicherte Wartung den höheren Preis rechtfertigen. DateWorks ist angesichts des Wartungsrisikos und der erforderlichen Lizenzprüfung weniger attraktiv.

Andere Entscheidungen sind akzeptabel, wenn man Prioritäten und Annahmen klar benennt.
