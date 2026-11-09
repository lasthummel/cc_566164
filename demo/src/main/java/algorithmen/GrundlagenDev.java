package algorithmen;

import java.util.Arrays;

public class GrundlagenDev {

    public static void main(String[] args) {
        
        System.out.println("*** A1 - Zählen von Elementen unter einer Bedingung");

        int[] dauer = { 77, -2, 40, 11, 0 };

        int ergebnis = zaehleLangeFaelle(dauer);

        System.out.println("Ergebnis: " + ergebnis);

        System.out.println("\n*** A2 - Min und Max");

        int[] minMax = findeMinMax(dauer);

        System.out.println("minMax: " + Arrays.toString(minMax));
    }

    /*
     Entwickeln Sie mit Pseudocode die Funktion `findeMinMax`, die als Parameter ein Array
      mit Ganzzahlen bekommt, den kleinesten und den größten Wert ermittelt und die ermittelten 
      Werte zurück liefert.
     
     */
    static int[] findeMinMax(int[] zahlen) {
        int min = zahlen[0];
        int max = zahlen[0];

        for (int i=1; i < zahlen.length; i++) {
            int x = zahlen[i];
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }

        return new int[] { min, max };
    }

    /*
    Entwickeln Sie mit Pseudocode die Funktion `zaehleLangeFaelle(dauer: Ganzzahl[]): Ganzzahl`.
    Die Funktion soll die Anzahl der Elemente des Array ermitteln, die den Wert > 30 haben. 
    Die Funktion liefert die Anzahl zurück.
    */
    static int zaehleLangeFaelle(int[] dauer) {
        int anzahl = 0;

        for (int i=0; i<dauer.length; i++) {
            if (dauer[i] > 30) {
                anzahl++;
            }
        }

        return anzahl;
    }
}
