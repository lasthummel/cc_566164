package javaexam;

class Planet {

    // x, y und name sind Instanzvariablen (oder Objektvariablen oder Objektattribute oder Objektfelder)
    // Alle Attribute werden automatisch vorinitialisiert (wenn Sie es nicht explizit programmieren)
    int x, y;
    String name;

    // ausgeben und bewegen sind Instanzmethoden (oder Objektmethoden)
    void ausgeben() {
        System.out.println(name + " [" + x + ", " + y + "]");
    }

    void bewegen(int newX, int newY) {
        x = newX;
        y = newY;
    }
}

public class B06_KlasseAlsTyp {

    public static void main(String[] args) {
        
        Planet p1 = new Planet();

        System.out.println("p1.x: " + p1.x); // 0
        System.out.println("p1.y: " + p1.y);
        System.out.println("p1.name: " + p1.name); // null

        p1.x = 12;
        p1.y = 33;
        p1.name = "Merkur";

        p1.ausgeben();
        p1.bewegen(77, -5);
        p1.ausgeben();


        Planet p2 = new Planet();
        p2.ausgeben();

    }
}
