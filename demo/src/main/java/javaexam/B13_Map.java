package javaexam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class B13_Map {
    public static void main(String[] args) {
        // Map: Container für Key-Value-Paare
        // Die Schlüssel sind einzigartig (keine doppelten können gespeichert werden)

        Map<Integer, String> map = new HashMap<>();

        // put fürs Hinzufügen...
        map.put(1, "mo");
        map.put(2, "di");
        map.put(3, "mi");
        
        System.out.println("1. map: " + map); // {1=mo, 2=di, 3=mi}
        
        // ...und auch put fürs Updaten
        map.put(2, "DIENSTAG");
        
        System.out.println("2. map: " + map); // {1=mo, 2=DIENSTAG, 3=mi}
        
        // get(key) : value     - mit dem Schlüssen nach dem Wert suchen (liefert null, wenn es keinen Schlüssel gibt)
        String value = map.get(22);
        System.out.println("mit key=22 wird nicht gefunden. value = " + value); // null
        
        value = map.get(1);
        System.out.println("mit key=1 wird gefunden: " + value); // mo
        
        // remove(key) zum Entfernen der Einträge
        map.remove(2);
        System.out.println("3. map: " + map); // {1=mo, 3=mi}

        // Iterieren geht nicht direkt, aber Sie können ein Set mit allen Schlüsseln holen und durch das Set iterieren:
        System.out.println("*** alles aus der Map:");
        Set<Integer> allKeys = map.keySet();
        for (Integer k : allKeys) {
            String v = map.get(k);
            System.out.println("key: " + k + ", value: " + v);
        }

        // ganz bestimmt nicht in der Prüfung: Methode 'merge' (s. die stringsStatisticsV2)
        stringsStatisticsV1();
        stringsStatisticsV2();

    } 

    static void stringsStatisticsV1() {
        System.out.println("\n*** Strings Statistics. V1");

        String[] array = { "mo", "fr", "di", "fr", "mo", "fr" };

        Map<String, Integer> map = new HashMap<>();

        for (String s : array) {

            Integer count = map.get(s);
            if (count == null) {
                map.put(s, 1);
            } else {
                map.put(s, count + 1);
            }

        }

        System.out.println(map);
    }

    static void stringsStatisticsV2() {
        System.out.println("\n*** Strings Statistics. V2");

        String[] array = { "mo", "fr", "di", "fr", "mo", "fr" };

        Map<String, Integer> map = new HashMap<>();

        for (String s : array) {

            // Integer count = map.get(s);
            // if (count == null) {
            //     map.put(s, 1);
            // } else {
            //     map.put(s, count + 1);
            // }
         
            // der Code aus der V1 kann mit einem merge-Aufruf ersetzt werden:
            map.merge(s, 1, Integer::sum);

            // Integer::sum ist eine Referenz auf eine Methode

            // Die Logik der merge:
            // Falls es den Schlüßel s noch nicht gibt, dann Schlüssel (Argument 1) und Wert (Argument 2) speichern,
            // ansonsten den neuen Wert mit der Remapping-Function (Argument 3) berechnen und den alten Wert ersetzten.
        }

        System.out.println(map);
    }
}
