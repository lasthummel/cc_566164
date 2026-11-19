# Aufgabe – Integration eines älteren Temperatursensors

Ein Unternehmen entwickelt eine Anwendung zur Überwachung der Temperatur in Serverräumen.

Die Klasse `TemperaturAnzeige` verwendet bereits die Schnittstelle `TemperaturSensor`. Diese definiert die Methode `getTemperaturCelsius() : double`, die die aktuelle Temperatur in Grad Celsius liefert.

Ein älterer Temperatursensor soll ebenfalls in die Anwendung eingebunden werden. Für diesen Sensor stellt der Hersteller eine Bibliothek mit der Klasse `LegacySensor` bereit. Diese Klasse besitzt die Methode `readFahrenheit() : double`, die die aktuelle Temperatur in Grad Fahrenheit liefert.

Die Klasse `LegacySensor` implementiert die Schnittstelle `TemperaturSensor` nicht. Der Quellcode der Bibliothek steht nicht zur Verfügung und kann nicht verändert werden.

Die Anwendung soll den älteren Sensor über die bereits vorhandene Schnittstelle verwenden können. Die Klasse `TemperaturAnzeige` und die Schnittstelle `TemperaturSensor` sollen unverändert bleiben. Dazu darf eine neue Klasse entwickelt werden.

Für die Umrechnung gilt:

    Celsius = (Fahrenheit - 32) * 5.0 / 9.0

a) Nennen Sie ein geeignetes Entwurfsmuster (Design Pattern), mit dem diese Anforderungen umgesetzt werden können.

b) Beschreiben Sie die Funktionsweise des gewählten Entwurfsmusters anhand der beschriebenen Anwendung. Gehen Sie dabei auf die Rollen der TemperaturAnzeige, der Schnittstelle TemperaturSensor, der neuen Klasse und des LegacySensor ein. Erläutern Sie den Ablauf einer Temperaturabfrage am Beispiel eines Messwertes von 77 Grad Fahrenheit.

c) Erläutern Sie einen Vorteil des gewählten Entwurfsmusters hinsichtlich der Wiederverwendbarkeit oder Erweiterbarkeit der Anwendung.

d) Erstellen Sie ein UML-Klassendiagramm, das die Lösung skizziert. Stellen Sie die vorhandenen Klassen, die Schnittstelle, die neue Klasse, die wesentlichen Methoden sowie die Beziehungen zwischen ihnen dar.
