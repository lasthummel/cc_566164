package algorithmen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrundlagenDev {

    public static void main(String[] args) {
        
        System.out.println("*** A1 - Zählen von Elementen unter einer Bedingung");

        int[] dauer = { 77, -2, 40, 11, 0 };

        int ergebnis = zaehleLangeFaelle(dauer);

        System.out.println("Ergebnis: " + ergebnis);

        System.out.println("\n*** A2 - Min und Max");

        int[] minMax = findeMinMax(dauer);

        System.out.println("minMax: " + Arrays.toString(minMax));

        System.out.println("\n** A3 - Liste filtern");
        
        List<Integer> listIn = List.of(77, -2, 40, 1000, 0, 33);
        
        List<Integer> listOut = filtereWerte(listIn, 30);
        
        System.out.println("listOut: " + listOut);

        System.out.println("\n** A5 - Gruppieren und Häufigkeiten ermitteln");

        String[] string = { "mo", "di", "mo", "fr", "mo", "di" };

        Map<String, Integer> mapCounts = ermittleHaeufigkeiten(string);

        System.out.println("mapCounts: " + mapCounts);
    }
    /*
        A5
     */
    static Map<String, Integer> ermittleHaeufigkeiten(String[] strings) {
        Map<String, Integer> mapCounts = new HashMap<>();

        for (String s : strings) {
            
            if ( mapCounts.containsKey(s) ) {
                Integer count = mapCounts.get(s);
                mapCounts.put(s, count + 1);
            } else {
                mapCounts.put(s, 1);
            }
        }

        return  mapCounts;
    }

    /*
        A3
     */
    static List<Integer> filtereWerte(List<Integer> list, int grenze) {
        List<Integer> listResult = new ArrayList<>();

        for (int i=0; i<list.size(); i++) {
            if (list.get(i) >= grenze) {
                listResult.add(list.get(i));
            }
        }

        return  listResult;
    }


    /*
        A2

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
        A1

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
