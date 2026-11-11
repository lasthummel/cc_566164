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

**Lösung**  

**Option A – bestehende Plattform erweitern**  

Vorteile:
- Betriebsumgebung, Monitoring und Anmeldung sind bereits vorhanden; dies kann den Einführungsaufwand reduzieren.
- Der vorhandene Verzeichnisdienst ist bereits integriert.
- Bestehende Betriebsprozesse müssen nicht vollständig neu aufgebaut werden.

Risiken:
- Die Plattform kann technische Schulden oder Einschränkungen aufweisen.
- Das Team muss bestehende Architektur und Konventionen verstehen; dadurch kann die Erweiterung schwieriger werden als erwartet.
- Die tatsächliche Eignung und Kapazität der Plattform ist noch nicht bestätigt.

**Option B – eigenständige Anwendung**  

Vorteile:
- Architektur und Komponenten können gezielt am konkreten Bedarf ausgerichtet werden.
- Schnittstellen und Erweiterungspunkte können von Anfang an bewusst gestaltet werden.
- Unnötige Abhängigkeiten der bestehenden Plattform können vermieden werden.

Risiken:
- Anmeldung, Deployment und Monitoring müssen integriert oder eingerichtet werden.
- Der initiale Entwicklungs- und Betriebsaufwand kann höher sein.
- Das Team muss Architektur, Betrieb und Dokumentation stärker selbst verantworten.

**Kriterien:** Zeit bis zum Einsatz, Integrationsaufwand, Teamkenntnisse, Wartbarkeit, Betrieb, Erweiterbarkeit und Gesamtkosten. Es gibt keine Grundlage, um ohne weitere Prüfung exakte Aufwands- oder Kostenzahlen anzugeben.

### b)
Geben Sie eine begründete Empfehlung für das weitere Vorgehen. Legen Sie sich nicht allein aufgrund einer Präferenz für ein Framework oder eine Architektur fest. (10 min)

**Lösung.**  

Die Option A kann bevorzugt werden, weil Anmeldung, Betrieb und Monitoring bereits integriert sind und die erste Version innerhalb von etwa zwölf Wochen eingesetzt werden soll. Vor einer endgültigen Entscheidung muss das Team jedoch die Erweiterbarkeit, den Zustand der Architektur und die Kapazität der Plattform prüfen. Wenn sich dabei erhebliche Einschränkungen oder hohe Anpassungskosten zeigen, sollte Option B anhand desselben Kriterienkatalogs verglichen werden.

Eine begründete Empfehlung für Option B ist ebenfalls vertretbar, wenn die Antwort beispielsweise nachweist, dass die bestehende Plattform ungeeignet oder nur mit unverhältnismäßigem Aufwand erweiterbar wäre. Solche Erkenntnisse sind im gegebenen Szenario noch nicht belegt; sie müssten daher als zu prüfende Hypothese formuliert werden.

### c) 
Nennen Sie zwei Prüfungen, die vor einer endgültigen Entscheidung noch durchgeführt werden sollten. (5 min)

Ihre Antwort soll mindestens enthalten:
- je zwei Vorteile und Risiken der Optionen,
- eine begründete Empfehlung oder eine begründete bedingte Empfehlung,
- Kriterien, anhand derer die Empfehlung überprüft werden kann.

**Lösung.**  

- Prototyp: eine kleine Erweiterung in Option A umsetzen und Integrationsaufwand sowie Architekturgrenzen prüfen.
- Bestehende Plattform untersuchen: Abhängigkeiten, Wartungsstatus, Sicherheitslage, Dokumentation und Betriebskapazität bewerten.
- Beide Optionen mit demselben Kriterienkatalog und einer groben Aufwandsschätzung vergleichen.
- Lizenzbedingungen und langfristige Wartbarkeit der benötigten Komponenten untersuchen.


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

**Lösung.**  

1. Platzhalter statt dauerhafter Beschriftungen: Der Feldname verschwindet beim Tippen. Das erschwert die Kontrolle eingegebener Werte und ist als Ersatz für ein korrekt zugeordnetes Label ungeeignet.
2. Keine Pflichtfeldkennzeichnung: Benutzer erkennen nicht, welche Angaben zwingend erforderlich sind.
3. Unspezifische Fehlermeldung: "Fehlerhafte Eingabe" erklärt weder die Ursache noch die Korrektur. Die Platzierung am Seitenanfang erschwert die Zuordnung zum Feld.
4. Eingaben werden zurückgesetzt: Nutzende müssen Daten erneut eingeben. Das verschlechtert die Fehlertoleranz und kann zu Abbrüchen führen.
5. Status nur über Rot/Grün: Farbe ist der einzige Informationsträger; die Bedeutung ist für Menschen mit Farbsehschwächen oder bei ungünstiger Darstellung nicht zuverlässig erkennbar.
6. Keine Rückmeldung nach dem Absenden: Es bleibt unklar, ob die Anfrage verarbeitet, gespeichert oder abgewiesen wurde.
7. Schaltfläche bleibt aktiv: Mehrfachklicks können doppelte Anfragen auslösen, wenn die Verarbeitung dies nicht abfängt.
8. Kein responsives Layout: Drei nebeneinanderliegende Felder können auf schmalen Displays zu sehr kleinen Eingabeflächen und schlechter Lesbarkeit führen.
9. Keine erneute Verfügbarkeitsprüfung bei Annahme: Zwischen Anzeige und Entscheidung kann eine andere Anfrage angenommen worden sein. Die Oberfläche kaschiert ein fachliches Integritätsrisiko.
10. Unklarer Status Anfrage/Reservierung: Benutzer können fälschlich annehmen, das Gerät sei bereits verbindlich reserviert.

