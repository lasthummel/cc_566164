package algorithmen;

public class GrundlagenDev {

    public static void main(String[] args) {
        
        int[] dauer = { 77, -2, 40, 11, 0 };

        int ergebnis = zaehleLangeFaelle(dauer);

        System.out.println("Ergebnis: " + ergebnis);
    }

    static int zaehleLangeFaelle(int[] dauer) {
        int anzahl = 0;

        for (int i=0; i<dauer.length; i++) {
            if (dauer[i] > 30) {
                anzahl++;
            }
        }

        return anzahl;
    }
}
