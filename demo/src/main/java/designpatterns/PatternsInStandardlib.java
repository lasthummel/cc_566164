package designpatterns;

import java.text.DateFormat;
import java.util.Locale;

public class PatternsInStandardlib {

    public static void main(String[] args) {
        bsp01();
        bsp02();
    }

    /*
       Die Klasse Locale.Builder wurde mit dem Design Pattern 'Builder' 

       Das komplizierte Locale-Objekt kann mit einem Builder vorbereitet werden.
       Dann wird die build-Methode die vorbereiteten Elemente überprüfen (Sprache, Land),
       und das neue Locale-Obejkt erstellen
     */
    static void bsp01() {

        Locale loc = new Locale.Builder()
                        .setLanguage("fr")
                        .setRegion("FR")
                        .build();
    }

    /*
        Die 'getDateInstance' ist ein Beispiel für 'Factory Method'
    */
    static void bsp02() {

        DateFormat fmt = DateFormat.getDateInstance(DateFormat.MEDIUM);

        
    }
}


