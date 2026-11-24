# Prüfungssimulation sim04 – Planen eines Softwareproduktes

**Bearbeitungszeit:** 90 Minuten  
**Erreichbare Punkte:** 100  
**Aufgaben:** 4.  


## Ausgangssituation

Die **RegioAnalytik GmbH** untersucht im Auftrag kommunaler Betriebe Wasserproben. Ihre bisherige Probenverwaltung besteht aus Papierformularen und einer gemeinsam verwendeten Tabellenkalkulation. Die Anwendung **ProbenNet** soll Probenentnahmen, Laboreingang, Qualitätsprüfung und die Freigabe von Prüfberichten dokumentieren.

**Außendienst:** erfasst Proben bei der Entnahme auf Tablets, zum Teil ohne Netzverbindung. Erfasst werden eine eindeutige Proben-ID, Entnahmestelle, Entnahmezeitpunkt und ein Freitextkommentar. Später werden Daten an den Server übertragen.

**Labor:** prüft eingegangene Proben auf Vollständigkeit und Plausibilität, erfasst die Untersuchungsergebnisse und kann fehlerhafte oder unvollständige Angaben zur Korrektur an den Außendienst zurückgeben.

**Laborleitung:** gibt ausschließlich vollständig dokumentierte und fachlich bestandene Proben zur Berichterstellung frei. Bei nicht bestandener Qualitätsprüfung wird die Probe gesperrt; eine Freigabe ist dann nicht möglich. Eine gesperrte Probe kann nur nach einer erneuten fachlichen Untersuchung mit neuen Ergebnissen erneut geprüft werden. Eine fachliche Nachprüfung gehört nicht zum hier modellierten Freigabeablauf.

**Auftraggeber:** kann nach Freigabe ausschließlich den eigenen Prüfbericht im Webportal abrufen. Untersuchungsrohdaten und interne Kommentare sind für Auftraggeber nicht sichtbar.

Die Datenübertragung kann bei schlechtem Mobilfunk mehrfach unterbrochen werden. Werden die Daten einer Probe auf zwei Geräten während einer Offlinephase verändert, muss ein Konflikt erkannt werden; eine Änderung darf nicht unbemerkt die andere überschreiben. Der Server verwaltet für jede Probe eine ganzzahlige Versionsnummer, die bei jeder erfolgreichen Änderung um 1 erhöht wird. Änderungen dürfen nur mit der aktuellen Versionsnummer erfolgen.

Anforderungen: 25 Außendienstgeräte, höchstens 100 gleichzeitige Portalzugriffe, personenbezogene Daten in Mitarbeiterkommentaren möglich; der erste produktive Einsatz ist in drei Monaten vorgesehen. Das Labor muss auch während eines kurzfristigen Ausfalls des Mobilfunknetzes weiterarbeiten können.

## Aufgabe 1: Spezifikation und Prüfbedingungen (24 Punkte)

### 1a – 8 Punkte

Formulieren Sie **vier unterschiedliche funktionale Anforderungen** an ProbenNet als prüfbare Aussagen. Nennen Sie jeweils ein konkretes beobachtbares Akzeptanzergebnis. (Je 2 Punkte.)

### 1b – 8 Punkte

Die Auftraggeberseite notiert zwei Anforderungen: "Synchronisation muss zuverlässig sein" und "Das Portal muss schnell reagieren". Formulieren Sie für beide **je ein messbares nichtfunktionales Akzeptanzkriterium** einschließlich einer sinnvollen Messbedingung. Plausible Zielwerte dürfen als Projektfestlegung eingeführt werden. (Je 4 Punkte.)

### 1c – 8 Punkte

Der Server besitzt für Probe P-510 die Version 7. Bei jedem erfolgreich angenommenen Änderungsauftrag wird die Version um genau 1 erhöht. Ankommende Aufträge werden in der dargestellten Reihenfolge vollständig nacheinander verarbeitet. Ein Auftrag wird genau dann angenommen, wenn seine übermittelte Basisversion der aktuellen Serverversion entspricht; abgelehnte Aufträge verändern keine Daten.

| Auftrag | Gerät | übermittelte Basisversion |
|---|---|---:|
| A | Tablet Ost | 7 |
| B | Tablet West | 7 |
| C | Tablet Ost | 8 |
| D | Tablet West | 9 |

Ermitteln Sie für jeden Auftrag, ob er angenommen wird und welche Serverversion **unmittelbar danach** vorliegt. (Vier Zeilen à 2 Punkte.)

## Aufgabe 2: Architektur, Schnittstelle und Risiken (26 Punkte)

### 2a – 8 Punkte

Vergleichen Sie (A) eine Webanwendung, die bei jedem Speichervorgang eine permanente Serververbindung voraussetzt, und (B) eine App mit lokaler Zwischenspeicherung und nachträglicher Synchronisation. Erläutern Sie **je zwei fallbezogene Vor- oder Nachteile** pro Variante. (Vier Aspekte à 2 Punkte.)

### 2b – 10 Punkte

