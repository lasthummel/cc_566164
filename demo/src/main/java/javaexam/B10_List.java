package javaexam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class B10_List {

    public static void main(String[] args) {
        bsp_erzeugen();
        bsp_methoden();
    }

    static void bsp_methoden() {
        System.out.println("\n*** Wichtige Methoden");

        List<String> list = new ArrayList<>();

        //  add(element) - speichern am Ende
        list.add("mo");
        list.add("di");
        list.add("mi");

        System.out.println("1. list: " + list); // 1. list: [mo, di, mi]
        
        // contains - überprüfen ob ein Element vorhanden ist
        // Achtung! Elemente brauchen sinnvole equals!!!
        
        boolean result = list.contains("Freitag");
        System.out.println("Freitag gefunden: " + result); // false
        
        result = list.contains("mo");
        System.out.println("mo gefunden: " + result); // true
        
        // get(index)
        String s = list.get(1);
        System.out.println("get(1): " + s); // di
        
        // size() - aktuelle Größe
        System.out.println("size: " + list.size()); // 3
        
        // set(index) - ersetzen
        list.set(1, "Dienstag");
        System.out.println("2. list: " + list); // 2. list: [mo, Dienstag, mi]
        
        // add(index, element)  - an der Position index hinzufügen
        list.add(2, "dazwischen");
        System.out.println("3. list: " + list); // 3. list: [mo, Dienstag, dazwischen, mi]
        
        // remove(index)
        list.remove(1);
        System.out.println("4. list: " + list); // 4. list: [mo, dazwischen, mi]

        // indexOf - liefert die Position oder -1
        // Achtung! Elemente brauchen sinnvole equals!!!
        int index = list.indexOf("mi");
        System.out.println("indexOf mi: " + index); // 2
        
        index = list.indexOf("Freitag");
        System.out.println("indexOf Freitag: " + index); // -1

    }

    static void bsp_erzeugen() {
        System.out.println("*** Listen erzeugen");

        List<String> listStr = new ArrayList<>();

        listStr.add("mo");
        listStr.add("di");
        listStr.add("mi");

        // unwahrscheinlich in der Prüfung: andere konkrete Klasse
        List<Integer> listInt = new LinkedList<>();

        // unwahrscheinlich in der Prüfung: Liste mit einer Fabrikmethode
        List<Integer> listInt2 = List.of(12, 13, 14);

        // unwahrscheinlich in der Prüfung: kopieren einer anderen List mit einem Konstruktor
        List<String> listStr2 = new ArrayList<>( listStr );

        System.out.println("listStr2: " + listStr2); // [mo, di, mi]

    }
}
