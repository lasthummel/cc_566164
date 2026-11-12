package javaexam;

public class B03_Operatoren {

    public static void main(String[] args) {
        
        /*
            Arithmetische:

            +
            -
            *
            /
            %       (Modulo-Operator)
        */

        
        /*
            Vergleichsoperatoren nur für primitive Typen:
            
            <
            <=      (kleiner oder gleich)
            >
            >=  

            Vergleichsoperatoren sowohl für primitive als auch für Referenztypen

            ==
            !=

            Achten Sie auf das Beispiel für den Vergleich der Objekte, das später kommt.
        */


        /*
            Logische Operatoren

            UND-Operatoren:

                &
                &&   (short circuit)

            ODER-Operatoren:

                |
                ||  (short circuit)

            XOR-Operator:

                ^

            NOT-Operator:

                !
        */

        /*
            Unterschied zw. dem 'normalen' Operator und seiner 'short circuit' Version:

            Der 'short circuit' Operator spart sicht das Auswerten des rechten Operandes,
            wenn das Gesamtergebnis aus der Auswertung des linken Operandes erkannt wird.
        */

        int x = 0;

        boolean result1 = (x==222) & (x++ == 1000); // 'normaler' &-Operator wertet den rechten Operanden aus
        
        System.out.println("result1: " + result1); // false
        System.out.println("x = " + x); // 1
        
        int y = 0;
        boolean result2 = (y==222) && (y++ == 1000); // 'short circuit' &&-Operator
        //                                           // erkennt sein Ergebnis false aus der linken Seite
        
        System.out.println("result2: " + result2); // false
        System.out.println("y = " + y); // 0
    }
}
