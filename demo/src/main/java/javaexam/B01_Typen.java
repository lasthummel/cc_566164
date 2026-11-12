package javaexam;

public class B01_Typen {

    public static void main(String[] args) {
        
        /*
            Primitive Typen: 

                byte, short, int, long
                float, double
                boolean
                char
        */

        int     x1; // 32-bit, ganze Zahlen
        double  x2; // 64-bit, Gleitkommazahlen
        boolean x3; // true oder false
        char    x4; // 16-bit, nichtnegativ

        char ch = 'a';

        /*
            Referenz-Typen (Klasen, Interfaces...)

            Eine Referenz ist wie ein Zeiger, der die Adresse eines Objektes speichern kann.
        */

        String s1; // kann ein String-Objekt referenzieren
        
        // KLassendefinition:
        class Person {}

        Person p1; // kann ein Person-Objekt referenzieren
        
    }

}
