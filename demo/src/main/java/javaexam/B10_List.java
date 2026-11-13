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

        System.out.println("1. list: " + list);

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
        // add(index, element)  - an der Position index hinzufügen
        // remove(index)
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
