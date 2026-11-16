# Aufgabe - Listen verarbeiten

## Ausgangssituation

Ein Unternehmen entwickelt eine Anwendung zur Verwaltung von Fahrzeugen und ihren Besitzern.

Zu jedem Besitzer werden der Name und das zugehörige Fahrzeug gespeichert. Ein Fahrzeug besitzt die Attribute Hersteller und Baujahr.

Die Anwendung soll die Daten mehrerer Besitzer in einer Liste verwalten und verschiedene Auswertungen ermöglichen.

Der folgende Java-Code bildet die Ausgangsbasis für die Aufgaben.

```java
import java.util.*; // importiert List und ArrayList 

class Besitzer {
    String name;
    Auto auto;

    Besitzer(String name, Auto auto) {
        this.name = name;
        this.auto = auto;
    }
}

class Auto {
    String hersteller;
    int baujahr;

    public Auto(String hersteller, int baujahr) {
        this.hersteller = hersteller;
        this.baujahr = baujahr;
    }    
}

public class App {
    static void print(List<Besitzer> listBesitzer) {
        for (int i=0; i<listBesitzer.size(); i++) {
            Besitzer besitzer = listBesitzer.get(i);
            Auto auto = besitzer.auto;
            System.out.printf("%2d. %5s - %5s, %d%n", i+1, besitzer.name, auto.hersteller, auto.baujahr);
        }
    }

    static List<Besitzer> createExampleList(String[] namen, Auto[] autos) {
        return null;
    }

    public static void main(String[] args) {
        String[] namen = { "Anna", "Tom", "Peter", "Paul", "Mary" };

        Auto[] autos = {
            new Auto("VW", 2003),
            new Auto("Audi", 2011),
            new Auto("VW", 2025),
            new Auto("Opel", 2021),
            new Auto("VW", 2017),
        };

        List<Besitzer> list = createExampleList(namen, autos);
        print(list);
    }
}
```

**Hinweise und Annahmen:**   

- die vorgestellten Klassen wurden extra einfach gehalten, um den Codeumfang zu reduzieren, und sind nicht als Empfehlung für die Realisierung in der Praxis gedacht.
- die zu erstellenden Methoden können davon ausgehen, dass die Argumente korrekt sind.

## A1 - Liste erstellen
Beim Ausführen der main-Methode entsteht aktuell ein Laufzeitfehler (NullPointerException), da die Methode `createExampleList` null liefert. Realisieren Sie die Methode `createExampleList`:

- Eine neue Instanz `java.util.ArrayList` erstellen
- Für jeden Namen aus dem Array `namen` ein Besitzer-Objekt erstellen und als Auto das entsprechende Element aus dem Array `autos` dem Besitzer zuordnen. Erlaubte Annahme: beide Arrays haben gültige Elemente und dieselbe Länge.
- Jedes so erstellte Besitzer-Element in der ArrayList speichern.
- Die Liste mit den Besitzer-Elementen zurück liefern.

**Lösung**  

```java
static List<Besitzer> createExampleList(String[] namen, Auto[] autos) {
    List<Besitzer> liste = new ArrayList<>();

    for (int i = 0; i < namen.length; i++) {
        Besitzer besitzer = new Besitzer(namen[i], autos[i]);
        liste.add(besitzer);
    }

    return liste;
}
```

## A2 – Suchen

Implementieren Sie die Methode `int indexOfBesitzer(List<Besitzer> listBesitzer, String name)` gemäß den folgenden Anforderungen:

- Die Methode durchsucht die übergebene Liste vom Anfang bis zum Ende nach einem Besitzer, dessen Name mit dem übergebenen Namen übereinstimmt.
- Beim Vergleich der Namen muss die Groß- und Kleinschreibung berücksichtigt werden.
- Wird ein passender Besitzer gefunden, gibt die Methode den Index des ersten passenden Listenelements zurück.
- Wird kein passender Besitzer gefunden, gibt die Methode `-1` zurück.

**Lösung**  

