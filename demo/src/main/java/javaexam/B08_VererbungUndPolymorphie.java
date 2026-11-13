package javaexam;

abstract class Figur {
    abstract double getFlaeche();
}

// Rechteck IS-A Figur
class Rechteck extends Figur {
    private int breite, hoehe;

    public Rechteck(int breite, int hoehe) {
         this.breite = breite;
        this.hoehe = hoehe;
    }

    double getFlaeche() { return breite * hoehe; }
    
    public String toString() { return "Rechteck " + breite + " X " + hoehe; }

} // end of Rechteck

// Kreis IS-A Figur
class Kreis extends Figur {
    private int radius;

    public Kreis(int radius) {
        this.radius = radius;
    }

    double getFlaeche() { return Math.PI * radius * radius; }
    
    public String toString() { return "Kreis R=" + radius; }
}

public class B08_VererbungUndPolymorphie {

    public static void main(String[] args) {
        
        Figur[] figuren = {
            new Rechteck(2, 3),
            new Kreis(2),
            new Rechteck(4, 5),
            new Kreis(3),
            new Rechteck(5, 2),
        };

        double flaeche = berechneGesamtflaeche(figuren);
        System.out.println("Gesamtfläche: " + flaeche);

    } // end of main

    /*
        Universelle Komponente (Funktion) berechneGesamtflaeche:

            - unterstützt beliebige Figuren
            - verwendet als Schnittstelle den Typ 'Figur'
            - ruft die Instanzmethode 'getFlaeche' polymorphisch auf
            
            - kann unabhängig von anderen Komponenten der Anwendung entwickelt werden

            Polymorphie:
                - Fugur ist der Basistyp und führt die Methode 'getFlaeche' ein.
                - Kinder-Klassen realisieren die 'getFlaeche' sinnvoll selbst.
                - Die Funktion 'berechneGesamtflaeche' ruft die 'getFlaeche' auf und
                  abhängig von dem Objekttyp, mit dem 'getFlaeche' aufgerufen wird,
                  wird die korrekte 'getFlaeche' seiner Klasse dynamisch ermittelt und gestartet.
    */
    static double berechneGesamtflaeche(Figur[] figuren) {
        double summe = 0;

        for (Figur f : figuren) {
            double flaeche = f.getFlaeche(); // polymorphischer Aufruf

            summe += flaeche;
        }

        return summe;
    }
}