### b)
Entwickeln Sie für drei der Probleme konkrete Verbesserungen. Beschreiben Sie jeweils, wie sich das Verhalten für die Benutzerin oder den Benutzer verändert. (10 min)

Vermeiden Sie reine Wertungen wie "unübersichtlich" oder "nicht benutzerfreundlich". Benennen Sie das konkrete Problem und dessen Auswirkung.

**Lösung.**  

- Beschriftung und Validierung: Dauerhafte Labels verwenden, Pflichtfelder kenntlich machen und Fehler direkt am betroffenen Feld verständlich erklären. Eingaben bleiben erhalten; Fehlerzustände werden nicht nur farblich kommuniziert.
- Status und Rückmeldung: Während der Verarbeitung einen Ladezustand anzeigen; nach erfolgreichem Speichern eine eindeutige Bestätigung mit Vorgangskennung und Status ausgeben. Bei einem Fehler keine Erfolgsmeldung zeigen und einen nachvollziehbaren Wiederholungsweg anbieten.
- Mehrfachübermittlung: Schaltfläche während der Anfrageverarbeitung vorübergehend deaktivieren und die serverseitige Verarbeitung gegen unbeabsichtigte doppelte Anfragen absichern. Das Deaktivieren der Schaltfläche allein ist kein zuverlässiger Schutz.
- Responsives Layout: Auf schmalen Displays Felder untereinander anordnen und Bedienbarkeit per Tastatur sicherstellen.
- Verfügbarkeitsstatus: Text wie "Verfügbar" bzw. "Für diesen Zeitraum nicht verfügbar" ergänzen; Statusänderung bei verbindlicher Annahme serverseitig erneut prüfen.
- Statusmodell verdeutlichen: Nach Absenden ausdrücklich "Anfrage eingegangen – noch nicht reserviert" anzeigen. Nach Annahme den Status "Verbindlich reserviert" nur dann anzeigen, wenn die Reservierung erfolgreich gespeichert wurde.


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

**Lösung in b)**  

### b)
Begründen Sie für drei Zuordnungen, warum die Maßnahme zum jeweiligen Risiko passt und welche Grenze sie hat. (7 min)

**Lösung.** 

- R1: konkurrierende Reservierungen
QS-Maßnahme: Gezielter Integrationstest mit zwei nahezu gleichzeitigen Reservierungsversuchen; Architektur-/Datenbankreview  
Begründung: Prüft das fachliche Ergebnis unter Konkurrenz und die Durchsetzung der Integritätsregel. Ein einfacher sequenzieller Unit-Test reicht dafür nicht aus.  

- R2: unberechtigte Änderungen  
QS-Maßnahme: Code Review und Integrationstests mit unterschiedlichen Rollen  
Begründung: Prüft, ob Berechtigungen serverseitig durchgesetzt werden. Ein UI-Test allein kann manipulierte Requests nicht ausschließen.  

- R3: falsche Erfolgsmeldung bei Speicherfehler  
QS-Maßnahme: Integrationstest mit kontrolliertem Persistenzfehler; Review der Fehlerbehandlung  
Begründung: Prüft, ob Erfolg nur nach erfolgreicher Speicherung gemeldet wird und Fehler nachvollziehbar behandelt werden.  

- R4: Regression bestehender Funktionen  
QS-Maßnahme: Automatisierte Unit- und Integrationstests, Regressionstests in der CI-Pipeline  
Begründung: Wiederholbare Tests erkennen, ob frühere Funktionen nach Änderungen weiterhin erwartungsgemäß arbeiten.  

- R5: schlechte mobile Bedienbarkeit  
QS-Maßnahme: Usability-Prüfung auf realistischen Viewports und manuelle Tastaturprüfung  
Begründung: Beobachtet tatsächliche Bedienbarkeit und Verständlichkeit; automatisierte Funktionstests erfassen dies nur begrenzt.  

- R6: Wartungs-/Betriebsaufwand durch Abhängigkeiten  
QS-Maßnahme: Review der Architektur und Abhängigkeiten; Prüfung von Lizenz, Wartungsstatus und Sicherheitsmeldungen  
Begründung: Macht technische und rechtliche Risiken vor der Festlegung sichtbar. Die Prüfung muss bei späteren Updates wiederholt werden.  

### c)
Erläutern Sie kurz, warum eine erfolgreiche Testsuite allein nicht beweist, dass die Anwendung fehlerfrei ist. (3 min)

Eine Maßnahme darf mehreren Risiken zugeordnet werden. Entscheidend ist die fachliche Begründung, nicht die bloße Anzahl genannter Werkzeuge.

**Lösung.**  

- Integrationstest für R1: kann Konkurrenzsituationen gezielt prüfen, deckt aber nicht automatisch alle möglichen Zeitabläufe und Lastsituationen ab. Ergänzend sind Architektur- und Datenbankmechanismen zu prüfen.
- Berechtigungstests für R2: prüfen definierte Rollen und Aktionen; ungetestete Endpunkte oder weitere Angriffspfade können weiterhin Fehler enthalten. Review und systematische Testfallableitung ergänzen sich.
- Automatisierte Regressionstests für R4: sind schnell wiederholbar, prüfen aber nur die implementierten Testfälle. Fehlende Tests können fehlerhaftes Verhalten unentdeckt lassen.
- Usability-Prüfung für R5: zeigt Probleme bei den geprüften Nutzenden und Geräten; sie beweist keine vollständige Barrierefreiheit für alle Nutzungssituationen.
- Abhängigkeitsprüfung für R6: kann bekannte Informationen und Lizenzrisiken sichtbar machen, aber nicht garantieren, dass eine Komponente zukünftig gepflegt oder frei von unbekannten Schwachstellen bleibt.
