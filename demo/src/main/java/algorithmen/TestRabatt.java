package algorithmen;

public class TestRabatt {
    static public int berechneRabatt(int alter, boolean mitglied) {
        int rabatt = 0;

        if (alter < 18) {
            rabatt = 10;
        }

        if (mitglied) {
            rabatt += 5;
        }

        return rabatt;
    }

    public static void main(String[] args) {
        
        int alter = Integer.MAX_VALUE + 1;  // int-Overflow!!!  alter ist negativ!

        int rabatt = berechneRabatt(alter, false);

        System.out.println("rabatt: " + rabatt); // ?
    }
}
