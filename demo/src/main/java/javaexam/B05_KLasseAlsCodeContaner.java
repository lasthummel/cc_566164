package javaexam;

// static void myFunction() {} // geht nicht: nur innerhalb einer Klasse möglich
// int x = 2; // geht nicht: nur innerhalb einer Klasse möglich

public class B05_KLasseAlsCodeContaner {

    /*
     * static sagt, dass es einfacher Algorithmus ist, der kein Objekt für den Aufruf benötigt.
     * 
     * static-Methoden werden außerhalb ihrer Klasse mit dem Klassennamen aufgerufen.
     * static-Methoden können innerhalb ihrer Klasse auch ohne den Klassennamen aufgerufen werden.
     * 
     */
    public static void sayHello() {
        System.err.println("****");
        System.out.println("Moin!");
        System.err.println("****");
    }

    /*
     * static sagt, dass es einfach eine Variable (oder Konstante) ist, 
     * die kein Objekt für den Zugriff benötigt.
     * 
     * static-Variablen (oder Konstanten) werden außerhalb ihrer Klasse mit dem Klassennamen erreicht.
     * static-Variablen (oder Konstanten) können innerhalb ihrer Klasse auch ohne den Klassennamen erreicht werden.
     * 
     */
    public static int minValue = 77;
    public static final int MAX_VALUE = 11111;

    public static void main(String[] args) {
        System.out.println("main läuft...");

        B05_KLasseAlsCodeContaner.sayHello();
        sayHello();

        System.out.println(B05_KLasseAlsCodeContaner.minValue); // 77
        System.out.println(MAX_VALUE);
    }
}