Entwerfen Sie einen REST-Aufruf zum Ändern des Freitextkommentars einer bestehenden Probe P-510. Legen Sie **HTTP-Methode, URI, ein JSON-Beispiel für den neuen Kommentar, die technische Übermittlung der Basisversion, einen geeigneten Erfolgsstatus und zwei passende Fehlerstatusfälle** fest. Die Identität der bearbeitenden Person ist aus einem vorhandenen Zugangstoken bekannt. Gehen Sie davon aus, dass eine Versionsprüfung atomar mit der Änderung erfolgt. (Methode/URI 2; JSON 2; Basisversion 2; Erfolg 1; zwei Fehlerfälle 3 Punkte.)

### 2c – 8 Punkte

Analysieren Sie zwei unterschiedliche technische oder organisatorische Risiken beim Einsatz von ProbenNet. Beschreiben Sie zu jedem Risiko **eine konkrete Folge und eine geeignete Gegenmaßnahme**. Die Risiken müssen aus unterschiedlichen Problemfeldern stammen (z. B. Datensicherheit und Ausfallsicherheit). (Je 4 Punkte.)

## Aufgabe 3: UML-Aktivitätsdiagramm zur Probenfreigabe (24 Punkte)

Für den **hier ausschließlich betrachteten Freigabeablauf** gelten folgende Regeln:

1. Das Labor prüft die Vollständigkeit der Probendokumentation.
2. Bei fehlenden Angaben fordert das Labor eine Korrektur vom Außendienst an. Der Außendienst ergänzt die Angaben. Anschließend prüft das Labor die Vollständigkeit erneut. Dieser Vorgang kann mehrfach auftreten.
3. Ist die Dokumentation vollständig, prüft das Labor die bereits vorliegenden Untersuchungsergebnisse anhand der fachlichen Qualitätskriterien.
4. Bestehen die Ergebnisse die Qualitätsprüfung nicht, sperrt das Labor die Probe und informiert den Außendienst. Der betrachtete Ablauf endet.
5. Bestehen die Ergebnisse die Qualitätsprüfung, gibt die Laborleitung die Probe frei. Das System stellt den zugehörigen Bericht für den Auftraggeber bereit. Der betrachtete Ablauf endet.

### 3a – 16 Punkte

Erstellen Sie ein vollständiges **UML-Aktivitätsdiagramm** für diesen Ablauf mit Start- und Endknoten, Aktivitäten, Entscheidungen, beschrifteten Guard-Bedingungen und der Korrekturschleife. Kennzeichnen Sie die Zuständigkeit von Labor, Außendienst, Laborleitung und System durch Swimlanes oder eine gleichwertig eindeutige Zuordnung.

### 3b – 8 Punkte

Erläutern Sie **eine notwendige serverseitige Berechtigungsprüfung und eine notwendige serverseitige Prüfung der fachlichen Freigabebedingungen** vor dem Freigeben einer Probe. Begründen Sie jeweils kurz, warum eine ausschließliche Prüfung im Frontend nicht ausreicht. (Je 4 Punkte.)

## Aufgabe 4: Bedienoberfläche und Qualitätssicherung (26 Punkte)

Auf einem Tablet soll der Außendienst eine neue Probe erfassen. Ein erster Entwurf sieht vor:

- Die Proben-ID kann nur durch einen sehr kleinen, nicht beschrifteten Scan-Knopf erfasst werden.
- Datum und Uhrzeit sind ein einzelnes frei beschreibbares Textfeld.
- Der Status einer Offline-Speicherung wird nicht angezeigt.
- Bei Synchronisationskonflikten zeigt die Anwendung lediglich "Fehler 42".
- Das Kommentarformular besitzt keine beschrifteten Eingabefelder und keine Tastaturnavigation.

### 4a – 9 Punkte

Analysieren Sie **drei unterschiedliche Probleme** und erläutern Sie jeweils eine konkrete Auswirkung auf Benutzbarkeit oder Barrierefreiheit. (Je 3 Punkte.)

### 4b – 9 Punkte

Entwerfen Sie eine kompakte **textuelle Bedienoberfläche mit genau sechs fachlichen Eingabe- bzw. Anzeigeelementen**. Nennen Sie je Element den Bedienelementtyp und die fachliche Funktion. Die Oberfläche muss eine manuelle Eingabe der Proben-ID ermöglichen und den Offline-/Synchronisationszustand erkennen lassen. Eine zusätzliche Speicheraktion darf ergänzt werden und wird nicht mitgezählt. (Sechs Elemente à 1,5 Punkte.)

### 4c – 8 Punkte

Entwickeln Sie vier voneinander verschiedene Testfälle für die Synchronisation: erfolgreicher Erstversuch, Offline-Zwischenspeicherung, Versionskonflikt und wiederholte Übertragung nach unklarer Antwort. Geben Sie für jeden Test **Ausgangslage/Aktion und erwartetes beobachtbares Ergebnis** an. Für den Wiederholungsfall ist anzunehmen, dass jede Änderung eine unveränderliche, global eindeutige Auftrags-ID erhält und ein bereits erfolgreich verarbeiteter Auftrag beim erneuten Empfang ohne erneute Änderung bestätigt wird. (Vier Testfälle à 2 Punkte.)