```java
static int indexOfBesitzer(List<Besitzer> listBesitzer, String name) {
    for (int i = 0; i < listBesitzer.size(); i++) {
        Besitzer besitzer = listBesitzer.get(i);

        if (besitzer.name.equals(name)) {
            return i;
        }
    }

    return -1;
}
```


## A3 – Testfälle entwickeln

Die in A2 implementierte Methode `indexOfBesitzer()` soll auf ihre korrekte Funktion überprüft werden.

Entwickeln Sie **fünf unterschiedliche Testfälle**, mit denen Sie das Verhalten der Methode überprüfen können.

Berücksichtigen Sie dabei sowohl erfolgreiche als auch erfolglose Suchvorgänge sowie unterschiedliche Positionen innerhalb der Liste.

Verwenden Sie die in A1 erstellte Liste als Grundlage.

Geben Sie für jeden Testfall den übergebenen Namen, das erwartete Ergebnis und den Zweck des Tests an.

| Testfall | Übergebener Name | Erwarteter Rückgabewert | Zweck des Tests |
|---|---|---|---|
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| 5 | | | |

**Lösung**  

| Testfall | Übergebener Name | Erwarteter Rückgabewert | Zweck des Tests |
|---|---|---|---|
| 1 | "Anna" | 0 | Treffer am Listenanfang |
| 2 | "Peter" | 2 | Treffer innerhalb der Liste |
| 3 | "Mary" | 4 | Treffer am Listenende |
| 4 | "Max" | -1 | Gesuchter Name nicht vorhanden |
| 5 | "anna" | -1 | Groß- und Kleinschreibung beachten |


## A4 - filtern

Realisieren Sie die Methode `List<Besitzer> filtereBesitzerNachAutoHersteller(List<Besitzer> listBesitzer, String hersteller)`.

Die Methode soll die übergebene Liste durchsuchen, und alle Besitzer in einer neuen Liste sammeln, die ein Auto von dem übergebenen Hersteller haben. Auch diese Methode soll beim Vergleich der Strings die Groß- und Kleinschreibung berücksichtigen.

**Lösung**  

```java
static List<Besitzer> filtereBesitzerNachAutoHersteller(
        List<Besitzer> listBesitzer, String hersteller) {

    List<Besitzer> ergebnis = new ArrayList<>();

    for (Besitzer besitzer : listBesitzer) {
        if (besitzer.auto.hersteller.equals(hersteller)) {
            ergebnis.add(besitzer);
        }
    }

    return ergebnis;
}
```

## A5 – Eindeutige Hersteller ermitteln

Implementieren Sie die Methode `List<String> ermittleHersteller(List<Besitzer> listBesitzer)` gemäß den folgenden Anforderungen:

- Die Methode durchläuft die übergebene Liste aller Besitzer und ermittelt die Hersteller ihrer Fahrzeuge.
- Jeder Hersteller darf in der Ergebnisliste nur einmal vorkommen.
- Beim Vergleich der Herstellernamen muss die Groß- und Kleinschreibung berücksichtigt werden.
- Die Reihenfolge der Hersteller in der Ergebnisliste muss ihrem erstmaligen Auftreten in der übergebenen Liste entsprechen.
- Die Methode gibt eine neue Liste mit den ermittelten Herstellern zurück.
- Falls die übergebene Liste leer ist, wird eine leere Liste zurückgegeben.

**Beispiel:**

Für die in A1 erstellte Liste ergibt sich beim Aufruf

```java
List<String> hersteller = ermittleHersteller(list);
```

folgender Listeninhalt:

```text
[VW, Audi, Opel]
```

**Lösung**

```java
static List<String> ermittleHersteller(List<Besitzer> listBesitzer) {
    List<String> herstellerListe = new ArrayList<>();

    for (Besitzer besitzer : listBesitzer) {
        String hersteller = besitzer.auto.hersteller;

        if (!herstellerListe.contains(hersteller)) {
            herstellerListe.add(hersteller);
        }
    }

    return herstellerListe;
}
```

