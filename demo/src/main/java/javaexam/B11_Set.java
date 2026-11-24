package javaexam;

import java.util.HashSet;
import java.util.Set;

public class B11_Set {

    public static void main(String[] args) {
        
        // Set speichert einzigartige Elemente

        Set<Integer> set = new HashSet<>();

        set.add(12);
        set.add(12);
        set.add(12);
        set.add(12);

        System.out.println("1. set: " + set); // 1. set: [12]

        set.add(-33);
        set.add(5);
        set.add(-33);
        set.add(-1);
        set.add(-33);
        set.add(17);

        System.out.println("2. set: " + set); // 2. set: [12, -33, 5, -1, 17] (Reihenfolge nicht garantiert)

        // contains(element) : boolean  - überprüfen, ob ein Element existiert
        // remove(element)              - Element entfernen
        // size() : int                 - aktuelle Größe ermitteln

        // Iterieren nur mit foreach
        System.out.println("*** iterieren");
        for (Integer element : set) {
            System.out.println(element);
        }

    }
}
