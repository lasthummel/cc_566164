package javaexam;

public class B02_Kontrollstrukturen {

    public static void main(String[] args) {
        
        test_if_else_if_else();
        test_for();
        test_while();
        test_do_while();
        test_foreach();
        test_switch();

    } // end of main

    static void test_switch() {
        System.out.println("\n*** switch");

        int tag = 1;

        switch (tag) {
            case 1:
                System.out.println("1");        
                break;
        }

        switch (tag) {
            case 1:
                System.out.println("2.1");        
                break;
            case 2:
                System.out.println("2.2");        
                break;
            case 3:
                System.out.println("2.3");        
                break;
        }

        switch (tag) {
            case 2:
                System.out.println("3.1");        
                break;
            case 3:
                System.out.println("3.2");        
                break;
            default:
                System.out.println("3.3"); // Ausgabe
        }
    }

    static void test_foreach() {
        System.out.println("\n*** foreach (erweiterte for)");

        /*
            foreach ist in der Prüfung nur für Arrays und Listen (oder andenren Collections)
         */

        String[] arr = { "mo", "di", "mi" };

        for (String v : arr) {
            System.out.println("v = " + v);
        }
    }

    static void test_do_while() {
        System.out.println("\n*** do-while-Schleife");
        /*
            i<5             - Ausführungskriterium (bei false Schleife beenden)
        */
       int i = 3;
       do {
          System.out.println("i = " + i);
          i++;
       } while(i<5);
    }

    static void test_while() {
        System.out.println("\n*** while-Schleife");
        /*
            i<5             - Ausführungskriterium (bei false Schleife beenden)
        */

        int i = 3;
        while(i<5) {
            System.out.println("i = " + i);
            i++;
        }
    }

    static void test_for() {
        System.out.println("\n*** for-Schleife");

        /*
            int i = 0       - Initialisierung
            i<5             - Ausführungskriterium (bei false Schleife beenden)
            i++             - Inkrementieren
        */
        for(int i = 0; i<5; i++) {
            System.out.println("i = " + i);
        }

        // System.out.println(i); // i ist nicht mehr gültig
    }

    static void test_if_else_if_else() {
        System.out.println("*** if, else if, else");
        int tag = 1;

        if (tag==1) {
            System.out.println("1");
        }

        // keine Ausgabe damit:
        if (tag==1111) {
            System.out.println("2");
        }

        if (tag==1) {
            System.out.println("3.1");
        } else if (tag==2) {
            System.out.println("3.2");
        } else if (tag==3) {
            System.out.println("3.3");
        }

        if(tag==2) {
            System.out.println("4.1");
        } else if(tag==3) {
            System.out.println("4.2");
        } else {
            System.out.println("4.3"); // Ausgabe
        }
    }

}