**Hinweis zur Verwendung von `contains()`:**

Die Methode `contains()` prüft mithilfe von `equals()`, ob ein Element bereits in der Liste vorhanden ist.

Bei `List<String>` funktioniert der inhaltliche Vergleich, weil die Klasse `String` die Methode `equals()` entsprechend implementiert hat. Zwei unterschiedliche `String`-Objekte mit demselben Zeichenketteninhalt gelten daher als gleich.

Bei `List<Besitzer>` oder `List<Auto>` ist das anders: Da unsere Klassen `Besitzer` und `Auto` die Methode `equals()` nicht überschreiben, wird die Implementierung aus `Object` verwendet. Diese prüft die **Referenzidentität**, also ob beide Referenzen auf dasselbe Objekt verweisen.

Sollen beispielsweise zwei unterschiedliche `Auto`-Objekte mit gleichem Hersteller und Baujahr als gleich gelten, muss `equals()` in der Klasse `Auto` entsprechend überschrieben werden. Dabei sollte auch `hashCode()` konsistent implementiert werden.

**Wichtig:** `contains()` funktioniert auch bei Listen mit `Besitzer`- oder `Auto`-Objekten. Ohne eine eigene `equals()`-Implementierung erkennt die Methode jedoch nur dieselbe Objektinstanz wieder, nicht unterschiedliche Objekte mit gleichen Attributwerten.


## A6 – Herstellerstatistik initialisieren

Für die Auswertung der Fahrzeugdaten steht zusätzlich die folgende Klasse `HerstellerStatistik` zur Verfügung:

```java
class HerstellerStatistik {
    String hersteller;
    int anzahl;
    double durchschnittBaujahr;

    HerstellerStatistik(String hersteller) {
        this.hersteller = hersteller;
        this.anzahl = 0;
        this.durchschnittBaujahr = 0.0;
    }
}
```

Implementieren Sie die Methode `List<HerstellerStatistik> erstelleHerstellerStatistik(List<Besitzer> listBesitzer)` gemäß den folgenden Anforderungen:

- Die Methode durchläuft die übergebene Liste aller Besitzer und ermittelt die unterschiedlichen Fahrzeughersteller.
- Für jeden unterschiedlichen Hersteller wird genau ein neues `HerstellerStatistik`-Objekt mithilfe des vorgegebenen Konstruktors erstellt.
- Die erstellten Objekte werden in einer neuen `ArrayList<HerstellerStatistik>` gespeichert.
- Die Reihenfolge der Hersteller in der Ergebnisliste entspricht ihrem erstmaligen Auftreten in der übergebenen Besitzerliste.
- Beim Vergleich der Herstellernamen muss die Groß- und Kleinschreibung berücksichtigt werden.
- Die Attribute `anzahl` und `durchschnittBaujahr` bleiben zunächst bei ihren Initialwerten. Sie werden erst in einer späteren Teilaufgabe berechnet.
- Die Methode gibt die Ergebnisliste zurück. Ist die übergebene Besitzerliste leer, wird eine leere Liste zurückgegeben.

**Erwartetes Ergebnis:**

Für die in A1 erstellte Besitzerliste enthält die zurückgegebene Liste folgende Objekte:

| Index | hersteller | anzahl | durchschnittBaujahr |
|---:|---|---:|---:|
| 0 | `"VW"` | 0 | 0.0 |
| 1 | `"Audi"` | 0 | 0.0 |
| 2 | `"Opel"` | 0 | 0.0 |

**Hinweis:** Die Methode aus A5 kann als Orientierung dienen. Beachten Sie jedoch, dass die Ergebnisliste diesmal keine `String`-Elemente, sondern `HerstellerStatistik`-Objekte enthält.

**Lösung**  

