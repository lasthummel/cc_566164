package javaexam;

class Tier {
    String name;
    int alter;

    Tier(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }

    public String toString() {
        return "Tier [name=" + name + ", alter=" + alter + "]";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Tier t2) {
            return name.equals(t2.name) && alter == t2.alter;
        }

        return false;
    }    
}

public class B09_ObjekteVergleichen {
    public static void main(String[] args) {
        
        Tier t1 = new Tier("Tom", 3);
        Tier t2 = new Tier("Tom", 3);

        /*
           Bitte vergleichen Sie die Referenztypen nur mit der Methode 'equals(Object)'!!!

           Die Operatoren == und != vergleichen die Adressen, die in den Referenzen stehen und nicht
           die Objektinhalte.

           Bitte ÜBERSCHREIBEN Sie die Methode 'equals(Object)', wenn Objekte Ihrer Klasse
           vergliechen werden müssen.
        */

        boolean result = t1.equals(t2);

        System.out.println("result: " + result);
    }
}
