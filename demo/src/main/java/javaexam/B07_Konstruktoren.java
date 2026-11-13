package javaexam;

class MyVector {
    private int x, y;

    // nicht prüfungsrelevant:
    // Eine Java Klasse kann die Methode toString() überschreiben,
    // damit das Ausgeben auf der Konsole bequem wird
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    /*
        Konstruktor:

            - Initialisiert das neue Objekt
            - Konstruktor ohne Parameter wird generiert, wenn es keine expliziten Konstruktoren gibt
            - Jeder Konstruktor initialisiert alle Attribute explizit oder implizit (dann generiert der Compiler die Initialisierung)
    */
    MyVector() {
    }

    MyVector(int x, int y) {
        this.x = x;
        this.y = y;
    }

} // end of MyVector 

public class B07_Konstruktoren {

    public static void main(String[] args) {
        
        MyVector v1 = new MyVector();
        System.out.println("v1: " + v1);

        MyVector v2 = new MyVector(-12, 33);
        System.out.println("v2: " + v2);

    }
}
