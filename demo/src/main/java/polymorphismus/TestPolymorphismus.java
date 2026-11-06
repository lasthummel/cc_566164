package polymorphismus;

class CharSupplier {
    private int count;
    char next() {
        count++; 
        return 'a'; 
    }
    boolean hasNext() { return count < 10; }
}

class ABCSupplier extends CharSupplier {
    private char ch = 'A';
    boolean hasNext() { return ch < 'D'; }
    char next() { 
        char chCopy = ch;
        ch++;
        return chCopy;
    }
}

class CharPrinter {
    /*
        Polymorphismus:

            CharSupplier kann unterschiedliche Gestalten haben.
            Realisierungen der Methoden hasNext und next werden zur Laufzeit bestimmt,
            abhängig davon, Instanz welcher konkreten Klasse hinter der Referenz 'supplier' steckt. 
     */
    void print(CharSupplier supplier) {
        while (supplier.hasNext()) {      //  hasNext-Methode wird polymorphisch aufgerufen
            char ch = supplier.next();    //  next-Methode wird polymorphisch aufgerufen
            System.out.print(ch + " ");
        }
        System.out.println();
    }
}

public class TestPolymorphismus {
    public static void main(String[] args) {
        CharSupplier s1 = new CharSupplier();
        CharPrinter p = new CharPrinter();
        
        p.print(s1); // a a a a a a a a a a

        p.print(new ABCSupplier()); // A B C

    }
}
