# Aufgabe – Integration eines älteren Temperatursensors

Ein Unternehmen entwickelt eine Anwendung zur Überwachung der Temperatur in Serverräumen.

Die Klasse `TemperaturAnzeige` verwendet bereits die Schnittstelle `TemperaturSensor`. Diese definiert die Methode `getTemperaturCelsius() : double`, die die aktuelle Temperatur in Grad Celsius liefert.

Ein älterer Temperatursensor soll ebenfalls in die Anwendung eingebunden werden. Für diesen Sensor stellt der Hersteller eine Bibliothek mit der Klasse `LegacySensor` bereit. Diese Klasse besitzt die Methode `readFahrenheit() : double`, die die aktuelle Temperatur in Grad Fahrenheit liefert.

Die Klasse `LegacySensor` implementiert die Schnittstelle `TemperaturSensor` nicht. Der Quellcode der Bibliothek steht nicht zur Verfügung und kann nicht verändert werden.

Die Anwendung soll den älteren Sensor über die bereits vorhandene Schnittstelle verwenden können. Die Klasse `TemperaturAnzeige` und die Schnittstelle `TemperaturSensor` sollen unverändert bleiben. Dazu darf eine neue Klasse entwickelt werden.

Für die Umrechnung gilt:

    Celsius = (Fahrenheit - 32) * 5.0 / 9.0

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

**Lösung.**

Adapter-Pattern (Adaptermuster), ein Strukturmuster.

Es macht eine vorhandene, inkompatible Schnittstelle für einen Client nutzbar, indem es sie an die vom Client erwartete Schnittstelle anpasst.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen der TemperaturAnzeige, der Schnittstelle TemperaturSensor, der neuen Klasse und des LegacySensor ein. Erläutern Sie den Ablauf einer Temperaturabfrage am Beispiel eines Messwertes von 77 Grad Fahrenheit.

**Lösung.**

- **TemperaturAnzeige (Client):** Verwendet ausschließlich die Schnittstelle TemperaturSensor für die Temperaturabfrage.
- **TemperaturSensor (Target):** Definiert die vom Client erwartete Methode `getTemperaturCelsius() : double`.
- **LegacySensor (Adaptee):** Ist die vorhandene Klasse mit der inkompatiblen Methode `readFahrenheit() : double`.
- **LegacySensorAdapter (Adapter):** Implementiert TemperaturSensor und besitzt eine Referenz auf ein LegacySensor-Objekt. Er delegiert die Messwertabfrage an dieses Objekt und rechnet das Ergebnis um.

Ein LegacySensor-Objekt wird dem Adapter bei seiner Erzeugung übergeben. Die TemperaturAnzeige erhält statt des Sensors direkt eine Referenz auf den Adapter vom Typ TemperaturSensor.

Die Temperaturabfrage läuft wie folgt ab:

1. Die TemperaturAnzeige ruft `getTemperaturCelsius()` auf.
2. Der LegacySensorAdapter ruft intern `readFahrenheit()` am LegacySensor auf.
3. Der LegacySensor liefert den Messwert 77 zurück.
4. Der Adapter rechnet den Wert um: (77 - 32) * 5.0 / 9.0 = 25.0.
5. Der Adapter gibt 25.0 an die TemperaturAnzeige zurück.

Die TemperaturAnzeige arbeitet wie bisher mit Grad Celsius und muss weder die Methode der älteren Bibliothek noch die Umrechnungsformel kennen.

Der Adapter könnte in Java beispielsweise so implementiert werden (die übrigen Typen sind bereits vorhanden):

```java
class LegacySensorAdapter implements TemperaturSensor {
    private final LegacySensor sensor;

    public LegacySensorAdapter(LegacySensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public double getTemperaturCelsius() {
        return (sensor.readFahrenheit() - 32) * 5.0 / 9.0;
    }
}
```

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Wiederverwendbarkeit oder Erweiterbarkeit der Anwendung.

**Lösung.**

Die vorhandene Sensorbibliothek kann unverändert wiederverwendet werden, obwohl ihre Schnittstelle nicht zur Anwendung passt.

Zukünftig kann ein weiterer Sensor mit einer anderen Schnittstelle durch einen zusätzlichen Adapter eingebunden werden. Solange dieser die Schnittstelle TemperaturSensor implementiert und Celsiuswerte liefert, muss die Klasse TemperaturAnzeige nicht geändert werden. Lediglich die Bereitstellung des passenden Sensorobjekts muss gegebenenfalls angepasst werden.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie die vorhandenen Klassen, die Schnittstelle, die neue Klasse, die wesentlichen Methoden sowie die Beziehungen zwischen ihnen dar.


**Lösung in der Datei `adapter.uxf`.**

Die wesentlichen Beziehungen sind:

- TemperaturAnzeige kennt TemperaturSensor (gerichtete Assoziation).
- LegacySensorAdapter implementiert TemperaturSensor (Realisierung: gestrichelte Linie mit leerer Dreieckspitze zur Schnittstelle).
- LegacySensorAdapter kennt LegacySensor (gerichtete Assoziation) und verwendet dessen Methode.

Das Diagramm zeigt einen Objektadapter: Er verwendet ein vorhandenes Objekt durch Delegation, statt von der Klasse LegacySensor zu erben. Eine Komposition im engeren UML-Sinn mit exklusiver Lebensdauerbindung ist nicht erforderlich.

**Abgrenzung zur Facade:** Der Adapter passt eine inkompatible Schnittstelle an eine bereits erwartete Schnittstelle an. Eine Facade bietet dagegen einen vereinfachten Zugang zu einem Subsystem.
