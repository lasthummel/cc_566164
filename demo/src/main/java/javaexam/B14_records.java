package javaexam;

import java.util.ArrayList;
import java.util.List;


public class B14_records {

    static class Tier {
        String name;
        int alter;

        Tier(String name, int alter) {
            this.name = name;
            this.alter = alter;
        }
    }

    // für die record-Klasse generiert der Compiler:
    // - den Konstruktor
    // - die sinnvollen equals und hashCode Methode
    // - Zugriffsmethoden
    record Animal (String name, int age) {

    }


    public static void main(String[] args) {

        List<Tier> listTiere = createListTiere();

        Tier tom = new Tier("Tom", 3);
        boolean result = listTiere.contains(tom); // Nicht gefunden, da die Klasse Tier die equals-Methode nicht überschreibt

        System.out.println("Tier Tom gefunden? result: " + result); // false
        
        List<Animal> listAnimals = createListAnimals();
        Animal jerry = new Animal("Jerry", 5);
        
        result = listAnimals.contains(jerry); // gefunden, da record-Klasse die sinnvolle equals automatisch bekommt
        
        System.out.println("Animal Jerry gefunden? result: " + result); // true
    }

    static List<Tier> createListTiere() {
        List<Tier> list = new ArrayList<>();
        list.add(new Tier("Tom", 3));
        list.add(new Tier("Jerry", 5));
        return list;
    }

    static List<Animal> createListAnimals() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("Tom", 3));
        list.add(new Animal("Jerry", 5));
        return list;
    }
}
