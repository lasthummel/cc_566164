package polymorphismus;

class CharSupplier {
    char next() { return 'a'; }
    boolean hasNext() { return true; }
}


class CharPrinter {
    void print(CharSupplier supplier) {

        while (supplier.hasNext()) {
            char ch = supplier.next();
            System.out.println(ch);
        }    
    }
}

public class TestPolymorphismus {


    public static void main(String[] args) {
        
        CharSupplier s1 = new CharSupplier();
        new CharPrinter().print(s1);

    }
}
