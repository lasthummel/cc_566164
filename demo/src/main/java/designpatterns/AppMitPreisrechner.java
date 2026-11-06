package designpatterns;

import java.util.Scanner;

public class AppMitPreisrechner {

    public static void main(String[] args) {

        /* Es wird dynamisch ermittelt, ob Rabatt verwendet werden soll
         */
        boolean mitRabatt = benutzeRabatt();

        PreisRechner pRechner = new PreisRechner();
        double betrag = 150;

        
        System.out.println("Betrag: " + betrag);
        
        PreisStrategie strategie;
        
        if (mitRabatt) {
            System.out.println("Der Preis wird mit Rabatt berechnet");
            
            strategie = new RabattStrategie();
            
        } else {
            System.out.println("Der Preis wird ohne Rabatt berechnet");
            
            strategie = new NormalpreisStrategie();
        }
        
        pRechner.setStrategie(strategie);
        // pRechner.setStrategie( b -> b*2 );
        
        double preis = pRechner.berechnePreis(betrag);
        
        System.out.println("Preis: " + preis);
        
    } // end of main

    /**
     * 
     * @return true, wenn Rabatt benutzt werden soll, ansonsten false
     */
    static boolean benutzeRabatt() {
        System.out.println("Mit Rabatt (j/n)?");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        char firstChar = s.charAt(0);

        boolean result = firstChar == 'j';

        return result;
    }

}