```java
static List<HerstellerStatistik> erstelleHerstellerStatistik(
        List<Besitzer> listBesitzer) {

    List<HerstellerStatistik> statistikListe = new ArrayList<>();

    for (Besitzer besitzer : listBesitzer) {
        String hersteller = besitzer.auto.hersteller;
        boolean vorhanden = false;

        // Prüfen, ob der Hersteller bereits vorhanden ist
        for (HerstellerStatistik statistik : statistikListe) {
            if (statistik.hersteller.equals(hersteller)) {
                vorhanden = true;
                break;
            }
        }

        // Nur für neue Hersteller ein Statistikobjekt erstellen
        if (!vorhanden) {
            statistikListe.add(new HerstellerStatistik(hersteller));
        }
    }

    return statistikListe;
}
```

## A7 – Herstellerstatistik berechnen

In A6 wurde die Methode `erstelleHerstellerStatistik()` implementiert, die für jeden unterschiedlichen Fahrzeughersteller ein `HerstellerStatistik`-Objekt erzeugt. Die Attribute `anzahl` und `durchschnittBaujahr` wurden dabei zunächst mit `0` beziehungsweise `0.0` initialisiert.

Implementieren Sie nun die Methode `List<HerstellerStatistik> berechneStatistik(List<Besitzer> listBesitzer)` gemäß den folgenden Anforderungen:

- Die Methode ruft zunächst `erstelleHerstellerStatistik()` aus A6 auf, um eine Liste mit den unterschiedlichen Herstellern zu erzeugen.
- Für jeden Hersteller in der Ergebnisliste werden alle zugehörigen Fahrzeuge aus der übergebenen Besitzerliste ermittelt.
- Die Anzahl dieser Fahrzeuge wird berechnet und im Attribut `anzahl` des jeweiligen `HerstellerStatistik`-Objekts gespeichert.
- Für jeden Hersteller wird außerdem das arithmetische Mittel der Baujahre seiner Fahrzeuge berechnet und im Attribut `durchschnittBaujahr` gespeichert.
- Beim Vergleich der Herstellernamen muss die Groß- und Kleinschreibung berücksichtigt werden.
- Die bereits erstellten `HerstellerStatistik`-Objekte sollen aktualisiert werden. Es dürfen keine zusätzlichen `HerstellerStatistik`-Objekte erzeugt werden.
- Die Methode gibt die vollständig berechnete Statistikliste zurück. Ist die übergebene Besitzerliste leer, wird eine leere Liste zurückgegeben.

**Erwartetes Ergebnis:**

Für die in A1 erstellte Besitzerliste ergibt sich folgende Statistik:

| Index | hersteller | anzahl | durchschnittBaujahr |
|---:|---|---:|---:|
| 0 | `"VW"` | 3 | 2015.0 |
| 1 | `"Audi"` | 1 | 2011.0 |
| 2 | `"Opel"` | 1 | 2021.0 |

**Hinweis:** Das durchschnittliche Baujahr wird berechnet, indem die Summe der Baujahre aller Fahrzeuge eines Herstellers durch deren Anzahl geteilt wird. Das Ergebnis soll als `double` gespeichert und nicht auf eine ganze Zahl gerundet werden.

**Lösung**  

```java
static List<HerstellerStatistik> berechneStatistik(
        List<Besitzer> listBesitzer) {

    // Statistikliste mit eindeutigen Herstellern erstellen
    List<HerstellerStatistik> statistikListe =
            erstelleHerstellerStatistik(listBesitzer);

    // Für jeden Hersteller die Statistik berechnen
    for (HerstellerStatistik statistik : statistikListe) {
        int summeBaujahre = 0;
        int anzahl = 0;

        // Alle Besitzer auf den aktuellen Hersteller prüfen
        for (Besitzer besitzer : listBesitzer) {
            if (besitzer.auto.hersteller.equals(statistik.hersteller)) {
                summeBaujahre += besitzer.auto.baujahr;
                anzahl++;
            }
        }

        // Vorhandenes Statistikobjekt aktualisieren
        statistik.anzahl = anzahl;
        statistik.durchschnittBaujahr =
                (double) summeBaujahre / anzahl;
    }

    return statistikListe;
}
```
