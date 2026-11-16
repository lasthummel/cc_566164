package algorithmen;

public class ErweiterteEntwickeln {

    public static void main(String[] args) {
        
        System.out.println("*** Test normalisiereKennung");
        String kennung = "a-b c?d e-f äüö";
        System.out.println("kennung: " + kennung);
        String normalisierteKennung = normalisiereKennung(kennung);
        System.out.println("normalisiert: " + normalisierteKennung);

    }

    static String normalisiereKennung(String kennung) {

        String ergebnis = "";

        for (int i=0; i<kennung.length(); i++) {
            char ch = kennung.charAt(i);
            
            if (ch != ' ') {
                
                if (ch == '-') {
                    ch = '_';
                } else {
                    ch = Character.toUpperCase(ch);
                }
                
                ergebnis += ch;
            }

        }

        return ergebnis;
    }
}
